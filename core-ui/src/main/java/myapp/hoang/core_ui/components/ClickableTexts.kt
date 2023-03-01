package myapp.hoang.core_ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import kotlinx.datetime.Instant
import myapp.hoang.core.utils.toRelativeTimeString
import myapp.hoang.core_ui.ChevronDownIcon
import myapp.hoang.core_ui.LinkBlue
import myapp.hoang.core_ui.LocalDimension
import myapp.hoang.core_ui.White

@Composable
fun AlreadyHaveAccountClickableText(
    isDialogShown: Boolean,
    onIsDialogShownChange: (Boolean) -> Unit,
    onBackClick: () -> Unit
) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
    ) {
        ClickableText(
            text = buildAnnotatedString {
                withStyle(style = SpanStyle(color = LinkBlue)) {
                    append("Already have an account?")
                }
            },
            style = MaterialTheme.typography.displaySmall,
            maxLines = 1,
            onClick = { onIsDialogShownChange(true) }
        )
    }
    if (isDialogShown) {
        AlreadyHaveAccountDialog(
            onConfirm = {
                onIsDialogShownChange(false)
                onBackClick()
                onBackClick()
            },
            onDismiss = { onIsDialogShownChange(false) }
        )
    }
}

@Composable
fun PartiallyClickableText(
    unclickableText: String,
    clickableText: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val annotatedText = buildAnnotatedString {
        withStyle(style = SpanStyle(color = White)) {
            append(unclickableText)
        }
        append(" ")
        pushStringAnnotation(tag = "link", annotation = "link")
        withStyle(style = SpanStyle(color = LinkBlue)) {
            append(clickableText)
        }
        pop()
    }
    ClickableText(
        text = annotatedText,
        style = MaterialTheme.typography.bodyMedium,
        onClick = { offset ->
            annotatedText.getStringAnnotations(
                tag = "link",
                start = offset,
                end = offset
            )
                .firstOrNull()?.let {
                    onClick()
                }
        },
        modifier = modifier
    )
}

@Composable
fun ProfileUsername(
    username: String,
    onClick: () -> Unit
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(
            space = LocalDimension.current.extraSmall,
            alignment = Alignment.Start
        ),
        modifier = Modifier
            .wrapContentWidth()
            .fillMaxHeight()
            .clickable(onClick = onClick)
    ) {
        Text(
            text = username,
            style = MaterialTheme.typography.headlineLarge
        )
        ChevronDownIcon(
            color = MaterialTheme.colorScheme.onPrimary,
            modifier = Modifier
                .padding(top = LocalDimension.current.extraSmall)
                .size(LocalDimension.current.mediumSmall)
        )
    }
}

@Composable
fun Likes(
    value: Int,
    onClick: (Int) -> Unit,
    modifier: Modifier = Modifier
) {
    ClickableText(
        text = buildAnnotatedString {
            withStyle(
                style = SpanStyle(
                    fontWeight = FontWeight.SemiBold,
                    color = MaterialTheme.colorScheme.onPrimary
                )
            ) {
                append("$value like")
                if (value > 1) append("s")
            }
        },
        style = MaterialTheme.typography.bodyMedium,
        onClick = onClick,
        modifier = modifier
    )
}

@Composable
fun UsernameAndCaption(
    username: String,
    caption: String,
    onUsernameClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val annotatedText = buildAnnotatedString {
        pushStringAnnotation(tag = "username", annotation = "username")
        withStyle(
            style = SpanStyle(
                fontWeight = FontWeight.Medium,
                color = MaterialTheme.colorScheme.onPrimary
            )
        ) {
            append(username)
        }
        pop()
        append(" ")
        withStyle(
            style = SpanStyle(
                fontWeight = FontWeight.Normal,
                color = MaterialTheme.colorScheme.onPrimary
            )
        ) {
            append(caption)
        }
    }
    ClickableText(
        text = annotatedText,
        style = MaterialTheme.typography.bodyMedium,
        onClick = { offset ->
            annotatedText.getStringAnnotations(
                tag = "username",
                start = offset,
                end = offset
            )
                .firstOrNull()?.let {
                    onUsernameClick()
                }
        },
        modifier = modifier
    )
}

@Composable
fun UsernameAndTimestamp(
    username: String,
    createdAt: Instant,
    isEdited: Boolean,
    onUsernameClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Text(
        text = buildAnnotatedString {
            withStyle(
                style = SpanStyle(
                    color = MaterialTheme.colorScheme.onPrimary,
                    fontWeight = FontWeight.Medium
                )
            ) {
                append(username)
            }
            append(" ")
            withStyle(
                style = SpanStyle(
                    color = MaterialTheme.colorScheme.onSecondary,
                    fontWeight = FontWeight.Medium
                )
            ) {
                append(createdAt.toRelativeTimeString())
                if (isEdited) {
                    append(" • ")
                    append("Edited")
                }
            }
        },
        style = MaterialTheme.typography.bodySmall,
        modifier = modifier
    )
}

@Composable
fun ReplyClickableText(
    onClick: (Int) -> Unit
) {
    ClickableText(
        text = buildAnnotatedString {
            withStyle(
                style = SpanStyle(
                    color = MaterialTheme.colorScheme.onSecondary,
                    fontWeight = FontWeight.Medium
                )
            ) {
                append("Reply")
            }
        },
        onClick = onClick
    )
}