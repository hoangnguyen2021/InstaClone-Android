package myapp.hoang.core_ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color.Companion.Transparent
import androidx.compose.ui.text.font.FontWeight
import myapp.hoang.core_ui.*

@Composable
fun NewPostIconButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    IconButton(
        onClick = onClick,
        colors = IconButtonDefaults.iconButtonColors(
            containerColor = Transparent,
            contentColor = MaterialTheme.colorScheme.onPrimary
        ),
        modifier = modifier
    ) {
        NewPostIcon(
            color = MaterialTheme.colorScheme.onPrimary
        )
    }
}

@Composable
fun HeartIconButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    IconButton(
        onClick = onClick,
        colors = IconButtonDefaults.iconButtonColors(
            containerColor = Transparent,
            contentColor = MaterialTheme.colorScheme.onPrimary
        ),
        modifier = modifier
    ) {
        HeartIcon(
            color = MaterialTheme.colorScheme.onPrimary
        )
    }
}

@Composable
fun MessageIconButton(
    onClick: () -> Unit
) {
    IconButton(
        onClick = onClick,
        colors = IconButtonDefaults.iconButtonColors(
            containerColor = Transparent,
            contentColor = MaterialTheme.colorScheme.onPrimary
        )
    ) {
        MessageIcon(
            color = MaterialTheme.colorScheme.onPrimary
        )
    }
}

@Composable
fun MenuIconButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    IconButton(
        onClick = onClick,
        colors = IconButtonDefaults.iconButtonColors(
            containerColor = Transparent,
            contentColor = MaterialTheme.colorScheme.onPrimary
        ),
        modifier = modifier
    ) {
        MenuIcon(
            color = MaterialTheme.colorScheme.onPrimary
        )
    }
}

@Composable
fun HomeIconButton(
    isSelected: Boolean,
    onClick: () -> Unit
) {
    IconButton(
        onClick = onClick,
        colors = IconButtonDefaults.iconButtonColors(
            containerColor = Transparent,
            contentColor = MaterialTheme.colorScheme.onPrimary
        )
    ) {
        HomeIcon(
            isSelected = isSelected,
            color = MaterialTheme.colorScheme.onPrimary
        )
    }
}

@Composable
fun SearchIconButton(
    isSelected: Boolean,
    onClick: () -> Unit
) {
    IconButton(
        onClick = onClick,
        colors = IconButtonDefaults.iconButtonColors(
            containerColor = Transparent,
            contentColor = MaterialTheme.colorScheme.onPrimary
        )
    ) {
        SearchIcon(
            isSelected = isSelected,
            color = MaterialTheme.colorScheme.onPrimary
        )
    }
}

@Composable
fun ReelsIconButton(
    isSelected: Boolean,
    onClick: () -> Unit
) {
    IconButton(
        onClick = onClick,
        colors = IconButtonDefaults.iconButtonColors(
            containerColor = Transparent,
            contentColor = MaterialTheme.colorScheme.onPrimary
        )
    ) {
        ReelsIcon(
            isSelected = isSelected,
            color = MaterialTheme.colorScheme.onPrimary
        )
    }
}

@Composable
fun ShopIconButton(
    isSelected: Boolean,
    onClick: () -> Unit
) {
    IconButton(
        onClick = onClick,
        colors = IconButtonDefaults.iconButtonColors(
            containerColor = Transparent,
            contentColor = MaterialTheme.colorScheme.onPrimary
        )
    ) {
        ShopIcon(
            isSelected = isSelected,
            color = MaterialTheme.colorScheme.onPrimary
        )
    }
}

@Composable
fun AddStoryIconButton(
    onClick: () -> Unit
) {
    OutlinedIconButton(
        onClick = onClick,
        colors = IconButtonDefaults.iconButtonColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer,
            contentColor = MaterialTheme.colorScheme.onPrimaryContainer
        ),
        shape = CircleShape,
        border = BorderStroke(
            width = LocalDimension.current.twoExtraSmall,
            color = MaterialTheme.colorScheme.primary
        ),
        modifier = Modifier.fillMaxSize()
    ) {
        PlusIcon(
            color = MaterialTheme.colorScheme.onPrimaryContainer,
            modifier = Modifier.size(LocalDimension.current.small)
        )
    }
}

