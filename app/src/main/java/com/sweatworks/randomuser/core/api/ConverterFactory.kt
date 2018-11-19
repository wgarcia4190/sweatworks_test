package com.sweatworks.randomuser.core.api

import okhttp3.MediaType
import okhttp3.RequestBody
import retrofit2.Converter
import retrofit2.Retrofit
import com.sweatworks.randomuser.util.Constants
import java.lang.reflect.Type

/**
 * @author Ing. Wilson Garcia
 * Created on 11/19/18
 */
class ConverterFactory: Converter.Factory(){
    private val MEDIA_TYPE = MediaType.parse(Constants.applicationJson)

    override fun requestBodyConverter(type: Type?, parameterAnnotations: Array<Annotation>?,
                                      methodAnnotations: Array<Annotation>?, retrofit: Retrofit?): Converter<*, RequestBody>? {

        return if (String::class.java == type) {
            Converter<String, RequestBody> { value -> RequestBody.create(MEDIA_TYPE, value) }
        } else null
    }
}