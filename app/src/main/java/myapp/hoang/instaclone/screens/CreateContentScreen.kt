package myapp.hoang.instaclone.screens

import android.Manifest
import android.os.Build
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.ExperimentalLifecycleComposeApi
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.rememberMultiplePermissionsState
import myapp.hoang.media.components.MediaGrid
import myapp.hoang.media.viewmodels.MediaStoreViewModel

@OptIn(
    ExperimentalPermissionsApi::class,
    ExperimentalLifecycleComposeApi::class
)
@Composable
fun CreateContentScreen() {
    val viewModel = hiltViewModel<MediaStoreViewModel>()

    val readImagesPermissionState = rememberMultiplePermissionsState(
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

    LaunchedEffect(key1 = true) {
        if (readImagesPermissionState.allPermissionsGranted) {
            viewModel.getAllMedia()
        } else {
            readImagesPermissionState.launchMultiplePermissionRequest()
        }
    }

    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .weight(0.1f)
        ) {

        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .weight(0.5f)
        ) {

        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .weight(0.4f)
        ) {
            MediaGrid(
                mediaList = uiState.mediaList,
                modifier = Modifier.fillMaxSize()
            )
        }
    }
}