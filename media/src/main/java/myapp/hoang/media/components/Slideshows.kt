package myapp.hoang.media.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.PagerState
import kotlinx.coroutines.delay
import myapp.hoang.core_ui.Black
import myapp.hoang.core_ui.LocalDimension
import myapp.hoang.core_ui.components.PostImage

@OptIn(ExperimentalPagerApi::class)
@Composable
fun PostSlideshow(
    mediaPaths: List<String>,
    pagerState: PagerState
) {
    var isNumericIndicatorShown by remember { mutableStateOf(mediaPaths.size > 1) }
    // flag to make sure to restart show/hide indicator effect
    var swiped by remember { mutableStateOf(false) }

    LaunchedEffect(key1 = isNumericIndicatorShown, key2 = swiped) {
        if (isNumericIndicatorShown) {
            swiped = false
            delay(5000L)
            isNumericIndicatorShown = false
        }
    }

    LaunchedEffect(key1 = pagerState) {
        if (mediaPaths.size > 1) {
            snapshotFlow { pagerState.currentPage }.collect {
                isNumericIndicatorShown = true
                swiped = true
            }
        }
    }

    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        HorizontalPager(
            count = mediaPaths.size,
            state = pagerState
        ) { page ->
            PostImage(
                path = mediaPaths[page],
                modifier = Modifier.fillMaxSize()
            )
        }
        Box(
            modifier = Modifier
                .align(Alignment.TopEnd)
                .padding(LocalDimension.current.medium)
                .wrapContentSize()
        ) {
            AnimatedVisibility(
                visible = isNumericIndicatorShown,
                enter = fadeIn(),
                exit = fadeOut()
            ) {
                SlideshowNumericIndicator(pagerState = pagerState)
            }
        }
    }
}

@OptIn(ExperimentalPagerApi::class)
@Composable
fun SlideshowNumericIndicator(
    pagerState: PagerState
) {
    Text(
        text = "${pagerState.currentPage.plus(1)}/${pagerState.pageCount}",
        style = MaterialTheme.typography.labelMedium,
        fontSize = 13.sp,
        fontWeight = FontWeight.SemiBold,
        modifier = Modifier
            .background(
                color = Black.copy(alpha = 0.8f),
                shape = RoundedCornerShape(50)
            )
            .padding(
                vertical = LocalDimension.current.extraSmall,
                horizontal = LocalDimension.current.small
            )
    )
}