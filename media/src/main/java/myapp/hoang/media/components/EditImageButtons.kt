package myapp.hoang.media.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import myapp.hoang.core_ui.*
import myapp.hoang.core_ui.components.EditImageButton

val editImageButtons = listOf(
    myapp.hoang.core_ui.components.models.EditImageButton(
        text = "Adjust",
        icon = {
            AdjustIcon(
                color = MaterialTheme.colorScheme.onPrimary,
                modifier = Modifier.fillMaxSize(0.5f)
            )
        }
    ),
    myapp.hoang.core_ui.components.models.EditImageButton(
        text = "Brightness",
        icon = {
            BrightnessIcon(
                color = MaterialTheme.colorScheme.onPrimary,
                modifier = Modifier.fillMaxSize(0.5f)
            )
        }
    ),
    myapp.hoang.core_ui.components.models.EditImageButton(
        text = "Contrast",
        icon = {
            ContrastIcon(
                color = MaterialTheme.colorScheme.onPrimary,
                modifier = Modifier.fillMaxSize(0.5f)
            )
        }
    ),
    myapp.hoang.core_ui.components.models.EditImageButton(
        text = "Structure",
        icon = {
            StructureIcon(
                color = MaterialTheme.colorScheme.onPrimary,
                modifier = Modifier.fillMaxSize(0.5f)
            )
        }
    ),
    myapp.hoang.core_ui.components.models.EditImageButton(
        text = "Warmth",
        icon = {
            WarmthIcon(
                color = MaterialTheme.colorScheme.onPrimary,
                modifier = Modifier.fillMaxSize(0.5f)
            )
        }
    ),
    myapp.hoang.core_ui.components.models.EditImageButton(
        text = "Saturation",
        icon = {
            SaturationIcon(
                color = MaterialTheme.colorScheme.onPrimary,
                modifier = Modifier.fillMaxSize(0.5f)
            )
        }
    ),
    myapp.hoang.core_ui.components.models.EditImageButton(
        text = "Color",
        icon = {
            ColorIcon(
                color = MaterialTheme.colorScheme.onPrimary,
                modifier = Modifier.fillMaxSize(0.5f)
            )
        }
    ),
    myapp.hoang.core_ui.components.models.EditImageButton(
        text = "Fade",
        icon = {
            FadeIcon(
                color = MaterialTheme.colorScheme.onPrimary,
                modifier = Modifier.fillMaxSize(0.5f)
            )
        }
    ),
    myapp.hoang.core_ui.components.models.EditImageButton(
        text = "Highlights",
        icon = {
            HighlightsIcon(
                color = MaterialTheme.colorScheme.onPrimary,
                modifier = Modifier.fillMaxSize(0.5f)
            )
        }
    ),
    myapp.hoang.core_ui.components.models.EditImageButton(
        text = "Shadows",
        icon = {
            ShadowsIcon(
                color = MaterialTheme.colorScheme.onPrimary,
                modifier = Modifier.fillMaxSize(0.5f)
            )
        }
    ),
    myapp.hoang.core_ui.components.models.EditImageButton(
        text = "Vignette",
        icon = {
            VignetteIcon(
                color = MaterialTheme.colorScheme.onPrimary,
                modifier = Modifier.fillMaxSize(0.6f)
            )
        }
    ),
    myapp.hoang.core_ui.components.models.EditImageButton(
        text = "Tilt Shift",
        icon = {
            TiltShiftIcon(
                color = MaterialTheme.colorScheme.onPrimary,
                modifier = Modifier.fillMaxSize(0.5f)
            )
        }
    ),
    myapp.hoang.core_ui.components.models.EditImageButton(
        text = "Sharpen",
        icon = {
            SharpenIcon(
                color = MaterialTheme.colorScheme.onPrimary,
                modifier = Modifier.fillMaxSize(0.5f)
            )
        }
    )
)

@Composable
fun EditImageButtonCarousel() {
    LazyRow(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(LocalDimension.current.small),
        contentPadding = PaddingValues(
            horizontal = LocalDimension.current.medium
        ),
        modifier = Modifier.fillMaxSize()
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