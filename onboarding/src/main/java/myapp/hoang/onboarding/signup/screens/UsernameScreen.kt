package myapp.hoang.onboarding.signup.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.ExperimentalLifecycleComposeApi
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import kotlinx.datetime.toJavaLocalDate
import myapp.hoang.core_ui.*
import myapp.hoang.core_ui.components.AlreadyHaveAccountClickableText
import myapp.hoang.core_ui.components.OnBoardingFilledButton
import myapp.hoang.core_ui.components.OnBoardingTextField
import myapp.hoang.onboarding.R
import myapp.hoang.onboarding.signup.viewmodels.SignupViewModel

@OptIn(ExperimentalLifecycleComposeApi::class)
@Composable
fun UsernameScreen(
    viewModel: SignupViewModel = hiltViewModel(),
    onBackClick: () -> Unit,
    onNextClick: (String) -> Unit
) {
    val context = LocalContext.current

    var username by remember { mutableStateOf("") }
    var isDialogShown by remember { mutableStateOf(false) }
    var isError by remember { mutableStateOf(false) }
    var errorSupportingText by remember { mutableStateOf("") }

    val showError = isError && errorSupportingText.isNotEmpty()

    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    // recover username when navigating back
    LaunchedEffect(key1 = uiState.signupForm.username) {
        uiState.signupForm.username?.let { username = it }
    }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween,
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
                text = stringResource(R.string.username_title),
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
                text = stringResource(R.string.username_label_1),
                color = White,
                style = MaterialTheme.typography.bodyMedium,
                textAlign = TextAlign.Start,
                modifier = Modifier.padding(end = LocalDimension.current.small)
            )
        }
        Box(
            contentAlignment = Alignment.CenterStart,
            modifier = Modifier
                .fillMaxWidth()
                .weight(0.1f)
        ) {
            OnBoardingTextField(
                value = username,
                onValueChange = { username = it },
                label = "Username",
                isError = isError
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
                onClick = {
                    if (username.isEmpty() || username.isBlank()) {
                        isError = true
                        errorSupportingText = context.getString(R.string.error_username_1)
                    } else {
                        isError = false
                        onNextClick(username)
                    }
                }
            )
        }
        Box(
            contentAlignment = Alignment.BottomCenter,
            modifier = Modifier
                .fillMaxWidth()
                .weight(if (showError) 0.55f else 0.6f)
        ) {
            AlreadyHaveAccountClickableText(
                isDialogShown = isDialogShown,
                onIsDialogShownChange = { isDialogShown = it },
                onBackClick = onBackClick
            )
        }
    }
}