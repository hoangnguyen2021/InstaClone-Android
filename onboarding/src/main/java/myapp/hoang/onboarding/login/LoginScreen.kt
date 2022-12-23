package myapp.hoang.onboarding.login

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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import myapp.hoang.core_ui.*
import myapp.hoang.core_ui.components.CreateAccountButton
import myapp.hoang.core_ui.components.LoginButton
import myapp.hoang.core_ui.components.PasswordTextField
import myapp.hoang.core_ui.components.UsernameTextField

@Composable
fun LoginScreen(
    onCreateAccountClick: () -> Unit
) {
    var username by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

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
        UsernameTextField(
            value = username,
            onValueChange = { username = it },
            label = "Username, email or mobile number"
        )
        Spacer(Modifier.height(LocalDimension.current.small))
        PasswordTextField(
            value = password,
            onValueChange = { password = it },
            label = "Password"
        )
        Spacer(Modifier.height(LocalDimension.current.small))
        LoginButton("Log in", {})
        Spacer(Modifier.height(LocalDimension.current.small))
        Text(
            text = "Forgot password?",
            color = White,
            style = MaterialTheme.typography.displayLarge,
        )
        Spacer(Modifier.height(128.dp))
        CreateAccountButton(
            text = "Create new account",
            onClick = onCreateAccountClick
        )
        MetaIcon(
            color = AliceBlue,
            modifier = Modifier
                .size(LocalDimension.current.sixExtraLarge)
        )
    }
}

@Preview
@Composable
fun LoginScreenPreview() {
    OnBoardingTheme {
        Surface(
            modifier = Modifier.fillMaxSize()
        ) {
            LoginScreen {}
        }
    }
}