package com.example.android_resources.screens.action.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.example.android_resources.R
import com.example.android_resources.data.database.entities.Action
import kotlinx.android.synthetic.main.recyclerview_action.view.*


class ActionsAdapter(var mActions: ArrayList<Action>) :
    RecyclerView.Adapter<ActionsAdapter.ActionsViewHolder>() {
    var cardViewList: ArrayList<ConstraintLayout> = ArrayList()
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ActionsViewHolder {
        val v: View =
            LayoutInflater.from(parent.context).inflate(R.layout.recyclerview_action, null, false)
        return ActionsViewHolder(v)
    }

    override fun getItemCount(): Int {
        return mActions.size
    }

    override fun onBindViewHolder(holder: ActionsAdapter.ActionsViewHolder, position: Int) {
        val currentAction = mActions[position]
        cardViewList.add(holder.card);
        //set image
        holder.text.text = currentAction.category
        //set text
        holder.card.setOnClickListener {
            for (cardView in cardViewList) {
                cardView.setBackgroundResource(R.drawable.card_edge_selected)
            }
            holder.card.setBackgroundResource(R.drawable.card_edge)
        }
    }

    class ActionsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val image: ImageView = itemView.action_rec_img
        val text: TextView = itemView.action_rec_text
        val card: ConstraintLayout = itemView.action_card
    }
}