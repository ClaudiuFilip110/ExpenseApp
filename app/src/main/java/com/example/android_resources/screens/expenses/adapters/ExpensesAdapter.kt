package com.example.android_resources.screens.expenses.adapters

import android.content.Context
import android.content.res.Resources
import android.os.Build
import android.util.Base64
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.android_resources.R
import com.example.android_resources.data.database.entities.Action
import com.example.android_resources.utils.DateUtils
import kotlinx.android.synthetic.main.recyclerview_expenses.view.*
import java.util.*
import kotlin.collections.ArrayList

class ExpensesAdapter(
    val context: Context,
    val mPayments: ArrayList<Action>,
    val mTotal: ArrayList<Double>,
    val resources: Resources = Resources.getSystem()
) :
    RecyclerView.Adapter<ExpensesAdapter.ExpensesViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ExpensesViewHolder {
        val v: View =
            LayoutInflater.from(parent.context).inflate(R.layout.recyclerview_expenses, null, false)
        return ExpensesViewHolder(v)
    }

    override fun getItemCount(): Int {
        return mPayments.size
    }

    override fun onBindViewHolder(holder: ExpensesViewHolder, position: Int) {
        val currentPayment = mPayments[position]
        val currentTotal = mTotal[position]
        //set image
        holder.bind(context, currentPayment, currentTotal, resources)

    }

    class ExpensesViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val image: ImageView = itemView.card_expenses_image
        val category: TextView = itemView.card_expenses_type
        val amount: TextView = itemView.card_expenses_amount
        val type: TextView = itemView.card_expenses_income_or_expense
        val currentBalance: TextView = itemView.card_expenses_total
        val date: TextView = itemView.card_expenses_day

        fun bind(context: Context, currentPayment: Action,currentTotal: Double, resources: Resources) {
            val curImage = currentPayment.category.toLowerCase()
            val drawableResourceId: Int =
                context.resources.getIdentifier(curImage, "drawable", context.packageName)
            Glide.with(itemView)
                .load(drawableResourceId)
                .into(image)
            //set text
            amount.text = currentPayment.amount.toString()
            category.text = currentPayment.category
            currentBalance.text = currentTotal.toString()
            date.text = DateUtils.convertSimpleDate(currentPayment.date).toString()
            type.text = if (category.text == "Income") {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    amount.setTextColor(resources.getColor(R.color.green, null))
                    type.setTextColor(resources.getColor(R.color.green, null))
                }
                "Income"
            } else {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    amount.setTextColor(resources.getColor(R.color.red, null))
                    type.setTextColor(resources.getColor(R.color.red, null))
                }
                "Expense"
            }
        }
    }
}