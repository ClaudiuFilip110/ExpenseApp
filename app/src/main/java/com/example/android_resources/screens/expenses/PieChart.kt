package com.example.android_resources.screens.expenses

import android.graphics.Color
import android.util.Log
import android.view.View
import com.example.android_resources.data.database.entities.Action
import com.github.mikephil.charting.components.Legend
import com.github.mikephil.charting.data.PieData
import com.github.mikephil.charting.data.PieDataSet
import com.github.mikephil.charting.data.PieEntry
import com.github.mikephil.charting.utils.ColorTemplate
import kotlinx.android.synthetic.main.fragment_expenses_adapter.view.*

class PieChart(var actions: ArrayList<Action>) {

    fun instantiate(v: View) {
        val chart = v.expenses_chart
        chart.centerText = "Actions"
        chart.setCenterTextSize(20f)

        // radius of the center hole in percent of maximum radius
        chart.holeRadius = 45f
        chart.transparentCircleRadius = 50f

        val l: Legend = chart.legend
        l.isEnabled = false
//        l.verticalAlignment = Legend.LegendVerticalAlignment.TOP
//        l.horizontalAlignment = Legend.LegendHorizontalAlignment.RIGHT
//        l.orientation = Legend.LegendOrientation.VERTICAL
//        l.setDrawInside(false)
        chart.data = generatePieData()
    }

    private fun uniqueTotalCategories(): Int {
        val unique = ArrayList<String>()
        for (action in actions) {
            if (!unique.contains(action.category))
                unique.add(action.category)
        }
        return unique.size
    }

    private fun uniqueCategoriesNames(): Map<String, Double?> {
        val map = mutableMapOf<String, Double?>()
        val unique = ArrayList<String>()
        for (action in actions) {
            if (!unique.contains(action.category)) {
                unique.add(action.category)
                map[action.category] = 0.0
            }
            map[action.category] = map[action.category]?.plus(action.amount)
        }
        return map
    }

    private fun generatePieData(): PieData? {
        val count = uniqueTotalCategories()
        val map = uniqueCategoriesNames()
        val entries1: ArrayList<PieEntry> = ArrayList()

        for ((k, v) in map) {
            if (v != null) {
                var w: Float = v.toFloat()
                if (w < 0) w *= -1
                entries1.add(PieEntry(w, k))
            }
        }
        val ds1 = PieDataSet(entries1, "Quarterly Revenues 2015")
        ds1.setColors(*ColorTemplate.VORDIPLOM_COLORS)
        ds1.sliceSpace = 2f
        ds1.valueTextColor = Color.WHITE
        ds1.valueTextSize = 12f
        return PieData(ds1)
    }
}