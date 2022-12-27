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
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import myapp.hoang.core.util.Validator
import myapp.hoang.core_ui.*
import myapp.hoang.core_ui.components.*
import myapp.hoang.onboarding.R

@Composable
fun SignupByPhoneScreen(
    onBackClick: () -> Unit,
    onNextClick: (Long, String) -> Unit,
    onSignUpWithEmailClick: () -> Unit
) {
    val context = LocalContext.current
    var mobileNumber by remember { mutableStateOf("") }
    var isDialogShown by remember { mutableStateOf(false) }
    var isError by remember { mutableStateOf(false) }
    var errorSupportingText by remember { mutableStateOf("") }

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
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top,
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
        ) {
            BackIcon(
                color = White,
                modifier = Modifier
                    .align(Alignment.Start)
                    .clickable(onClick = onBackClick)
            )
            Spacer(Modifier.height(LocalDimension.current.mediumSmall))
            Text(
                text = stringResource(R.string.signup_by_phone_title),
                color = White,
                style = MaterialTheme.typography.titleLarge,
                textAlign = TextAlign.Start,
                modifier = Modifier.align(Alignment.Start)
            )
            Spacer(Modifier.height(LocalDimension.current.small))
            Text(
                text = stringResource(R.string.signup_by_phone_label_1),
                color = White,
                style = MaterialTheme.typography.labelMedium,
                textAlign = TextAlign.Start,
                modifier = Modifier
                    .align(Alignment.Start)
                    .padding(end = LocalDimension.current.small)
            )
            Spacer(Modifier.height(LocalDimension.current.extraLarge))
            OnBoardingTextField(
                value = mobileNumber,
                onValueChange = { mobileNumber = it },
                label = "Mobile number",
                keyboardType = KeyboardType.Phone,
                isError = isError
            )
            Spacer(Modifier.height(LocalDimension.current.extraSmall))
            Text(
                text = if (isError) errorSupportingText
                else stringResource(R.string.signup_by_phone_label_2),
                color = if (isError) MaterialTheme.colorScheme.error else AliceBlue,
                style = MaterialTheme.typography.bodySmall,
                modifier = Modifier.align(Alignment.Start)
            )
            Spacer(Modifier.height(LocalDimension.current.mediumLarge))
            OnBoardingFilledButton(
                text = stringResource(R.string.next),
                onClick = {
                    if (mobileNumber.isEmpty() || mobileNumber.isBlank()) {
                        isError = true
                        errorSupportingText = context.getString(R.string.signup_by_phone_error_1)
                    } else if (!Validator.validateMobileNumber(mobileNumber)) {
                        isError = true
                        errorSupportingText = context.getString(R.string.signup_by_phone_error_2)
                    } else {
                        isError = false
                        onNextClick(mobileNumber.toLong(), "+1$mobileNumber")
                    }
                }
            )
            Spacer(Modifier.height(LocalDimension.current.medium))
            OnBoardingOutlinedButton(
                text = stringResource(R.string.signup_by_phone_button),
                onClick = onSignUpWithEmailClick
            )
        }
        AlreadyHaveAccountClickableText(
            isDialogShown = isDialogShown,
            onIsDialogShownChange = { isDialogShown = it },
            onBackClick = onBackClick
        )
    }
}