package myapp.hoang.onboarding.signup.viewmodels

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import kotlinx.datetime.LocalDate
import myapp.hoang.core_ui.utils.UiEvent
import myapp.hoang.core_ui.utils.UiText
import myapp.hoang.onboarding.R
import myapp.hoang.onboarding.signup.repositories.ImageUploadRepository
import myapp.hoang.onboarding.signup.repositories.SignupRepository
import java.io.File
import javax.inject.Inject

@HiltViewModel
class OnBoardingViewModel @Inject constructor(
    private val signupRepository: SignupRepository,
    private val imageUploadRepository: ImageUploadRepository
) : ViewModel() {
    private val _signupForm = MutableStateFlow(SignupForm())
    val signupForm = _signupForm.asStateFlow()

    private val _profilePic = MutableStateFlow<ByteArray?>(null)
    val profilePic = _profilePic.asStateFlow()

    private val _isLoading = MutableStateFlow(false)
    val isLoading = _isLoading.asStateFlow()

    private val _uiEvent = Channel<UiEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    private var sendVerificationJob: Job? = null
    private var checkVerificationJob: Job? = null
    private var uploadProfilePicAndSignUpJob: Job? = null
    private var getProfilePicJob: Job? = null

    fun setMobileNumber(mobileNumber: Long) {
        _signupForm.update {
            it.copy(
                mobileNumber = mobileNumber
            )
        }
    }

    fun setIsMobileNumberVerified(isMobileNumberVerified: Boolean) {
        _signupForm.update {
            it.copy(
                isMobileNumberVerified = isMobileNumberVerified
            )
        }
    }

    fun setEmail(email: String) {
        _signupForm.update {
            it.copy(
                email = email
            )
        }
    }

    fun setIsEmailVerified(isEmailVerified: Boolean) {
        _signupForm.update {
            it.copy(
                isEmailVerified = isEmailVerified
            )
        }
    }

    fun setFullName(fullName: String) {
        _signupForm.update {
            it.copy(
                fullName = fullName
            )
        }
    }

    fun setBirthday(birthday: LocalDate) {
        _signupForm.update {
            it.copy(
                birthday = birthday
            )
        }
    }

    fun setUsername(username: String) {
        _signupForm.update {
            it.copy(
                username = username
            )
        }
    }

    fun setPassword(password: String) {
        _signupForm.update {
            it.copy(
                password = password
            )
        }
    }

    fun setAgreedToPolicy(agreedToPolicy: Boolean) {
        _signupForm.update {
            it.copy(
                agreedToPolicy = agreedToPolicy
            )
        }
    }

    fun setProfilePicPath(profilePicPath: String) {
        _signupForm.update {
            it.copy(
                profilePicPath = profilePicPath
            )
        }
    }

    fun sendConfirmationCode(recipient: String) {
        _isLoading.value = true
        sendVerificationJob?.cancel()
        sendVerificationJob = viewModelScope.launch {
            try {
                signupRepository.sendVerificationCode(recipient)
                _isLoading.value = false
                _uiEvent.send(UiEvent.NextScreen)
            } catch (e: Exception) {
                _isLoading.value = false
                _uiEvent.send(
                    UiEvent.ShowToast(UiText.StringResource(R.string.error_send_confirmation_code))
                )
            }
        }
    }

    fun checkConfirmationCode(recipient: String, confirmationCode: String) {
        _isLoading.value = true
        checkVerificationJob?.cancel()
        checkVerificationJob = viewModelScope.launch {
            try {
                signupRepository.checkVerificationCode(recipient, confirmationCode)
                _isLoading.value = false
                _uiEvent.send(UiEvent.HideErrorSupportingText)
                _uiEvent.send(UiEvent.NextScreen)
            } catch (e: Exception) {
                _isLoading.value = false
                _uiEvent.send(
                    UiEvent.ShowErrorSupportingText(
                        UiText.StringResource(R.string.error_check_confirmation_code)
                    )
                )
            }
        }
    }

    fun uploadProfilePicAndSignUp(imageFile: File) {
        _isLoading.value = true
        uploadProfilePicAndSignUpJob?.cancel()
        uploadProfilePicAndSignUpJob = viewModelScope.launch {
            try {
                val profilePicPath = imageUploadRepository.uploadProfilePic(imageFile)
                setProfilePicPath(profilePicPath)
                signupRepository.signUp(signupForm.value)
                _isLoading.value = false
                _uiEvent.send(UiEvent.NextScreen)
            } catch (e: Exception) {
                _isLoading.value = false
                Log.d("MYTAG", e.toString())
            }
        }
    }

    fun getProfilePic(profilePicPath: String) {
        _isLoading.value = true
        getProfilePicJob?.cancel()
        getProfilePicJob = viewModelScope.launch {
            try {
                _profilePic.value = imageUploadRepository.getProfilePic(profilePicPath)
            } catch (e: Exception) {
                _isLoading.value = false
            }
        }
    }
}