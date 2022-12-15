package myapp.hoang.core_ui

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

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
        Surface(modifier = Modifier.fillMaxSize()) {
            Row {
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
        Surface(modifier = Modifier.fillMaxSize()) {
            Row {
                SecondaryButton("Message", {})
            }
        }
    }
}