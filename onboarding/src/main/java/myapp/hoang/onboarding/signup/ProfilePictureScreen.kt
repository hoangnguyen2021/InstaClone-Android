package myapp.hoang.onboarding.signup

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
import com.canhub.cropper.CropImageContract
import com.canhub.cropper.CropImageContractOptions
import com.canhub.cropper.CropImageOptions
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.isGranted
import com.google.accompanist.permissions.rememberPermissionState
import kotlinx.coroutines.launch
import myapp.hoang.core.util.FileUtils
import myapp.hoang.core_ui.*
import myapp.hoang.core_ui.components.*
import myapp.hoang.core_ui.components.bottomsheet.BottomDrawer
import myapp.hoang.core_ui.components.bottomsheet.BottomDrawerValue
import myapp.hoang.core_ui.components.bottomsheet.rememberBottomDrawerState
import myapp.hoang.onboarding.R

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class, ExperimentalPermissionsApi::class)
@Composable
fun ProfilePictureScreen(
    onBackClick: () -> Unit,
    onNextClick: () -> Unit
) {
    val context = LocalContext.current

    val drawerState = rememberBottomDrawerState(BottomDrawerValue.Closed)
    val scope = rememberCoroutineScope()

    var imageUri by remember { mutableStateOf<Uri?>(null) }
    var currentTmpUri by remember { mutableStateOf<Uri?>(null) }

    val pickImageLauncher =
        rememberLauncherForActivityResult(
            contract = ActivityResultContracts.PickVisualMedia(),
            onResult = { uri -> if (uri != null) imageUri = uri }
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
                    onAddPicture = { scope.launch { drawerState.expand() } },
                    onNextClick = onNextClick
                )
            },
            modifier = Modifier.fillMaxSize()
        ) {
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
                }
            )
        }
    }
}

@Composable
fun ProfilePictureContent(
    imageUri: Uri?,
    onBackClick: () -> Unit,
    onCrop: () -> Unit
) {
    val scrollState = rememberScrollState()

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top,
        modifier = Modifier
            .fillMaxSize()
            .background(brush = onBoardingBackgroundBrush)
            .padding(
                vertical = LocalDimension.current.large,
                horizontal = LocalDimension.current.mediumSmall
            )
            .verticalScroll(scrollState)
    ) {
        BackIcon(
            color = White,
            modifier = Modifier
                .align(Alignment.Start)
                .clickable(onClick = onBackClick)
        )
        Spacer(Modifier.height(LocalDimension.current.mediumSmall))
        Text(
            text = stringResource(R.string.profile_pic_title),
            color = White,
            style = MaterialTheme.typography.titleLarge,
            textAlign = TextAlign.Start,
            modifier = Modifier.align(Alignment.Start)
        )
        Spacer(Modifier.height(LocalDimension.current.small))
        Text(
            text = stringResource(R.string.profile_pic_label_1),
            color = White,
            style = MaterialTheme.typography.labelMedium,
            textAlign = TextAlign.Start,
            modifier = Modifier
                .align(Alignment.Start)
                .padding(end = LocalDimension.current.small)
        )
        Spacer(Modifier.height(LocalDimension.current.large))
        OnBoardingProfilePicture(imageUri = imageUri)
        if (imageUri != null) {
            Spacer(Modifier.height(LocalDimension.current.large))
            OnBoardingEditButton(
                text = "Edit",
                onClick = onCrop
            )
        }
    }
}

@Composable
fun BottomBar(
    imageUri: Uri?,
    onAddPicture: () -> Unit,
    onNextClick: () -> Unit
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
            verticalArrangement = Arrangement.Top,
            modifier = Modifier
                .fillMaxSize()
                .padding(
                    vertical = LocalDimension.current.medium,
                    horizontal = LocalDimension.current.mediumSmall
                )
        ) {
            OnBoardingFilledButton(
                text = stringResource(
                    if (imageUri == null) R.string.profile_pic_button_1 else R.string.profile_pic_button_3
                ),
                onClick = if (imageUri == null) onAddPicture else onNextClick
            )
            Spacer(Modifier.height(LocalDimension.current.mediumSmall))
            OnBoardingOutlinedButton(
                text = stringResource(
                    if (imageUri == null) R.string.profile_pic_button_2 else R.string.profile_pic_button_4
                ),
                onClick = if (imageUri == null) onNextClick else onAddPicture
            )
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
        SwipeIndicatorIcon(
            color = Color(0xFFCBD2DA),
            modifier = Modifier
                .size(LocalDimension.current.fourExtraLarge)
                .offset(y = (-12).dp)
        )
        CancelIcon(
            color = White,
            modifier = Modifier
                .size(LocalDimension.current.medium)
                .align(Alignment.Start)
                .clickable(onClick = onCloseDrawer)
        )
        Spacer(Modifier.height(LocalDimension.current.large))
        Text(
            text = stringResource(R.string.confirmation_code_title_2),
            color = White,
            style = MaterialTheme.typography.titleLarge,
            textAlign = TextAlign.Start,
            modifier = Modifier.align(Alignment.Start)
        )
        Spacer(Modifier.height(LocalDimension.current.large))
        BottomSheetTopButton(
            text = stringResource(R.string.profile_pic_button_5),
            onClick = {
                onCloseDrawer()
                onChooseFromGallery()
            }
        )
        if (imageUri == null) {
            BottomSheetBottomButton(
                text = stringResource(R.string.profile_pic_button_6),
                onClick = {
                    onCloseDrawer()
                    onTakePhoto()
                }
            )
        } else {
            BottomSheetMiddleButton(
                text = stringResource(R.string.profile_pic_button_6),
                onClick = {
                    onCloseDrawer()
                    onTakePhoto()
                }
            )
            BottomSheetBottomButton(
                text = stringResource(R.string.profile_pic_button_7),
                onClick = {
                    onCloseDrawer()
                    onRemovePhoto()
                }
            )
        }
    }
}