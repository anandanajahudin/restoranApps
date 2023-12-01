package com.racodex.melly.screens.signup


import android.content.Context
import androidx.compose.runtime.mutableStateOf
import androidx.datastore.preferences.core.edit
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.racodex.melly.repositories.UserRepository
import com.racodex.melly.sealed.DataResponse
import com.racodex.melly.sealed.Error
import com.racodex.melly.sealed.UiState
import com.racodex.melly.utils.LOGGED_USER_ID
import com.racodex.melly.utils.UserPref
import com.racodex.melly.utils.dataStore
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * A View model with hiltViewModel annotation that is used to access this view model everywhere needed
 */
@HiltViewModel
class SignupViewModel @Inject constructor(
    private val userRepository: UserRepository,
    private val context: Context,
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

    fun authenticateUser(
        emailOrPhone: String,
        password: String,
        onAuthenticated: () -> Unit,
        onAuthenticationFailed: () -> Unit,
    ) {
        if (emailOrPhone.isBlank() || password.isBlank()) onAuthenticationFailed()
        else {
            uiState.value = UiState.Loading
            /** We will use the coroutine so that we don't block our dear : The UiThread */
            viewModelScope.launch {
                delay(3000)
                userRepository.signInUser(
                    email = emailOrPhone,
                    password = password,
                ).let {
                    when (it) {
                        is DataResponse.Success -> {
                            it.data?.let { user ->
                                /** Authenticated successfully */
                                uiState.value = UiState.Success
                                UserPref.updateUser(user = user)
                                /** save user id */
                                saveUserIdToPreferences(userId = user.userId)
                                onAuthenticated()
                            }
                        }
                        is DataResponse.Error -> {
                            /** An error occurred while authenticating */
                            uiState.value = UiState.Error(error = it.error ?: Error.Network)
                            onAuthenticationFailed()
                        }
                    }
                }
            }
        }
    }

    private suspend fun saveUserIdToPreferences(userId: Int) {
        context.dataStore.edit {
            it[LOGGED_USER_ID] = userId
        }
    }
}