package dev.than0s.mydiary.screen.edit_note

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.SentimentNeutral
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.sp
import dev.than0s.mydiary.screen.diary.DateShower
import dev.than0s.mydiary.screen.diary.Note
import dev.than0s.mydiary.screen.diary.getCalendar

@Composable
fun Note(note: Note) {
    Column {
        DateShower(calendar = getCalendar(note.date))
        Image(imageVector = Icons.Rounded.SentimentNeutral, contentDescription = "Emoji")
        TextField(placeHolder = "Title", modifier = Modifier.fillMaxWidth(), fontSize = 30.sp)
        TextField(
            placeHolder = "Diary entry",
            modifier = Modifier
                .fillMaxHeight()
                .fillMaxWidth(),
            fontSize = 20.sp
        )
    }
}

@Composable
fun TextField(placeHolder: String, modifier: Modifier, fontSize: TextUnit) {
    val noteText = remember { mutableStateOf("") }
    TextField(
        value = noteText.value,
        modifier = modifier,
        onValueChange = { noteText.value = it },
        placeholder = { Text(placeHolder, fontSize = fontSize) },
        colors = OutlinedTextFieldDefaults.colors(
            focusedBorderColor = Color.Transparent,
            unfocusedBorderColor = Color.Transparent,
        ),
        textStyle = TextStyle.Default.copy(fontSize = fontSize)
    )
}