package myapp.hoang.onboarding.signup

import android.Manifest
import android.annotation.SuppressLint
import android.net.Uri
import android.os.Build
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresApi
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.core.content.FileProvider
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.isGranted
import com.google.accompanist.permissions.rememberPermissionState
import kotlinx.coroutines.launch
import myapp.hoang.core_ui.*
import myapp.hoang.core_ui.components.*
import myapp.hoang.core_ui.components.bottomsheet.BottomDrawer
import myapp.hoang.core_ui.components.bottomsheet.BottomDrawerValue
import myapp.hoang.core_ui.components.bottomsheet.rememberBottomDrawerState
import myapp.hoang.onboarding.R
import java.io.File


@RequiresApi(Build.VERSION_CODES.TIRAMISU)
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

    // can be a URI or Bitmap
    var imageData by remember { mutableStateOf<Any?>(null) }
    var currentTmpUri by remember { mutableStateOf<Uri?>(null) }

    val galleryLauncher =
        rememberLauncherForActivityResult(ActivityResultContracts.PickVisualMedia()) { uri ->
            if (uri != null) imageData = uri
        }
    val takePictureLauncher =
        rememberLauncherForActivityResult(ActivityResultContracts.TakePicture()) { isSuccessful ->
            if (isSuccessful) imageData = currentTmpUri
        }

    val readImagesPermissionState = rememberPermissionState(
        permission = Manifest.permission.READ_MEDIA_IMAGES,
        onPermissionResult = { isGranted ->
            if (isGranted)
                galleryLauncher.launch(
                    PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly)
                )
        }
    )
    val cameraPermissionState = rememberPermissionState(
        permission = Manifest.permission.CAMERA,
        onPermissionResult = { isGranted ->
            if (isGranted) takePictureLauncher.launch(null)
        }
    )

    BottomDrawer(
        drawerContent = {
            ProfilePictureDrawer(
                onCloseDrawer = { scope.launch { drawerState.close() } },
                onChooseFromGallery = {
                    if (readImagesPermissionState.status.isGranted) {
                        galleryLauncher.launch(
                            PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly)
                        )
                    } else {
                        readImagesPermissionState.launchPermissionRequest()
                    }
                },
                onTakePhoto = {
                    if (cameraPermissionState.status.isGranted) {
                        val tmpFile = File
                            .createTempFile("tmp_image_file", ".png", context.cacheDir)
                            .apply {
                                createNewFile()
                                deleteOnExit()
                            }
                        currentTmpUri = FileProvider.getUriForFile(
                            context,
                            "${context.packageName}.provider",
                            tmpFile
                        )
                        takePictureLauncher.launch(currentTmpUri)
                    } else {
                        cameraPermissionState.launchPermissionRequest()
                    }
                }
            )
        },
        drawerState = drawerState,
        drawerShape = RoundedCornerShape(
            topStart = LocalDimension.current.extraSmall,
            topEnd = LocalDimension.current.extraSmall
        ),
        scrimColor = Black.copy(alpha = 0.7f)
    ) {
        Scaffold(
            bottomBar = {
                BottomBar(
                    onAddPicture = { scope.launch { drawerState.expand() } },
                    onNextClick = onNextClick
                )
            },
            modifier = Modifier.fillMaxSize()
        ) {
            ProfilePictureContent(
                imageUri = imageData,
                onBackClick = onBackClick,
                onNextClick = onNextClick
            )
        }
    }
}

@Composable
fun ProfilePictureContent(
    imageUri: Any?,
    onBackClick: () -> Unit,
    onNextClick: () -> Unit
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
        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data(imageUri)
                .crossfade(true)
                .build(),
            error = painterResource(id = R.drawable.profile_pic_placeholder),
            contentDescription = "Profile picture",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxWidth(0.75f)
                .aspectRatio(1f)
                .border(
                    BorderStroke(5.dp, LightGray),
                    CircleShape
                )
                .padding(LocalDimension.current.twoExtraSmall)
                .clip(CircleShape)
        )
    }
}

@Composable
fun BottomBar(
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
                text = stringResource(R.string.profile_pic_button_1),
                onClick = onAddPicture
            )
            Spacer(Modifier.height(LocalDimension.current.mediumSmall))
            OnBoardingOutlinedButton(
                text = stringResource(R.string.profile_pic_button_2),
                onClick = onNextClick
            )
        }
    }
}

@Composable
fun ProfilePictureDrawer(
    onCloseDrawer: () -> Unit,
    onChooseFromGallery: () -> Unit,
    onTakePhoto: () -> Unit
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
            text = stringResource(R.string.profile_pic_button_3),
            onClick = {
                onCloseDrawer()
                onChooseFromGallery()
            }
        )
        BottomSheetBottomButton(
            text = stringResource(R.string.profile_pic_button_4),
            onClick = {
                onCloseDrawer()
                onTakePhoto()
            }
        )
    }
}