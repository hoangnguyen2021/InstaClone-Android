package myapp.hoang.instaclone.screens.createcontent

import android.Manifest
import android.os.Build
import android.util.Log
import androidx.compose.foundation.*
import androidx.compose.foundation.gestures.ScrollableDefaults
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.ExperimentalLifecycleComposeApi
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.rememberMultiplePermissionsState
import de.palm.composestateevents.EventEffect
import myapp.hoang.core_ui.LocalDimension
import myapp.hoang.core_ui.White
import myapp.hoang.core_ui.components.*
import myapp.hoang.instaclone.R
import myapp.hoang.media.components.InstaCloneCropper
import myapp.hoang.media.components.MediaCollectionSelect
import myapp.hoang.media.components.MediaGrid
import myapp.hoang.media.models.SelectMediaMode
import myapp.hoang.media.viewmodels.MediaStoreViewModel

@OptIn(
    ExperimentalPermissionsApi::class,
    ExperimentalLifecycleComposeApi::class,
    ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class
)
@Composable
fun SelectMediaScreen(
    onClose: () -> Unit,
    onNextScreen: () -> Unit,
    viewModel: MediaStoreViewModel = hiltViewModel()
) {
    val readMediaPermissionState = rememberMultiplePermissionsState(
        permissions =
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU)
            listOf(
                Manifest.permission.READ_MEDIA_IMAGES,
                Manifest.permission.READ_MEDIA_VIDEO
            )
        else
            listOf(
                Manifest.permission.READ_EXTERNAL_STORAGE
            ),
        onPermissionsResult = { map ->
            if (map.values.all { it }) {
                viewModel.getAllMedia()
            }
        }
    )

    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    val snackbarHostState = remember { SnackbarHostState() }
    val scrollState = rememberScrollState()
    val overscrollEffect = ScrollableDefaults.overscrollEffect()
    val context = LocalContext.current

    LaunchedEffect(key1 = readMediaPermissionState.allPermissionsGranted) {
        if (readMediaPermissionState.allPermissionsGranted) {
            viewModel.getAllMedia()
        } else {
            readMediaPermissionState.launchMultiplePermissionRequest()
        }
    }

    LaunchedEffect(key1 = uiState.selectMediaMode) {
        // when switch to single mode from multiple mode
        if (uiState.selectMediaMode == SelectMediaMode.SINGLE) {
            // re-select the media with focusedMediaIndex
            viewModel.toggleMediaSelection(uiState.focusedMediaIndex)
        }
    }

    LaunchedEffect(key1 = uiState.selectedMediaList) {
        Log.d("MYTAG", uiState.selectedMediaList.toString())
    }

    EventEffect(
        event = uiState.nextScreenEvent,
        onConsumed = viewModel::onConsumedNextScreenEvent
    ) {
        onNextScreen()
    }

    EventEffect(
        event = uiState.showLimitAlert,
        onConsumed = viewModel::onConsumedShowLimitAlert
    ) {
        snackbarHostState.showSnackbar(context.getString(R.string.select_media_limit_alert))
    }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        snackbarHost = { SnackbarHost(snackbarHostState) },
    ) { it ->
        Column(
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.Start,
            modifier = Modifier
                .fillMaxSize()
                .padding(it)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Start,
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(0.08f)
                    .padding(horizontal = LocalDimension.current.small)
            ) {
                CloseIconButton(
                    onClick = onClose,
                    modifier = Modifier.weight(0.1f)
                )
                Text(
                    text = stringResource(R.string.new_post),
                    style = MaterialTheme.typography.headlineLarge,
                    modifier = Modifier
                        .weight(0.8f)
                        .padding(start = LocalDimension.current.medium)
                )
                NextIconButton(
                    onClick = { viewModel.startCropping() },
                    modifier = Modifier.weight(0.1f)
                )
            }
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(0.5f)
            ) {
                if (uiState.selectedMediaList.isEmpty()) {
                    ImagePreviewPlaceholder()
                } else {
                    Row(
                        horizontalArrangement = Arrangement.spacedBy(LocalDimension.current.large),
                        modifier = Modifier.fillMaxSize()
                            .horizontalScroll(
                                state = scrollState,
                                flingBehavior = ScrollableDefaults.flingBehavior()
                            )
                            .overscroll(overscrollEffect)
                    ) {
                        uiState.selectedMediaList.forEach { selectedMedia ->
                            if (selectedMedia.originalBitmap != null) {
                                InstaCloneCropper(
                                    crop = selectedMedia.crop,
                                    onCropStart = {},
                                    onCropSuccess = { viewModel.finishCropping(selectedMedia.index, it) },
                                    imageBitmap = selectedMedia.originalBitmap!!,
                                    modifier = Modifier
                                        .fillMaxHeight(0.8f)
                                        .aspectRatio(1f)
                                )
                            }
                        }
                    }
                }
            }
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(0.07f)
                    .padding(horizontal = LocalDimension.current.mediumSmall)
            ) {
                MediaCollectionSelect(
                    mediaCollection = stringResource(R.string.gallery),
                    onClick = {}
                )
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(
                        space = LocalDimension.current.small,
                        alignment = Alignment.Start
                    ),
                    modifier = Modifier
                        .wrapContentWidth()
                        .fillMaxHeight()
                ) {
                    MultipleMediaIconButton(
                        checked = uiState.selectMediaMode == SelectMediaMode.MULTIPLE,
                        onCheckedChange = { viewModel.toggleSelectMediaMode() },
                        modifier = Modifier.size(LocalDimension.current.twoExtraLarge)
                    )
                    CameraIconButton(
                        onClick = { },
                        Modifier.size(LocalDimension.current.twoExtraLarge)
                    )
                }
            }
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(0.35f)
            ) {
                MediaGrid(
                    mediaList = uiState.mediaList,
                    selectMediaMode = uiState.selectMediaMode,
                    selectedMediaList = uiState.selectedMediaList,
                    focusedMediaIndex = uiState.focusedMediaIndex,
                    onMediaSelect = { viewModel.toggleMediaSelection(it) },
                    modifier = Modifier.fillMaxSize()
                )
            }
        }
    }
}