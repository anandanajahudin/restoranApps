package com.racodex.melly.sealed

import com.racodex.melly.sealed.Error as ErrorBody

sealed class UiState {
    object Idle : UiState()
    object Loading : UiState()
    object Success : UiState()
    class Error(val error: ErrorBody) : UiState()
}
