package myapp.hoang.onboarding.signup

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import myapp.hoang.core.util.DateUtils
import myapp.hoang.core.util.DateUtils.getToday
import myapp.hoang.core_ui.*
import myapp.hoang.core_ui.components.*
import myapp.hoang.core_ui.components.bottomsheet.*

@Composable
fun BirthdayScreen(
    onBackClick: () -> Unit,
    onNextClick: (String) -> Unit
) {
    val drawerState = rememberBottomDrawerState(BottomDrawerValue.Closed)
    val scope = rememberCoroutineScope()

    BottomDrawer(
        drawerContent = {
            BirthdayDrawerContent(
                drawerState = drawerState,
                scope = scope
            )
        },
        drawerState = drawerState,
        drawerShape = RoundedCornerShape(
            topStart = LocalDimension.current.extraSmall,
            topEnd = LocalDimension.current.extraSmall
        ),
        scrimColor = Black.copy(alpha = 0.7f)
    ) {
        BirthdayContent(
            onBackClick = onBackClick,
            drawerState = drawerState,
            scope = scope
        )
    }
}

@Composable
fun BirthdayContent(
    onBackClick: () -> Unit,
    drawerState: BottomDrawerState,
    scope: CoroutineScope
) {
    var birthday by remember { mutableStateOf(getToday()) }
    val age by remember { derivedStateOf { DateUtils.calculateAge(birthday) } }
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
                text = "What's your birthday?",
                color = White,
                style = MaterialTheme.typography.titleLarge,
                textAlign = TextAlign.Start,
                modifier = Modifier.align(Alignment.Start)
            )
            Spacer(Modifier.height(LocalDimension.current.small))
            PartiallyClickableText(
                unclickableText = "Use your own birthday, even if this account is for a business, a pet or something else. No one will see this on your profile.",
                clickableText = "Why do I need to provide my birthday?",
                modifier = Modifier
                    .align(Alignment.Start)
                    .padding(end = LocalDimension.current.small),
                onClick = { scope.launch { drawerState.expand() } }
            )
            Spacer(Modifier.height(LocalDimension.current.extraLarge))
            OnBoardingBirthdayField(
                value = birthday,
                onValueChange = { birthday = it },
                label = "Birthday ($age years old)"
            )
            Spacer(Modifier.height(LocalDimension.current.mediumLarge))
            OnBoardingFilledButton(
                text = "Next",
                onClick = {}
            )
        }
        AlreadyHaveAccountClickableText(
            isDialogShown = isDialogShown,
            onIsDialogShownChange = { isDialogShown = it },
            onBackClick = onBackClick
        )
    }
}

@Composable
fun BirthdayDrawerContent(
    drawerState: BottomDrawerState,
    scope: CoroutineScope
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top,
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(0.75f)
            .background(
                brush = Brush.verticalGradient(
                    0.0f to Color(0xFF213536),
                    0.6f to Color(0xFF1C2E3D),
                )
            )
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
        Spacer(Modifier.height(LocalDimension.current.medium))
        Text(
            text = "Birthdays",
            color = White,
            style = MaterialTheme.typography.titleLarge,
            textAlign = TextAlign.Start,
            modifier = Modifier.align(Alignment.Start)
        )
        Spacer(Modifier.height(LocalDimension.current.extraSmall))
        PartiallyClickableText(
            unclickableText = "Providing your birthday improves the features and ads you see, and helps to keep the Instagram community safe. You can find your birthday in your personal information account settings.",
            clickableText = "Learn more",
            modifier = Modifier
                .align(Alignment.Start)
                .padding(end = LocalDimension.current.small),
            onClick = {}
        )
    }
}