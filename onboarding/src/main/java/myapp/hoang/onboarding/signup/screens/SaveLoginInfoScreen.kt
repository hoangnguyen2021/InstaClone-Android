package myapp.hoang.onboarding.signup.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.ExperimentalLifecycleComposeApi
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import myapp.hoang.core_ui.*
import myapp.hoang.core_ui.components.*
import myapp.hoang.onboarding.R
import myapp.hoang.onboarding.signup.viewmodels.SignupViewModel

@OptIn(ExperimentalLifecycleComposeApi::class)
@Composable
fun SaveLoginInfoScreen(
    viewModel: SignupViewModel = hiltViewModel(),
    onBackClick: () -> Unit,
    onNextClick: () -> Unit
) {
    var isDialogShown by remember { mutableStateOf(false) }

    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

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
                text = stringResource(R.string.save_login_info_title),
                color = White,
                style = MaterialTheme.typography.titleLarge,
                textAlign = TextAlign.Start
            )
        }
        Box(
            contentAlignment = Alignment.TopStart,
            modifier = Modifier
                .fillMaxWidth()
                .weight(0.08f)
        ) {
            Text(
                text = buildAnnotatedString {
                    append(stringResource(R.string.save_login_info_label_1))
                    withStyle(SpanStyle(fontWeight = FontWeight.SemiBold)) {
                        append(uiState.signupForm.fullName ?: "")
                    }
                    append(stringResource(R.string.save_login_info_label_2))
                },
                color = White,
                style = MaterialTheme.typography.bodyMedium,
                textAlign = TextAlign.Start,
                modifier = Modifier.padding(end = LocalDimension.current.small)
            )
        }
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .fillMaxWidth()
                .weight(0.1f)
        ) {
            OnBoardingFilledButton(
                text = stringResource(R.string.save_login_info_button_1),
                onClick = onNextClick
            )
        }
        Box(
            contentAlignment = Alignment.Center,

            modifier = Modifier
                .fillMaxWidth()
                .weight(0.1f)
        ) {
            OnBoardingOutlinedButton(
                text = stringResource(R.string.save_login_info_button_2),
                onClick = onNextClick
            )
        }
        Box(
            contentAlignment = Alignment.BottomCenter,
            modifier = Modifier
                .fillMaxWidth()
                .weight(0.6f)
        ) {
            AlreadyHaveAccountClickableText(
                isDialogShown = isDialogShown,
                onIsDialogShownChange = { isDialogShown = it },
                onBackClick = onBackClick
            )
        }
    }
}