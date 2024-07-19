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
fun Settings(
    viewModel: SettingsViewModel = hiltViewModel(),
    openScreen: (String) -> Unit,
    restartApp: () -> Unit
) {
    SettingsContent(
        isAnonymous = viewModel.isAnonymous(),
        onSignOutClick = { viewModel.onSignOutClick(restartApp) },
        onDeleteAccountClick = { viewModel.onDeleteAccountMyClick(restartApp) },
        openScreen = openScreen,
    )
}

@Composable
fun SettingsContent(
    isAnonymous: Boolean,
    onSignOutClick: () -> Unit,
    onDeleteAccountClick: () -> Unit,
    openScreen: (String) -> Unit,
) {
    Column {
        if (isAnonymous) {
            Option(CREATE_ACCOUNT) {
                openScreen(CREATE_ACCOUNT)
            }
            Option(SIGN_IN) {
                openScreen(SIGN_IN)
            }
        } else {
            Option(SIGN_OUT, onSignOutClick)
            Option(DELETE_ACCOUNT, onDeleteAccountClick)
        }
    }
}

@Composable
fun Option(title: String, onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .clickable {
                onClick()
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
    SettingsContent(false, {}, {}, {})
}