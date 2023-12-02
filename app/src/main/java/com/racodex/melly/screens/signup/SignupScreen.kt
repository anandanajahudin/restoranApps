package com.racodex.melly.screens.signup

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.racodex.melly.sealed.UiState

@Composable
fun SignupScreen(
    signupViewModel: SignupViewModel = hiltViewModel(),
) {
    Column(
        modifier = Modifier.fillMaxSize(

        )
    ) {
        val uiState by remember { signupViewModel.uiState }
        when (uiState) {
            is UiState.Idle -> {}
            is UiState.Loading -> {}
            is UiState.Success -> {}
            is UiState.Error -> {}
        }
    }
}