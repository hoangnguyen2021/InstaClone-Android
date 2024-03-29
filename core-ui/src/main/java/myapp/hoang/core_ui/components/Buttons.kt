package myapp.hoang.core_ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color.Companion.Transparent
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.font.FontWeight
import myapp.hoang.core_ui.*

@Composable
fun OnBoardingFilledButton(
    text: String,
    onClick: () -> Unit,
    isLoading: Boolean = false,
    isEnabled: Boolean = true
) {
    Button(
        onClick = onClick,
        enabled = isEnabled,
        shape = RoundedCornerShape(LocalDimension.current.extraSmall),
        colors = ButtonDefaults.buttonColors(
            containerColor = MaterialTheme.colorScheme.tertiaryContainer,
            contentColor = MaterialTheme.colorScheme.onTertiaryContainer
        ),
        contentPadding = PaddingValues(
            start = LocalDimension.current.sixExtraLarge,
            top = LocalDimension.current.extraSmall,
            end = LocalDimension.current.sixExtraLarge,
            bottom = LocalDimension.current.extraSmall
        ),
        modifier = Modifier
            .fillMaxWidth()
            .height(LocalDimension.current.sixExtraLarge),
    ) {
        if (isLoading) {
            OnBoardingProgressIndicator()
        } else {
            Text(
                text = text,
                color = MaterialTheme.colorScheme.onTertiaryContainer,
                style = MaterialTheme.typography.labelLarge,
                modifier = Modifier.padding(vertical = LocalDimension.current.zero)
            )
        }
    }
}

@Composable
fun CreateAccountButton(
    text: String,
    onClick: () -> Unit,
    isEnabled: Boolean = true
) {
    OutlinedButton(
        onClick = onClick,
        enabled = isEnabled,
        shape = RoundedCornerShape(LocalDimension.current.extraSmall),
        colors = ButtonDefaults.outlinedButtonColors(),
        border = BorderStroke(
            width = LocalDimension.current.unit,
            color = MaterialTheme.colorScheme.outline,
        ),
        contentPadding = PaddingValues(
            start = LocalDimension.current.sixExtraLarge,
            top = LocalDimension.current.extraSmall,
            end = LocalDimension.current.sixExtraLarge,
            bottom = LocalDimension.current.extraSmall
        ),
        modifier = Modifier
            .fillMaxWidth()
            .height(LocalDimension.current.fourExtraLarge),
    ) {
        Text(
            text = text,
            color = MaterialTheme.colorScheme.onTertiaryContainer,
            style = MaterialTheme.typography.displayMedium,
            modifier = Modifier.padding(vertical = LocalDimension.current.zero)
        )
    }
}

@Composable
fun OnBoardingEditButton(
    text: String,
    onClick: () -> Unit
) {
    OutlinedButton(
        onClick = onClick,
        modifier = Modifier
            .wrapContentWidth()
            .height(LocalDimension.current.twoExtraLarge),
        shape = RoundedCornerShape(LocalDimension.current.extraSmall),
        border = BorderStroke(
            width = LocalDimension.current.unit,
            color = MaterialTheme.colorScheme.outline,
        ),
        contentPadding = PaddingValues(
            horizontal = LocalDimension.current.medium
        )
    ) {
        EditIcon(color = White)
        Spacer(modifier = Modifier.width(LocalDimension.current.small))
        Text(
            text = text,
            color = MaterialTheme.colorScheme.onTertiaryContainer,
            style = MaterialTheme.typography.displayMedium,
            modifier = Modifier.padding(vertical = LocalDimension.current.zero)
        )
    }
}

@Composable
fun OnBoardingOutlinedButton(
    text: String,
    onClick: () -> Unit,
    isEnabled: Boolean = true
) {
    OutlinedButton(
        onClick = onClick,
        modifier = Modifier
            .fillMaxWidth()
            .height(LocalDimension.current.sixExtraLarge),
        enabled = isEnabled,
        shape = RoundedCornerShape(LocalDimension.current.extraSmall),
        colors = ButtonDefaults.outlinedButtonColors(),
        border = BorderStroke(
            width = LocalDimension.current.unit,
            color = MaterialTheme.colorScheme.outline,
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
            color = MaterialTheme.colorScheme.onTertiaryContainer,
            style = MaterialTheme.typography.labelLarge,
            modifier = Modifier.padding(vertical = LocalDimension.current.zero)
        )
    }
}

@Composable
fun BottomSheetTopButton(
    text: String,
    onClick: () -> Unit,
    isEnabled: Boolean = true
) {
    Button(
        onClick = onClick,
        modifier = Modifier
            .fillMaxWidth()
            .height(LocalDimension.current.fiveExtraLarge),
        enabled = isEnabled,
        shape = RoundedCornerShape(
            topStart = LocalDimension.current.extraSmall,
            topEnd = LocalDimension.current.extraSmall
        ),
        colors = ButtonDefaults.buttonColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer,
            contentColor = MaterialTheme.colorScheme.onPrimaryContainer
        ),
        contentPadding = PaddingValues(
            start = LocalDimension.current.medium,
            top = LocalDimension.current.extraSmall,
            end = LocalDimension.current.medium,
            bottom = LocalDimension.current.extraSmall
        )
    ) {
        Text(
            text = text,
            color = MaterialTheme.colorScheme.onPrimaryContainer,
            style = MaterialTheme.typography.labelLarge,
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .padding(vertical = LocalDimension.current.zero)
        )
    }
}

