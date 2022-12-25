package myapp.hoang.onboarding.signup

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import myapp.hoang.core_ui.*
import myapp.hoang.core_ui.components.*
import myapp.hoang.core_ui.components.bottomsheet.*
import myapp.hoang.onboarding.R

@Composable
fun ConfirmationCodeScreen(
    username: String,
    onNextClick: () -> Unit,
    onBackClick: () -> Unit,
    onLoginClick: () -> Unit
) {
    val drawerState = rememberBottomDrawerState(BottomDrawerValue.Closed)
    val scope = rememberCoroutineScope()

    BottomDrawer(
        drawerContent = {
            ConfirmationCodeDrawerContent(
                drawerState = drawerState,
                scope = scope,
                onLoginClick = onLoginClick
            )
        },
        drawerState = drawerState,
        drawerShape = RoundedCornerShape(
            topStart = LocalDimension.current.extraSmall,
            topEnd = LocalDimension.current.extraSmall
        ),
        scrimColor = Black.copy(alpha = 0.7f)
    ) {
        ConfirmationCodeContent(
            username = username,
            onNextClick = onNextClick,
            onBackClick = onBackClick,
            drawerState = drawerState,
            scope = scope
        )
    }
}

@Composable
fun ConfirmationCodeContent(
    username: String,
    onBackClick: () -> Unit,
    onNextClick: () -> Unit,
    drawerState: BottomDrawerState,
    scope: CoroutineScope
) {
    var confirmationCode by remember { mutableStateOf("") }

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
        BackIcon(
            color = White,
            modifier = Modifier
                .align(Alignment.Start)
                .clickable(onClick = onBackClick)
        )
        Spacer(Modifier.height(LocalDimension.current.mediumSmall))
        Text(
            text = stringResource(R.string.confirmation_code_title),
            color = White,
            style = MaterialTheme.typography.titleLarge,
            textAlign = TextAlign.Start,
            modifier = Modifier.align(Alignment.Start)
        )
        Spacer(Modifier.height(LocalDimension.current.small))
        Text(
            text = stringResource(R.string.confirmation_code_label_1) + "$username.",
            color = White,
            style = MaterialTheme.typography.labelMedium,
            textAlign = TextAlign.Start,
            modifier = Modifier
                .align(Alignment.Start)
                .padding(end = LocalDimension.current.small)
        )
        Spacer(Modifier.height(LocalDimension.current.extraLarge))
        OnBoardingTextField(
            value = confirmationCode,
            onValueChange = { confirmationCode = it },
            label = "Confirmation code",
            keyboardType = KeyboardType.Number
        )
        Spacer(Modifier.height(LocalDimension.current.mediumLarge))
        OnBoardingFilledButton(
            text = stringResource(R.string.next),
            onClick = onNextClick
        )
        Spacer(Modifier.height(LocalDimension.current.medium))
        OnBoardingOutlinedButton(
            text = stringResource(R.string.confirmation_code_button_1),
            onClick = { scope.launch { drawerState.expand() } }
        )
    }
}

@Composable
fun ConfirmationCodeDrawerContent(
    drawerState: BottomDrawerState,
    scope: CoroutineScope,
    onLoginClick: () -> Unit
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top,
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(0.75f)
            .background(brush = onBoardingDrawerBrush)
            .padding(horizontal = LocalDimension.current.mediumSmall)
    ) {
        SwipeIndicatorIcon(
            color = Color(0xFFCBD2DA),
            modifier = Modifier
                .size(LocalDimension.current.fourExtraLarge)
                .offset(y = (-12).dp)
        )
        CancelIcon(
            color = White,
            modifier = Modifier
                .size(LocalDimension.current.medium)
                .align(Alignment.Start)
                .clickable { scope.launch { drawerState.close() } }
        )
        Spacer(Modifier.height(LocalDimension.current.large))
        BottomSheetTopButton(
            text = stringResource(R.string.confirmation_code_button_2),
            onClick = {
                scope.launch { drawerState.close() }
            }
        )
        BottomSheetBottomButton(
            text = stringResource(R.string.confirmation_code_button_3),
            onClick = {
                scope.launch { drawerState.close() }
                onLoginClick()
            }
        )
    }
}