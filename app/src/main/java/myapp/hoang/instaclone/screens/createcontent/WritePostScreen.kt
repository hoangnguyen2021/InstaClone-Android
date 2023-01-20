package myapp.hoang.instaclone.screens.createcontent

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
import myapp.hoang.core_ui.LocalDimension
import myapp.hoang.core_ui.components.BackIconButton
import myapp.hoang.core_ui.components.CheckMarkIconButton
import myapp.hoang.core_ui.components.WritePostCaptionTextField
import myapp.hoang.instaclone.R
import myapp.hoang.media.viewmodels.MediaStoreViewModel

@OptIn(ExperimentalLifecycleComposeApi::class)
@Composable
fun WritePostScreen(
    onBack: () -> Unit,
    onClose: () -> Unit,
    viewModel: MediaStoreViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    var caption by remember { mutableStateOf("") }

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
            Text(
                text = stringResource(R.string.new_post),
                style = MaterialTheme.typography.headlineLarge,
                modifier = Modifier
                    .weight(0.8f)
                    .padding(start = LocalDimension.current.medium)
            )
            CheckMarkIconButton(
                onClick = {
                    viewModel.uploadPostImageAndCreatePost()
                },
                modifier = Modifier.weight(0.1f)
            )
        }
        Column(
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.Start,
            modifier = Modifier
                .fillMaxWidth()
                .weight(0.92f)
        ) {
            WritePostCaptionTextField(
                value = caption,
                onValueChange = { caption = it },
                label = "Write a caption...",
                leadingBitmap = uiState.editedImageBitmap!!
            )
        }
    }
}