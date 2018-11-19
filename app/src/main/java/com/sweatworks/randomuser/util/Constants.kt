package com.sweatworks.randomuser.util

/**
 * @author Ing. Wilson Garcia
 * Created on 11/19/18
 */
class Constants private constructor(){

    companion object {
        const val contentType = "Content-Type"
        const val applicationJson = "application/json"
        const val BASE_DOMAIN = "https://randomuser.me/"
        const val RESULTS_PARAMETER = "results"
        const val SEED_PARAMETER = "seed"
        const val CUSTOM_SEED = "custom_seed"
        const val INC_PARAMETER = "inc"
        const val INC_VALUES = "name,email,phone,cell,location,picture"
    }
}