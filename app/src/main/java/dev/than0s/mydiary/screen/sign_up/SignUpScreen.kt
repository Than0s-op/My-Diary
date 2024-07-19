package dev.than0s.mydiary.screen.sign_up

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Android
import androidx.compose.material.icons.rounded.Facebook
import androidx.compose.material3.Button
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import dev.than0s.mydiary.screen.sign_up.google.GoogleScreen

@Composable
fun SignUpScreen(viewModel: SignUpViewModel = hiltViewModel(), restartApp: () -> Unit) {
    SignUpScreenContent(restartApp)
}

@Composable
private fun SignUpScreenContent(restartApp: () -> Unit) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var showGoogleAuth by remember { mutableStateOf(false) }
    var passwordVisibility by remember { mutableStateOf(false) }
    Column {
        TextField(value = email, onValueChange = { newValue ->
            email = newValue
        }, label = { Text(emailPlaceHolder) }, singleLine = true
        )
        TextField(
            value = password,
            onValueChange = { newValue ->
                password = newValue
            },
            visualTransformation = if (passwordVisibility) VisualTransformation.None else PasswordVisualTransformation(),
            label = { Text(passwordPlaceHolder) },
            trailingIcon = {
                IconButton(onClick = {
                    passwordVisibility = !passwordVisibility
                }) {
                    Icon(
                        imageVector = Icons.Rounded.Android,
                        contentDescription = Icons.Rounded.Android.name
                    )
                }
            },
            singleLine = true
        )
        ElevatedButton(onClick = {

        }) {
            Text(signUpLabel)
        }
        IconButton(onClick = {
            showGoogleAuth = !showGoogleAuth
        }) {
            Icon(
                imageVector = Icons.Rounded.Facebook,
                contentDescription = "Localized description",
            )
        }
    }
    if (showGoogleAuth) {
        GoogleScreen(restartApp = restartApp)
        println("I'm here")
    }
}

@Preview(showSystemUi = true)
@Composable
private fun SignUpScreenPreview() {
    SignUpScreenContent({})
}

const val emailPlaceHolder = "Email"
const val passwordPlaceHolder = "Password"
const val signUpLabel = "Sign Up"