package myapp.hoang.onboarding.signup.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import de.palm.composestateevents.EventEffect
import kotlinx.coroutines.launch
import myapp.hoang.core_ui.*
import myapp.hoang.core_ui.components.*
import myapp.hoang.core_ui.components.bottomsheet.*
import myapp.hoang.onboarding.R
import myapp.hoang.onboarding.signup.viewmodels.SignupViewModel
import myapp.hoang.onboarding.signup.models.SignupForm

@Composable
fun ConfirmationCodeScreen(
    viewModel: SignupViewModel = hiltViewModel(),
    type: String,
    onNextClick: (String, String) -> Unit,
    onBackClick: () -> Unit,
    onLoginClick: () -> Unit,
    onNextScreen: () -> Unit
) {
    val context = LocalContext.current

    val drawerState = rememberBottomDrawerState(BottomDrawerValue.Closed)
    val scope = rememberCoroutineScope()

    var confirmationCode by remember { mutableStateOf("") }
    var isError by remember { mutableStateOf(false) }
    var errorSupportingText by remember { mutableStateOf("") }

    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    EventEffect(
        event = uiState.nextScreenEvent,
        onConsumed = viewModel::onConsumedNextScreenEvent
    ) {
        onNextScreen()
    }

    EventEffect(
        event = uiState.showErrorSupportingTextEvent,
        onConsumed = viewModel::onConsumedShowErrorSupportingTextEvent
    ) {
        isError = true
        errorSupportingText = it.asString(context)
    }

    EventEffect(
        event = uiState.hideErrorSupportingTextEvent,
        onConsumed = viewModel::onConsumedHideErrorSupportingTextEvent
    ) {
        isError = false
        errorSupportingText = ""
    }

    BottomDrawer(
        drawerContent = {
            ConfirmationCodeDrawer(
                onCloseDrawer = { scope.launch { drawerState.close() } },
                onLoginClick = onLoginClick
            )
        },
        drawerState = drawerState,
        drawerShape = RoundedCornerShape(
            topStart = LocalDimension.current.extraSmall,
            topEnd = LocalDimension.current.extraSmall
        ),
        scrimColor = Black.copy(alpha = 0.7f),
        gesturesEnabled = drawerState.isOpen
    ) {
        ConfirmationCodeContent(
            type = type,
            signupForm = uiState.signupForm,
            confirmationCode = confirmationCode,
            onConfirmationCodeChange = { confirmationCode = it },
            isError = isError,
            errorSupportingText = errorSupportingText,
            isLoading = uiState.isLoading,
            onNextClick = {
                onNextClick(
                    if (type == "phone") uiState.signupForm.mobileNumber.toString()
                    else uiState.signupForm.email.toString(),
                    confirmationCode
                )
            },
            onBackClick = onBackClick,
            onExpandDrawer = { scope.launch { drawerState.expand() } }
        )
    }
}

@Composable
fun ConfirmationCodeContent(
    type: String,
    signupForm: SignupForm,
    confirmationCode: String,
    onConfirmationCodeChange: (String) -> Unit,
    isError: Boolean,
    errorSupportingText: String,
    isLoading: Boolean,
    onBackClick: () -> Unit,
    onNextClick: () -> Unit,
    onExpandDrawer: () -> Unit
) {
    val showError = isError && errorSupportingText.isNotEmpty()

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top,
        modifier = Modifier
            .fillMaxSize()
            .background(brush = onBoardingBackgroundBrush)
            .padding(
                vertical = LocalDimension.current.large,
                horizontal = LocalDimension.current.mediumSmall
            )
    ) {
        Box(
            contentAlignment = Alignment.TopStart,
            modifier = Modifier
                .fillMaxWidth()
                .weight(0.05f)
        ) {
            BackIcon(
                color = White,
                modifier = Modifier.clickable(onClick = onBackClick)
            )
        }
        Box(
            contentAlignment = Alignment.CenterStart,
            modifier = Modifier
                .fillMaxWidth()
                .weight(0.07f)
        ) {
            Text(
                text = stringResource(R.string.confirmation_code_title_1),
                color = White,
                style = MaterialTheme.typography.titleLarge,
                textAlign = TextAlign.Start,
                maxLines = 1
            )
        }
        Box(
            contentAlignment = Alignment.TopStart,
            modifier = Modifier
                .fillMaxWidth()
                .weight(0.08f)
        ) {
            Text(
                text = stringResource(R.string.confirmation_code_label_1) +
                        "${if (type == "phone") signupForm.mobileNumber else signupForm.email}.",
                color = White,
                style = MaterialTheme.typography.bodyMedium,
                textAlign = TextAlign.Start,
                modifier = Modifier
                    .padding(end = LocalDimension.current.small)
            )
        }
        Box(
            contentAlignment = Alignment.CenterStart,
            modifier = Modifier
                .fillMaxWidth()
                .weight(0.1f)
        ) {
            OnBoardingTextField(
                value = confirmationCode,
                onValueChange = { onConfirmationCodeChange(it) },
                label = "Confirmation code",
                keyboardType = KeyboardType.Number,
                isError = isError,
            )
        }
        if (showError) {
            Box(
                contentAlignment = Alignment.TopStart,
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(0.05f)
            ) {
                Text(
                    text = errorSupportingText,
                    color = MaterialTheme.colorScheme.error,
                    style = MaterialTheme.typography.bodySmall
                )
            }
        }
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .fillMaxWidth()
                .weight(0.1f)
        ) {
            OnBoardingFilledButton(
                text = stringResource(R.string.next),
                onClick = onNextClick,
                isLoading = isLoading
            )
        }
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .fillMaxWidth()
                .weight(0.1f)
        ) {
            OnBoardingOutlinedButton(
                text = stringResource(R.string.confirmation_code_button_1),
                onClick = onExpandDrawer
            )
        }
        Box(
            contentAlignment = Alignment.BottomCenter,
            modifier = Modifier
                .fillMaxWidth()
                .weight(if (showError) 0.45f else 0.5f)
        ) {
        }
    }
}

@Composable
fun ConfirmationCodeDrawer(
    onCloseDrawer: () -> Unit,
    onLoginClick: () -> Unit
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top,
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(0.75f)
            .background(brush = onBoardingDrawerBrush)
            .padding(horizontal = LocalDimension.current.mediumSmall)
    ) {
        SwipeIndicatorIcon(
            color = Color(0xFFCBD2DA),
            modifier = Modifier
                .size(LocalDimension.current.fourExtraLarge)
                .offset(y = (-12).dp)
        )
        CancelIcon(
            color = White,
            modifier = Modifier
                .size(LocalDimension.current.medium)
                .align(Alignment.Start)
                .clickable(onClick = onCloseDrawer)
        )
        Spacer(Modifier.height(LocalDimension.current.large))
        BottomSheetTopButton(
            text = stringResource(R.string.confirmation_code_button_2),
            onClick = onCloseDrawer
        )
        BottomSheetBottomButton(
            text = stringResource(R.string.confirmation_code_button_3),
            onClick = {
                onCloseDrawer()
                onLoginClick()
            }
        )
    }
}