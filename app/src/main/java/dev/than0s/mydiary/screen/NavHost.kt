package dev.than0s.mydiary.screen

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.MenuBook
import androidx.compose.material.icons.rounded.CalendarMonth
import androidx.compose.material.icons.rounded.Lightbulb
import androidx.compose.material.icons.rounded.Settings
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import dagger.hilt.android.AndroidEntryPoint
import dev.than0s.mydiary.CALENDAR_SCREEN
import dev.than0s.mydiary.CREATE_ACCOUNT
import dev.than0s.mydiary.DELETE_ACCOUNT
import dev.than0s.mydiary.DIARY_SCREEN
import dev.than0s.mydiary.EDIT_NOTE_SCREEN
import dev.than0s.mydiary.ID
import dev.than0s.mydiary.INSIGHTS_SCREEN
import dev.than0s.mydiary.SETTING_SCREEN
import dev.than0s.mydiary.SIGN_IN
import dev.than0s.mydiary.SIGN_OUT
import dev.than0s.mydiary.SPLASH_SCREEN
import dev.than0s.mydiary.screen.calendar.Calendar
import dev.than0s.mydiary.screen.diary.DiaryScreen
import dev.than0s.mydiary.screen.edit_note.EditNote
import dev.than0s.mydiary.screen.insights.Insights
import dev.than0s.mydiary.screen.log_in.GoogleAuth
import dev.than0s.mydiary.screen.settings.Settings
import dev.than0s.mydiary.screen.sign_up.SignUpScreen
import dev.than0s.mydiary.screen.splash.SplashScreen
import dev.than0s.mydiary.ui.theme.MyDiaryTheme

@AndroidEntryPoint
class NavHost : ComponentActivity() {

    private lateinit var navController: NavHostController
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyDiaryTheme {
                App()
            }
        }
    }

    @Preview
    @Composable
    fun App() {
        navController = rememberNavController()
        val mutableState = remember { mutableStateOf(DIARY_SCREEN) }

        Scaffold(
            topBar = { AppBar(mutableState) },
            bottomBar = { NavigationBar(navController = navController) }
        ) { paddingValue ->
            Surface(
                modifier = Modifier
                    .padding(paddingValue)
            ) {
                NavHost(navController = navController, startDestination = SPLASH_SCREEN) {
                    composable(route = SPLASH_SCREEN) {
                        SplashScreen { route ->
                            navController.popBackStack()
                            navController.navigate(route)
                        }
                    }
                    composable(route = DIARY_SCREEN) {
                        DiaryScreen(
                            openScreen = { route ->
                                navController.navigate(route)
                            }
                        )
                        mutableState.value = DIARY_SCREEN
                    }
                    composable(route = CALENDAR_SCREEN) {
                        Calendar()
                        mutableState.value = CALENDAR_SCREEN
                    }
                    composable(route = INSIGHTS_SCREEN) {
                        Insights()
                        mutableState.value = INSIGHTS_SCREEN
                    }
                    composable(route = SETTING_SCREEN) {
                        Settings(
                            openScreen = { route ->
                                navController.navigate(route)
                            },
                            restartApp = ::restartApp
                        )
                        mutableState.value = SETTING_SCREEN
                    }
                    composable(
                        route = "$EDIT_NOTE_SCREEN/{$ID}",
                        arguments = listOf(
                            navArgument(ID) {
                                type = NavType.StringType
                            }
                        )
                    ) {
                        EditNote(popUpScreen = navController::popBackStack)
                        mutableState.value = EDIT_NOTE_SCREEN
                    }
                    composable(route = SIGN_IN) {
                        SignUpScreen(restartApp = ::restartApp)
                    }
                    composable(route = DELETE_ACCOUNT) {

                    }
                }
            }
        }
    }


    @Composable
    fun NavigationBar(navController: NavController) {
        var selectedItem by remember { mutableIntStateOf(0) }
        val items = listOf(
            Pair(DIARY_SCREEN, Icons.AutoMirrored.Rounded.MenuBook),
            Pair("Calendar", Icons.Rounded.CalendarMonth),
            Pair("Insights", Icons.Rounded.Lightbulb),
            Pair("Settings", Icons.Rounded.Settings)
        )

        NavigationBar {
            items.forEachIndexed { index, item ->
                NavigationBarItem(
                    icon = { Icon(item.second, contentDescription = item.first) },
                    label = { Text(item.first) },
                    selected = selectedItem == index,
                    onClick = {
                        selectedItem = index
                        navController.popBackStack()
                        navController.navigate(route = item.first)
                    },
                )
            }
        }
    }

    private fun restartApp() {
        navController.navigate(SPLASH_SCREEN) {
            launchSingleTop = true
            popUpTo(0) { inclusive = true }
        }
    }

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun AppBar(text: MutableState<String>) {
        TopAppBar(
            colors = TopAppBarDefaults.topAppBarColors(
                containerColor = MaterialTheme.colorScheme.primaryContainer,
                titleContentColor = MaterialTheme.colorScheme.primary,
            ),
            title = {
                Text(text = text.value)
            }
        )
    }
}