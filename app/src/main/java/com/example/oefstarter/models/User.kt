package com.example.oefstarter.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class User(
    val id: Long,
    val email: String,
    var password: String
) : Parcelable