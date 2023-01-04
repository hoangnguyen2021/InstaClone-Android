package myapp.hoang.core_ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import myapp.hoang.core_ui.*

@Composable
fun NewPostIconButton(
    onClick: () -> Unit
) {
    IconButton(
        onClick = onClick,
        colors = IconButtonDefaults.iconButtonColors(
            contentColor = MaterialTheme.colorScheme.onSurface
        )
    ) {
        NewPostIcon(
            color = MaterialTheme.colorScheme.onSurface
        )
    }
}

@Composable
fun HeartIconButton(
    onClick: () -> Unit
) {
    IconButton(
        onClick = onClick,
        colors = IconButtonDefaults.iconButtonColors(
            contentColor = MaterialTheme.colorScheme.onSurface
        )
    ) {
        HeartIcon(
            color = MaterialTheme.colorScheme.onSurface
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
            contentColor = MaterialTheme.colorScheme.onSurface
        )
    ) {
        MessageIcon(
            color = MaterialTheme.colorScheme.onSurface
        )
    }
}

@Composable
fun MenuIconButton(
    onClick: () -> Unit
) {
    IconButton(
        onClick = onClick,
        colors = IconButtonDefaults.iconButtonColors(
            contentColor = MaterialTheme.colorScheme.onSurface
        )
    ) {
        MenuIcon(
            color = MaterialTheme.colorScheme.onSurface
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
            contentColor = MaterialTheme.colorScheme.onSurface
        )
    ) {
        HomeIcon(
            isSelected = isSelected,
            color = MaterialTheme.colorScheme.onSurface
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
            contentColor = MaterialTheme.colorScheme.onSurface
        )
    ) {
        SearchIcon(
            isSelected = isSelected,
            color = MaterialTheme.colorScheme.onSurface
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
            contentColor = MaterialTheme.colorScheme.onSurface
        )
    ) {
        ReelsIcon(
            isSelected = isSelected,
            color = MaterialTheme.colorScheme.onSurface
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
            contentColor = MaterialTheme.colorScheme.onSurface
        )
    ) {
        ShopIcon(
            isSelected = isSelected,
            color = MaterialTheme.colorScheme.onSurface
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
            modifier = Modifier.size(LocalDimension.current.mediumLarge)
        )
    }
}