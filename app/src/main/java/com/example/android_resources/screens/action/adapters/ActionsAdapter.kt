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


class ActionsAdapter(
    var context: Context,
    var mActions: ArrayList<Action>,
    var selected: Boolean = false,
    var selectedCategory: String = ""
) :
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
        holder.bind(context, mActions, cardViewList, position, selected, selectedCategory)
    }

    class ActionsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val image: ImageView = itemView.action_rec_img
        val text: TextView = itemView.action_rec_text
        val card: ConstraintLayout = itemView.action_card

        fun bind(
            context: Context,
            mActions: ArrayList<Action>,
            cardViewList: ArrayList<ConstraintLayout>,
            position: Int,
            selected: Boolean,
            selectedCategory: String
        ) {
            val currentAction = mActions[position]
            cardViewList.add(card)
            //set image
            val imageRes = currentAction.category.toLowerCase()
            val drawableResourceId: Int =
                context.resources.getIdentifier(imageRes, "drawable", context.packageName)
            Glide.with(itemView)
                .load(drawableResourceId)
                .into(image)
            //set text
            text.text = currentAction.category
            //click
            card.setOnClickListener {
                for (cardView in cardViewList) {
                    cardView.setBackgroundResource(R.drawable.card_edge_selected)
                }
                //set category
                card.setBackgroundResource(R.drawable.card_edge)
                ActionView.category = currentAction.category
            }
            if (selected && selectedCategory == text.text) {
                card.setBackgroundResource(R.drawable.card_edge)
            }
        }
    }
}