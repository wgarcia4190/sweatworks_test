package com.sweatworks.randomuser.presenter

import com.sweatworks.randomuser.core.presenter.BasePresenter
import com.sweatworks.randomuser.model.Contact

/**
 * @author Ing. Wilson Garcia
 * Created on 11/19/18
 */
interface ContactContract {

    interface View: BasePresenter.View {
        fun setContacts(contacts: MutableList<Contact>)
    }

    interface Presenter: BasePresenter {
        fun getContactList(page: Int)
    }
}