package com.sweatworks.randomuser.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import org.json.JSONObject

/**
 * @author Ing. Wilson Garcia
 * Created on 11/19/18
 */
@Parcelize
data class Contact(val name: Name, val email: String, val phone: String, val cell: String,
                   val location: Location, val picture: Picture): Parcelable {

    var favorite: Boolean = false

    constructor(json: JSONObject): this(Name(json.optJSONObject("name")),
            json.optString("email"), json.optString("phone"),
            json.optString("cell"), Location(json.optJSONObject("location")),
            Picture(json.optJSONObject("picture")))

    override fun toString(): String {
        return this.name.getFullname()
    }
}