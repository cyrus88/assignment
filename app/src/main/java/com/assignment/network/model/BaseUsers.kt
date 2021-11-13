package com.assignment.network.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class BaseUsers(
    @SerializedName("data")
    @Expose
    var userlist: MutableList<Users>? = null
)
