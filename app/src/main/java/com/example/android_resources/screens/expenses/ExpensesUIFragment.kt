package com.example.android_resources.screens.expenses

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.android_resources.R
import com.example.android_resources.screens.expenses.adapters.ExpensesAdapter
import com.github.mikephil.charting.components.Legend
import com.github.mikephil.charting.data.PieData
import com.github.mikephil.charting.data.PieDataSet
import com.github.mikephil.charting.data.PieEntry
import com.github.mikephil.charting.utils.ColorTemplate
import kotlinx.android.synthetic.main.fragment_expenses_adapter.view.*


class ExpensesUIFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val v: View = inflater.inflate(R.layout.fragment_expenses_adapter, container, false)
        recycler(v)
        pieChart(v)
        return v
    }

    private fun recycler(v: View) {
        val recycler = v.expenses_recycler_view
        val list = ArrayList<String>()
        list.add("Claudiu")
        list.add("Filip")
        list.add("de la vrancea")
        list.add("de la vrancea")
        list.add("de la vrancea")

        recycler.layoutManager = LinearLayoutManager(activity)
        recycler.adapter = ExpensesAdapter(list)
    }

    private fun pieChart(v: View) {
        val chart = v.expenses_chart
        chart.centerText = "Quarterly Revenue"
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

    private fun generatePieData(): PieData? {
        val count = 4

        val entries1: ArrayList<PieEntry> = ArrayList()

        for (i in 0 until count) {
            entries1.add(
                PieEntry(
                    (Math.random() * 60 + 40).toFloat(),
                    "Quarter " + (i + 1)
                )
            )
        }
        val ds1 = PieDataSet(entries1, "Quarterly Revenues 2015")
        ds1.setColors(*ColorTemplate.VORDIPLOM_COLORS)
        ds1.sliceSpace = 2f
        ds1.valueTextColor = Color.WHITE
        ds1.valueTextSize = 12f
        return PieData(ds1)

    }
}