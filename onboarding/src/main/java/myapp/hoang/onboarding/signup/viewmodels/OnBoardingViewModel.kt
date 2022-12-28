package myapp.hoang.onboarding.signup.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import myapp.hoang.onboarding.signup.repositories.SignupRepository
import java.time.LocalDate
import javax.inject.Inject

@HiltViewModel
class OnBoardingViewModel @Inject constructor(
    private val signupRepository: SignupRepository
) : ViewModel() {
    private val _signupForm = MutableStateFlow(SignupForm())
    val signupForm = _signupForm.asStateFlow()

    private val _toastEvent = MutableStateFlow<String?>(null)
    val toastEvent = _toastEvent.asStateFlow()

    private var sendVerificationJob: Job? = null

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
        sendVerificationJob?.cancel()
        sendVerificationJob = viewModelScope.launch {
            try {
                signupRepository.sendVerificationCode(mobileNumber)
                _toastEvent.value = "Verification code sent"
            } catch (e: Exception) {
                _toastEvent.value = e.toString()
            }
        }
    }
}