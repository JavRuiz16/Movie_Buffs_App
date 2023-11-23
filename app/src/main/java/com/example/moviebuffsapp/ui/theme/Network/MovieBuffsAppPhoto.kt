package com.example.moviebuffsapp.ui.theme.Network

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class MovieBuffsAppPhoto(
    val id: String,
    @SerialName(value = "img_src")
    val imgSrc: String
)

