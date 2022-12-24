package myapp.hoang.core_ui.components

import androidx.compose.foundation.background
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import myapp.hoang.core_ui.*
import java.time.LocalDate

val years = (1900..2022).map { it.toString() }
val monthsNumber = (1..12).map { it.toString() }
val days = (1..31).map { it.toString() }
val monthsNames = listOf(
    "Jan",
    "Feb",
    "Mar",
    "Apr",
    "May",
    "Jun",
    "Jul",
    "Aug",
    "Sep",
    "Oct",
    "Nov",
    "Dec"
)

val currentMonth = LocalDate.now().monthValue
val currentDay = LocalDate.now().dayOfMonth
val currentYear = LocalDate.now().year

@Composable
fun DatePickerDialog(
    label: String,
    onDismiss: () -> Unit
) {
    Dialog(onDismissRequest = onDismiss) {
        DatePicker(label, onDismiss)
    }
}

@Preview
@Composable
fun DatePickerDialogPreview() {
    OnBoardingTheme {
        Surface(
            modifier = Modifier.fillMaxSize()
        ) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(
                        brush = onBoardingBackgroundBrush
                    )
                    .padding(
                        horizontal = LocalDimension.current.small
                    )
            ) {
                var isDialogShown by remember { mutableStateOf(false) }

                Button(
                    shape = RoundedCornerShape(10.dp),
                    modifier = Modifier
                        .padding(horizontal = 20.dp)
                        .align(Alignment.Center),
                    onClick = {
                        isDialogShown = true
                    }) {
                    Text(text = "Date Picker", style = MaterialTheme.typography.bodyLarge)
                }

                if (isDialogShown) {
                    DatePickerDialog(label = "Set date") {
                        isDialogShown = false
                    }
                }
            }
        }
    }
}

@Composable
fun DatePicker(
    label: String,
    onDismissRequest: () -> Unit
) {
    Card(
        shape = RoundedCornerShape(LocalDimension.current.small),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface
        ),
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
            var chosenYear by remember { mutableStateOf(currentYear) }
            var chosenMonth by remember { mutableStateOf(currentMonth) }
            var chosenDay by remember { mutableStateOf(currentDay) }

            Text(
                text = label,
                style = MaterialTheme.typography.labelLarge,
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
                    .align(Alignment.Start)
                    .padding(horizontal = LocalDimension.current.medium)
            )
            Spacer(modifier = Modifier.height(LocalDimension.current.large))
            DateSelectionSection(
                onYearChosen = { chosenYear = it.toInt() },
                onMonthChosen = { chosenMonth = monthsNames.indexOf(it) },
                onDayChosen = { chosenDay = it.toInt() },
            )
            Spacer(modifier = Modifier.height(LocalDimension.current.large))
            Button(
                shape = RoundedCornerShape(5.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 10.dp),
                onClick = onDismissRequest
            ) {
                Text(
                    text = "Done",
                    style = MaterialTheme.typography.bodyLarge,
                    modifier = Modifier.fillMaxWidth(),
                    textAlign = TextAlign.Center
                )
            }
        }
    }
}

@Composable
fun DateSelectionSection(
    onYearChosen: (String) -> Unit,
    onMonthChosen: (String) -> Unit,
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
            firstIndex = Int.MAX_VALUE / 2 - 4,
            onItemSelected = onMonthChosen,
            modifier = Modifier
                .fillMaxWidth()
                .height(LocalDimension.current.tenExtraLarge)
                .weight(1f)
        )
        InfiniteItemsPicker(
            items = days,
            firstIndex = Int.MAX_VALUE / 2 - 1,
            onItemSelected = onDayChosen,
            modifier = Modifier
                .fillMaxWidth()
                .height(LocalDimension.current.tenExtraLarge)
                .weight(1f)
        )
        InfiniteItemsPicker(
            items = years,
            firstIndex = Int.MAX_VALUE / 2 + 60,
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
                        modifier = Modifier.alpha(if (it == secondVisibleItemIndex) 1f else 0.3f),
                        style = MaterialTheme.typography.bodyLarge
                    )
                    Spacer(modifier = Modifier.height(6.dp))
                }
            },
            modifier = Modifier.fillMaxSize()
        )
    }
}
