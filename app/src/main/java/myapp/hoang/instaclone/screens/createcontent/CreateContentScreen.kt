package myapp.hoang.instaclone.screens.createcontent

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import myapp.hoang.core.navigation.CreateContentScreen
import myapp.hoang.media.viewmodels.MediaStoreViewModel

@Composable
fun CreateContentScreen(
    onClose: () -> Unit
) {
    val navController = rememberNavController()
    val viewModel = hiltViewModel<MediaStoreViewModel>()

    NavHost(
        navController = navController,
        startDestination = CreateContentScreen.SelectContentScreen.route
    ) {
        composable(route = CreateContentScreen.SelectContentScreen.route) {
            SelectContentScreen(
                onClose = onClose,
                onNextScreen = { navController.navigate(CreateContentScreen.EditImageScreen.route) },
                viewModel = viewModel
            )
        }
        composable(route = CreateContentScreen.EditImageScreen.route) {
            EditImageScreen(
                onBack = { navController.navigateUp() },
                onNextScreen = { },
                viewModel = viewModel
            )
        }
    }
}