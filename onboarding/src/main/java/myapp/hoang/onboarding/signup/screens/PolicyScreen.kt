package myapp.hoang.onboarding.signup.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.ClickableText
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
import myapp.hoang.core_ui.*
import myapp.hoang.core_ui.components.*
import myapp.hoang.onboarding.R

@Composable
fun PolicyScreen(
    onBackClick: () -> Unit,
    onNextClick: () -> Unit
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
                .weight(0.12f)
        ) {
            Text(
                text = stringResource(R.string.policy_title),
                color = White,
                style = MaterialTheme.typography.titleLarge,
                textAlign = TextAlign.Start,
                maxLines = 2
            )
        }
        Column(
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.spacedBy(
                space = LocalDimension.current.medium,
                alignment = Alignment.Top
            ),
            modifier = Modifier
                .fillMaxWidth()
                .weight(0.38f)
        ) {
            PartiallyClickableText(
                unclickableText = stringResource(R.string.policy_body_1),
                clickableText = stringResource(R.string.learn_more),
                modifier = Modifier
                    .align(Alignment.Start)
                    .padding(end = LocalDimension.current.small),
                onClick = {}
            )
            PolicyBody2(
                modifier = Modifier
                    .align(Alignment.Start)
                    .padding(end = LocalDimension.current.small),
                onClick = {}
            )
            PolicyBody3(
                modifier = Modifier
                    .align(Alignment.Start)
                    .padding(end = LocalDimension.current.small),
                onClick = {}
            )
        }
        Box(
            contentAlignment = Alignment.TopStart,
            modifier = Modifier
                .fillMaxWidth()
                .weight(0.1f)
        ) {
            OnBoardingFilledButton(
                text = "I agree",
                onClick = { onNextClick() }
            )
        }
        Box(
            contentAlignment = Alignment.BottomCenter,
            modifier = Modifier
                .fillMaxWidth()
                .weight(0.35f)
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
fun PolicyBody2(
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    val annotatedText = buildAnnotatedString {
        withStyle(style = SpanStyle(color = White)) {
            append(stringResource(R.string.policy_body_2_1))
        }
        withStyle(style = SpanStyle(color = White, fontWeight = FontWeight.Bold)) {
            append(stringResource(R.string.policy_body_2_2))
        }
        withStyle(style = SpanStyle(color = White)) {
            append(stringResource(R.string.policy_body_2_3))
        }
        pushStringAnnotation(tag = "terms", annotation = "")
        withStyle(style = SpanStyle(color = LinkBlue)) {
            append(stringResource(R.string.policy_body_2_4))
        }
        pop()
        withStyle(style = SpanStyle(color = White)) {
            append(stringResource(R.string.policy_body_2_5))
        }
        pushStringAnnotation(tag = "privacy_policy", annotation = "")
        withStyle(style = SpanStyle(color = LinkBlue)) {
            append(stringResource(R.string.policy_body_2_6))
        }
        pop()
        withStyle(style = SpanStyle(color = White)) {
            append(stringResource(R.string.policy_body_2_7))
        }
        pushStringAnnotation(tag = "cookies_policy", annotation = "")
        withStyle(style = SpanStyle(color = LinkBlue)) {
            append(stringResource(R.string.policy_body_2_8))
        }
        pop()
        withStyle(style = SpanStyle(color = White)) {
            append(stringResource(R.string.policy_body_2_9))
        }
    }
    ClickableText(
        text = annotatedText,
        style = MaterialTheme.typography.bodyMedium,
        onClick = {},
        modifier = modifier
    )
}

@Composable
fun PolicyBody3(
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    val annotatedText = buildAnnotatedString {
        withStyle(style = SpanStyle(color = White)) {
            append(stringResource(R.string.policy_body_3_1))
        }
        pushStringAnnotation(tag = "privacy_policy", annotation = "")
        withStyle(style = SpanStyle(color = LinkBlue)) {
            append(stringResource(R.string.policy_body_3_2))
        }
        pop()
        withStyle(style = SpanStyle(color = White)) {
            append(stringResource(R.string.policy_body_3_3))
        }
    }
    ClickableText(
        text = annotatedText,
        style = MaterialTheme.typography.bodyMedium,
        onClick = {},
        modifier = modifier
    )
}