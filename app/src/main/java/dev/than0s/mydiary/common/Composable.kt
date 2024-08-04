package dev.than0s.mydiary.common

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Delete
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable

@Composable
fun ShowAlertDialog(
    title: String,
    message: String,
    onDismiss: () -> Unit,
    onConformation: () -> Unit
) {
    AlertDialog(
        icon = {
            Icon(imageVector = Icons.Rounded.Delete, contentDescription = "Delete")
        },
        title = {
            Text(text = title)
        },
        text = {
            Text(text = message)
        },
        onDismissRequest = {
            onDismiss()
        },
        confirmButton = {
            TextButton(onClick = onConformation) {
                Text(text = "Yes")
            }
        },
        dismissButton = {
            TextButton(onClick = onDismiss) {
                Text(text = "No")
            }
        }
    )
}