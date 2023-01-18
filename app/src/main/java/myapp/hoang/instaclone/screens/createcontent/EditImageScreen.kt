package myapp.hoang.instaclone.screens.createcontent

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.ExperimentalLifecycleComposeApi
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import myapp.hoang.core_ui.*
import myapp.hoang.core_ui.components.*
import myapp.hoang.core_ui.components.models.EditImageButton
import myapp.hoang.core_ui.components.models.EditImageTab
import myapp.hoang.media.viewmodels.MediaStoreViewModel

val editImageTabs = listOf(
    EditImageTab(text = "Filter"),
    EditImageTab(text = "Edit")
)

val editImageButtons = listOf(
    EditImageButton(
        text = "Adjust",
        icon = {
            AdjustIcon(
                color = MaterialTheme.colorScheme.onPrimary,
                modifier = Modifier.fillMaxSize(0.5f)
            )
        }
    ),
    EditImageButton(
        text = "Brightness",
        icon = {
            BrightnessIcon(
                color = MaterialTheme.colorScheme.onPrimary,
                modifier = Modifier.fillMaxSize(0.5f)
            )
        }
    ),
    EditImageButton(
        text = "Contrast",
        icon = {
            ContrastIcon(
                color = MaterialTheme.colorScheme.onPrimary,
                modifier = Modifier.fillMaxSize(0.5f)
            )
        }
    ),
    EditImageButton(
        text = "Structure",
        icon = {
            StructureIcon(
                color = MaterialTheme.colorScheme.onPrimary,
                modifier = Modifier.fillMaxSize(0.5f)
            )
        }
    ),
    EditImageButton(
        text = "Warmth",
        icon = {
            WarmthIcon(
                color = MaterialTheme.colorScheme.onPrimary,
                modifier = Modifier.fillMaxSize(0.5f)
            )
        }
    ),
    EditImageButton(
        text = "Saturation",
        icon = {
            SaturationIcon(
                color = MaterialTheme.colorScheme.onPrimary,
                modifier = Modifier.fillMaxSize(0.5f)
            )
        }
    ),
    EditImageButton(
        text = "Color",
        icon = {
            ColorIcon(
                color = MaterialTheme.colorScheme.onPrimary,
                modifier = Modifier.fillMaxSize(0.5f)
            )
        }
    ),
    EditImageButton(
        text = "Fade",
        icon = {
            FadeIcon(
                color = MaterialTheme.colorScheme.onPrimary,
                modifier = Modifier.fillMaxSize(0.5f)
            )
        }
    ),
    EditImageButton(
        text = "Highlights",
        icon = {
            HighlightsIcon(
                color = MaterialTheme.colorScheme.onPrimary,
                modifier = Modifier.fillMaxSize(0.5f)
            )
        }
    ),
    EditImageButton(
        text = "Shadows",
        icon = {
            ShadowsIcon(
                color = MaterialTheme.colorScheme.onPrimary,
                modifier = Modifier.fillMaxSize(0.5f)
            )
        }
    ),
    EditImageButton(
        text = "Vignette",
        icon = {
            VignetteIcon(
                color = MaterialTheme.colorScheme.onPrimary,
                modifier = Modifier.fillMaxSize(0.6f)
            )
        }
    ),
    EditImageButton(
        text = "Tilt Shift",
        icon = {
            TiltShiftIcon(
                color = MaterialTheme.colorScheme.onPrimary,
                modifier = Modifier.fillMaxSize(0.5f)
            )
        }
    ),
    EditImageButton(
        text = "Sharpen",
        icon = {
            SharpenIcon(
                color = MaterialTheme.colorScheme.onPrimary,
                modifier = Modifier.fillMaxSize(0.5f)
            )
        }
    )
)

@OptIn(ExperimentalLifecycleComposeApi::class)
@Composable
fun EditImageScreen(
    onBack: () -> Unit,
    onNextScreen: () -> Unit,
    viewModel: MediaStoreViewModel = hiltViewModel()
) {
    var selectedTabIndex by remember { mutableStateOf(0) }
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start,
            modifier = Modifier
                .fillMaxWidth()
                .weight(0.08f)
                .padding(horizontal = LocalDimension.current.small)
        ) {
            BackIconButton(
                onClick = onBack,
                modifier = Modifier.weight(0.1f)
            )
            MagicWandIcon(
                color = MaterialTheme.colorScheme.onPrimary,
                modifier = Modifier.weight(0.8f)
            )
            NextIconButton(
                onClick = {},
                modifier = Modifier.weight(0.1f)
            )
        }
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .fillMaxWidth()
                .weight(0.5f)
        ) {
            if (uiState.croppedImageBitmap == null) {
                ImagePreviewPlaceholder()
            } else {
                ImageEditPreview(
                    bitmap = uiState.croppedImageBitmap!!,
                    modifier = Modifier
                        .fillMaxSize()
                        .aspectRatio(1f)
                        .clip(RectangleShape)
                )
            }
        }
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top,
            modifier = Modifier
                .fillMaxWidth()
                .weight(0.42f)
                .background(MaterialTheme.colorScheme.surfaceVariant)
        ) {
            when (selectedTabIndex) {
                0 -> {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Start,
                        modifier = Modifier
                            .fillMaxWidth()
                            .weight(0.82f)
                    ) {
                        Text("page 1")
                    }
                }
                1 -> {
                    LazyRow(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(LocalDimension.current.small),
                        contentPadding = PaddingValues(
                            horizontal = LocalDimension.current.medium
                        ),
                        modifier = Modifier
                            .fillMaxWidth()
                            .weight(0.82f)
                    ) {
                        items(items = editImageButtons) { item ->
                            Column(
                                horizontalAlignment = Alignment.CenterHorizontally,
                                verticalArrangement = Arrangement
                                    .spacedBy(
                                        space = LocalDimension.current.small,
                                        alignment = Alignment.CenterVertically
                                    ),
                                modifier = Modifier.fillMaxSize()
                            ) {
                                Text(
                                    text = item.text,
                                    style = MaterialTheme.typography.labelSmall
                                )
                                EditImageButton(
                                    onClick = { },
                                    icon = { item.icon() },
                                    modifier = Modifier.size(LocalDimension.current.nineExtraLarge)
                                )
                            }
                        }
                    }
                }
            }

            EditImageTabRow(
                tabs = editImageTabs,
                selectedTabIndex = selectedTabIndex,
                onSwitchTab = { selectedTabIndex = it },
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(0.18f)
                    .offset(y = LocalDimension.current.extraSmall)
            )
        }
    }
}