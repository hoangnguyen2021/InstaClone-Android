package myapp.hoang.instaclone

import android.content.Intent
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
import myapp.hoang.core.navigation.OnBoardingScreen
import myapp.hoang.onboarding.login.screens.LoginScreen
import myapp.hoang.onboarding.signup.screens.*
import myapp.hoang.onboarding.signup.viewmodels.SignupViewModel
import java.io.File

@AndroidEntryPoint
class OnBoardingActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        installSplashScreen()
        setContent {
            OnBoardingTheme {
                val navController = rememberNavController()
                val viewModel = hiltViewModel<SignupViewModel>()
                val context = LocalContext.current

                Surface(
                    modifier = Modifier.fillMaxSize()
                ) {
                    NavHost(
                        navController = navController,
                        startDestination = OnBoardingScreen.LoginScreen.route
                    ) {
                        composable(route = OnBoardingScreen.LoginScreen.route) {
                            LoginScreen (
                                onCreateAccount = {
                                    navController.navigate(OnBoardingScreen.SignupByPhoneScreen.route)
                                },
                                onLogin = {
                                    startActivity(
                                        Intent(
                                            this@OnBoardingActivity,
                                            MainActivity::class.java
                                        )
                                    )
                                }
                            )
                        }
                        composable(route = OnBoardingScreen.SignupByPhoneScreen.route) {
                            SignupByPhoneScreen(
                                viewModel = viewModel,
                                onBackClick = { navController.navigateUp() },
                                onNextClick = {
                                    viewModel.setMobileNumber(it.toLong())
                                    viewModel.sendConfirmationCode(it)
                                },
                                onSignUpWithEmailClick = {
                                    navController.navigate(OnBoardingScreen.SignupByEmailScreen.route)
                                },
                                onNextScreen = {
                                    navController.navigate(OnBoardingScreen.ConfirmationCodeScreen.withArgs(it))
                                }
                            )
                        }
                        composable(route = OnBoardingScreen.SignupByEmailScreen.route) {
                            SignupByEmailScreen(
                                viewModel = viewModel,
                                onBackClick = { navController.navigateUp() },
                                onNextClick = {
                                    viewModel.setEmail(it)
                                    viewModel.sendConfirmationCode(it)
                                },
                                onSignUpWithMobileNumberClick = { navController.navigateUp() },
                                onNextScreen = {
                                    navController.navigate(OnBoardingScreen.ConfirmationCodeScreen.withArgs(it))
                                }
                            )
                        }
                        composable(
                            route = "${OnBoardingScreen.ConfirmationCodeScreen.route}/{type}",
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
                                        navController.navigate(OnBoardingScreen.FullNameScreen.route)
                                    }
                                )
                            }
                        }
                        composable(route = OnBoardingScreen.FullNameScreen.route) {
                            FullNameScreen(
                                onBackClick = { navController.navigateUp() },
                                onNextClick = {
                                    viewModel.setFullName(it)
                                    navController.navigate(OnBoardingScreen.PasswordScreen.route)
                                }
                            )
                        }
                        composable(route = OnBoardingScreen.PasswordScreen.route) {
                            PasswordScreen(
                                onBackClick = { navController.navigateUp() },
                                onNextClick = {
                                    viewModel.setPassword(it)
                                    navController.navigate(OnBoardingScreen.SaveLoginInfoScreen.route)
                                }
                            )
                        }
                        composable(route = OnBoardingScreen.SaveLoginInfoScreen.route) {
                            SaveLoginInfoScreen(
                                viewModel = viewModel,
                                onBackClick = { navController.navigateUp() },
                                onNextClick = { navController.navigate(OnBoardingScreen.BirthdayScreen.route) }
                            )
                        }
                        composable(route = OnBoardingScreen.BirthdayScreen.route) {
                            BirthdayScreen(
                                onBackClick = { navController.navigateUp() },
                                onNextClick = {
                                    viewModel.setBirthday(it.toKotlinLocalDate())
                                    navController.navigate(OnBoardingScreen.UsernameScreen.route)
                                }
                            )
                        }
                        composable(route = OnBoardingScreen.UsernameScreen.route) {
                            UsernameScreen(
                                onBackClick = { navController.navigateUp() },
                                onNextClick = {
                                    viewModel.setUsername(it)
                                    navController.navigate(OnBoardingScreen.PolicyScreen.route)
                                }
                            )
                        }
                        composable(route = OnBoardingScreen.PolicyScreen.route) {
                            PolicyScreen(
                                onBackClick = { navController.navigateUp() },
                                onNextClick = {
                                    viewModel.setAgreedToPolicy(true)
                                    navController.navigate(OnBoardingScreen.ProfilePictureScreen.route)
                                }
                            )
                        }
                        composable(route = OnBoardingScreen.ProfilePictureScreen.route) {
                            ProfilePictureScreen(
                                viewModel = viewModel,
                                onBackClick = { navController.navigateUp() },
                                onNextClick = { uri ->
                                    if (uri != null) {
                                        val imageFile = File(FileUtils.getUriFilePath(context, uri))
                                        viewModel.uploadProfilePicAndSignUp(imageFile)
                                    } else {
                                        viewModel.signUp()
                                    }
                                },
                                onNextScreen = {
                                    navController.navigate(OnBoardingScreen.WelcomeScreen.route)
                                }
                            )
                        }
                        composable(route = OnBoardingScreen.WelcomeScreen.route) {
                            WelcomeScreen(viewModel = viewModel)
                        }
                    }
                }
            }
        }
//        startActivity(
//            Intent(
//                this@OnBoardingActivity,
//                MainActivity::class.java
//            )
//        )
    }
}