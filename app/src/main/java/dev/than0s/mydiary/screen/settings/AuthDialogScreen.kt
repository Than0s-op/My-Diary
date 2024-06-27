package dev.than0s.mydiary.screen.settings

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Row
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Facebook
import androidx.compose.material.icons.rounded.Mail
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import com.vanpra.composematerialdialogs.MaterialDialog
import com.vanpra.composematerialdialogs.MaterialDialogState
import com.vanpra.composematerialdialogs.rememberMaterialDialogState
import dev.than0s.mydiary.EMAIL_AUTH_SCREEN
import dev.than0s.mydiary.GOOGLE_AUTH_SCREEN


@Composable
fun AuthDialog(openScreen: (String) -> Unit) {
    val dialogState = rememberMaterialDialogState(true)
    AuthDialogContent(dialogState = dialogState, onSelected = openScreen)
}

@Composable
fun AuthDialogContent(dialogState: MaterialDialogState, onSelected: (String) -> Unit) {
    MaterialDialog(dialogState = dialogState) {
        Card {
            Row {
                DialogOption(icon = Icons.Rounded.Facebook) {
                    onSelected(GOOGLE_AUTH_SCREEN)
                    dialogState.hide()
                }
                DialogOption(icon = Icons.Rounded.Mail) {
                    onSelected(EMAIL_AUTH_SCREEN)
                    dialogState.hide()
                }
            }
        }
    }
}

@Composable
fun DialogOption(icon: ImageVector, onClicked: () -> Unit) {
    Button(onClick = { onClicked() }) {
        Image(imageVector = icon, contentDescription = icon.name)
    }
}

@Preview()
@Composable
fun AuthDialogPreview() {
    AuthDialogContent(dialogState = rememberMaterialDialogState(true)) {

    }
}