package dev.than0s.mydiary.screen.edit_note

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Save
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.vanpra.composematerialdialogs.MaterialDialog
import com.vanpra.composematerialdialogs.MaterialDialogState
import com.vanpra.composematerialdialogs.datetime.date.datepicker
import com.vanpra.composematerialdialogs.rememberMaterialDialogState
import dev.than0s.mydiary.BottomBar
import dev.than0s.mydiary.ButtonActions
import dev.than0s.mydiary.ScaffoldState
import dev.than0s.mydiary.common.emojiList
import dev.than0s.mydiary.common.monthNames
import dev.than0s.mydiary.screen.diary.Note
import dev.than0s.mydiary.screen.diary.getCalendar
import java.time.LocalDate
import java.util.Calendar

@Composable
fun EditNote(viewModel: EditNoteViewModel = hiltViewModel(), popUpScreen: () -> Unit) {
    val note = viewModel.note.value
    EditNoteContent(
        note = note,
        onTitleChange = viewModel::onTitleChange,
        onDescriptionChange = viewModel::onDescriptionChange,
        onDoneClick = { viewModel.onDoneClick(popUpScreen) },
        onDateChange = viewModel::onDateChange,
        onEmojiChange = viewModel::onEmojiChange
    )
}

@Composable
fun EditNoteContent(
    note: Note,
    onTitleChange: (String) -> Unit,
    onDescriptionChange: (String) -> Unit,
    onDoneClick: () -> Unit,
    onDateChange: (LocalDate) -> Unit,
    onEmojiChange: (Int) -> Unit,
) {
    val dateDialogState = rememberMaterialDialogState()
    val emojiDialogState = rememberMaterialDialogState()

    ScaffoldState.floatingActionButtonState = remember {
        ButtonActions(
            onClick = { onDoneClick() },
            content = {
                Icon(Icons.Rounded.Save, "Floating action button.")
            },
            visibility = true
        )
    }
    ScaffoldState.bottomBarState = remember {
        BottomBar(false)
    }

    DatePicker(dateDialogState = dateDialogState, onDateChange = onDateChange)
    EmojiPicker(emojiDialogState = emojiDialogState, onEmojiChange = onEmojiChange)
    Column(
        verticalArrangement = Arrangement.spacedBy(10.dp),
        horizontalAlignment = Alignment.Start,
        modifier = Modifier.padding(horizontal = 15.dp)
    ) {
        Row(
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxWidth()
        ) {
            FilledTonalButton(
                onClick = { dateDialogState.show() }
            ) {
                DateShower(
                    calendar = getCalendar(note.date),
                )
            }
        }
        Image(
            painter = painterResource(id = emojiList[note.emoji]),
            contentDescription = "Reaction",
            modifier = Modifier
                .clickable {
                    emojiDialogState.show()
                }
                .height(50.dp)
                .width(50.dp)
        )
        TextField(
            value = note.title,
            placeHolder = "Title",
            onValueChange = onTitleChange,
            modifier = Modifier.fillMaxWidth(),
            fontSize = 30.sp
        )
        TextField(
            value = note.description,
            placeHolder = "Write Here...",
            onValueChange = onDescriptionChange,
            modifier = Modifier
                .fillMaxHeight()
                .fillMaxWidth(),
            fontSize = 20.sp
        )
    }
}

@Composable
fun FloatingButton(onDoneClick: () -> Unit) {
    FloatingActionButton(onClick = { onDoneClick() }) {
        Icon(Icons.Rounded.Save, "Floating action button.")
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TextField(
    value: String,
    placeHolder: String,
    modifier: Modifier = Modifier,
    fontSize: TextUnit,
    onValueChange: (String) -> Unit
) {
    TextField(
        value = value,
        modifier = modifier,
        onValueChange = { newValue -> onValueChange(newValue) },
        placeholder = { Text(placeHolder, fontSize = fontSize, color = Color.Gray) },
        colors = TextFieldDefaults.textFieldColors(
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent
        ),
        textStyle = TextStyle.Default.copy(fontSize = fontSize),
    )
}

@Composable
fun DatePicker(dateDialogState: MaterialDialogState, onDateChange: (LocalDate) -> Unit) {
    MaterialDialog(dialogState = dateDialogState, buttons = {
        positiveButton(text = "ok")
        negativeButton(text = "cancel")
    }) {
        datepicker(initialDate = LocalDate.now(), title = "Pick a date") {
            onDateChange(it)
        }
    }
}

@Composable
fun EmojiPicker(emojiDialogState: MaterialDialogState, onEmojiChange: (Int) -> Unit) {
    MaterialDialog(
        dialogState = emojiDialogState,
        shape = MaterialTheme.shapes.large
    ) {
        Card(
            modifier = Modifier
                .padding(10.dp)
                .height(300.dp)
        ) {
            LazyVerticalGrid(
                columns = GridCells.Adaptive(70.dp),
                content = {
                    items(emojiList.size) { index ->
                        Image(
                            painter = painterResource(id = emojiList[index]),
                            contentDescription = "emoji",
                            modifier = Modifier
                                .clickable {
                                    onEmojiChange(index)
                                    this@MaterialDialog.dialogState.hide()
                                }
                                .height(50.dp)
                                .width(50.dp)
                        )
                    }
                })
        }
    }
}

@Composable
fun DateShower(calendar: Calendar, modifier: Modifier = Modifier) {
    Row(modifier = modifier, horizontalArrangement = Arrangement.spacedBy(4.dp)) {
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
            Text(
                text = it.get(Calendar.YEAR).toString(),
                fontWeight = FontWeight.Bold,
                color = Color.Gray
            )
        }
    }
}

@Preview(showSystemUi = true)
@Composable
fun EditNoteScreenPreview() {
    EditNoteContent(
        note = Note(userId = "0"),
        onTitleChange = {},
        onDescriptionChange = {},
        onDoneClick = {},
        onDateChange = {},
        onEmojiChange = {})
}