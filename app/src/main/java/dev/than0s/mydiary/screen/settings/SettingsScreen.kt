package dev.than0s.mydiary.screen.settings

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import dev.than0s.mydiary.CREATE_ACCOUNT
import dev.than0s.mydiary.DELETE_ACCOUNT
import dev.than0s.mydiary.SIGN_IN
import dev.than0s.mydiary.SIGN_OUT

@Composable
fun Settings(viewModel: SettingsViewModel = hiltViewModel(), openScreen: (String) -> Unit) {
    SettingsContent(
        isAnonymous = viewModel.accountService.isAnonymous,
        openScreen = openScreen
    )
}

@Composable
fun SettingsContent(isAnonymous: Boolean, openScreen: (String) -> Unit) {
    Column {
        if (isAnonymous) {
            Option(CREATE_ACCOUNT, openScreen)
            Option(SIGN_IN, openScreen)
        } else {
            Option(SIGN_OUT, openScreen)
            Option(DELETE_ACCOUNT, openScreen)
        }
    }
}

@Composable
fun Option(title: String, onClick: (String) -> Unit) {
    Card(
        modifier = Modifier
            .clickable {
                onClick(title)
            }
            .fillMaxWidth()
    ) {
        Text(text = title)
    }
    Spacer(modifier = Modifier.padding(5.dp))
}

@Preview(showSystemUi = true)
@Composable
fun SettingsPreview() {
    SettingsContent(true, {})
}

const val GOOGLE_AUTH = "Google Auth"
const val EMAIL_AUTH = "Email Auth"