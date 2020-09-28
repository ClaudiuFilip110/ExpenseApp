package com.example.android_resources.screens.main

import android.view.View
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.FragmentTransaction
import com.example.android_resources.R
import com.example.android_resources.screens.budget.BudgetFragment
import com.example.android_resources.screens.converter.ConverterActivity
import com.example.android_resources.screens.expenses.ExpensesFragment
import kotlinx.android.synthetic.main.activity_main.view.*
import kotlinx.android.synthetic.main.toolbar.view.*

class MainView(private val activity: MainActivity) {
    val layout: View = View.inflate(activity, R.layout.activity_main, null)

    fun clickOnDrawerItem() {
        layout.nav_item_main_page.setOnClickListener {
            MainActivity.start(activity)
            activity.finish()
        }
        layout.nav_item_converter.setOnClickListener {
            ConverterActivity.start(activity)
        }
        layout.nav_item_logout.setOnClickListener {
            activity.logout()
        }
    }

    init {
        clickOnDrawerItem()
        initBtmNav()
        initPlus()
    }

    private fun initPlus() {
        layout.toolbar_plus.setOnClickListener {
            activity.startAction()
        }
    }

    fun initDrawer(drawer_layout: DrawerLayout, toolbar: androidx.appcompat.widget.Toolbar) {
        activity.supportActionBar
        val drawerToggle: ActionBarDrawerToggle = object : ActionBarDrawerToggle(
            activity,
            drawer_layout,
            toolbar,
            (R.string.open),
            (R.string.close)
        ) {}
        drawerToggle.isDrawerIndicatorEnabled = true
        drawer_layout.addDrawerListener(drawerToggle)
        drawerToggle.syncState()
    }

    fun initFragment() {
        val budgetFragment = BudgetFragment()
        activity.supportFragmentManager.beginTransaction()
            .add(R.id.main_fragment, budgetFragment)
            .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN).commit()
    }

    fun initBtmNav() {
        layout.main_btmNav.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.btm_nav_budget -> {
                    layout.main_btmNav.menu.findItem(it.itemId).isChecked = true
                    layout.main_btmNav.menu.findItem(R.id.btm_nav_expenses).isChecked = false
                    val budgetFragment = BudgetFragment()
                    layout.toolbar_title.text = "My Budget"
                    activity.supportFragmentManager.beginTransaction()
                        .replace(R.id.main_fragment, budgetFragment).commit()
                }
                R.id.btm_nav_expenses -> {
                    layout.main_btmNav.menu.findItem(it.itemId).isChecked = false
                    layout.main_btmNav.menu.findItem(R.id.btm_nav_budget).isChecked = false
                    val expensesFragment = ExpensesFragment()
                    layout.toolbar_title.text = "My Expenses"
                    activity.supportFragmentManager.beginTransaction()
                        .replace(R.id.main_fragment, expensesFragment).commit()
                }
            }
            true
        }
    }
}