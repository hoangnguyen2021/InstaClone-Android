package myapp.hoang.onboarding.signup.screens

import android.Manifest
import android.annotation.SuppressLint
import android.net.Uri
import android.os.Build
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.ExperimentalLifecycleComposeApi
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.canhub.cropper.CropImageContract
import com.canhub.cropper.CropImageContractOptions
import com.canhub.cropper.CropImageOptions
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.isGranted
import com.google.accompanist.permissions.rememberPermissionState
import de.palm.composestateevents.EventEffect
import kotlinx.coroutines.launch
import myapp.hoang.core.utils.FileUtils
import myapp.hoang.core_ui.*
import myapp.hoang.core_ui.components.*
import myapp.hoang.core_ui.components.bottomsheet.BottomDrawer
import myapp.hoang.core_ui.components.bottomsheet.BottomDrawerValue
import myapp.hoang.core_ui.components.bottomsheet.rememberBottomDrawerState
import myapp.hoang.onboarding.R
import myapp.hoang.onboarding.signup.viewmodels.SignupViewModel

@OptIn(
    ExperimentalMaterial3Api::class,
    ExperimentalPermissionsApi::class,
    ExperimentalLifecycleComposeApi::class
)
@Composable
fun ProfilePictureScreen(
    viewModel: SignupViewModel = hiltViewModel(),
    onBackClick: () -> Unit,
    onNextClick: (Uri?) -> Unit,
    onNextScreen: () -> Unit
) {
    val context = LocalContext.current
    val drawerState = rememberBottomDrawerState(BottomDrawerValue.Closed)
    val scope = rememberCoroutineScope()

    var imageUri by remember { mutableStateOf<Uri?>(null) }
    var currentTmpUri by remember { mutableStateOf<Uri?>(null) }

    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    val pickImageLauncher =
        rememberLauncherForActivityResult(
            contract = ActivityResultContracts.PickVisualMedia(),
            onResult = { uri -> uri?.let { imageUri = it } }
        )
    val takePictureLauncher =
        rememberLauncherForActivityResult(
            contract = ActivityResultContracts.TakePicture(),
            onResult = { isSuccessful -> if (isSuccessful) imageUri = currentTmpUri }
        )
    val cropImageLauncher =
        rememberLauncherForActivityResult(
            contract = CropImageContract(),
            onResult = { cropResult ->
                if (cropResult.isSuccessful) imageUri = cropResult.uriContent
            }
        )

    val readImagesPermissionState = rememberPermissionState(
        permission =
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU)
            Manifest.permission.READ_MEDIA_IMAGES
        else
            Manifest.permission.READ_EXTERNAL_STORAGE,
        onPermissionResult = { isGranted ->
            if (isGranted)
                pickImageLauncher.launch(
                    PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly)
                )
        }
    )
    val cameraPermissionState = rememberPermissionState(
        permission = Manifest.permission.CAMERA,
        onPermissionResult = { isGranted ->
            if (isGranted) {
                currentTmpUri = FileUtils.createTmpFileUri(context)
                takePictureLauncher.launch(currentTmpUri)
            }
        }
    )

    EventEffect(
        event = uiState.nextScreenEvent,
        onConsumed = viewModel::onConsumedNextScreenEvent
    ) {
        onNextScreen()
    }

    BottomDrawer(
        drawerContent = {
            ProfilePictureDrawer(
                imageUri = imageUri,
                onCloseDrawer = { scope.launch { drawerState.close() } },
                onChooseFromGallery = {
                    if (readImagesPermissionState.status.isGranted) {
                        pickImageLauncher.launch(
                            PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly)
                        )
                    } else {
                        readImagesPermissionState.launchPermissionRequest()
                    }
                },
                onTakePhoto = {
                    if (cameraPermissionState.status.isGranted) {
                        currentTmpUri = FileUtils.createTmpFileUri(context)
                        takePictureLauncher.launch(currentTmpUri)
                    } else {
                        cameraPermissionState.launchPermissionRequest()
                    }
                },
                onRemovePhoto = { imageUri = null }
            )
        },
        drawerState = drawerState,
        drawerShape = RoundedCornerShape(
            topStart = LocalDimension.current.extraSmall,
            topEnd = LocalDimension.current.extraSmall
        ),
        scrimColor = Black.copy(alpha = 0.7f),
        gesturesEnabled = drawerState.isOpen
    ) {
        Scaffold(
            bottomBar = {
                BottomBar(
                    imageUri = imageUri,
                    isLoading = uiState.isLoading,
                    onAddPicture = { scope.launch { drawerState.expand() } },
                    onNextClick = onNextClick
                )
            },
            modifier = Modifier.fillMaxSize()
        ) { innerPadding ->
            ProfilePictureContent(
                imageUri = imageUri,
                onBackClick = onBackClick,
                onCrop = {
                    cropImageLauncher.launch(
                        CropImageContractOptions(
                            uri = imageUri,
                            cropImageOptions = CropImageOptions(
                                aspectRatioX = 1,
                                aspectRatioY = 1,
                                fixAspectRatio = true,
                                multiTouchEnabled = true,
                                activityBackgroundColor = android.graphics.Color.BLACK
                            )
                        )
                    )
                },
                modifier = Modifier.padding(innerPadding)
            )
        }
    }
}