@Composable
fun ToggleDiscoverPeopleIconButton(
    checked: Boolean,
    onCheckedChange: (Boolean) -> Unit,
    modifier: Modifier = Modifier
) {
    FilledIconToggleButton(
        checked = checked,
        onCheckedChange = onCheckedChange,
        colors = IconButtonDefaults.filledIconToggleButtonColors(
            containerColor = MaterialTheme.colorScheme.secondaryContainer,
            contentColor = MaterialTheme.colorScheme.onSecondaryContainer,
            checkedContainerColor = MaterialTheme.colorScheme.secondaryContainer,
            checkedContentColor = MaterialTheme.colorScheme.onSecondaryContainer
        ),
        shape = RoundedCornerShape(LocalDimension.current.small),
        modifier = modifier
    ) {
        DiscoverPeopleIcon(
            isSelected = checked,
            color = MaterialTheme.colorScheme.onSecondaryContainer,
            modifier = Modifier.size(LocalDimension.current.medium)
        )
    }
}

@Composable
fun CloseIconButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    IconButton(
        onClick = onClick,
        colors = IconButtonDefaults.iconButtonColors(
            containerColor = Transparent,
            contentColor = MaterialTheme.colorScheme.onPrimary
        ),
        modifier = modifier
    ) {
        CloseIcon(
            color = MaterialTheme.colorScheme.onPrimary
        )
    }
}

@Composable
fun BackIconButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    IconButton(
        onClick = onClick,
        colors = IconButtonDefaults.iconButtonColors(
            containerColor = Transparent,
            contentColor = MaterialTheme.colorScheme.onPrimary
        ),
        modifier = modifier
    ) {
        BackIcon2(
            color = MaterialTheme.colorScheme.onPrimary,
            modifier = Modifier.size(LocalDimension.current.large)
        )
    }
}

@Composable
fun NextIconButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    IconButton(
        onClick = onClick,
        colors = IconButtonDefaults.iconButtonColors(
            containerColor = Transparent,
            contentColor = MaterialTheme.colorScheme.primaryContainer
        ),
        modifier = modifier
    ) {
        NextIcon(
            color = MaterialTheme.colorScheme.primaryContainer,
            modifier = Modifier.size(LocalDimension.current.large)
        )
    }
}

@Composable
fun CheckMarkIconButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    IconButton(
        onClick = onClick,
        colors = IconButtonDefaults.iconButtonColors(
            containerColor = Transparent,
            contentColor = MaterialTheme.colorScheme.primaryContainer
        ),
        modifier = modifier
    ) {
        CheckMarkIcon(
            color = MaterialTheme.colorScheme.primaryContainer,
            modifier = Modifier.size(LocalDimension.current.extraLarge)
        )
    }
}

@Composable
fun MultipleMediaIconButton(
    checked: Boolean,
    onCheckedChange: (Boolean) -> Unit,
    modifier: Modifier = Modifier
) {
    FilledIconToggleButton(
        checked = checked,
        onCheckedChange = onCheckedChange,
        colors = IconButtonDefaults.filledIconToggleButtonColors(
            containerColor = MaterialTheme.colorScheme.tertiaryContainer,
            contentColor = MaterialTheme.colorScheme.onTertiaryContainer,
            checkedContainerColor = MaterialTheme.colorScheme.primaryContainer,
            checkedContentColor = MaterialTheme.colorScheme.onPrimaryContainer
        ),
        shape = CircleShape,
        modifier = modifier
    ) {
        MultipleIcon(
            color = MaterialTheme.colorScheme.onTertiaryContainer,
            modifier = Modifier.size(LocalDimension.current.mediumLarge)
        )
    }
}

