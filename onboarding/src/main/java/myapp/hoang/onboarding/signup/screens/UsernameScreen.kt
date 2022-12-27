package myapp.hoang.onboarding.signup.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import myapp.hoang.core_ui.*
import myapp.hoang.core_ui.components.AlreadyHaveAccountClickableText
import myapp.hoang.core_ui.components.OnBoardingFilledButton
import myapp.hoang.core_ui.components.OnBoardingTextField

@Composable
fun UsernameScreen(
    onBackClick: () -> Unit,
    onNextClick: (String) -> Unit
) {
    var username by remember { mutableStateOf("") }
    var isDialogShown by remember { mutableStateOf(false) }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier
            .fillMaxSize()
            .background(
                brush = onBoardingBackgroundBrush
            )
            .padding(
                vertical = LocalDimension.current.large,
                horizontal = LocalDimension.current.mediumSmall
            )
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top,
            modifier = Modifier.wrapContentHeight()
        ) {
            BackIcon(
                color = White,
                modifier = Modifier
                    .align(Alignment.Start)
                    .clickable(onClick = onBackClick)
            )
            Spacer(Modifier.height(LocalDimension.current.mediumSmall))
            Text(
                text = "Create a username",
                color = White,
                style = MaterialTheme.typography.titleLarge,
                textAlign = TextAlign.Start,
                modifier = Modifier.align(Alignment.Start)
            )
            Spacer(Modifier.height(LocalDimension.current.small))
            Text(
                text = "Add a username or user our suggestion. You can change this at any time.",
                color = White,
                style = MaterialTheme.typography.labelMedium,
                textAlign = TextAlign.Start,
                modifier = Modifier
                    .align(Alignment.Start)
                    .padding(end = LocalDimension.current.small)
            )
            Spacer(Modifier.height(LocalDimension.current.extraLarge))
            OnBoardingTextField(
                value = username,
                onValueChange = { username = it },
                label = "Username"
            )
            Spacer(Modifier.height(LocalDimension.current.mediumLarge))
            OnBoardingFilledButton(
                text = "Next",
                onClick = { onNextClick(username) }
            )
        }
        AlreadyHaveAccountClickableText(
            isDialogShown = isDialogShown,
            onIsDialogShownChange = { isDialogShown = it },
            onBackClick = onBackClick
        )
    }
}