@Composable
fun ProfilePictureContent(
    imageUri: Uri?,
    onBackClick: () -> Unit,
    onCrop: () -> Unit,
    modifier: Modifier = Modifier
) {
    val scrollState = rememberScrollState()

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top,
        modifier = modifier
            .fillMaxSize()
            .background(brush = onBoardingBackgroundBrush)
            .padding(
                vertical = LocalDimension.current.large,
                horizontal = LocalDimension.current.mediumSmall
            )
            .verticalScroll(scrollState)
    ) {
        Box(
            contentAlignment = Alignment.TopStart,
            modifier = Modifier
                .fillMaxWidth()
                .weight(0.06f)
        ) {
            BackIcon(
                color = White,
                modifier = Modifier.clickable(onClick = onBackClick)
            )
        }
        Box(
            contentAlignment = Alignment.CenterStart,
            modifier = Modifier
                .fillMaxWidth()
                .weight(0.1f)
        ) {
            Text(
                text = stringResource(R.string.profile_pic_title),
                color = White,
                style = MaterialTheme.typography.titleLarge,
                textAlign = TextAlign.Start
            )
        }
        Box(
            contentAlignment = Alignment.TopStart,
            modifier = Modifier
                .fillMaxWidth()
                .weight(0.12f)
        ) {
            Text(
                text = stringResource(R.string.profile_pic_label_1),
                color = White,
                style = MaterialTheme.typography.bodyMedium,
                textAlign = TextAlign.Start,
                modifier = Modifier.padding(end = LocalDimension.current.small)
            )
        }
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(
                space = LocalDimension.current.large,
                alignment = Alignment.Top
            ),
            modifier = Modifier
                .fillMaxWidth()
                .weight(0.72f)
        ) {
            OnBoardingProfilePicture(imageUri = imageUri)
            if (imageUri != null) {
                OnBoardingEditButton(
                    text = "Edit",
                    onClick = onCrop
                )
            }
        }
    }
}

