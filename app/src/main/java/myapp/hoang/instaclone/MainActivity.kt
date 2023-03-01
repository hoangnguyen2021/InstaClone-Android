package myapp.hoang.instaclone

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Surface
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.ExperimentalLifecycleComposeApi
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import myapp.hoang.core_ui.InstaCloneTheme
import myapp.hoang.instaclone.features.users.viewmodels.UserPreferencesViewModel
import myapp.hoang.instaclone.screens.*
import myapp.hoang.instaclone.screens.createcontent.CreateContentScreen

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val userPreferencesViewModel by viewModels<UserPreferencesViewModel>()

    @OptIn(
        ExperimentalPagerApi::class,
        ExperimentalLifecycleComposeApi::class
    )
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        userPreferencesViewModel.getUserPreferences()
        setContent {
            InstaCloneTheme {
                Surface(
                    modifier = Modifier.fillMaxSize()
                ) {
                    val userPreferencesUiState by userPreferencesViewModel.uiState.collectAsStateWithLifecycle()
                    val pagerState = rememberPagerState(
                        initialPage = 1
                    )
                    val scope = rememberCoroutineScope()

                    HorizontalPager(
                        count = 2,
                        state = pagerState,
                        modifier = Modifier.fillMaxSize()
                    ) { page ->
                        when (page) {
                            0 -> CreateContentScreen(
                                onClose = { scope.launch { pagerState.animateScrollToPage(1) } }
                            )
                            1 -> MainScreen(
                                userPreferences = userPreferencesUiState.userPreferences
                            )
                        }
                    }
                }
            }
        }
    }
}