package com.example.android_resources.screens.expenses.adapters

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
import kotlinx.android.synthetic.main.recyclerview_expenses.view.*

class ExpensesAdapter(val mPayments: ArrayList<Action>) :
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
        //set image
        val byteArray: ByteArray = Base64.decode(currentPayment.categoryImage, Base64.DEFAULT)
        Glide.with(holder.itemView)
            .load(byteArray)
            .into(holder.image)
        //set text
        holder.amount.text = currentPayment.amount.toString()
        holder.category.text = currentPayment.category
//        if(holder.amount.toString().toDouble()<0){
//            holder.type.text = "Expenses"
//        } else {
//            holder.type.text = "Income"
//        }

    }

    class ExpensesViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val image: ImageView = itemView.card_expenses_image
        val category: TextView = itemView.card_expenses_type
        val amount: TextView = itemView.card_expenses_amount
        val type: TextView = itemView.card_expenses_income_or_expense
    }
}