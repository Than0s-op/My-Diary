package dev.than0s.mydiary.screen.splash

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import dev.than0s.mydiary.DIARY_SCREEN

@Composable
fun SplashScreen(viewModel: SplashViewModel = hiltViewModel(), popAndOpen: (String) -> Unit) {
    viewModel.loadUser {
        popAndOpen(DIARY_SCREEN)
    }
}

@Composable
fun SplashScreenContent() {

}

@Preview(showSystemUi = true)
@Composable
fun SplashScreenPreview() {

}