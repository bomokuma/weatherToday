package com.bzios.mecha.weathertoday.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class BaseApiResponse<T> {

    @SerializedName("success")
    @Expose
    internal val success: Boolean = false

    @SerializedName("data")
    @Expose
    internal var data: T? = null

    @SerializedName("massage")
    @Expose
    internal val message: String = ""
}
