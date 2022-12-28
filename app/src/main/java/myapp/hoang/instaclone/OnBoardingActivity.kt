package myapp.hoang.instaclone

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Surface
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.flowWithLifecycle
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import dagger.hilt.android.AndroidEntryPoint
import myapp.hoang.core_ui.*
import myapp.hoang.instaclone.navigation.Screen
import myapp.hoang.onboarding.login.LoginScreen
import myapp.hoang.onboarding.signup.screens.*
import myapp.hoang.onboarding.signup.viewmodels.OnBoardingViewModel

@AndroidEntryPoint
class OnBoardingActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        installSplashScreen()
        setContent {
            OnBoardingTheme {
                val navController = rememberNavController()
                val viewModel = hiltViewModel<OnBoardingViewModel>()
                val lifecycleOwner = LocalLifecycleOwner.current
                val toastFlow = remember(viewModel.toastEvent, lifecycleOwner) {
                    viewModel.toastEvent.flowWithLifecycle(
                        lifecycleOwner.lifecycle,
                        Lifecycle.State.STARTED
                    )
                }
                val toastState: String? by toastFlow.collectAsState(initial = null)
                if (toastState != null) {
                    Toast.makeText(LocalContext.current, toastState, Toast.LENGTH_SHORT).show()
                }

                Surface(
                    modifier = Modifier.fillMaxSize()
                ) {
                    NavHost(
                        navController = navController,
                        startDestination = Screen.LoginScreen.route
                    ) {
                        composable(route = Screen.LoginScreen.route) {
                            LoginScreen {
                                navController.navigate(Screen.SignupByPhoneScreen.route)
                            }
                        }
                        composable(route = Screen.SignupByPhoneScreen.route) {
                            SignupByPhoneScreen(
                                onBackClick = { navController.navigateUp() },
                                onNextClick = { mobileNumberLong, mobileNumberString ->
                                    viewModel.setMobileNumber(mobileNumberLong)
                                    viewModel.sendVerificationCode(mobileNumberLong.toString())
                                    navController.navigate(
                                        Screen.ConfirmationCodeScreen.withArgs(
                                            mobileNumberString
                                        )
                                    )
                                },
                                onSignUpWithEmailClick = {
                                    navController.navigate(Screen.SignupByEmailScreen.route)
                                }
                            )
                        }
                        composable(route = Screen.SignupByEmailScreen.route) {
                            SignupByEmailScreen(
                                onBackClick = { navController.navigateUp() },
                                onNextClick = {
                                    viewModel.setEmail(it)
                                    navController.navigate(Screen.ConfirmationCodeScreen.withArgs(it))
                                },
                                onSignUpWithMobileNumberClick = { navController.navigateUp() }
                            )
                        }
                        composable(
                            route = "${Screen.ConfirmationCodeScreen.route}/{username}",
                            arguments = listOf(
                                navArgument("username") {
                                    type = NavType.StringType
                                    nullable = false
                                }
                            )
                        ) { entry ->
                            val username = entry.arguments?.getString("username")
                            if (username != null) {
                                ConfirmationCodeScreen(
                                    username = username,
                                    onBackClick = { navController.navigateUp() },
                                    onNextClick = {
                                        viewModel.setIsMobileNumberVerified(true)
                                        navController.navigate(Screen.FullNameScreen.route)
                                    },
                                    onLoginClick = {
                                        navController.navigateUp()
                                        navController.navigateUp()
                                        if (!username.startsWith("+"))
                                            navController.navigateUp()
                                    }
                                )
                            }
                        }
                        composable(route = Screen.FullNameScreen.route) {
                            FullNameScreen(
                                onBackClick = { navController.navigateUp() },
                                onNextClick = {
                                    viewModel.setFullName(it)
                                    navController.navigate(Screen.PasswordScreen.route)
                                }
                            )
                        }
                        composable(route = Screen.PasswordScreen.route) {
                            PasswordScreen(
                                onBackClick = { navController.navigateUp() },
                                onNextClick = { navController.navigate(Screen.SaveLoginInfoScreen.route) }
                            )
                        }
                        composable(route = Screen.SaveLoginInfoScreen.route) {
                            SaveLoginInfoScreen(
                                onBackClick = { navController.navigateUp() },
                                onNextClick = { navController.navigate(Screen.BirthdayScreen.route) }
                            )
                        }
                        composable(route = Screen.BirthdayScreen.route) {
                            BirthdayScreen(
                                onBackClick = { navController.navigateUp() },
                                onNextClick = {
                                    viewModel.setBirthday(it)
                                    navController.navigate(Screen.UsernameScreen.route)
                                }
                            )
                        }
                        composable(route = Screen.UsernameScreen.route) {
                            UsernameScreen(
                                onBackClick = { navController.navigateUp() },
                                onNextClick = {
                                    viewModel.setUsername(it)
                                    navController.navigate(Screen.PolicyScreen.route)
                                }
                            )
                        }
                        composable(route = Screen.PolicyScreen.route) {
                            PolicyScreen(
                                onBackClick = { navController.navigateUp() },
                                onNextClick = {
                                    viewModel.setAgreedToPolicy(true)
                                    navController.navigate(Screen.ProfilePictureScreen.route)
                                }
                            )
                        }
                        composable(route = Screen.ProfilePictureScreen.route) {
                            ProfilePictureScreen(
                                onBackClick = { navController.navigateUp() },
                                onNextClick = {
                                    viewModel.setProfilePicUrl(it)
                                }
                            )
                        }
                    }
                }
            }
        }
    }
}