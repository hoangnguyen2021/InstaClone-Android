package myapp.hoang.core_ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.text.selection.TextSelectionColors
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color.Companion.Transparent
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import myapp.hoang.core.utils.DateUtils.getDateString
import myapp.hoang.core.utils.DateUtils.parseDateString
import myapp.hoang.core_ui.*
import java.time.LocalDate

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun OnBoardingTextField(
    value: String,
    onValueChange: (String) -> Unit,
    label: String,
    keyboardType: KeyboardType = KeyboardType.Text,
    isError: Boolean = false
) {
    var isFocused by remember { mutableStateOf(false) }

    TextField(
        value = value,
        onValueChange = onValueChange,
        colors = TextFieldDefaults.textFieldColors(
            textColor = MaterialTheme.colorScheme.onPrimaryContainer,
            containerColor = MaterialTheme.colorScheme.primaryContainer,
            cursorColor = White,
            selectionColors = TextSelectionColors(Turquoise, Verdigris),
            errorTrailingIconColor = MaterialTheme.colorScheme.error,
            errorLabelColor = MaterialTheme.colorScheme.onError,
            focusedIndicatorColor = Transparent,
            unfocusedIndicatorColor = Transparent,
            disabledIndicatorColor = Transparent,
            errorIndicatorColor = Transparent
        ),
        modifier = Modifier
            .fillMaxWidth()
            .height(LocalDimension.current.sevenExtraLarge)
            .background(MaterialTheme.colorScheme.primaryContainer)
            .border(
                width = 1.dp,
                color = if (isError) MaterialTheme.colorScheme.error
                else if (isFocused) AliceBlue
                else GrayBlue,
                shape = RoundedCornerShape(LocalDimension.current.extraSmall)
            )
            .padding(vertical = LocalDimension.current.extraSmall)
            .onFocusChanged { focusState ->
                isFocused = focusState.isFocused
            },
        textStyle = MaterialTheme.typography.bodyLarge,
        label = {
            Text(
                text = label,
                color = if (isError) MaterialTheme.colorScheme.error
                else if (isFocused) AliceBlue
                else MaterialTheme.colorScheme.onPrimary
            )
        },
        trailingIcon = {
            if (isError) {
                ErrorIcon(color = MaterialTheme.colorScheme.error)
            } else if (value.isNotEmpty()) {
                CancelIcon(
                    color = AliceBlue,
                    modifier = Modifier
                        .size(LocalDimension.current.medium)
                        .clickable { onValueChange("") }
                )
            } else null
        },
        keyboardOptions = KeyboardOptions(
            keyboardType = keyboardType
        ),
        isError = isError,
        singleLine = true,
        maxLines = 1,
        shape = RoundedCornerShape(LocalDimension.current.extraSmall)
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun OnBoardingPasswordField(
    value: String,
    onValueChange: (String) -> Unit,
    label: String,
    isError: Boolean = false
) {
    var isFocused by remember { mutableStateOf(false) }
    var isPasswordHidden by remember { mutableStateOf(true) }

    TextField(
        value = value,
        onValueChange = onValueChange,
        colors = TextFieldDefaults.textFieldColors(
            textColor = MaterialTheme.colorScheme.onPrimaryContainer,
            containerColor = MaterialTheme.colorScheme.primaryContainer,
            cursorColor = White,
            selectionColors = TextSelectionColors(Turquoise, Verdigris),
            errorTrailingIconColor = MaterialTheme.colorScheme.error,
            errorLabelColor = MaterialTheme.colorScheme.onError,
            focusedIndicatorColor = Transparent,
            unfocusedIndicatorColor = Transparent,
            disabledIndicatorColor = Transparent,
            errorIndicatorColor = Transparent
        ),
        modifier = Modifier
            .fillMaxWidth()
            .height(LocalDimension.current.sevenExtraLarge)
            .background(MaterialTheme.colorScheme.primaryContainer)
            .border(
                width = 1.dp,
                color = if (isError) MaterialTheme.colorScheme.error
                else if (isFocused) AliceBlue
                else GrayBlue,
                shape = RoundedCornerShape(LocalDimension.current.extraSmall)
            )
            .padding(vertical = LocalDimension.current.extraSmall)
            .onFocusChanged { focusState ->
                isFocused = focusState.isFocused
            },
        textStyle = MaterialTheme.typography.bodyLarge,
        label = {
            Text(
                text = label,
                color = if (isError) MaterialTheme.colorScheme.error
                else if (isFocused) AliceBlue
                else MaterialTheme.colorScheme.onPrimary
            )
        },
        trailingIcon = {
            if (!isFocused) null
            else if (isPasswordHidden) {
                PasswordHiddenIcon(
                    color = if (isError) MaterialTheme.colorScheme.error else AliceBlue,
                    modifier = Modifier
                        .size(LocalDimension.current.mediumLarge)
                        .clickable { isPasswordHidden = false }
                )
            } else {
                PasswordShownIcon(
                    color = if (isError) MaterialTheme.colorScheme.error else AliceBlue,
                    modifier = Modifier
                        .size(LocalDimension.current.mediumLarge)
                        .clickable { isPasswordHidden = true }
                )
            }
        },
        visualTransformation = if (isPasswordHidden)
            PasswordVisualTransformation()
        else VisualTransformation.None,
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Password,
        ),
        isError = isError,
        singleLine = true,
        maxLines = 1,
        shape = RoundedCornerShape(LocalDimension.current.extraSmall)
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun OnBoardingBirthdayField(
    value: LocalDate,
    onValueChange: (LocalDate) -> Unit,
    label: String = "Birthday"
) {
    var isDialogShown by remember { mutableStateOf(false) }

    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
    ) {
        TextField(
            value = getDateString(value),
            onValueChange = { onValueChange(parseDateString(it)) },
            label = {
                Text(
                    text = label,
                    color = AliceBlue
                )
            },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Text
            ),
            singleLine = true,
            maxLines = 1,
            enabled = false,
            colors = TextFieldDefaults.textFieldColors(
                textColor = MaterialTheme.colorScheme.onPrimaryContainer,
                disabledTextColor = MaterialTheme.colorScheme.onPrimaryContainer,
                containerColor = MaterialTheme.colorScheme.primaryContainer,
                cursorColor = White,
                selectionColors = TextSelectionColors(Turquoise, Verdigris),
                focusedIndicatorColor = Transparent,
                unfocusedIndicatorColor = Transparent,
                disabledIndicatorColor = Transparent
            ),
            textStyle = MaterialTheme.typography.bodyLarge,
            shape = RoundedCornerShape(LocalDimension.current.extraSmall),
            modifier = Modifier
                .fillMaxWidth()
                .height(LocalDimension.current.sevenExtraLarge)
                .background(MaterialTheme.colorScheme.primaryContainer)
                .border(
                    width = 1.dp,
                    color = AliceBlue,
                    shape = RoundedCornerShape(LocalDimension.current.extraSmall)
                )
                .padding(vertical = LocalDimension.current.extraSmall)
                .clickable { isDialogShown = true }
        )
    }
    if (isDialogShown) {
        DatePickerDialog(
            label = "Set date",
            onDismiss = { isDialogShown = false },
            textFieldValue = value,
            onTextFieldValueChange = onValueChange
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WritePostCaptionTextField(
    value: String,
    onValueChange: (String) -> Unit,
    label: String,
    leadingBitmap: ImageBitmap,
    keyboardType: KeyboardType = KeyboardType.Text
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Start,
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
    ) {
        ImageEditPreview(
            bitmap = leadingBitmap,
            modifier = Modifier
                .size(LocalDimension.current.sixExtraLarge)
                .aspectRatio(1f)
                .clip(RectangleShape)
        )
        // use box to center TextField vertically
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .heightIn(min = LocalDimension.current.tenExtraLarge),
            contentAlignment = Alignment.Center,
        ) {
            TextField(
                value = value,
                onValueChange = onValueChange,
                colors = TextFieldDefaults.textFieldColors(
                    textColor = MaterialTheme.colorScheme.onPrimary,
                    placeholderColor = MaterialTheme.colorScheme.onSurfaceVariant,
                    containerColor = MaterialTheme.colorScheme.primary,
                    cursorColor = Turquoise,
                    selectionColors = TextSelectionColors(Turquoise, Verdigris),
                    focusedIndicatorColor = Transparent,
                    unfocusedIndicatorColor = Transparent,
                    disabledIndicatorColor = Transparent
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight(),
                textStyle = MaterialTheme.typography.bodyMedium,
                placeholder = {
                    Text(
                        text = label,
                        style = MaterialTheme.typography.labelMedium,
                        fontWeight = FontWeight.Normal
                    )
                },
                keyboardOptions = KeyboardOptions(
                    keyboardType = keyboardType
                ),
                shape = RectangleShape
            )
        }
    }
    Divider(
        color = MaterialTheme.colorScheme.outline,
        thickness = 0.3.dp,
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
    )
}