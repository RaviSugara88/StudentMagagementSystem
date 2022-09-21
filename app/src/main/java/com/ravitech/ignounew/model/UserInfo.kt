package com.ravitech.ignounew.model

import com.google.firebase.database.IgnoreExtraProperties

@IgnoreExtraProperties
data class UserInfo(
    var name: String? = ""
)