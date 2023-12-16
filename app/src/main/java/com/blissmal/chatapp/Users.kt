package com.blissmal.chatapp

import android.provider.ContactsContract.CommonDataKinds.Email

data class Users(
    var name: String? = null,
    var email: String? = null,
    var uid: String? = null
)