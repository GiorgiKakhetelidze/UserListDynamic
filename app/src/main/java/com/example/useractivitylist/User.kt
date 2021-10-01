package com.example.useractivitylist

import android.os.Parcelable
import kotlinx.parcelize.Parcelize


@Parcelize
data class User(
    val name: String,
    val lastName: String,
    val mail: String
) : Parcelable
