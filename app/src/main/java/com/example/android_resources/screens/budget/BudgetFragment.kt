package com.example.android_resources.screens.budget

import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.android_resources.R
import com.example.android_resources.utils.Constants
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.components.YAxis
import com.github.mikephil.charting.data.BarData
import com.github.mikephil.charting.data.BarDataSet
import com.github.mikephil.charting.data.BarEntry
import com.github.mikephil.charting.formatter.ValueFormatter
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet
import kotlinx.android.synthetic.main.fragment_budget.view.*
import java.util.*
import kotlin.collections.ArrayList


class BudgetFragment : Fragment() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val v = inflater.inflate(R.layout.fragment_budget, container, false)
        //card click
        clickOnCard(v)

        //chart
        val data = createChartData()
        configureChartAppearance(v)
        prepareChartData(v, data)
        return v
    }

    private fun clickOnCard(v: View) {
        v.budget_cur_bal_card.setOnClickListener {
            Toast.makeText(activity?.baseContext, "TEXT", Toast.LENGTH_SHORT).show()
        }
        v.budget_today_exp_card.setOnClickListener {
            Toast.makeText(activity?.baseContext, "TEXT", Toast.LENGTH_SHORT).show()
        }
        v.budget_week_exp_card.setOnClickListener {
            Toast.makeText(activity?.baseContext, "TEXT", Toast.LENGTH_SHORT).show()
        }
        v.budget_month_exp_card.setOnClickListener {
            Toast.makeText(activity?.baseContext, "TEXT", Toast.LENGTH_SHORT).show()
        }
    }

    private fun configureChartAppearance(v: View) {
        v.budget_chart.description.isEnabled = false
        v.budget_chart.setDrawValueAboveBar(true)
        v.budget_chart.setPinchZoom(false)
        val xAxis: XAxis = v.budget_chart.xAxis
        xAxis.valueFormatter = object : ValueFormatter() {
            override fun getFormattedValue(value: Float): String {
                return Constants.MONTHS[value.toInt()]
            }
        }
        val axisLeft: YAxis = v.budget_chart.axisLeft
        axisLeft.granularity = 10f
        axisLeft.axisMinimum = -30f
        val axisRight: YAxis = v.budget_chart.axisRight
        axisRight.granularity = 10f
        axisRight.axisMinimum = -30f
    }

    private fun createChartData(): BarData {
        val valuesPos: ArrayList<BarEntry> = ArrayList()
        val valuesNeg: ArrayList<BarEntry> = ArrayList()
        for (i in 0 until Constants.MAX_X_VALUE) {
            val x = i.toFloat()
            val r = Random()
            val y: Float =
                Constants.MIN_Y_VALUE + r.nextFloat() * (Constants.MAX_Y_VALUE - Constants.MIN_Y_VALUE)
            if (y >= 0) {
                valuesPos.add(BarEntry(x, y))
            } else {
                valuesNeg.add(BarEntry(x, y))
            }
        }
        val set1 = BarDataSet(valuesPos, "Positive values")
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            set1.color = resources.getColor(R.color.green, null)
        }
        val set2 = BarDataSet(valuesNeg, "Negative values")
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            set2.color = resources.getColor(R.color.red, null)
        }
        val dataSets: ArrayList<IBarDataSet> = ArrayList()
        dataSets.add(set1)
        dataSets.add(set2)
        return BarData(dataSets)
    }

    private fun prepareChartData(v: View, data: BarData) {
        data.setValueTextSize(12f)
        v.budget_chart.data = data
        v.budget_chart.invalidate()
    }
}