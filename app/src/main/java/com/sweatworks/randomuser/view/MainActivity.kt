package com.sweatworks.randomuser.view

import android.app.SearchManager
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.SearchView
import android.view.Menu
import android.view.View
import android.widget.AbsListView
import android.widget.ArrayAdapter
import com.sweatworks.randomuser.core.adapter.ContactListAdapter
import com.sweatworks.randomuser.core.ui.BaseActivity
import com.sweatworks.randomuser.model.Contact
import com.sweatworks.randomuser.presenter.ContactContract
import com.sweatworks.randomuser.presenter.ContactPresenter
import com.sweatworks.randomuser.util.PreferenceUtils
import kotlinx.android.synthetic.main.main_activity.*
import sweatworks.com.randomuser.R

/**
 * @author Ing. Wilson Garcia
 * Created on 11/19/18
 */

class MainActivity : BaseActivity(R.layout.main_activity, ContactPresenter()), ContactContract.View{

    private var currentPage: Int = 1
    private var isScrolling = false
    private var contactsName: MutableList<Contact> = mutableListOf()
    private lateinit var preferenceUtils: PreferenceUtils

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        this.presenter?.initPresenter(this, this)
        this.preferenceUtils = PreferenceUtils(this)

        this.initComponents()
        this.loadData()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main, menu)
        createSearchView(menu)
        return super.onCreateOptionsMenu(menu)
    }

    private fun initComponents(){
        contactList.layoutManager = GridLayoutManager(this, 3,
                GridLayoutManager.VERTICAL, false)
        favoriteList.layoutManager = GridLayoutManager(this, 1,
                GridLayoutManager.HORIZONTAL, false)

        setupEvents()
    }

    private fun setupEvents(){
        contactList.addOnScrollListener(object: RecyclerView.OnScrollListener(){
            override fun onScrollStateChanged(recyclerView: RecyclerView?, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                if (newState == AbsListView.OnScrollListener.SCROLL_STATE_TOUCH_SCROLL) {
                    isScrolling = true
                }
            }

            override fun onScrolled(recyclerView: RecyclerView?, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)

                val layoutManager = recyclerView?.layoutManager as GridLayoutManager

                val currentItems = layoutManager.childCount
                val totalItems = layoutManager.itemCount
                val scrollOutOfScreen = layoutManager.findFirstVisibleItemPosition()

                if (isScrolling && (currentItems.plus(scrollOutOfScreen) == totalItems)) {
                    isScrolling = false
                    currentPage = currentPage.plus(1)

                    loadData()
                }
            }
        })
    }

    private fun createSearchView(menu: Menu?){
        val searchMenuItem = menu?.findItem(R.id.action_search)!!
        val searchView = searchMenuItem?.actionView as SearchView
        val searchAutoComplete = searchView.findViewById(android.support.v7.appcompat.R.id.search_src_text) as SearchView.SearchAutoComplete
        val searchManager = getSystemService(Context.SEARCH_SERVICE) as SearchManager

        searchView.setSearchableInfo(searchManager.getSearchableInfo(componentName))
        searchAutoComplete.setAdapter(ArrayAdapter<Contact>(this, android.R.layout.simple_dropdown_item_1line, contactsName))
        searchAutoComplete.setTextColor(Color.WHITE)
        searchAutoComplete.setDropDownBackgroundResource(android.R.color.white)
        
        searchAutoComplete.setOnItemClickListener { adapterView, view, i, l ->
            searchMenuItem.collapseActionView()

            val contact = adapterView?.getItemAtPosition(i) as Contact
            val intent = Intent(this, ContactDetailsActivity::class.java)
            intent.putExtra("contact", contact)
            startActivity(intent)
        }
    }

    private fun loadData(){
        progressBar.visibility = View.VISIBLE
        (this.presenter as ContactPresenter).getContactList(currentPage)
    }

    override fun setContacts(contacts: MutableList<Contact>) {
        progressBar.visibility = View.GONE

        this.contactsName.addAll(contacts)
        if(contactList.adapter == null)
            contactList.adapter = ContactListAdapter(contacts, this)
        else {
            (contactList.adapter as ContactListAdapter).addData(contacts)
        }
    }

    fun setFavoriteContacts(contacts: MutableList<Contact>) {
        if(!contacts.isEmpty()){
            favoritesContainer.visibility = View.VISIBLE
            favoriteList.adapter = ContactListAdapter(contacts, this)

        }
    }

    override fun onResume() {
        super.onResume()
        this.setFavoriteContacts(preferenceUtils.getFavorites())
    }
}