package com.sweatworks.randomuser.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import org.json.JSONObject

/**
 * @author Ing. Wilson Garcia
 * Created on 11/19/18
 */
@Parcelize
data class Name(val title: String, val first: String, val last: String): Parcelable {

    constructor(json: JSONObject): this(json.optString("title"),
            json.optString("first"), json.optString("last"))

    fun getFullname(): String{
        return "$first $last"
    }
}