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
import org.koin.android.ext.android.inject
import org.koin.core.KoinComponent
import org.koin.core.context.GlobalContext
import org.koin.core.parameter.parametersOf


class ExpensesFragment() : Fragment(), KoinComponent {
    private val view: ExpensesView by inject { parametersOf(this) }
    private val presenter: ExpensesPresenter by inject { parametersOf(view, GlobalContext.get()) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val v = view.layout
        presenter.dates()
        view.setViewPagerAdapter(activity)
        view.tabMediator()
        return v
    }

    companion object {
        fun newInstance(position: Int): Fragment {
            val fragment = ExpensesUIFragment()
            val args = Bundle()
            args.putDouble("total", 0.0)
            when (position) {
                0 -> {
                    args.putDouble("total", ExpensesView.week)
                    args.putString("name", "week")
                }
                1 -> {
                    args.putDouble("total", ExpensesView.month)
                    args.putString("name", "month")
                }
                2 -> {
                    args.putDouble("total", ExpensesView.year)
                    args.putString("name", "year")
                }
            }
            fragment.arguments = args
            return fragment
        }
    }
}