package myapp.hoang.instaclone.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.ExperimentalLifecycleComposeApi
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import myapp.hoang.core_ui.LocalDimension
import myapp.hoang.core_ui.MagicWandIcon
import myapp.hoang.core_ui.components.*
import myapp.hoang.media.viewmodels.MediaStoreViewModel

@OptIn(ExperimentalLifecycleComposeApi::class)
@Composable
fun EditImageScreen(
    onBack: () -> Unit,
    onNext: () -> Unit
) {
    val viewModel = hiltViewModel<MediaStoreViewModel>()

    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .fillMaxWidth()
                .weight(0.08f)
                .padding(horizontal = LocalDimension.current.small)
        ) {
            BackIconButton(
                onClick = {},
                modifier = Modifier.weight(0.1f)
            )
            MagicWandIcon(
                color = MaterialTheme.colorScheme.onPrimary
            )
            NextIconButton(
                onClick = {},
                modifier = Modifier.weight(0.1f)
            )
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
                .weight(0.35f)
        ) {

        }
    }
}