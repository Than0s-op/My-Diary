package dev.than0s.mydiary.screen

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
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
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import dev.than0s.mydiary.screen.calendar.Calendar
import dev.than0s.mydiary.screen.diary.Diary
import dev.than0s.mydiary.screen.insights.Insights
import dev.than0s.mydiary.screen.settings.Settings

class NavHost : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            App()
        }
    }

    @Preview
    @Composable
    fun App() {
        val navController = rememberNavController()
        val mutableState = remember { mutableStateOf("Diary") }
        Scaffold(
            topBar = { AppBar(mutableState) },
            bottomBar = { NavigationBar(navController = navController) }
        ) { paddingValue ->
            Surface(
                modifier = Modifier
                    .padding(paddingValue)
            ) {
                NavHost(navController = navController, startDestination = "Diary") {
                    composable(route = "Diary") {
                        Diary()
                        mutableState.value = "Diary"
                    }
                    composable(route = "Calendar") {
                        Calendar()
                        mutableState.value = "Calendar"
                    }
                    composable(route = "Insights") {
                        Insights()
                        mutableState.value = "Insights"
                    }
                    composable(route = "Settings") {
                        Settings()
                        mutableState.value = "Settings"
                    }
                }
            }
        }
    }


    @Composable
    fun NavigationBar(navController: NavController) {
        var selectedItem by remember { mutableIntStateOf(0) }
        val items = listOf(
            Pair("Diary", Icons.AutoMirrored.Rounded.MenuBook),
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
                        navController.navigate(route = item.first)
                    },
                )
            }
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