package dev.than0s.mydiary.screen.diary

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import dev.than0s.mydiary.AppBar
import dev.than0s.mydiary.BottomBar
import dev.than0s.mydiary.ButtonActions
import dev.than0s.mydiary.DIARY_SCREEN
import dev.than0s.mydiary.EDIT_NOTE_SCREEN
import dev.than0s.mydiary.ScaffoldState
import dev.than0s.mydiary.common.emojiList
import dev.than0s.mydiary.common.monthNames
import dev.than0s.mydiary.ui.theme.spacing
import java.util.Calendar
import java.util.Date

@Composable
fun DiaryScreen(viewModel: DiaryViewModel = hiltViewModel(), openScreen: (String) -> Unit) {
    val notes = viewModel.notes.collectAsStateWithLifecycle(emptyList())
    DiaryScreenContent(list = notes.value, openScreen = openScreen)
}

@Composable
fun DiaryScreenContent(list: List<Note>, openScreen: (String) -> Unit) {
    ScaffoldState.floatingActionButtonState = remember {
        ButtonActions(
            onClick = {
                openScreen("$EDIT_NOTE_SCREEN/0")
            },
            content = {
                Icon(Icons.Filled.Add, "Floating action button.")
            },
            visibility = true
        )
    }
    ScaffoldState.bottomBarState = remember {
        BottomBar(selected = 0)
    }
    ScaffoldState.topBarState = remember {
        AppBar(title = DIARY_SCREEN)
    }

    LazyColumn(
        content = {
            items(items = list, key = { it.id }) { item ->
                Item(note = item, openScreen = openScreen)
            }
        },
        modifier = Modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.medium)
    )
}

@Composable
fun Item(note: Note, openScreen: (String) -> Unit) {
    ElevatedCard(
        elevation = CardDefaults.cardElevation(
            defaultElevation = 6.dp
        ),
        modifier = Modifier
            .padding(horizontal = MaterialTheme.spacing.small)
            .clickable(interactionSource = remember { MutableInteractionSource() },
                indication = rememberRipple(bounded = true), onClick = {
                    openScreen("$EDIT_NOTE_SCREEN/${note.id}")
                })
    ) {
        Row(
            modifier = Modifier
                .height(IntrinsicSize.Min) //intrinsic measurements
                .fillMaxWidth()
                .wrapContentHeight()
                .padding(
                    horizontal = MaterialTheme.spacing.small,
                    vertical = MaterialTheme.spacing.extraSmall
                ),
            horizontalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.medium),
            verticalAlignment = Alignment.CenterVertically,
        ) {

            Image(
                painter = painterResource(emojiList[note.emoji]),
                contentDescription = "Reaction",
                modifier = Modifier
                    .weight(0.5f)
                    .fillMaxHeight()
                    .padding(vertical = MaterialTheme.spacing.extraSmall)
            )

            Column(
                verticalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.extraSmall),
                modifier = Modifier.weight(2.0f)
            ) {
                DateShower(calendar = getCalendar(note.date))
                Text(
                    text = note.description + "\n\n\n",
                    fontWeight = FontWeight.Thin,
                    maxLines = 3,
                    overflow = TextOverflow.Ellipsis,
                )
            }
        }
    }
}

@Composable
fun DateShower(calendar: Calendar, modifier: Modifier = Modifier) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.extraSmall)
    ) {
        calendar.let {
            Text(
                text = it.get(Calendar.DAY_OF_MONTH).toString(),
                fontWeight = FontWeight.Bold,
                color = Color.Gray
            )
            Text(
                text = monthNames[it.get(Calendar.MONTH)].substring(0, 3),
                fontWeight = FontWeight.Bold,
                color = Color.Gray
            )
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
    val note = Note(description = "Hello World\nHello World\nHello World", userId = "")
    DiaryScreenContent(list = listOf(note), openScreen = {})
}