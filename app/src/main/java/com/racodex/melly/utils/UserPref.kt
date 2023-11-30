package com.racodex.melly.utils

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import com.racodex.melly.models.User

object UserPref {
    private val _user = mutableStateOf<User?>(null)
    val user: State<User?> = _user

    fun updateUser(user: User){
        _user.value = user
    }
}