package com.example.android_resources.screens.budget

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import org.koin.android.ext.android.inject
import org.koin.core.KoinComponent
import org.koin.core.context.GlobalContext.get
import org.koin.core.parameter.parametersOf

class BudgetFragment : Fragment(), KoinComponent {
    private val view: BudgetView by inject { parametersOf(this) }
    private val presenter: BudgetPresenter by inject { parametersOf(view, get()) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val v = view.layout
        //card click
        view.clickOnCard(v)
        //chart
        val data = com.example.android_resources.screens.budget.BarData()
        data.configureChartAppearance(v)
        data.prepareChartData(v, data.createChartData(resources))
        return v
    }
}