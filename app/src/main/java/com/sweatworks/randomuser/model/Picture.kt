package com.sweatworks.randomuser.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import org.json.JSONObject

/**
 * @author Ing. Wilson Garcia
 * Created on 11/19/18
 */
@Parcelize
data class Picture(val thumbnail: String, val medium: String, val large: String): Parcelable {
    constructor(json: JSONObject): this(json.optString("thumbnail"),
            json.optString("medium"), json.optString("large"))
}