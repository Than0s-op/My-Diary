package dev.than0s.mydiary.screen.settings

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import dev.than0s.mydiary.BottomBar
import dev.than0s.mydiary.ButtonActions
import dev.than0s.mydiary.CREATE_ACCOUNT
import dev.than0s.mydiary.DELETE_ACCOUNT
import dev.than0s.mydiary.SIGN_IN
import dev.than0s.mydiary.SIGN_IN_SCREEN
import dev.than0s.mydiary.SIGN_OUT
import dev.than0s.mydiary.SIGN_UP_SCREEN
import dev.than0s.mydiary.ScaffoldState

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
    ScaffoldState.floatingActionButtonState = remember {
        ButtonActions(visibility = false)
    }
    ScaffoldState.bottomBarState = remember {
        BottomBar()
    }
    Column {
        if (isAnonymous) {
            Option(CREATE_ACCOUNT) {
                openScreen(SIGN_UP_SCREEN)
            }
            Option(SIGN_IN) {
                openScreen(SIGN_IN_SCREEN)
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