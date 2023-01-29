package myapp.hoang.instaclone.screens.createcontent

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.ExperimentalLifecycleComposeApi
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import myapp.hoang.core_ui.LocalDimension
import myapp.hoang.core_ui.components.*
import myapp.hoang.media.viewmodels.MediaSharedStorageViewModel

@OptIn(ExperimentalLifecycleComposeApi::class)
@Composable
fun EditImagesScreen(
    onBack: () -> Unit,
    onImageClick: (Int) -> Unit,
    onNextScreen: () -> Unit,
    viewModel: MediaSharedStorageViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    Column(
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.Start,
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
            Spacer(modifier = Modifier.weight(0.8f))
            NextIconButton(
                onClick = onNextScreen,
                modifier = Modifier.weight(0.1f)
            )
        }
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .fillMaxWidth()
                .weight(0.4f)
                .background(MaterialTheme.colorScheme.surfaceVariant)
        ) {
            ImagesEditPreview(
                bitmaps = uiState.selectedMediaList.mapNotNull { it.filteredBitmap },
                onImageClick = onImageClick,
                modifier = Modifier.fillMaxSize()
            )
        }
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .fillMaxWidth()
                .weight(0.52f)
                .background(MaterialTheme.colorScheme.surfaceVariant)
        ) {

        }
    }
}