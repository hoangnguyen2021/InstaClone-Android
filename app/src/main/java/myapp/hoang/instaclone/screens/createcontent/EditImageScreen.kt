package myapp.hoang.instaclone.screens.createcontent

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.RectangleShape
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.ExperimentalLifecycleComposeApi
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import myapp.hoang.core_ui.LocalDimension
import myapp.hoang.core_ui.MagicWandIcon
import myapp.hoang.core_ui.components.*
import myapp.hoang.core_ui.components.models.EditImageTab
import myapp.hoang.media.viewmodels.MediaStoreViewModel

val editImageTabs = listOf(
    EditImageTab(text = "Filter"),
    EditImageTab(text = "Edit")
)

@OptIn(ExperimentalLifecycleComposeApi::class)
@Composable
fun EditImageScreen(
    onBack: () -> Unit,
    onNext: () -> Unit,
    viewModel: MediaStoreViewModel = hiltViewModel()
) {
    var selectedTabIndex by remember { mutableStateOf(0) }
    val scope = rememberCoroutineScope()
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
            if (uiState.imageBitmap == null) {
                ImagePlaceholder()
            } else {
                ImageEditPreview(
                    bitmap = uiState.imageBitmap!!,
                    modifier = Modifier
                        .fillMaxSize()
                        .aspectRatio(1f)
                        .clip(RectangleShape)
                )
            }
        }
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Bottom,
            modifier = Modifier
                .fillMaxWidth()
                .weight(0.42f)
                .background(MaterialTheme.colorScheme.surfaceVariant)
        ) {
            when (selectedTabIndex) {
                0 -> {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Start
                    ) {
                        Text("page 1")
                    }
                }
                1 -> {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Start
                    ) {
                        Text("page 2")
                    }
                }
            }

            EditImageTabRow(
                tabs = editImageTabs,
                selectedTabIndex = selectedTabIndex,
                onSwitchTab = { selectedTabIndex = it },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(LocalDimension.current.fiveExtraLarge)
            )
        }
    }
}