package myapp.hoang.instaclone

import android.util.Log
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import java.time.LocalDate
import javax.inject.Inject

@HiltViewModel
class OnBoardingViewModel @Inject constructor(): ViewModel()  {
    private val _signupForm = MutableStateFlow(SignupForm())
    val signupForm = _signupForm.asStateFlow()

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
        Log.d("MYTAG", signupForm.value.toString())
    }
}