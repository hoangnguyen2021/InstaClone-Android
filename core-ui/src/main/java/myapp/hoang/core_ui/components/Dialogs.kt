package myapp.hoang.core_ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import myapp.hoang.core_ui.*

@Composable
fun AlreadyHaveAccountDialog(
    onConfirm: () -> Unit,
    onDismiss: () -> Unit
) {
    AlertDialog(
        onDismissRequest = onDismiss,
        title = {
            Text(
                text = "Already have an account?",
                style = TextStyle(
                    fontFamily = FontFamily.SansSerif,
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp,
                    letterSpacing = 0.sp
                ),
                maxLines = 1
            )
        },
        confirmButton = { ConfirmButton(onConfirm) },
        dismissButton = { DismissButton(onDismiss) },
        shape = RoundedCornerShape(LocalDimension.current.small),
        containerColor = Charcoal,
        titleContentColor = White,
        textContentColor = White
    )
}

@Composable
fun ConfirmButton(onClick: () -> Unit) {
    Text(
        text = "Log in",
        color = White,
        style = TextStyle(
            fontFamily = FontFamily.SansSerif,
            fontWeight = FontWeight.SemiBold,
            fontSize = 15.sp,
            letterSpacing = 0.sp
        ),
        modifier = Modifier
            .padding(LocalDimension.current.twoExtraSmall)
            .clickable(onClick = onClick),
        maxLines = 1
    )
}

@Composable
fun DismissButton(onClick: () -> Unit) {
    Text(
        text = "Continue creating account",
        color = White,
        style = TextStyle(
            fontFamily = FontFamily.SansSerif,
            fontWeight = FontWeight.SemiBold,
            fontSize = 15.sp,
            letterSpacing = 0.sp,
        ),
        modifier = Modifier
            .padding(LocalDimension.current.twoExtraSmall)
            .clickable(onClick = onClick),
        maxLines = 1
    )
}

@Preview
@Composable
fun AlreadyHaveAccountDialogPreview() {
    OnBoardingTheme {
        Surface(
            modifier = Modifier
                .fillMaxSize()
        ) {
            var isShown by remember { mutableStateOf(true) }

            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.SpaceEvenly,
                modifier = Modifier
                    .background(
                        brush = onBoardingBackgroundBrush
                    )
                    .padding(
                        horizontal = LocalDimension.current.small
                    )
            ) {
                if (isShown) {
                    AlreadyHaveAccountDialog(
                        onConfirm = {},
                        onDismiss = { isShown = false }
                    )
                }
            }
        }
    }
}