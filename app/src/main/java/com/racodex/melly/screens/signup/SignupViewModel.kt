package com.racodex.melly.screens.signup


import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.racodex.melly.repositories.UserRepository
import com.racodex.melly.sealed.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

/**
 * A View model with hiltViewModel annotation that is used to access this view model everywhere needed
 */
@HiltViewModel
class SignupViewModel @Inject constructor(
    private val userRepository: UserRepository,
) : ViewModel() {
    val uiState = mutableStateOf<UiState>(UiState.Idle)
    val emailOrPhone = mutableStateOf<String?>("racodex@gmail.com")
    val password = mutableStateOf<String?>("12345678")

    fun updateEmailOrPhone(value: String?) {
        this.emailOrPhone.value = value
    }

    fun updatePassword(value: String?) {
        this.password.value = value
    }

}