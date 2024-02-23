package com.example.a30daysofrecipes.data

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class Recipe(
    @StringRes val dayNumberRes: Int,
    @StringRes val titleRes: Int,
    @StringRes val urlRes: Int,
    @DrawableRes val imageRes: Int,
)
