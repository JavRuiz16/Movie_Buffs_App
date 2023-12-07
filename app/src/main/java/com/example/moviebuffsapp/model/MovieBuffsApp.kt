package com.example.moviebuffsapp.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class MovieBuffsApp(
    @StringRes val stringResourceId: Int,
    @DrawableRes val imageResourceId: Int,
)
