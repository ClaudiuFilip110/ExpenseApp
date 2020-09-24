package com.example.android_resources.screens.expenses

import android.view.View
import androidx.fragment.app.FragmentActivity
import com.example.android_resources.R
import com.example.android_resources.screens.expenses.adapters.ViewPagerAdapter
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.android.synthetic.main.fragment_expenses.view.*

class ExpensesView(private val fragment: ExpensesFragment) {
    val layout: View = View.inflate(fragment.context, R.layout.fragment_expenses, null)

    fun tabMediator() {
        TabLayoutMediator(layout.expenses_tab_layout, layout.expenses_view_pager) { tab, position ->
            when (position) {
                0 ->
                    tab.text = "This week"
                1 ->
                    tab.text = "This month"
                2 ->
                    tab.text = "This year"
            }
        }.attach()
    }

    fun setViewPagerAdapter(activity: FragmentActivity?) {
        activity?.let {
            layout.expenses_view_pager.adapter =
                ViewPagerAdapter(
                    it
                )
        }
    }
}