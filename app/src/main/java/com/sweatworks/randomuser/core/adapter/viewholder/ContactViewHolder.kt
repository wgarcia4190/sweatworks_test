package com.sweatworks.randomuser.core.adapter.viewholder

import android.content.Context
import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.sweatworks.randomuser.model.Contact
import com.sweatworks.randomuser.view.ContactDetailsActivity
import sweatworks.com.randomuser.R

/**
 * @author Ing. Wilson Garcia
 * Created on 11/19/18
 */
class ContactViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){

    private val contactImage: ImageView by lazy {
        itemView.findViewById(R.id.contactImage) as ImageView
    }

    private val contactName: TextView by lazy {
        itemView.findViewById(R.id.contactName) as TextView
    }

    fun bindElement(contact: Contact, context: Context){
        Glide.with(context).load(contact.picture.thumbnail).into(contactImage)
        contactName.text = contact.name.getFullname()

        contactImage.setOnClickListener {
            val intent = Intent(context, ContactDetailsActivity::class.java)
            intent.putExtra("contact", contact)

            context.startActivity(intent)
        }
    }
}