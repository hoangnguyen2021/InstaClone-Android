package myapp.hoang.onboarding.login.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import de.palm.composestateevents.EventEffect
import myapp.hoang.core_ui.*
import myapp.hoang.core_ui.components.*
import myapp.hoang.onboarding.R
import myapp.hoang.onboarding.login.viewmodels.LoginViewModel

@Composable
fun LoginScreen(
    onCreateAccount: () -> Unit,
    onLogin: () -> Unit
) {
    // can be username, email, or mobile number
    var user by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    // missing fields
    var isDialog1Shown by remember { mutableStateOf(false) }
    // can't find account
    var isDialog2Shown by remember { mutableStateOf(false) }

    val viewModel = hiltViewModel<LoginViewModel>()
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    EventEffect(
        event = uiState.loginEvent,
        onConsumed = viewModel::onConsumedLoginEvent
    ) {
        onLogin()
    }

    EventEffect(
        event = uiState.showDialog1Event,
        onConsumed = viewModel::onConsumedShowDialog1Event
    ) {
        isDialog1Shown = true
    }

    EventEffect(
        event = uiState.showDialog2Event,
        onConsumed = viewModel::onConsumedShowDialog2Event
    ) {
        isDialog2Shown = true
    }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceEvenly,
        modifier = Modifier
            .fillMaxSize()
            .background(brush = onBoardingBackgroundBrush)
            .padding(horizontal = LocalDimension.current.mediumSmall)
    ) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .fillMaxWidth()
                .weight(0.1f)
        ) {
            Text(
                text = "English (US)",
                color = MaterialTheme.colorScheme.onSecondary,
                style = MaterialTheme.typography.bodySmall
            )
        }
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .fillMaxWidth()
                .weight(0.25f)
        ) {
            GradientInstagramIcon(
                modifier = Modifier.size(LocalDimension.current.sevenExtraLarge)
            )
        }
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .fillMaxWidth()
                .weight(0.1f)
        ) {
            OnBoardingTextField(
                value = user,
                onValueChange = { user = it },
                label = stringResource(R.string.login_label_1),
                keyboardType = KeyboardType.Text
            )
        }
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .fillMaxWidth()
                .weight(0.1f)
        ) {
            OnBoardingPasswordField(
                value = password,
                onValueChange = { password = it },
                label = stringResource(R.string.login_label_2)
            )
        }
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .fillMaxWidth()
                .weight(0.1f)
        ) {
            OnBoardingFilledButton(
                text = stringResource(R.string.login_button_1),
                onClick = { viewModel.logIn(user, password) },
                isLoading = uiState.isLoading
            )
        }
        Box(
            contentAlignment = Alignment.TopCenter,
            modifier = Modifier
                .fillMaxWidth()
                .weight(0.2f)
        ) {
            Text(
                text = stringResource(R.string.login_label_3),
                color = White,
                style = MaterialTheme.typography.displayMedium,
            )
        }
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .fillMaxWidth()
                .weight(0.05f)
        ) {
            CreateAccountButton(
                text = stringResource(R.string.login_button_2),
                onClick = onCreateAccount
            )
        }
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .fillMaxWidth()
                .weight(0.1f)
        ) {
            MetaIcon(
                color = AliceBlue,
                modifier = Modifier
                    .size(LocalDimension.current.sixExtraLarge)
            )
        }
    }
    if (isDialog1Shown) {
        AlertDialog(
            title = stringResource(R.string.dialog_login_title_1),
            body = stringResource(R.string.dialog_login_body_1),
            dismissText = stringResource(R.string.dialog_login_button_1),
            onDismiss = { isDialog1Shown = false }
        )
    }
    if (isDialog2Shown) {
        AlertDialog(
            title = stringResource(R.string.dialog_login_title_2),
            body = stringResource(R.string.dialog_login_body_2, user),
            dismissText = stringResource(R.string.dialog_login_button_3),
            confirmText = stringResource(R.string.dialog_login_button_2),
            onDismiss = { isDialog2Shown = false },
            onConfirm = onCreateAccount
        )
    }
}