package myapp.hoang.onboarding.signup

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import myapp.hoang.core_ui.*
import myapp.hoang.core_ui.components.*
import myapp.hoang.onboarding.login.LoginScreen

@Composable
fun SignupScreen() {
    var mobileNumber by remember { mutableStateOf("") }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier
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
            modifier = Modifier.fillMaxHeight(0.7f)
        ) {
            Spacer(Modifier.height(LocalDimension.current.large))
            BackIcon(
                color = White,
                modifier = Modifier.align(Alignment.Start)
            )
            Spacer(Modifier.height(LocalDimension.current.mediumSmall))
            Text(
                text = "What's your mobile number?",
                color = White,
                style = MaterialTheme.typography.titleLarge,
                textAlign = TextAlign.Start,
                modifier = Modifier.align(Alignment.Start)
            )
            Spacer(Modifier.height(LocalDimension.current.small))
            Text(
                text = "Enter the mobile number where you can be contacted. No one will see this on your profile.",
                color = White,
                style = MaterialTheme.typography.labelMedium,
                textAlign = TextAlign.Start
            )
            Spacer(Modifier.height(LocalDimension.current.extraLarge))
            MobileNumberField(
                value = mobileNumber,
                onValueChange = { mobileNumber = it },
                label = "Mobile number"
            )
            Spacer(Modifier.height(LocalDimension.current.extraSmall))
            Text(
                text = "You may receive SMS notifications from us for security and login purposes.",
                color = AliceBlue,
                style = MaterialTheme.typography.bodySmall,
                textAlign = TextAlign.Start
            )
            Spacer(Modifier.height(LocalDimension.current.extraLarge))
            LoginButton("Next", {})
            Spacer(Modifier.height(LocalDimension.current.medium))
            EmailMobileNumberSwitchButton("Sign up with email", {})
        }
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Already have an account?",
                color = LinkBlue,
                style = MaterialTheme.typography.labelSmall,
                textAlign = TextAlign.Center
            )
            Spacer(Modifier.height(LocalDimension.current.large))
        }
    }
}

@Preview
@Composable
fun SignupScreenPreview() {
    OnBoardingTheme {
        Surface(
            modifier = Modifier.fillMaxSize()
        ) {
            SignupScreen()
        }
    }
}