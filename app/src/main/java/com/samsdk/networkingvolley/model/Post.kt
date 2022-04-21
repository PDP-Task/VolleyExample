package com.samsdk.networkingvolley.model

import android.os.Parcelable

data class Post(
    val id: Int,
    val avatar_url: String,
    val login: String,
    val site_admin: Boolean,
    val type: String
)