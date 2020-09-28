package com.example.android_resources.screens.action.adapters

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.android_resources.R
import com.example.android_resources.data.database.entities.Action
import com.example.android_resources.screens.action.ActionView
import kotlinx.android.synthetic.main.recyclerview_action.view.*


class ActionsAdapter(var context: Context, var mActions: ArrayList<Action>) :
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

    override fun onBindViewHolder(holder: ActionsViewHolder, position: Int) {
        val currentAction = mActions[position]
        cardViewList.add(holder.card)
        //set image
        val image = currentAction.category.toLowerCase()
        val drawableResourceId: Int =
            context.resources.getIdentifier(image, "drawable", context.packageName)
        Glide.with(holder.itemView)
            .load(drawableResourceId)
            .into(holder.image)
        //set text
        holder.text.text = currentAction.category
        //click
        holder.card.setOnClickListener {
            for (cardView in cardViewList) {
                cardView.setBackgroundResource(R.drawable.card_edge_selected)
            }
            //set category
            holder.card.setBackgroundResource(R.drawable.card_edge)
            ActionView.category = currentAction.category
        }
        holder.bind()
    }

    class ActionsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val image: ImageView = itemView.action_rec_img
        val text: TextView = itemView.action_rec_text
        val card: ConstraintLayout = itemView.action_card

        //TODO: you can put all the view fields updates here
        fun bind(){

        }
    }
}