@Composable
fun BottomBar(
    imageUri: Uri?,
    isLoading: Boolean,
    onAddPicture: () -> Unit,
    onNextClick: (Uri?) -> Unit
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top,
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(0.2f)
            .background(color = Color(0xFF1C2E3D))
    ) {
        Divider(
            thickness = 0.5.dp,
            color = MaterialTheme.colorScheme.onPrimary
        )
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(
                space = LocalDimension.current.mediumSmall,
                alignment = Alignment.CenterVertically
            ),
            modifier = Modifier
                .fillMaxSize()
                .padding(
                    vertical = LocalDimension.current.medium,
                    horizontal = LocalDimension.current.mediumSmall
                )
        ) {
            Box(
                contentAlignment = Alignment.TopStart,
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
            ) {
                if (imageUri == null) {
                    OnBoardingFilledButton(
                        text = stringResource(
                            R.string.profile_pic_button_1
                        ),
                        onClick = onAddPicture,
                        isLoading = isLoading
                    )
                } else {
                    OnBoardingFilledButton(
                        text = stringResource(R.string.profile_pic_button_3),
                        onClick = { onNextClick(imageUri) },
                        isLoading = isLoading
                    )
                }
            }
            Box(
                contentAlignment = Alignment.TopStart,
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
            ) {
                if (imageUri == null) {
                    OnBoardingOutlinedButton(
                        text = stringResource(R.string.profile_pic_button_2),
                        onClick = { onNextClick(null) }
                    )
                } else {
                    OnBoardingOutlinedButton(
                        text = stringResource(R.string.profile_pic_button_4),
                        onClick = onAddPicture
                    )
                }
            }
        }
    }
}

@Composable
fun ProfilePictureDrawer(
    imageUri: Uri?,
    onCloseDrawer: () -> Unit,
    onChooseFromGallery: () -> Unit,
    onTakePhoto: () -> Unit,
    onRemovePhoto: () -> Unit
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top,
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(0.75f)
            .background(brush = onBoardingDrawerBrush)
            .padding(horizontal = LocalDimension.current.mediumSmall)
    ) {
        Box(
            contentAlignment = Alignment.TopCenter,
            modifier = Modifier
                .fillMaxWidth()
                .weight(0.05f)
        ) {
            SwipeIndicatorIcon(
                color = Color(0xFFCBD2DA),
                modifier = Modifier
                    .size(LocalDimension.current.fourExtraLarge)
                    .offset((-8).dp)
            )
        }
        Box(
            contentAlignment = Alignment.CenterStart,
            modifier = Modifier
                .fillMaxWidth()
                .weight(0.05f)
        ) {
            CancelIcon(
                color = White,
                modifier = Modifier
                    .size(LocalDimension.current.medium)
                    .clickable(onClick = onCloseDrawer)
            )
        }
        Box(
            contentAlignment = Alignment.CenterStart,
            modifier = Modifier
                .fillMaxWidth()
                .weight(0.1f)
        ) {
            Text(
                text = stringResource(R.string.confirmation_code_title_2),
                color = White,
                style = MaterialTheme.typography.titleLarge,
                textAlign = TextAlign.Start
            )
        }
        Box(
            contentAlignment = Alignment.CenterStart,
            modifier = Modifier
                .fillMaxWidth()
                .weight(0.08f)
        ) {
            BottomSheetTopButton(
                text = stringResource(R.string.profile_pic_button_5),
                onClick = {
                    onCloseDrawer()
                    onChooseFromGallery()
                }
            )
        }
        if (imageUri == null) {
            Box(
                contentAlignment = Alignment.CenterStart,
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(0.08f)
            ) {
                BottomSheetBottomButton(
                    text = stringResource(R.string.profile_pic_button_6),
                    onClick = {
                        onCloseDrawer()
                        onTakePhoto()
                    }
                )
            }
            Box(
                contentAlignment = Alignment.CenterStart,
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(0.64f)
            ) {
            }
        } else {
            Box(
                contentAlignment = Alignment.CenterStart,
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(0.08f)
            ) {
                BottomSheetMiddleButton(
                    text = stringResource(R.string.profile_pic_button_6),
                    onClick = {
                        onCloseDrawer()
                        onTakePhoto()
                    }
                )
            }
            Box(
                contentAlignment = Alignment.CenterStart,
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(0.08f)
            ) {
                BottomSheetBottomButton(
                    text = stringResource(R.string.profile_pic_button_7),
                    onClick = {
                        onCloseDrawer()
                        onRemovePhoto()
                    }
                )
            }
            Box(
                contentAlignment = Alignment.CenterStart,
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(0.56f)
            ) {
            }
        }
    }
}