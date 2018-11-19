package com.sweatworks.randomuser.util

import android.content.Context
import android.content.SharedPreferences
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.sweatworks.randomuser.model.Contact
import rx.Observable

/**
 * @author Ing. Wilson Garcia
 * Created on 11/19/18
 */
class PreferenceUtils
constructor(context: Context) {

    private var sharedPreferences: SharedPreferences =
            context.getSharedPreferences("com.sweatworks.random_user", Context.MODE_PRIVATE)
    private var gson = Gson()
    private val type = object : TypeToken<MutableList<Contact>>() {}.type
    private var jsonName = "favorite_contacts"

    fun saveFavorites(contact:Contact){
        var favJson = sharedPreferences!!.getString(jsonName, "")
        var favoriteContactList = mutableListOf<Contact>()

        if(favJson.isNotEmpty()){
            favoriteContactList = gson.fromJson(favJson!!, type)
        }

        favoriteContactList.add(contact)
        favJson = gson.toJson(favoriteContactList)

        sharedPreferences!!.edit().putString(jsonName, favJson).apply()
    }

    fun getFavorites():MutableList<Contact>{
        val favJson = sharedPreferences!!.getString(jsonName, "")
        var favoriteContactList = arrayListOf<Contact>()

        if(favJson.isNotEmpty()){
            favoriteContactList = gson.fromJson(favJson!!, type)
        }

        return favoriteContactList
    }
}