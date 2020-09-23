package com.example.android_resources.screens.expenses

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.android_resources.R
import com.example.android_resources.data.database.repositories.UserRepository
import com.example.android_resources.screens.expenses.adapters.ViewPagerAdapter
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.android.synthetic.main.fragment_expenses.view.*


class ExpensesFragment() : Fragment() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val v: View = inflater.inflate(R.layout.fragment_expenses, container, false)

        activity?.let {
            v.expenses_view_pager.adapter =
                ViewPagerAdapter(
                    it
                )
        }
        //cred ca aici trebuie facuta logica
        TabLayoutMediator(v.expenses_tab_layout, v.expenses_view_pager) { tab, position ->
            when (position) {
                0 ->
                    tab.text = "This week"
                1 ->
                    tab.text = "This month"
                2 ->
                    tab.text = "This year"
            }
        }.attach()
        return v
    }

    companion object {
        fun newInstance(position: Int): Fragment {
            val fragment = ExpensesUIFragment()
            val args = Bundle()
            fragment.arguments = args
            return fragment
        }
    }
}