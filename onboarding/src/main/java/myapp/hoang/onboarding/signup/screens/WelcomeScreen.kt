package myapp.hoang.onboarding.signup.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import myapp.hoang.core_ui.Black
import myapp.hoang.core_ui.LocalDimension
import myapp.hoang.core_ui.components.InstaCloneBrand
import myapp.hoang.core_ui.components.OnBoardingProfilePicture2
import myapp.hoang.core_ui.onBoardingBackgroundBrush
import myapp.hoang.onboarding.R
import myapp.hoang.onboarding.signup.viewmodels.SignupViewModel

@Composable
fun WelcomeScreen(
    viewModel: SignupViewModel = hiltViewModel(),
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    uiState.signupForm.profilePicPath?.let {
        LaunchedEffect(key1 = uiState.signupForm.profilePicPath) {
            viewModel.getProfilePic(it)
        }
    }

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
        InstaCloneBrand(
            color = Black,
            modifier = Modifier.wrapContentWidth()
        )
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier.fillMaxSize()
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
            ) {
                OnBoardingProfilePicture2(imageUri = uiState.profilePic)
                Spacer(modifier = Modifier.height(LocalDimension.current.sixExtraLarge))
                Text(
                    text = "${stringResource(R.string.welcome_text_1)}${uiState.signupForm.username}",
                    style = TextStyle(
                        fontFamily = FontFamily.SansSerif,
                        fontWeight = FontWeight.Normal,
                        fontSize = 14.sp,
                        lineHeight = 18.sp,
                        letterSpacing = 0.4.sp,
                    ),
                    color = MaterialTheme.colorScheme.onSurface
                )
            }
        }
    }
}