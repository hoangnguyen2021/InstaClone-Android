package myapp.hoang.core_ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import myapp.hoang.core_ui.*

@Composable
fun PrimaryButton(
    text: String,
    onClick: () -> Unit,
    isEnabled: Boolean = true
) {
    Button(
        onClick = onClick,
        modifier = Modifier.heightIn(min = LocalDimension.current.threeExtraLarge),
        enabled = isEnabled,
        shape = RoundedCornerShape(10.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = MaterialTheme.colorScheme.primary,
            contentColor = MaterialTheme.colorScheme.onPrimary
        ),
        contentPadding = PaddingValues(
            start = LocalDimension.current.sixExtraLarge,
            top = LocalDimension.current.extraSmall,
            end = LocalDimension.current.sixExtraLarge,
            bottom = LocalDimension.current.extraSmall
        )
    ) {
        Text(
            text = text,
            color = MaterialTheme.colorScheme.onPrimary,
            modifier = Modifier.padding(vertical = 0.dp)
        )
    }
}

@Preview
@Composable
fun PrimaryButtonPreview() {
    InstaCloneTheme {
        Surface(modifier = Modifier.fillMaxWidth()) {
            Row(horizontalArrangement = Arrangement.Center) {
                PrimaryButton("Follow", {})
            }
        }
    }
}

@Composable
fun SecondaryButton(
    text: String,
    onClick: () -> Unit,
    isEnabled: Boolean = true
) {
    Button(
        onClick = onClick,
        modifier = Modifier
            .heightIn(min = LocalDimension.current.threeExtraLarge),
        enabled = isEnabled,
        shape = RoundedCornerShape(10.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = MaterialTheme.colorScheme.secondary,
            contentColor = MaterialTheme.colorScheme.onSecondary
        ),
        contentPadding = PaddingValues(
            start = LocalDimension.current.sixExtraLarge,
            top = LocalDimension.current.extraSmall,
            end = LocalDimension.current.sixExtraLarge,
            bottom = LocalDimension.current.extraSmall
        )
    ) {
        Text(
            text = text,
            color = MaterialTheme.colorScheme.onSecondary,
            modifier = Modifier.padding(vertical = 0.dp)
        )
    }
}

@Preview
@Composable
fun SecondaryButtonPreview() {
    InstaCloneTheme {
        Surface(modifier = Modifier.fillMaxWidth()) {
            Row(horizontalArrangement = Arrangement.Center) {
                SecondaryButton("Message", {})
            }
        }
    }
}

@Preview
@Composable
fun AddPostButtonPreview() {
    InstaCloneTheme {
        Surface(modifier = Modifier.fillMaxWidth()) {
            Row(horizontalArrangement = Arrangement.Center) {
                IconButton({}) {
                    NewPostIcon()
                }
            }
        }
    }
}

@Preview
@Composable
fun NotificationButtonPreview() {
    InstaCloneTheme {
        Surface(modifier = Modifier.fillMaxWidth()) {
            Row(horizontalArrangement = Arrangement.Center) {
                IconButton({}) {
                    HeartIcon()
                }
            }
        }
    }
}

@Preview
@Composable
fun MessageButtonPreview() {
    InstaCloneTheme {
        Surface(modifier = Modifier.fillMaxWidth()) {
            Row(horizontalArrangement = Arrangement.Center) {
                IconButton({}) {
                    MessageIcon()
                }
            }
        }
    }
}

@Preview
@Composable
fun HomeButtonPreview() {
    InstaCloneTheme {
        Surface(modifier = Modifier.fillMaxWidth()) {
            Row(horizontalArrangement = Arrangement.Center) {
                IconButton({}) {
                    HomeUnselectedIcon()
                }
                IconButton({}) {
                    HomeSelectedIcon()
                }
            }
        }
    }
}