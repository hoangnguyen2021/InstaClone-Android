package myapp.hoang.core_ui.utils

sealed class UiEvent {
    object NoEvent: UiEvent()
    data class ShowToast(val message: UiText): UiEvent()
    data class ShowErrorSupportingText(val message: UiText): UiEvent()
    object HideErrorSupportingText: UiEvent()
    object NextScreen: UiEvent()
}