package com.sweatworks.randomuser.core.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.sweatworks.randomuser.core.adapter.viewholder.ContactViewHolder
import com.sweatworks.randomuser.model.Contact
import sweatworks.com.randomuser.R

/**
 * @author Ing. Wilson Garcia
 * Created on 11/19/18
 */
class ContactListAdapter(val contacts: MutableList<Contact>, val context: Context): RecyclerView.Adapter<ContactViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContactViewHolder {
        val view : View = LayoutInflater.from(parent.context)
                .inflate(R.layout.contact_list_item, parent, false)

        return ContactViewHolder(view)
    }

    override fun getItemCount(): Int {
        return contacts.size
    }

    override fun onBindViewHolder(holder: ContactViewHolder, position: Int) {
        holder.bindElement(contacts[position], context)
    }

    fun addData(newContacts: MutableList<Contact>){
        this.contacts.addAll(newContacts)
        this.notifyDataSetChanged()
    }

}