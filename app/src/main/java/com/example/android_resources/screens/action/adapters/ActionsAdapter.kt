package com.example.android_resources.screens.action.adapters

import android.R.drawable
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Canvas
import android.graphics.drawable.BitmapDrawable
import android.util.Base64
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.graphics.drawable.toBitmap
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.android_resources.R
import com.example.android_resources.data.database.entities.Action
import com.example.android_resources.screens.action.ActionView
import kotlinx.android.synthetic.main.recyclerview_action.view.*
import java.io.ByteArrayOutputStream


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
        cardViewList.add(holder.card)
        //set image
        val byteArray: ByteArray = Base64.decode(currentAction.categoryImage, Base64.DEFAULT)
        Glide.with(holder.itemView)
            .load(byteArray)
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
            ActionView.image = currentAction.categoryImage
//            Log.d("ACTION","current category: " + ActionView.category)
//            Log.d("ACTION","image in ad: " + ActionView.image)
            //set image

//            val bitmap = getBitmapFromView(holder.image)
////            val bitmapDrawable = holder.image.drawable
////            val bitmap: Bitmap = bitmapDrawable.toBitmap()
//            if(bitmap!=null){
//                val stream = ByteArrayOutputStream()
//                bitmap.compress(Bitmap.CompressFormat.JPEG, 100, stream)
//                val imageInByte: ByteArray = stream.toByteArray()
//                ActionView.image = Base64.encodeToString(imageInByte,Base64.DEFAULT)
//            } else {
//                Log.d("ACTION","bitmap null")
//            }
        }
    }

    class ActionsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val image: ImageView = itemView.action_rec_img
        val text: TextView = itemView.action_rec_text
        val card: ConstraintLayout = itemView.action_card
    }
}