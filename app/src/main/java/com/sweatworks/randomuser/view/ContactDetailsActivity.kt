package com.sweatworks.randomuser.view

import android.app.AlertDialog
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.provider.ContactsContract
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import com.bumptech.glide.Glide
import com.sweatworks.randomuser.core.ui.BaseActivity
import com.sweatworks.randomuser.model.Contact
import com.sweatworks.randomuser.util.PreferenceUtils
import kotlinx.android.synthetic.main.contact_details_activity.*
import sweatworks.com.randomuser.R


/**
 * @author Ing. Wilson Garcia
 * Created on 11/19/18
 */
class ContactDetailsActivity : BaseActivity(R.layout.contact_details_activity) {

    private lateinit var contact: Contact
    private lateinit var preferenceUtils: PreferenceUtils

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        contact = intent.getParcelableExtra("contact")

        preferenceUtils = PreferenceUtils(this)
        this.fetchContactData()
        this.setupEvents()

        for(favContact in preferenceUtils.getFavorites()){
            if (contact.name.getFullname() == favContact.name.getFullname()){
                contact.favorite = true
            }
        }

    }

    private fun fetchContactData() {
        Glide.with(this).load(contact.picture.large).into(contactImage)
        username.text = contact.name.getFullname()
        email.text = contact.email
        phone.text = contact.phone
        mobile.text = contact.cell
        address.text = contact.location.getLocation()
    }

    private fun setupEvents() {
        addContactButton.setOnClickListener {
            val intent = Intent(Intent.ACTION_INSERT)
            intent.type = ContactsContract.Contacts.CONTENT_TYPE;

            intent.putExtra(ContactsContract.Intents.Insert.NAME, contact.name.getFullname())
            intent.putExtra(ContactsContract.Intents.Insert.EMAIL, contact.email)
            intent.putExtra(ContactsContract.Intents.Insert.PHONE, contact.cell)

            if (Integer.valueOf(Build.VERSION.SDK) > 14)
                intent.putExtra("finishActivityOnSaveCompleted", true)
            startActivityForResult(intent, 1000)

        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.details, menu)
        if (contact.favorite) {
            menu?.findItem(R.id.action_no_fav)?.isVisible = false
        } else {
            menu?.findItem(R.id.action_fav)?.isVisible = false
        }

        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        if (item?.itemId == android.R.id.home) {
            this.finish()
        } else if (item?.itemId == R.id.action_no_fav) {
            showAddFavoriteConfirmation()
        }

        return super.onOptionsItemSelected(item)
    }

    private fun showAddFavoriteConfirmation() {
        val builder = AlertDialog.Builder(this)
        builder.setMessage(getString(R.string.add_favorites_confirmation))
        builder.setCancelable(true)

        builder.setPositiveButton(
                getString(R.string.btn_accept)
        ) { dialog, _ ->
            saveToFavorites()
            invalidateOptionsMenu()

            Toast.makeText(this, getString(R.string.saved_successfully), Toast.LENGTH_LONG).show()

            dialog.cancel()
        }

        builder.setNegativeButton(
                getString(R.string.btn_cancel)
        ) { dialog, _ -> dialog.cancel() }

        val alertDialog = builder.create()
        alertDialog.show()
    }

    private fun saveToFavorites() {
        contact.favorite = true
        preferenceUtils.saveFavorites(contact)
    }


}