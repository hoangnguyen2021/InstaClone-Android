package myapp.hoang.core_ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import myapp.hoang.core_ui.LocalDimension

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun InstaCloneTopAppBar() {
    TopAppBar(
        title = {
            InstaCloneBrand(
                color = MaterialTheme.colorScheme.onSurface,
                modifier = Modifier
                    .wrapContentWidth()
                    .fillMaxHeight()
                    .padding(vertical = LocalDimension.current.extraSmall)
            )
        },
        actions = {
            NewPostIconButton {

            }
            HeartIconButton {

            }
            MessageIconButton {

            }
        },
        modifier = Modifier
            .fillMaxWidth()
            .height(LocalDimension.current.fiveExtraLarge)
    )
}

@Composable
fun InstaCloneBottomAppBar() {
    BottomAppBar(
        containerColor = MaterialTheme.colorScheme.surface,
        contentColor = MaterialTheme.colorScheme.onSurface,
        tonalElevation = LocalDimension.current.zero,
        modifier = Modifier
            .fillMaxWidth()
            .height(LocalDimension.current.fiveExtraLarge)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceEvenly,
            modifier = Modifier
                .fillMaxSize()
        ) {
            HomeIconButton(isSelected = false) {

            }
            SearchIconButton(isSelected = false) {

            }
            ReelsIconButton(isSelected = false) {

            }
            ShopIconButton(isSelected = false) {

            }
            BottomAppBarProfilePic(isSelected = false) {

            }
        }
    }
}
