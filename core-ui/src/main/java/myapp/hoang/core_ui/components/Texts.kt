package myapp.hoang.core_ui.components

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import myapp.hoang.core_ui.Black
import myapp.hoang.core_ui.R
import myapp.hoang.core_ui.billabongFamily

@Composable
fun InstaCloneBrand(
    modifier: Modifier = Modifier
) {
    Text(
        text = stringResource(R.string.instaclone),
        style = TextStyle(
            fontFamily = billabongFamily,
            fontWeight = FontWeight.SemiBold,
            fontSize = 36.sp,
            letterSpacing = 0.6.sp
        ),
        color = Black,
        maxLines = 1,
        modifier = modifier
    )
}