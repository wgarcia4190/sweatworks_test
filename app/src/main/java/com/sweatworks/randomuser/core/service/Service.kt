package com.sweatworks.randomuser.core.service

/**
 * @author Ing. Wilson Garcia
 * Created on 11/19/18
 */
interface Service<T> {
    fun getApi(): T
}