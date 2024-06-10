package dev.than0s.mydiary.screen.note

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.sp

@Preview(showSystemUi = true)
@Composable
fun Note() {
    Column {
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