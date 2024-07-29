package dev.than0s.mydiary.screen

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Note
import androidx.compose.material.icons.automirrored.filled.Notes
import androidx.compose.material.icons.automirrored.outlined.Note
import androidx.compose.material.icons.automirrored.outlined.Notes
import androidx.compose.material.icons.automirrored.rounded.MenuBook
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material.icons.rounded.Settings
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import dev.than0s.mydiary.DIARY_SCREEN
import dev.than0s.mydiary.EDIT_NOTE_SCREEN
import dev.than0s.mydiary.ID
import dev.than0s.mydiary.SETTING_SCREEN
import dev.than0s.mydiary.SIGN_IN_SCREEN
import dev.than0s.mydiary.SIGN_UP_SCREEN
import dev.than0s.mydiary.SPLASH_SCREEN
import dev.than0s.mydiary.screen.diary.DiaryScreen
import dev.than0s.mydiary.screen.edit_note.EditNote
import dev.than0s.mydiary.screen.settings.Settings
import dev.than0s.mydiary.screen.sign_in.SignInScreen
import dev.than0s.mydiary.screen.sign_up.SignUpScreen
import dev.than0s.mydiary.screen.splash.SplashScreen
import dev.than0s.mydiary.AppState


@Composable
fun NavGraphHost() {
    NavGraphHostContent()
}

@Composable
private fun NavGraphHostContent() {
    val navController = rememberNavController()
    val appBarTitle = remember { mutableStateOf(DIARY_SCREEN) }
    AppState.snackbarHostState = remember { SnackbarHostState() }

    Scaffold(
        topBar = { AppBar(appBarTitle) },
        bottomBar = { NavigationBar(navController = navController) },
        snackbarHost = { SnackbarHost(hostState = AppState.snackbarHostState) }
    ) { paddingValue ->
        Surface(
            modifier = Modifier
                .padding(paddingValue)
        ) {
            Graph(
                navController = navController,
                appBarTitle = appBarTitle
            )
        }
    }
}

@Composable
private fun Graph(navController: NavHostController, appBarTitle: MutableState<String>) {
    NavHost(navController = navController, startDestination = SPLASH_SCREEN) {
        composable(route = SPLASH_SCREEN) {
            SplashScreen(
                popAndOpen = navController::popAndOpen
            )
        }
        composable(route = DIARY_SCREEN) {
            DiaryScreen(
                openScreen = navController::openScreen
            )
            appBarTitle.value = DIARY_SCREEN
        }
        composable(route = SETTING_SCREEN) {
            Settings(
                openScreen = navController::openScreen,
                restartApp = navController::restartApp
            )
            appBarTitle.value = SETTING_SCREEN
        }
        composable(
            route = "$EDIT_NOTE_SCREEN/{$ID}",
            arguments = listOf(
                navArgument(ID) {
                    type = NavType.StringType
                }
            )
        ) {
            EditNote(
                popUpScreen = navController::popBackStack
            )
            appBarTitle.value = EDIT_NOTE_SCREEN
        }
        composable(route = SIGN_IN_SCREEN) {
            SignInScreen(
                restartApp = navController::restartApp
            )
        }
        composable(route = SIGN_UP_SCREEN) {
            SignUpScreen(
                restartApp = navController::restartApp
            )
        }
    }
}

data class NavigationBarItem(
    val title: String,
    val selectedIcon: ImageVector,
    val unselectedIcon: ImageVector,
)

@Composable
private fun NavigationBar(navController: NavHostController) {
    var selectedItem by remember { mutableIntStateOf(0) }
    val items = listOf(
        NavigationBarItem(
            DIARY_SCREEN,
            Icons.AutoMirrored.Filled.Note,
            Icons.AutoMirrored.Outlined.Note
        ),
        NavigationBarItem(
            SETTING_SCREEN,
            Icons.Filled.Settings,
            Icons.Outlined.Settings
        ),
    )

    NavigationBar {
        items.forEachIndexed { index, item ->
            NavigationBarItem(
                icon = {
                    Icon(
                        if (index == selectedItem) {
                            item.selectedIcon
                        } else item.unselectedIcon,
                        contentDescription = item.title
                    )
                },
                label = { Text(item.title) },
                selected = selectedItem == index,
                onClick = {
                    selectedItem = index
                    navController.popAndOpen(item.title)
                },
            )
        }
    }
}

private fun NavHostController.openScreen(screen: String) {
    navigate(screen)
}

private fun NavHostController.popAndOpen(screen: String) {
    popBackStack()
    navigate(screen)
}

private fun NavHostController.restartApp() {
    navigate(SPLASH_SCREEN) {
        launchSingleTop = true
        popUpTo(0) { inclusive = true }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppBar(text: MutableState<String>) {
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()
    TopAppBar(
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer,
            titleContentColor = MaterialTheme.colorScheme.primary,
        ),
        title = {
            Text(text = text.value)
        },
        scrollBehavior = scrollBehavior
    )
}

@Preview
@Composable
private fun NavGraphHostPreview() {
    NavGraphHostContent()
}