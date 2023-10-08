package com.utkarsha.spamblocker.adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Adapter
import com.bumptech.glide.Glide
import com.utkarsha.spamblocker.R
import com.utkarsha.spamblocker.model.Sms
import com.utkarsha.spamblocker.utils.SpamModel
import kotlinx.coroutines.runBlocking


class SmsAdapter(private val message: List<Sms>, private val context : Context) : Adapter<SmsAdapter.ViewHolder>() {

    private var diceRoll = (1..16).random()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_sms, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val sms = message[position]

        holder.apply {
            title.text = sms.title
            body.text = sms.body

            if(sms.isSpam == "Spam") {
                background.setCardBackgroundColor(context.getColor(R.color.spam_red))
                //body.setTextColor(context.getColor(R.color.black))
            }

            when (diceRoll) {
                1 -> {
                    Glide.with(holder.itemView.context).load(R.drawable.avatar_1).into(image)
                    diceRoll++
                }

                2 -> {
                    Glide.with(holder.itemView.context).load(R.drawable.avatar_2).into(image)
                    diceRoll++
                }

                3 -> {
                    Glide.with(holder.itemView.context).load(R.drawable.avatar_3).into(image)
                    diceRoll++
                }

                4 -> {
                    Glide.with(holder.itemView.context).load(R.drawable.avatar_4).into(image)
                    diceRoll++
                }

                5 -> {
                    Glide.with(holder.itemView.context).load(R.drawable.avatar_5).into(image)
                    diceRoll++
                }

                6 -> {
                    Glide.with(holder.itemView.context).load(R.drawable.avatar_6).into(image)
                    diceRoll++
                }

                7 -> {
                    Glide.with(holder.itemView.context).load(R.drawable.avatar_7).into(image)
                    diceRoll++
                }

                8 -> {
                    Glide.with(holder.itemView.context).load(R.drawable.avatar_8).into(image)
                    diceRoll++
                }

                9 -> {
                    Glide.with(holder.itemView.context).load(R.drawable.avatar_9).into(image)
                    diceRoll++
                }

                10 -> {
                    Glide.with(holder.itemView.context).load(R.drawable.avatar_10).into(image)
                    diceRoll++
                }

                11 -> {
                    Glide.with(holder.itemView.context).load(R.drawable.avatar_11).into(image)
                    diceRoll++
                }

                12 -> {
                    Glide.with(holder.itemView.context).load(R.drawable.avatar_12).into(image)
                    diceRoll++
                }

                13 -> {
                    Glide.with(holder.itemView.context).load(R.drawable.avatar_13).into(image)
                    diceRoll++
                }

                14 -> {
                    Glide.with(holder.itemView.context).load(R.drawable.avatar_14).into(image)
                    diceRoll++
                }

                15 -> {
                    Glide.with(holder.itemView.context).load(R.drawable.avatar_15).into(image)
                    diceRoll++
                }

                16 -> {
                    Glide.with(holder.itemView.context).load(R.drawable.avatar_16).into(image)
                    diceRoll = 1
                }
            }
        }
    }

    override fun getItemCount(): Int {
        return message.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val title = itemView.findViewById<TextView>(R.id.item_sms_title)
        val body = itemView.findViewById<TextView>(R.id.item_sms_body)
        val image = itemView.findViewById<ImageView>(R.id.item_sms_image)
        val background = itemView.findViewById<CardView>(R.id.itemCardView)
    }
}