package dev.than0s.mydiary.screen.diary

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.rounded.SentimentDissatisfied
import androidx.compose.material.icons.rounded.SentimentNeutral
import androidx.compose.material.icons.rounded.SentimentSatisfied
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import dev.than0s.mydiary.EDIT_NOTE_SCREEN
import java.util.Calendar
import java.util.Date

@Composable
fun DiaryScreen(viewModel: DiaryViewModel = hiltViewModel(), openScreen: (String) -> Unit) {
    val notes = viewModel.notes.collectAsStateWithLifecycle(emptyList())
    DiaryScreenContent(list = notes.value, openScreen = openScreen)
}

@Composable
fun DiaryScreenContent(list: List<Note>, openScreen: (String) -> Unit) {
    Scaffold(
        floatingActionButton = { FloatingButton(openScreen) },
    ) { paddingValue ->
        LazyColumn(
            content = {
                items(items = list, key = { it.id }) { item ->
                    Item(note = item, openScreen = openScreen)
                }
            },
            modifier = Modifier
                .padding(paddingValue)
                .fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        )
    }
}

@Composable
fun Item(note: Note, openScreen: (String) -> Unit) {
    val emojis = Icons.Rounded.let {
        listOf(it.SentimentDissatisfied, it.SentimentNeutral, it.SentimentSatisfied)
    }

    Card(
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceVariant,
        ),
        modifier = Modifier.clickable { openScreen("$EDIT_NOTE_SCREEN/${note.id}") }
    ) {

        Row(
            modifier = Modifier
                .height(IntrinsicSize.Min) //intrinsic measurements
                .fillMaxWidth()
                .padding(horizontal = 10.dp, vertical = 6.dp),
            horizontalArrangement = Arrangement.spacedBy(4.dp)
        ) {

            DateShower(calendar = getCalendar(note.date))

            HorizontalDivider(
                modifier = Modifier
                    .fillMaxHeight()  //fill the max height
                    .width(2.dp),
                color = Color.Gray
            )

            Column {
                Image(imageVector = emojis[note.emoji], contentDescription = "Emoji")
                Text(text = note.description)
            }
        }
    }
}

@Composable
fun FloatingButton(openScreen: (String) -> Unit) {
    FloatingActionButton(onClick = { openScreen("$EDIT_NOTE_SCREEN/0") }) {
        Icon(Icons.Filled.Add, "Floating action button.")
    }
}

@Composable
fun DateShower(calendar: Calendar, modifier: Modifier = Modifier) {
    Column(modifier = modifier) {
        calendar.let {
            Text(text = it.get(Calendar.YEAR).toString())
            Text(text = it.get(Calendar.MONTH).toString())
            Text(text = it.get(Calendar.DAY_OF_MONTH).toString())
        }
    }
}

fun getCalendar(date: Date): Calendar {
    val calendar = Calendar.getInstance()
    calendar.time = date
    return calendar
}

@Preview(showSystemUi = true)
@Composable
fun DiaryScreenPreview() {
    DiaryScreenContent(listOf(Note())) {}
}