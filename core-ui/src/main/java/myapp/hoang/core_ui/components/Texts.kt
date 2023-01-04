package myapp.hoang.core_ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import myapp.hoang.core_ui.R
import myapp.hoang.core_ui.InstagramFamily
import myapp.hoang.core_ui.LocalDimension

@Composable
fun InstaCloneBrand(
    color: Color,
    modifier: Modifier = Modifier
) {
    Text(
        text = " " + stringResource(R.string.instaclone) + " ",
        style = TextStyle(
            fontFamily = InstagramFamily,
            fontWeight = FontWeight.Normal,
            fontSize = 26.sp,
            letterSpacing = 0.sp
        ),
        color = color,
        maxLines = 1,
        modifier = modifier
    )
}

@Composable
fun ProfileStat(
    value: Int,
    unit: String
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .wrapContentWidth()
            .fillMaxHeight()
    ) {
        Text(
            text = value.toString(),
            style = MaterialTheme.typography.titleLarge,
            maxLines = 1
        )
        Spacer(modifier = Modifier.height(LocalDimension.current.twoExtraSmall))
        Text(
            text = unit,
            style = MaterialTheme.typography.titleMedium,
            maxLines = 1
        )
    }
}