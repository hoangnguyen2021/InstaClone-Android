package myapp.hoang.onboarding.login.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.ExperimentalLifecycleComposeApi
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import de.palm.composestateevents.EventEffect
import myapp.hoang.core_ui.*
import myapp.hoang.core_ui.components.CreateAccountButton
import myapp.hoang.core_ui.components.OnBoardingFilledButton
import myapp.hoang.core_ui.components.OnBoardingPasswordField
import myapp.hoang.core_ui.components.OnBoardingTextField
import myapp.hoang.onboarding.R
import myapp.hoang.onboarding.login.viewmodels.LoginViewModel

@OptIn(ExperimentalLifecycleComposeApi::class)
@Composable
fun LoginScreen(
    onCreateAccountClick: () -> Unit,
    onNextScreen: () -> Unit
) {
    val viewModel = hiltViewModel<LoginViewModel>()

    var user by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    EventEffect(
        event = uiState.nextScreenEvent,
        onConsumed = viewModel::onConsumedNextScreenEvent
    ) {
        onNextScreen()
    }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceEvenly,
        modifier = Modifier
            .fillMaxSize()
            .background(
                brush = Brush.verticalGradient(
                    0.0f to Color(0xFF223234),
                    0.6f to Color(0xFF1C2E3D),
                )
            )
            .padding(
                horizontal = LocalDimension.current.mediumSmall
            )
    ) {
        Spacer(Modifier.height(LocalDimension.current.large))
        Text(
            text = "English (US)",
            color = MaterialTheme.colorScheme.onSecondary,
            style = MaterialTheme.typography.bodySmall
        )
        Spacer(Modifier.height(LocalDimension.current.eightExtraLarge))
        GradientInstagramIcon(
            modifier = Modifier.size(LocalDimension.current.sevenExtraLarge)
        )
        Spacer(Modifier.height(LocalDimension.current.nineExtraLarge))
        OnBoardingTextField(
            value = user,
            onValueChange = { user = it },
            label = stringResource(R.string.login_label_1),
            keyboardType = KeyboardType.Text
        )
        Spacer(Modifier.height(LocalDimension.current.small))
        OnBoardingPasswordField(
            value = password,
            onValueChange = { password = it },
            label = stringResource(R.string.login_label_2)
        )
        Spacer(Modifier.height(LocalDimension.current.small))
        OnBoardingFilledButton(
            text = stringResource(R.string.login_button_1),
            onClick = { viewModel.logIn(user, password) },
            isLoading = uiState.isLoading
        )
        Spacer(Modifier.height(LocalDimension.current.small))
        Text(
            text = stringResource(R.string.login_label_3),
            color = White,
            style = MaterialTheme.typography.displayLarge,
        )
        Spacer(Modifier.height(128.dp))
        CreateAccountButton(
            text = stringResource(R.string.login_button_2),
            onClick = onCreateAccountClick
        )
        MetaIcon(
            color = AliceBlue,
            modifier = Modifier
                .size(LocalDimension.current.sixExtraLarge)
        )
    }
}