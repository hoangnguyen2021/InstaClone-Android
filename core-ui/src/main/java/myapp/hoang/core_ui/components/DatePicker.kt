package myapp.hoang.core_ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import myapp.hoang.core.utils.DateUtils.days
import myapp.hoang.core.utils.DateUtils.monthsNames
import myapp.hoang.core.utils.DateUtils.years
import myapp.hoang.core_ui.*
import java.time.LocalDate

@Composable
fun DatePicker(
    label: String,
    onDismiss: () -> Unit,
    textFieldValue: LocalDate,
    onTextFieldValueChange: (LocalDate) -> Unit
) {
    Card(
        shape = RoundedCornerShape(LocalDimension.current.small),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface,
            contentColor = MaterialTheme.colorScheme.onSurface,
        ),
        modifier = Modifier
            .fillMaxWidth(0.8f)
            .wrapContentHeight()
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .padding(
                    vertical = LocalDimension.current.large,
                    horizontal = LocalDimension.current.small
                )
        ) {
            var chosenYear by remember { mutableStateOf(textFieldValue.year) }
            var chosenMonth by remember { mutableStateOf(textFieldValue.monthValue) }
            var chosenDay by remember { mutableStateOf(textFieldValue.dayOfMonth) }

            Text(
                text = label,
                color = MaterialTheme.colorScheme.onSurface,
                style = MaterialTheme.typography.labelLarge,
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
                    .align(Alignment.Start)
                    .padding(horizontal = LocalDimension.current.mediumSmall)
            )
            Spacer(modifier = Modifier.height(LocalDimension.current.large))
            DateSelectionSection(
                monthChosen = chosenMonth,
                dayChosen = chosenDay,
                yearChosen = chosenYear,
                onMonthChosen = { chosenMonth = monthsNames.indexOf(it) + 1 },
                onYearChosen = { chosenYear = it.toInt() },
                onDayChosen = { chosenDay = it.toInt() },
            )
            Spacer(modifier = Modifier.height(LocalDimension.current.large))
            Button(
                shape = RoundedCornerShape(LocalDimension.current.extraSmall),
                colors = ButtonDefaults.buttonColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    contentColor = MaterialTheme.colorScheme.onPrimaryContainer
                ),
                contentPadding = PaddingValues(horizontal = LocalDimension.current.large) ,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(LocalDimension.current.fourExtraLarge)
                ,
                onClick = {
                    onDismiss()
                    onTextFieldValueChange(LocalDate.of(chosenYear, chosenMonth, chosenDay))
                }
            ) {
                Text(
                    text = "Set",
                    color = MaterialTheme.colorScheme.onPrimaryContainer,
                    style = MaterialTheme.typography.bodyLarge,
                    textAlign = TextAlign.Center
                )
            }
        }
    }
}

@Composable
fun DateSelectionSection(
    monthChosen: Int,
    yearChosen: Int,
    dayChosen: Int,
    onMonthChosen: (String) -> Unit,
    onYearChosen: (String) -> Unit,
    onDayChosen: (String) -> Unit,
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center,
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
    ) {
        InfiniteItemsPicker(
            items = monthsNames,
            firstIndex = Int.MAX_VALUE / 2 - 5 + monthChosen,
            onItemSelected = onMonthChosen,
            modifier = Modifier
                .fillMaxWidth()
                .height(LocalDimension.current.tenExtraLarge)
                .weight(1f)
        )
        InfiniteItemsPicker(
            items = days,
            firstIndex = Int.MAX_VALUE / 2 - 2 + dayChosen,
            onItemSelected = onDayChosen,
            modifier = Modifier
                .fillMaxWidth()
                .height(LocalDimension.current.tenExtraLarge)
                .weight(1f)
        )
        FiniteItemsPicker(
            items = listOf("") + years + listOf(""),
            firstIndex = 122,
            onItemSelected = onYearChosen,
            modifier = Modifier
                .fillMaxWidth()
                .height(LocalDimension.current.tenExtraLarge)
                .weight(1f)
        )
    }
}

@Composable
fun InfiniteItemsPicker(
    items: List<String>,
    firstIndex: Int,
    modifier: Modifier = Modifier,
    onItemSelected: (String) -> Unit,
) {
    val listState = rememberLazyListState(firstIndex)
    val secondVisibleItemIndex by remember { derivedStateOf { listState.firstVisibleItemIndex + 1 } }
    var currentValue by remember { mutableStateOf("") }

    LaunchedEffect(key1 = !listState.isScrollInProgress) {
        onItemSelected(currentValue)
        listState.animateScrollToItem(index = listState.firstVisibleItemIndex)
    }

    Box(modifier = modifier) {
        LazyColumn(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            state = listState,
            content = {
                items(count = Int.MAX_VALUE) {
                    val index = it % items.size
                    if (it == secondVisibleItemIndex) {
                        currentValue = items[index]
                    }
                    Spacer(modifier = Modifier.height(6.dp))
                    Text(
                        text = items[index],
                        color = MaterialTheme.colorScheme.onSurface,
                        style = MaterialTheme.typography.bodyLarge,
                        modifier = Modifier.alpha(if (it == secondVisibleItemIndex) 1f else 0.2f),
                    )
                    Spacer(modifier = Modifier.height(6.dp))
                }
            },
            modifier = Modifier.fillMaxSize()
        )
    }
}

@Composable
fun FiniteItemsPicker(
    items: List<String>,
    firstIndex: Int,
    modifier: Modifier = Modifier,
    onItemSelected: (String) -> Unit,
) {
    val listState = rememberLazyListState(firstIndex)
    val secondVisibleItemIndex by remember { derivedStateOf { listState.firstVisibleItemIndex + 1 } }
    var currentValue by remember { mutableStateOf("") }

    LaunchedEffect(key1 = !listState.isScrollInProgress) {
        onItemSelected(currentValue)
        listState.animateScrollToItem(index = listState.firstVisibleItemIndex)
    }

    Box(modifier = modifier) {
        LazyColumn(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            state = listState,
            content = {
                items(count = items.size) { index ->
                    if (index == secondVisibleItemIndex) {
                        currentValue = items[index]
                    }
                    Spacer(modifier = Modifier.height(6.dp))
                    Text(
                        text = items[index],
                        color = MaterialTheme.colorScheme.onSurface,
                        style = MaterialTheme.typography.bodyLarge,
                        modifier = Modifier.alpha(if (index == secondVisibleItemIndex) 1f else 0.2f),
                    )
                    Spacer(modifier = Modifier.height(6.dp))
                }
            },
            modifier = Modifier.fillMaxSize()
        )
    }
}
