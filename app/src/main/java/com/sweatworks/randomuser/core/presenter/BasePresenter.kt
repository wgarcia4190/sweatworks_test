package com.sweatworks.randomuser.core.presenter

import android.content.Context

/**
 * @author Ing. Wilson Garcia
 * Created on 11/19/18
 */
interface BasePresenter {

    interface View

    fun subscribe()
    fun unsubscribe()
    fun initPresenter(context: Context, view: BasePresenter.View)
}