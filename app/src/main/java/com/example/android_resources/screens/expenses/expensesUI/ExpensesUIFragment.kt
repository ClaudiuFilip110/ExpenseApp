package com.example.android_resources.screens.expenses.expensesUI

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import org.koin.android.ext.android.inject
import org.koin.core.KoinComponent
import org.koin.core.context.GlobalContext.get
import org.koin.core.parameter.parametersOf


class ExpensesUIFragment() : Fragment(), KoinComponent {
    private val view: ExpensesUIView by inject { parametersOf(this) }
    private val presenter: ExpensesUIPresenter by inject { parametersOf(view, get()) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val v = view.layout
        view.setTitle(arguments?.get("total"))
        val balanceList = presenter.getBalanceUntilDate(arguments?.get("name"))
        val actionList = presenter.getListUntilDate(arguments?.get("name"))
        view.setAdapter(arguments?.get("name"), actionList, balanceList, resources)
        presenter.chart(v, arguments?.get("name"))

        return v
    }
}