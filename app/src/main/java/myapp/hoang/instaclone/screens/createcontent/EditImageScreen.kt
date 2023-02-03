package myapp.hoang.instaclone.screens.createcontent

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.asImageBitmap
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.ExperimentalLifecycleComposeApi
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import myapp.hoang.core_ui.*
import myapp.hoang.core_ui.components.*
import myapp.hoang.core_ui.components.models.EditImageTab
import myapp.hoang.media.components.EditImageButtonCarousel
import myapp.hoang.media.components.FilterPreviews
import myapp.hoang.media.models.SelectMediaMode
import myapp.hoang.media.viewmodels.MediaSharedStorageViewModel

val editImageTabs = listOf(
    EditImageTab(text = "Filter"),
    EditImageTab(text = "Edit")
)

@OptIn(ExperimentalLifecycleComposeApi::class)
@Composable
fun EditImageScreen(
    selectedMediaListIndex: Int,
    onBack: () -> Unit,
    onNextScreen: () -> Unit,
    viewModel: MediaSharedStorageViewModel = hiltViewModel()
) {
    var selectedTabIndex by remember { mutableStateOf(0) }
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    val selectedMedia = uiState.selectedMediaList[selectedMediaListIndex]

    var previewBitmap by remember {
        mutableStateOf(selectedMedia.croppedBitmap)
    }

    LaunchedEffect(key1 = true) {
        previewBitmap?.let { viewModel.getAllImageFilters(it) }
    }

    LaunchedEffect(key1 = uiState.imageFilterList) {
        if (uiState.imageFilterList.isNotEmpty())
            previewBitmap = uiState
                .imageFilterList[selectedMedia.filterIndex]
                .filterPreview
                .asImageBitmap()
    }

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
            when (uiState.selectMediaMode) {
                SelectMediaMode.SINGLE ->
                    BackIconButton(
                        onClick = onBack,
                        modifier = Modifier.weight(0.1f)
                    )
                SelectMediaMode.MULTIPLE ->
                    CloseIconButton(
                        onClick = {
                            viewModel.resetImageFilter(selectedMedia.index)
                            onBack()
                        },
                        modifier = Modifier.weight(0.1f)
                    )
            }
            MagicWandIcon(
                color = MaterialTheme.colorScheme.onPrimary,
                modifier = Modifier.weight(0.8f)
            )
            when (uiState.selectMediaMode) {
                SelectMediaMode.SINGLE ->
                    NextIconButton(
                        onClick = {
                            viewModel.applyImageFilter(
                                selectedMedia.index,
                                previewBitmap
                            )
                            onNextScreen()
                        },
                        modifier = Modifier.weight(0.1f)
                    )
                SelectMediaMode.MULTIPLE ->
                    CheckMarkIconButton(
                        onClick = {
                            viewModel.applyImageFilter(
                                selectedMedia.index,
                                previewBitmap
                            )
                            onBack()
                        },
                        modifier = Modifier.weight(0.1f)
                    )
            }
        }
        if (uiState.isLoading) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(0.92f)
                    .background(MaterialTheme.colorScheme.surfaceVariant)
            )
        } else {
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(0.5f)
            ) {
                if (uiState.selectedMediaList.isEmpty()) {
                    ImagePreviewPlaceholder()
                } else {
                    previewBitmap?.let { bitmap ->
                        ImageEditPreview(
                            bitmap = bitmap,
                            modifier = Modifier
                                .fillMaxSize()
                                .aspectRatio(1f)
                                .clip(RectangleShape)
                        )
                    }
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
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .weight(0.82f)
                        ) {
                            previewBitmap?.let {
                                FilterPreviews(
                                    imageFilterList = uiState.imageFilterList,
                                    focusedImageFilterIndex = selectedMedia.filterIndex,
                                    onFilterSelect = { filterIndex ->
                                        viewModel.selectImageFilter(
                                            selectedMedia.index,
                                            filterIndex
                                        )
                                        previewBitmap = uiState
                                            .imageFilterList[filterIndex]
                                            .filterPreview
                                            .asImageBitmap()
                                    },
                                    modifier = Modifier.fillMaxSize()
                                )
                            }
                        }
                    }
                    1 -> {
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .weight(0.82f)
                        ) {
                            EditImageButtonCarousel()
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
}