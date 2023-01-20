package myapp.hoang.instaclone.screens.createcontent

import android.Manifest
import android.os.Build
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.ExperimentalLifecycleComposeApi
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.rememberMultiplePermissionsState
import de.palm.composestateevents.EventEffect
import myapp.hoang.core_ui.LocalDimension
import myapp.hoang.core_ui.components.*
import myapp.hoang.instaclone.R
import myapp.hoang.media.components.InstaCloneCropper
import myapp.hoang.media.components.MediaCollectionSelect
import myapp.hoang.media.components.MediaGrid
import myapp.hoang.media.viewmodels.MediaStoreViewModel

@OptIn(
    ExperimentalPermissionsApi::class,
    ExperimentalLifecycleComposeApi::class
)
@Composable
fun SelectContentScreen(
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

    LaunchedEffect(key1 = readMediaPermissionState.allPermissionsGranted) {
        if (readMediaPermissionState.allPermissionsGranted) {
            viewModel.getAllMedia()
        } else {
            readMediaPermissionState.launchMultiplePermissionRequest()
        }
    }

    EventEffect(
        event = uiState.nextScreenEvent,
        onConsumed = viewModel::onConsumedNextScreenEvent
    ) {
        onNextScreen()
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
            if (uiState.imageBitmap == null) {
                ImagePreviewPlaceholder()
            } else {
                InstaCloneCropper(
                    crop = uiState.crop,
                    onCropStart = {},
                    onCropSuccess = { viewModel.finishCropping(it) },
                    imageBitmap = uiState.imageBitmap!!,
                    modifier = Modifier.fillMaxSize()
                )
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
                    onClick = { },
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
                onMediaClick = { viewModel.getBitmapFromUri(it) },
                modifier = Modifier.fillMaxSize()
            )
        }
    }
}