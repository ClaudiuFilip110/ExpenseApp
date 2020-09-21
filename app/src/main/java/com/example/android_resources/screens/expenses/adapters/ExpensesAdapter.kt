package com.example.android_resources.screens.expenses.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.android_resources.R
import kotlinx.android.synthetic.main.recyclerview_expenses.view.*

class ExpensesAdapter(val mPayments: ArrayList<String>) :
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
        holder.amount.setOnClickListener {
            holder.amount.text = currentPayment
        }
    }

    class ExpensesViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val amount: TextView = itemView.card_expenses_amount
    }
}