package myapp.hoang.onboarding.signup.screens

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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch
import myapp.hoang.core.utils.DateUtils
import myapp.hoang.core.utils.DateUtils.getToday
import myapp.hoang.core_ui.*
import myapp.hoang.core_ui.components.*
import myapp.hoang.core_ui.components.bottomsheet.*
import myapp.hoang.onboarding.R
import java.time.LocalDate

@Composable
fun BirthdayScreen(
    onBackClick: () -> Unit,
    onNextClick: (LocalDate) -> Unit
) {
    val drawerState = rememberBottomDrawerState(BottomDrawerValue.Closed)
    val scope = rememberCoroutineScope()

    var birthday by remember { mutableStateOf(getToday()) }
    val age by remember { derivedStateOf { DateUtils.calculateAge(birthday) } }

    BottomDrawer(
        drawerContent = {
            BirthdayDrawer(
                onCloseDrawer = { scope.launch { drawerState.close() } }
            )
        },
        drawerState = drawerState,
        drawerShape = RoundedCornerShape(
            topStart = LocalDimension.current.extraSmall,
            topEnd = LocalDimension.current.extraSmall
        ),
        scrimColor = Black.copy(alpha = 0.7f),
        gesturesEnabled = drawerState.isOpen
    ) {
        BirthdayContent(
            birthday = birthday,
            age = age,
            onBirthdayChange = { birthday = it },
            onBackClick = onBackClick,
            onNextClick = { onNextClick(birthday) },
            onExpandDrawer = { scope.launch { drawerState.expand() } }
        )
    }
}

@Composable
fun BirthdayContent(
    birthday: LocalDate,
    age: Int,
    onBirthdayChange: (LocalDate) -> Unit,
    onBackClick: () -> Unit,
    onNextClick: () -> Unit,
    onExpandDrawer: () -> Unit
) {
    var isDialogShown by remember { mutableStateOf(false) }

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
                text = stringResource(R.string.birthday_title_1),
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
                .weight(0.14f)
        ) {
            PartiallyClickableText(
                unclickableText = stringResource(R.string.birthday_label_1),
                clickableText = stringResource(R.string.birthday_label_2),
                modifier = Modifier.padding(end = LocalDimension.current.small),
                onClick = onExpandDrawer
            )
        }
        Box(
            contentAlignment = Alignment.CenterStart,
            modifier = Modifier
                .fillMaxWidth()
                .weight(0.1f)
        ) {
            OnBoardingBirthdayField(
                value = birthday,
                onValueChange = { onBirthdayChange(it) },
                label = "Birthday ($age years old)"
            )
        }
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .fillMaxWidth()
                .weight(0.1f)
        ) {
            OnBoardingFilledButton(
                text = stringResource(R.string.next),
                onClick = onNextClick
            )
        }
        Box(
            contentAlignment = Alignment.BottomCenter,
            modifier = Modifier
                .fillMaxWidth()
                .weight(0.54f)
        ) {
            AlreadyHaveAccountClickableText(
                isDialogShown = isDialogShown,
                onIsDialogShownChange = { isDialogShown = it },
                onBackClick = onBackClick
            )
        }
    }
}

@Composable
fun BirthdayDrawer(
    onCloseDrawer: () -> Unit
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
                .clickable(onClick = onCloseDrawer)
        )
        Spacer(Modifier.height(LocalDimension.current.large))
        Text(
            text = stringResource(id = R.string.birthday_title_2),
            color = White,
            style = MaterialTheme.typography.titleLarge,
            textAlign = TextAlign.Start,
            modifier = Modifier.align(Alignment.Start)
        )
        Spacer(Modifier.height(LocalDimension.current.mediumSmall))
        PartiallyClickableText(
            unclickableText = stringResource(R.string.birthday_body_1),
            clickableText = stringResource(R.string.learn_more),
            modifier = Modifier
                .align(Alignment.Start)
                .padding(end = LocalDimension.current.small),
            onClick = {}
        )
    }
}