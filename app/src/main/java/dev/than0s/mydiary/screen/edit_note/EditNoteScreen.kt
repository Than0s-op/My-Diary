package dev.than0s.mydiary.screen.edit_note

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Save
import androidx.compose.material.icons.rounded.SentimentNeutral
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.vanpra.composematerialdialogs.MaterialDialog
import com.vanpra.composematerialdialogs.MaterialDialogState
import com.vanpra.composematerialdialogs.datetime.date.datepicker
import com.vanpra.composematerialdialogs.rememberMaterialDialogState
import dev.than0s.mydiary.screen.diary.DateShower
import dev.than0s.mydiary.screen.diary.Note
import dev.than0s.mydiary.screen.diary.getCalendar
import java.time.LocalDate

@Composable
fun EditNote(viewModel: EditNoteViewModel = hiltViewModel(), popUpScreen: () -> Unit) {
    val note = viewModel.note.value
    EditNoteContent(
        note = note,
        onTitleChange = viewModel::onTitleChange,
        onDescriptionChange = viewModel::onDescriptionChange,
        onDoneClick = { viewModel.onDoneClick(popUpScreen) },
        onDateChange = viewModel::onDateChange,
    )
}

@Composable
fun EditNoteContent(
    note: Note,
    onTitleChange: (String) -> Unit,
    onDescriptionChange: (String) -> Unit,
    onDoneClick: () -> Unit,
    onDateChange: (LocalDate) -> Unit,
) {
    Scaffold(floatingActionButton = { FloatingButton(onDoneClick) }) { paddingValue ->
        Surface(
            modifier = Modifier
                .padding(paddingValue)
        ) {
            val dateDialogState = rememberMaterialDialogState()
            DatePicker(dateDialogState = dateDialogState, onDateChange = onDateChange)
            Column {
                DateShower(calendar = getCalendar(note.date),
                    modifier = Modifier.clickable {
                        dateDialogState.show()
                    }
                )
                Image(imageVector = Icons.Rounded.SentimentNeutral, contentDescription = "Emoji")
                TextField(
                    value = note.title,
                    placeHolder = "Title",
                    onValueChange = onTitleChange,
                    modifier = Modifier.fillMaxWidth(),
                    fontSize = 30.sp
                )
                TextField(
                    value = note.description,
                    placeHolder = "Diary entry",
                    onValueChange = onDescriptionChange,
                    modifier = Modifier
                        .fillMaxHeight()
                        .fillMaxWidth(),
                    fontSize = 20.sp
                )
            }
        }
    }
}

@Composable
fun FloatingButton(onDoneClick: () -> Unit) {
    FloatingActionButton(onClick = { onDoneClick() }) {
        Icon(Icons.Rounded.Save, "Floating action button.")
    }
}

@Composable
fun TextField(
    value: String,
    placeHolder: String,
    modifier: Modifier,
    fontSize: TextUnit,
    onValueChange: (String) -> Unit
) {
    TextField(
        value = value,
        modifier = modifier,
        onValueChange = { newValue -> onValueChange(newValue) },
        placeholder = { Text(placeHolder, fontSize = fontSize) },
        colors = OutlinedTextFieldDefaults.colors(
            focusedBorderColor = Color.Transparent,
            unfocusedBorderColor = Color.Transparent,
        ),
        textStyle = TextStyle.Default.copy(fontSize = fontSize)
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

@Preview(showSystemUi = true)
@Composable
fun EditNoteScreenPreview() {
//    EditNoteContent(note = Note(), onTitleChange = {}, onDescriptionChange = {}, onDoneClick = {}, onDateChange = {}, activity = LocalContext as AppCompatActivity)
}