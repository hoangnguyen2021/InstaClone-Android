package myapp.hoang.onboarding.signup.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import de.palm.composestateevents.consumed
import de.palm.composestateevents.triggered
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import kotlinx.datetime.LocalDate
import myapp.hoang.core_ui.utils.UiText
import myapp.hoang.onboarding.R
import myapp.hoang.onboarding.signup.repositories.ImageUploadRepository
import myapp.hoang.onboarding.signup.repositories.SignupRepository
import java.io.File
import javax.inject.Inject

@HiltViewModel
class SignupViewModel @Inject constructor(
    private val signupRepository: SignupRepository,
    private val imageUploadRepository: ImageUploadRepository
) : ViewModel() {
    private val _uiState = MutableStateFlow(SignupUiState())
    val uiState = _uiState.asStateFlow()

    private var state: SignupUiState
        get() = _uiState.value
        set(newState) {
            _uiState.update { newState }
        }

    private var sendVerificationJob: Job? = null
    private var checkVerificationJob: Job? = null
    private var uploadProfilePicAndSignupJob: Job? = null
    private var signupJob: Job? = null
    private var getProfilePicJob: Job? = null

    fun setMobileNumber(mobileNumber: Long) {
        state = state.copy(
            signupForm = state.signupForm.copy(
                mobileNumber = mobileNumber
            )
        )
    }

    fun setIsMobileNumberVerified(isMobileNumberVerified: Boolean) {
        state = state.copy(
            signupForm = state.signupForm.copy(
                isMobileNumberVerified = isMobileNumberVerified
            )
        )
    }

    fun setEmail(email: String) {
        state = state.copy(
            signupForm = state.signupForm.copy(
                email = email
            )
        )
    }

    fun setIsEmailVerified(isEmailVerified: Boolean) {
        state = state.copy(
            signupForm = state.signupForm.copy(
                isEmailVerified = isEmailVerified
            )
        )
    }

    fun setFullName(fullName: String) {
        state = state.copy(
            signupForm = state.signupForm.copy(
                fullName = fullName
            )
        )
    }

    fun setBirthday(birthday: LocalDate) {
        state = state.copy(
            signupForm = state.signupForm.copy(
                birthday = birthday
            )
        )
    }

    fun setUsername(username: String) {
        state = state.copy(
            signupForm = state.signupForm.copy(
                username = username
            )
        )
    }

    fun setPassword(password: String) {
        state = state.copy(
            signupForm = state.signupForm.copy(
                password = password
            )
        )
    }

    fun setAgreedToPolicy(agreedToPolicy: Boolean) {
        state = state.copy(
            signupForm = state.signupForm.copy(
                agreedToPolicy = agreedToPolicy
            )
        )
    }

    fun setProfilePicPath(profilePicPath: String) {
        state = state.copy(
            signupForm = state.signupForm.copy(
                profilePicPath = profilePicPath
            )
        )
    }

    fun sendConfirmationCode(recipient: String) {
        state = state.copy(
            isLoading = true
        )
        sendVerificationJob?.cancel()
        sendVerificationJob = viewModelScope.launch {
            state = try {
                signupRepository.sendVerificationCode(recipient)
                state.copy(
                    isLoading = false,
                    nextScreenEvent = triggered
                )
            } catch (e: Exception) {
                state.copy(
                    isLoading = false,
                    showToastEvent = triggered(UiText.StringResource(R.string.error_send_confirmation_code))
                )
            }
        }
    }

    fun checkConfirmationCode(recipient: String, confirmationCode: String) {
        state = state.copy(
            isLoading = true
        )
        checkVerificationJob?.cancel()
        checkVerificationJob = viewModelScope.launch {
            state = try {
                signupRepository.checkVerificationCode(recipient, confirmationCode)
                state.copy(
                    isLoading = false,
                    hideErrorSupportingTextEvent = triggered,
                    nextScreenEvent = triggered
                )
            } catch (e: Exception) {
                state.copy(
                    isLoading = false,
                    showErrorSupportingTextEvent =
                    triggered(UiText.StringResource(R.string.error_check_confirmation_code))
                )
            }
        }
    }

    fun uploadProfilePicAndSignUp(imageFile: File) {
        state = state.copy(
            isLoading = true
        )
        uploadProfilePicAndSignupJob?.cancel()
        uploadProfilePicAndSignupJob = viewModelScope.launch {
            state = try {
                val profilePicPath = imageUploadRepository.uploadProfilePic(imageFile)
                setProfilePicPath(profilePicPath)
                signupRepository.signUp(state.signupForm)
                state.copy(
                    isLoading = false,
                    nextScreenEvent = triggered
                )
            } catch (e: Exception) {
                state.copy(
                    isLoading = false,
                )
            }
        }
    }

    fun signUp() {
        state = state.copy(
            isLoading = true
        )
        signupJob?.cancel()
        signupJob = viewModelScope.launch {
            state = try {
                signupRepository.signUp(state.signupForm)
                state.copy(
                    isLoading = false,
                    nextScreenEvent = triggered
                )
            } catch (e: Exception) {
                state.copy(
                    isLoading = false,
                )
            }
        }
    }

    fun getProfilePic(profilePicPath: String) {
        state = state.copy(
            isLoading = true
        )
        getProfilePicJob?.cancel()
        getProfilePicJob = viewModelScope.launch {
            state = try {
                state.copy(
                    profilePic = imageUploadRepository.getProfilePic(profilePicPath)
                )
            } catch (e: Exception) {
                state.copy(
                    isLoading = false,
                )
            }
        }
    }

    fun onConsumedNextScreenEvent(){
        state = state.copy(nextScreenEvent = consumed)
    }

    fun onConsumedShowToastEvent(){
        state = state.copy(showToastEvent = consumed())
    }

    fun onConsumedHideErrorSupportingTextEvent(){
        state = state.copy(hideErrorSupportingTextEvent = consumed)
    }

    fun onConsumedShowErrorSupportingTextEvent(){
        state = state.copy(showErrorSupportingTextEvent = consumed())
    }
}