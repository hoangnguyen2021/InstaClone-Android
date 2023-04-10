package myapp.hoang.instaclone.screens.createcontent

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import myapp.hoang.core.navigation.CreateContentScreen
import myapp.hoang.core.navigation.CreateContentScreen.EditImageScreen.INDEX
import myapp.hoang.media.models.SelectMediaMode
import myapp.hoang.media.viewmodels.MediaSharedStorageViewModel

/**
 * New Post flow.
 *
 */
@Composable
fun CreateContentScreen(
    onClose: () -> Unit
) {
    val navController = rememberNavController()
    val viewModel = hiltViewModel<MediaSharedStorageViewModel>()

    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    NavHost(
        navController = navController,
        startDestination = CreateContentScreen.SelectMediaScreen.route
    ) {
        composable(route = CreateContentScreen.SelectMediaScreen.route) {
            SelectMediaScreen(
                onClose = onClose,
                onNextScreen = {
                    if (uiState.selectMediaMode == SelectMediaMode.MULTIPLE)
                        navController.navigate(CreateContentScreen.EditImagesScreen.route)
                    else
                        navController.navigate(
                            CreateContentScreen.EditImageScreen.withArgs("0")
                        )
                },
                viewModel = viewModel
            )
        }
        composable(route = CreateContentScreen.EditImagesScreen.route) {
            EditImagesScreen(
                onBack = { navController.navigateUp() },
                onImageClick = { index ->
                    navController.navigate(
                        CreateContentScreen.EditImageScreen.withArgs(index.toString())
                    )
                },
                onNextScreen = { navController.navigate(CreateContentScreen.WritePostScreen.route) },
                viewModel = viewModel
            )
        }
        composable(
            route = "${CreateContentScreen.EditImageScreen.route}/{$INDEX}",
            arguments = listOf(
                navArgument(INDEX) {
                    type = NavType.IntType
                    defaultValue = 0
                    nullable = false
                }
            )
        ) { entry ->
            val selectedMediaListIndex = entry.arguments?.getInt(INDEX, 0) ?: 0
            EditImageScreen(
                selectedMediaListIndex = selectedMediaListIndex,
                onBack = { navController.navigateUp() },
                onNextScreen = { navController.navigate(CreateContentScreen.WritePostScreen.route) },
                viewModel = viewModel
            )
        }
        composable(route = CreateContentScreen.WritePostScreen.route) {
            WritePostScreen(
                onBack = { navController.navigateUp() },
                onClose = {
                    navController.navigate(CreateContentScreen.SelectMediaScreen.route) {
                        popUpTo(navController.graph.id) {
                            inclusive = true
                        }
                    }
                    onClose()
                },
                viewModel = viewModel
            )
        }
    }
}