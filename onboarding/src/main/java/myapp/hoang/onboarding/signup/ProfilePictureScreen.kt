package myapp.hoang.onboarding.signup

import android.annotation.SuppressLint
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch
import myapp.hoang.core_ui.*
import myapp.hoang.core_ui.components.*
import myapp.hoang.core_ui.components.bottomsheet.BottomDrawer
import myapp.hoang.core_ui.components.bottomsheet.BottomDrawerValue
import myapp.hoang.core_ui.components.bottomsheet.rememberBottomDrawerState
import myapp.hoang.onboarding.R

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfilePictureScreen(
    onBackClick: () -> Unit,
    onNextClick: () -> Unit
) {
    val drawerState = rememberBottomDrawerState(BottomDrawerValue.Closed)
    val scope = rememberCoroutineScope()

    BottomDrawer(
        drawerContent = {
            ProfilePictureDrawer(
                onCloseDrawer = { scope.launch { drawerState.close() } }
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
                onBackClick = onBackClick,
                onNextClick = onNextClick
            )
        }
    }
}

@Composable
fun ProfilePictureContent(
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
        Image(
            painter = painterResource(id = R.drawable.profile_pic_placeholder),
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
    onCloseDrawer: () -> Unit
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
            onClick = onCloseDrawer
        )
        BottomSheetBottomButton(
            text = stringResource(R.string.profile_pic_button_4),
            onClick = {
                onCloseDrawer()
            }
        )
    }
}