@Composable
fun CameraIconButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    FilledIconButton(
        onClick = onClick,
        colors = IconButtonDefaults.iconButtonColors(
            containerColor = MaterialTheme.colorScheme.tertiaryContainer,
            contentColor = MaterialTheme.colorScheme.onTertiaryContainer
        ),
        shape = CircleShape,
        modifier = modifier
    ) {
        CameraIcon(
            color = MaterialTheme.colorScheme.onTertiaryContainer,
            modifier = Modifier.size(LocalDimension.current.mediumLarge)
        )
    }
}

@Composable
fun EditImageButton(
    onClick: () -> Unit,
    icon: @Composable () -> Unit,
    modifier: Modifier
) {
    OutlinedIconButton(
        onClick = onClick,
        colors = IconButtonDefaults.iconButtonColors(
            containerColor = Transparent,
            contentColor = MaterialTheme.colorScheme.onPrimary
        ),
        shape = CircleShape,
        border = BorderStroke(
            width = LocalDimension.current.half,
            color = MaterialTheme.colorScheme.onPrimary
        ),
        modifier = modifier
    ) {
        icon()
    }
}

@Composable
fun MoreIconButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    IconButton(
        onClick = onClick,
        colors = IconButtonDefaults.iconButtonColors(
            containerColor = Transparent,
            contentColor = MaterialTheme.colorScheme.onPrimary
        ),
        modifier = modifier
    ) {
        MoreIcon(
            color = MaterialTheme.colorScheme.onPrimary
        )
    }
}

@Composable
fun LikeIconButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    IconButton(
        onClick = onClick,
        colors = IconButtonDefaults.iconButtonColors(
            containerColor = Transparent,
            contentColor = MaterialTheme.colorScheme.onPrimary
        ),
        modifier = modifier
    ) {
        LikeIcon(
            color = MaterialTheme.colorScheme.onPrimary
        )
    }
}

@Composable
fun UnlikeIconButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    IconButton(
        onClick = onClick,
        colors = IconButtonDefaults.iconButtonColors(
            containerColor = Transparent,
            contentColor = HeartColor
        ),
        modifier = modifier
    ) {
        UnlikeIcon()
    }
}

@Composable
fun CommentIconButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    IconButton(
        onClick = onClick,
        colors = IconButtonDefaults.iconButtonColors(
            containerColor = Transparent,
            contentColor = MaterialTheme.colorScheme.onPrimary
        ),
        modifier = modifier
    ) {
        CommentIcon(
            color = MaterialTheme.colorScheme.onPrimary
        )
    }
}

@Composable
fun SendIconButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    IconButton(
        onClick = onClick,
        colors = IconButtonDefaults.iconButtonColors(
            containerColor = Transparent,
            contentColor = MaterialTheme.colorScheme.onPrimary
        ),
        modifier = modifier
    ) {
        SendIcon(
            color = MaterialTheme.colorScheme.onPrimary
        )
    }
}

@Composable
fun SaveIconButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    IconButton(
        onClick = onClick,
        colors = IconButtonDefaults.iconButtonColors(
            containerColor = Transparent,
            contentColor = MaterialTheme.colorScheme.onPrimary
        ),
        modifier = modifier
    ) {
        SaveIcon(
            color = MaterialTheme.colorScheme.onPrimary
        )
    }
}

@Composable
fun LikeIconButtonWithNumber(
    isLiked: Boolean,
    number: Int,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    IconButton(
        onClick = onClick,
        colors = IconButtonDefaults.iconButtonColors(
            containerColor = Transparent
        ),
        modifier = modifier
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(LocalDimension.current.twoExtraSmall),
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
        ) {
            if (isLiked) {
                UnlikeIcon(
                    modifier = Modifier.size(LocalDimension.current.medium)
                )
            } else {
                LikeIcon(
                    color = MaterialTheme.colorScheme.onSecondary,
                    modifier = Modifier.size(LocalDimension.current.medium)
                )
            }
            Text(
                text = number.toString(),
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSecondary,
                fontWeight = FontWeight.Medium
            )
        }
    }
}