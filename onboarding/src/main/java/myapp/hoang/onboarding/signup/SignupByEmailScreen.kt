package myapp.hoang.onboarding.signup

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import myapp.hoang.core_ui.*
import myapp.hoang.core_ui.components.*

@Composable
fun SignupByEmailScreen(
    onBackClick: () -> Unit,
    onNextClick: (String) -> Unit,
    onSignUpWithMobileNumberClick: () -> Unit
) {
    var email by remember { mutableStateOf("") }
    var isDialogShown by remember { mutableStateOf(false) }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween,
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
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top,
            modifier = Modifier.fillMaxHeight(0.7f)
        ) {
            Spacer(Modifier.height(LocalDimension.current.large))
            BackIcon(
                color = White,
                modifier = Modifier
                    .align(Alignment.Start)
                    .clickable(onClick = onBackClick)
            )
            Spacer(Modifier.height(LocalDimension.current.mediumSmall))
            Text(
                text = "What's your email?",
                color = White,
                style = MaterialTheme.typography.titleLarge,
                textAlign = TextAlign.Start,
                modifier = Modifier.align(Alignment.Start)
            )
            Spacer(Modifier.height(LocalDimension.current.small))
            Text(
                text = "Enter the email where you can be contacted. No one will see this on your profile.",
                color = White,
                style = MaterialTheme.typography.labelMedium,
                textAlign = TextAlign.Start,
                modifier = Modifier
                    .align(Alignment.Start)
                    .padding(end = LocalDimension.current.small)
            )
            Spacer(Modifier.height(LocalDimension.current.extraLarge))
            OnBoardingTextField(
                value = email,
                onValueChange = { email = it },
                label = "Email",
                keyboardType = KeyboardType.Email
            )
            Spacer(Modifier.height(LocalDimension.current.mediumLarge))
            OnBoardingFilledButton(
                text = "Next",
                onClick = { onNextClick(email) }
            )
            Spacer(Modifier.height(LocalDimension.current.medium))
            OnBoardingOutlinedButton(
                text = "Sign up with mobile number",
                onClick = onSignUpWithMobileNumberClick)
        }
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top,
        ) {
            Text(
                text = "Already have an account?",
                color = LinkBlue,
                style = MaterialTheme.typography.labelSmall,
                textAlign = TextAlign.Center,
                modifier = Modifier.clickable { isDialogShown = true }
            )
            Spacer(Modifier.height(LocalDimension.current.large))
        }
        if (isDialogShown) {
            AlreadyHaveAccountDialog(
                onConfirm = {
                    isDialogShown = false
                    onBackClick()
                    onBackClick()
                },
                onDismiss = { isDialogShown = false }
            )
        }
    }
}

@Preview
@Composable
fun SignupByEmailScreenPreview() {
    OnBoardingTheme {
        Surface(
            modifier = Modifier.fillMaxSize()
        ) {
            SignupByEmailScreen({}, {}, {})
        }
    }
}