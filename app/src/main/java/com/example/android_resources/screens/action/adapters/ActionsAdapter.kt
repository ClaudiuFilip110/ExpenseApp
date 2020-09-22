package com.example.android_resources.screens.action.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.android_resources.R
import com.example.android_resources.screens.expenses.adapters.ExpensesAdapter
import kotlinx.android.synthetic.main.recyclerview_action.view.*
import kotlinx.android.synthetic.main.recyclerview_expenses.view.*

class ActionsAdapter(val mActions: ArrayList<String>) :
    RecyclerView.Adapter<ActionsAdapter.ActionsViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ActionsAdapter.ActionsViewHolder {
        val v: View =
            LayoutInflater.from(parent.context).inflate(R.layout.recyclerview_action, null, false)
        return ActionsAdapter.ActionsViewHolder(v)
    }

    override fun getItemCount(): Int {
        return mActions.size
    }

    override fun onBindViewHolder(holder: ActionsAdapter.ActionsViewHolder, position: Int) {
        val currentAction = mActions[position]
        holder.text.setOnClickListener {
            holder.text.text = "IONUUUUUUUUUT"
        }
    }

    class ActionsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val image: ImageView = itemView.action_rec_img
        val text: TextView = itemView.action_rec_text
    }
}