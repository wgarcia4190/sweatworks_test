package com.sweatworks.randomuser.core.service

import android.content.Context
import com.sweatworks.randomuser.core.api.APICore
import com.sweatworks.randomuser.util.Constants
import okhttp3.ResponseBody
import retrofit2.http.GET
import retrofit2.http.Query
import rx.Observable

/**
 * @author Ing. Wilson Garcia
 * Created on 11/19/18
 */
class ContactService(context: Context?): Service<ContactService.ContactAPI>, APICore(context, Constants.BASE_DOMAIN){


    override fun getApi(): ContactAPI {
        return retrofit!!.create(ContactAPI::class.java)
    }


    interface ContactAPI {
        @GET("api")
        fun getContacts(@Query("page") page: Int): Observable<ResponseBody>
    }
}