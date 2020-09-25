package com.example.android_resources.screens.budget

import android.content.res.Resources
import android.os.Build
import android.util.Log
import android.view.View
import com.example.android_resources.R
import com.example.android_resources.data.database.entities.Action
import com.example.android_resources.utils.Constants
import com.example.android_resources.utils.DateUtils
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

class BarData(val actions: ArrayList<Action>) {

    fun prepareChartData(v: View, data: BarData) {
        data.setValueTextSize(12f)
        v.budget_chart.data = data
        v.budget_chart.invalidate()
    }

    private fun getAmountPerMonth(month: Int): Float {
        var amount = 0.0f
        for (action in actions) {
            var date = DateUtils.convertDate(action.date)
            if (date.month.toString() == Constants.FullMONTHS[month].toUpperCase()) {
                amount += action.amount.toFloat()
            }
        }
        return amount
    }

    fun createChartData(resources: Resources): BarData {
        val valuesPos: ArrayList<BarEntry> = ArrayList()
        val valuesNeg: ArrayList<BarEntry> = ArrayList()
        for (i in 0 until Constants.MAX_X_VALUE) {
            val x = i.toFloat()
            val y: Float = getAmountPerMonth(i)
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

    fun configureChartAppearance(v: View) {
        v.budget_chart.description.isEnabled = false
        v.budget_chart.setDrawValueAboveBar(true)
        v.budget_chart.setPinchZoom(false)
        val xAxis: XAxis = v.budget_chart.xAxis
        xAxis.labelCount = 12;
        xAxis.valueFormatter = object : ValueFormatter() {
            override fun getFormattedValue(value: Float): String {
                return Constants.MONTHS[value.toInt()]
            }
        }
        val axisLeft: YAxis = v.budget_chart.axisLeft
        axisLeft.granularity = 25f
        axisLeft.axisMaximum = 200f
        axisLeft.axisMinimum = -200f
        val axisRight: YAxis = v.budget_chart.axisRight
        axisRight.granularity = 25f
        axisRight.axisMaximum = 200f
        axisRight.axisMinimum = -200f
    }
}