package com.sweatworks.randomuser.presenter

import android.content.Context
import com.sweatworks.randomuser.core.presenter.BasePresenter
import com.sweatworks.randomuser.core.service.ContactService
import com.sweatworks.randomuser.model.Contact
import org.json.JSONArray
import org.json.JSONObject
import rx.Subscription
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers
import rx.subscriptions.CompositeSubscription

/**
 * @author Ing. Wilson Garcia
 * Created on 11/19/18
 */
class ContactPresenter: ContactContract.Presenter {

    private lateinit var contactService: ContactService
    private lateinit var view: ContactContract.View
    private var subscriptions: CompositeSubscription? = null

    override fun initPresenter(context: Context, view: BasePresenter.View) {
        this.contactService = ContactService(context)
        this.view = view as ContactContract.View
    }


    override fun getContactList(page: Int) {
        val subscription: Subscription
        subscription = contactService.getApi().getContacts(page)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ response ->
                    val jsonObject = JSONObject(response.string())
                    val contactsArray: JSONArray = jsonObject.optJSONArray("results")
                    val contactList: MutableList<Contact> = mutableListOf()

                    for(index in 0 until contactsArray.length()){
                        val contact = Contact(contactsArray.getJSONObject(index))
                        contactList.add(contact)
                    }

                    this.view.setContacts(contactList)
                },{ t: Throwable? ->
                    t?.printStackTrace()
                })

        subscriptions?.add(subscription)
    }

    override fun subscribe() {
        subscriptions = CompositeSubscription()
    }

    override fun unsubscribe() {
        subscriptions?.unsubscribe()
        subscriptions?.clear()
    }


}