@Composable
fun BottomSheetMiddleButton(
    text: String,
    onClick: () -> Unit,
    isEnabled: Boolean = true
) {
    Button(
        onClick = onClick,
        modifier = Modifier
            .fillMaxWidth()
            .height(LocalDimension.current.fiveExtraLarge),
        enabled = isEnabled,
        shape = RectangleShape,
        colors = ButtonDefaults.buttonColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer,
            contentColor = MaterialTheme.colorScheme.onPrimaryContainer
        ),
        contentPadding = PaddingValues(
            start = LocalDimension.current.medium,
            top = LocalDimension.current.extraSmall,
            end = LocalDimension.current.medium,
            bottom = LocalDimension.current.extraSmall
        )
    ) {
        Text(
            text = text,
            color = MaterialTheme.colorScheme.onPrimaryContainer,
            style = MaterialTheme.typography.labelLarge,
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .padding(vertical = LocalDimension.current.zero)
        )
    }
}

@Composable
fun BottomSheetBottomButton(
    text: String,
    onClick: () -> Unit,
    isEnabled: Boolean = true
) {
    Button(
        onClick = onClick,
        modifier = Modifier
            .fillMaxWidth()
            .height(LocalDimension.current.fiveExtraLarge),
        enabled = isEnabled,
        shape = RoundedCornerShape(
            bottomStart = LocalDimension.current.extraSmall,
            bottomEnd = LocalDimension.current.extraSmall
        ),
        colors = ButtonDefaults.buttonColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer,
            contentColor = MaterialTheme.colorScheme.onPrimaryContainer
        ),
        contentPadding = PaddingValues(
            start = LocalDimension.current.medium,
            top = LocalDimension.current.extraSmall,
            end = LocalDimension.current.medium,
            bottom = LocalDimension.current.extraSmall
        )
    ) {
        Text(
            text = text,
            color = MaterialTheme.colorScheme.onPrimaryContainer,
            style = MaterialTheme.typography.labelLarge,
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .padding(vertical = LocalDimension.current.zero)
        )
    }
}

@Composable
fun PrimaryButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    isEnabled: Boolean = true
) {
    Button(
        onClick = onClick,
        enabled = isEnabled,
        shape = RoundedCornerShape(LocalDimension.current.small),
        colors = ButtonDefaults.buttonColors(
            containerColor = MaterialTheme.colorScheme.primary,
            contentColor = MaterialTheme.colorScheme.onPrimary
        ),
        contentPadding = PaddingValues(
            start = LocalDimension.current.extraLarge,
            top = LocalDimension.current.extraSmall,
            end = LocalDimension.current.extraLarge,
            bottom = LocalDimension.current.extraSmall
        ),
        modifier = modifier
    ) {
        Text(
            text = text,
            color = MaterialTheme.colorScheme.onPrimary,
            modifier = Modifier.padding(vertical = LocalDimension.current.zero)
        )
    }
}

@Composable
fun SecondaryButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    isEnabled: Boolean = true,
) {
    Button(
        onClick = onClick,
        enabled = isEnabled,
        shape = RoundedCornerShape(LocalDimension.current.small),
        colors = ButtonDefaults.buttonColors(
            containerColor = MaterialTheme.colorScheme.secondaryContainer,
            contentColor = MaterialTheme.colorScheme.onSecondaryContainer
        ),
        contentPadding = PaddingValues(
            start = LocalDimension.current.extraLarge,
            top = LocalDimension.current.extraSmall,
            end = LocalDimension.current.extraLarge,
            bottom = LocalDimension.current.extraSmall
        ),
        modifier = modifier
    ) {
        Text(
            text = text,
            color = MaterialTheme.colorScheme.onSecondaryContainer,
            style = MaterialTheme.typography.displayMedium,
            modifier = Modifier.padding(vertical = LocalDimension.current.zero)
        )
    }
}

@Composable
fun TransparentButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    isEnabled: Boolean = false,
) {
    Button(
        onClick = onClick,
        enabled = isEnabled,
        shape = RectangleShape,
        colors = ButtonDefaults.buttonColors(
            containerColor = Transparent,
            contentColor = MaterialTheme.colorScheme.primaryContainer,
            disabledContainerColor = Transparent,
            disabledContentColor = MaterialTheme.colorScheme.primaryContainer
        ),
        contentPadding = PaddingValues(LocalDimension.current.zero),
        modifier = modifier
    ) {
        Text(
            text = text,
            style = MaterialTheme.typography.labelMedium,
            fontWeight = FontWeight.Normal,
            color = MaterialTheme.colorScheme.primaryContainer,
            maxLines = 1,
            modifier = Modifier.wrapContentSize()
        )
    }
}