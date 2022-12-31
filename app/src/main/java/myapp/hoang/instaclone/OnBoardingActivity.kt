package myapp.hoang.instaclone

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.datetime.toKotlinLocalDate
import myapp.hoang.core.utils.FileUtils
import myapp.hoang.core_ui.*
import myapp.hoang.instaclone.navigation.Screen
import myapp.hoang.onboarding.login.LoginScreen
import myapp.hoang.onboarding.signup.screens.*
import myapp.hoang.onboarding.signup.viewmodels.OnBoardingViewModel
import java.io.File

@AndroidEntryPoint
class OnBoardingActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        installSplashScreen()
        setContent {
            OnBoardingTheme {
                val navController = rememberNavController()
                val viewModel = hiltViewModel<OnBoardingViewModel>()
                val context = LocalContext.current

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
                                viewModel = viewModel,
                                onBackClick = { navController.navigateUp() },
                                onNextClick = {
                                    viewModel.setMobileNumber(it.toLong())
                                    viewModel.sendConfirmationCode(it)
                                },
                                onSignUpWithEmailClick = {
                                    navController.navigate(Screen.SignupByEmailScreen.route)
                                },
                                onNextScreen = {
                                    navController.navigate(Screen.ConfirmationCodeScreen.withArgs(it))
                                }
                            )
                        }
                        composable(route = Screen.SignupByEmailScreen.route) {
                            SignupByEmailScreen(
                                viewModel = viewModel,
                                onBackClick = { navController.navigateUp() },
                                onNextClick = {
                                    viewModel.setEmail(it)
                                    viewModel.sendConfirmationCode(it)
                                },
                                onSignUpWithMobileNumberClick = { navController.navigateUp() },
                                onNextScreen = {
                                    navController.navigate(Screen.ConfirmationCodeScreen.withArgs(it))
                                }
                            )
                        }
                        composable(
                            route = "${Screen.ConfirmationCodeScreen.route}/{type}",
                            arguments = listOf(
                                navArgument("type") {
                                    type = NavType.StringType
                                    nullable = false
                                }
                            )
                        ) { entry ->
                            val type = entry.arguments?.getString("type")
                            if (type == "phone" || type == "email") {
                                ConfirmationCodeScreen(
                                    viewModel = viewModel,
                                    type = type,
                                    onBackClick = { navController.navigateUp() },
                                    onNextClick = { recipient, code ->
                                        viewModel.checkConfirmationCode(recipient, code)
                                    },
                                    onLoginClick = {
                                        navController.navigateUp()
                                        navController.navigateUp()
                                        if (type == "email") navController.navigateUp()
                                    },
                                    onNextScreen = {
                                        if (type == "phone") viewModel.setIsMobileNumberVerified(true)
                                        else viewModel.setIsEmailVerified(true)
                                        navController.navigate(Screen.FullNameScreen.route)
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
                                onNextClick = {
                                    viewModel.setPassword(it)
                                    navController.navigate(Screen.SaveLoginInfoScreen.route)
                                }
                            )
                        }
                        composable(route = Screen.SaveLoginInfoScreen.route) {
                            SaveLoginInfoScreen(
                                viewModel = viewModel,
                                onBackClick = { navController.navigateUp() },
                                onNextClick = { navController.navigate(Screen.BirthdayScreen.route) }
                            )
                        }
                        composable(route = Screen.BirthdayScreen.route) {
                            BirthdayScreen(
                                onBackClick = { navController.navigateUp() },
                                onNextClick = {
                                    viewModel.setBirthday(it.toKotlinLocalDate())
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
                                viewModel = viewModel,
                                onBackClick = { navController.navigateUp() },
                                onNextClick = { uri ->
                                    uri?.let {
                                        val imageFile = File(FileUtils.getUriFilePath(context, it))
                                        viewModel.uploadProfilePicAndSignUp(imageFile)
                                    }
                                },
                                onNextScreen = {
                                    navController.navigate(Screen.WelcomeScreen.route)
                                }
                            )
                        }
                        composable(route = Screen.WelcomeScreen.route) {
                            WelcomeScreen(viewModel = viewModel)
                        }
                    }
                }
            }
        }
    }
}