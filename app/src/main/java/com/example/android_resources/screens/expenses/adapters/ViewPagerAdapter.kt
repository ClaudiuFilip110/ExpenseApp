package com.example.android_resources.screens.expenses.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.android_resources.screens.expenses.ExpensesFragment


class ViewPagerAdapter(fragmentActivity: FragmentActivity) :
    FragmentStateAdapter(fragmentActivity) {
    override fun getItemCount(): Int {
        return size
    }

    override fun createFragment(position: Int): Fragment {
        return ExpensesFragment.newInstance(
            position
        )
    }

    companion object {
        const val size = 3
    }
}