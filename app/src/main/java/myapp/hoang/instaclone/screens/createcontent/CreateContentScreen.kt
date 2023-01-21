package myapp.hoang.instaclone.screens.createcontent

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.ExperimentalLifecycleComposeApi
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import de.palm.composestateevents.EventEffect
import myapp.hoang.core.navigation.CreateContentScreen
import myapp.hoang.media.models.SelectMediaMode
import myapp.hoang.media.viewmodels.MediaStoreViewModel

/**
 * New Post flow.
 *
 */
@OptIn(ExperimentalLifecycleComposeApi::class)
@Composable
fun CreateContentScreen(
    onClose: () -> Unit
) {
    val navController = rememberNavController()
    val viewModel = hiltViewModel<MediaStoreViewModel>()

    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    EventEffect(
        event = uiState.nextScreenEvent,
        onConsumed = viewModel::onConsumedNextScreenEvent
    ) {
        onClose()
    }

    NavHost(
        navController = navController,
        startDestination = CreateContentScreen.SelectContentScreen.route
    ) {
        composable(route = CreateContentScreen.SelectContentScreen.route) {
            SelectMediaScreen(
                onClose = onClose,
                onNextScreen = {
                    if (uiState.selectMediaMode == SelectMediaMode.MULTIPLE)
                        navController.navigate(CreateContentScreen.EditImagesScreen.route)
                    else
                        navController.navigate(CreateContentScreen.EditImageScreen.route)
                },
                viewModel = viewModel
            )
        }
        composable(route = CreateContentScreen.EditImagesScreen.route) {
            EditImagesScreen(
                onBack = { navController.navigateUp() },
                onNextScreen = { navController.navigate(CreateContentScreen.WritePostScreen.route) },
                viewModel = viewModel
            )
        }
        composable(route = CreateContentScreen.EditImageScreen.route) {
            EditImageScreen(
                onBack = { navController.navigateUp() },
                onNextScreen = { navController.navigate(CreateContentScreen.WritePostScreen.route) },
                viewModel = viewModel
            )
        }
        composable(route = CreateContentScreen.WritePostScreen.route) {
            WritePostScreen(
                onBack = { navController.navigateUp() },
                onClose = onClose,
                viewModel = viewModel
            )
        }
    }
}