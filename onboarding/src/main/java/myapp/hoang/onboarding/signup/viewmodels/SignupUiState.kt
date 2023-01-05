package myapp.hoang.onboarding.signup.viewmodels

import de.palm.composestateevents.StateEvent
import de.palm.composestateevents.StateEventWithContent
import de.palm.composestateevents.consumed
import myapp.hoang.core_ui.utils.UiText
import myapp.hoang.onboarding.signup.models.SignupForm

data class SignupUiState(
    val signupForm: SignupForm = SignupForm(),
    val profilePic: ByteArray? = null,
    val isLoading: Boolean = false,
    val showToastEvent: StateEventWithContent<UiText> = consumed(),
    val showErrorSupportingTextEvent: StateEventWithContent<UiText> = consumed(),
    val hideErrorSupportingTextEvent: StateEvent = consumed,
    val nextScreenEvent: StateEvent = consumed
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as SignupUiState

        if (signupForm != other.signupForm) return false
        if (profilePic != null) {
            if (other.profilePic == null) return false
            if (!profilePic.contentEquals(other.profilePic)) return false
        } else if (other.profilePic != null) return false
        if (isLoading != other.isLoading) return false
        if (showToastEvent != other.showToastEvent) return false
        if (showErrorSupportingTextEvent != other.showErrorSupportingTextEvent) return false
        if (hideErrorSupportingTextEvent != other.hideErrorSupportingTextEvent) return false
        if (nextScreenEvent != other.nextScreenEvent) return false

        return true
    }

    override fun hashCode(): Int {
        var result = signupForm.hashCode()
        result = 31 * result + (profilePic?.contentHashCode() ?: 0)
        result = 31 * result + isLoading.hashCode()
        result = 31 * result + showToastEvent.hashCode()
        result = 31 * result + showErrorSupportingTextEvent.hashCode()
        result = 31 * result + hideErrorSupportingTextEvent.hashCode()
        result = 31 * result + nextScreenEvent.hashCode()
        return result
    }
}
