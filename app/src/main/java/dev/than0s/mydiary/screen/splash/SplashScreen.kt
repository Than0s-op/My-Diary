package dev.than0s.mydiary.screen.splash

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import dev.than0s.mydiary.DIARY_SCREEN

@Composable
fun SplashScreen(viewModel: SplashViewModel = hiltViewModel(), popAndOpen: (String) -> Unit) {
    viewModel.createAnonymousUser {
        popAndOpen(DIARY_SCREEN)
    }
}