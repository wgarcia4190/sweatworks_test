package com.sweatworks.randomuser.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import org.json.JSONObject

/**
 * @author Ing. Wilson Garcia
 * Created on 11/19/18
 */
@Parcelize
data class Location(val street: String, val city: String, val state: String): Parcelable {
    constructor(json: JSONObject): this(json.optString("street"), json.optString("city"), json.optString("state"))

    fun getLocation(): String {
        return "$street, $city, $state"
    }
}