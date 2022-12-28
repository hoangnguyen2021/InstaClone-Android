package myapp.hoang.onboarding.signup.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import myapp.hoang.core_ui.utils.UiEvent
import myapp.hoang.core_ui.utils.UiText
import myapp.hoang.onboarding.R
import myapp.hoang.onboarding.signup.repositories.SignupRepository
import java.time.LocalDate
import javax.inject.Inject

@HiltViewModel
class OnBoardingViewModel @Inject constructor(
    private val signupRepository: SignupRepository
) : ViewModel() {
    private val _signupForm = MutableStateFlow(SignupForm())
    val signupForm = _signupForm.asStateFlow()

    private val _isLoading = MutableStateFlow(false)
    val isLoading = _isLoading.asStateFlow()

    private val _uiEvent = Channel<UiEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    private var sendVerificationJob: Job? = null
    private var checkVerificationJob: Job? = null

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

    fun setAgreedToPolicy(agreedToPolicy: Boolean) {
        _signupForm.update {
            it.copy(
                agreedToPolicy = agreedToPolicy
            )
        }
    }

    fun setProfilePicUrl(profilePicUrl: String) {
        _signupForm.update {
            it.copy(
                profilePicUrl = profilePicUrl
            )
        }
    }

    fun sendVerificationCode(mobileNumber: String) {
        _isLoading.value = true
        sendVerificationJob?.cancel()
        sendVerificationJob = viewModelScope.launch {
            try {
                //signupRepository.sendVerificationCode(mobileNumber)
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

    fun checkVerificationCode(mobileNumber: String, code: String) {
        _isLoading.value = true
        checkVerificationJob?.cancel()
        checkVerificationJob = viewModelScope.launch {
            try {
                //signupRepository.checkVerificationCode(mobileNumber, code)
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
}