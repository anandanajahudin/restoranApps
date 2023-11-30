package com.racodex.melly.models

import androidx.annotation.DrawableRes

data class OnboardItem(
    @DrawableRes val image: Int,
    val text: String,
)
