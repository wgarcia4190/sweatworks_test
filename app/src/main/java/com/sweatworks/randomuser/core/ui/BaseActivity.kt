package com.sweatworks.randomuser.core.ui

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.sweatworks.randomuser.core.presenter.BasePresenter

/**
 * @author Ing. Wilson Garcia
 * Created on 11/19/18
 */
open class BaseActivity(private val layout: Int, protected var presenter: BasePresenter? = null): AppCompatActivity(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(layout)
    }

    override fun onStart() {
        super.onStart()
        presenter?.subscribe()
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter?.unsubscribe()
    }
}