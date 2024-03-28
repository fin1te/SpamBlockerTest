package com.utkarsha.spamblocker.adapter

import android.content.Context
import android.graphics.Color
import android.text.util.Linkify
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Adapter
import com.bumptech.glide.Glide
import com.utkarsha.spamblocker.R
import com.utkarsha.spamblocker.model.Sms
import com.utkarsha.spamblocker.model.testSms
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
//            body.autoLinkMask = Linkify.ALL

            if(sms.isSpam == "Spam") {
//                body.autoLinkMask = 0
                body.linksClickable = false
                body.isClickable = false
//                body.setLinkTextColor(context.getColor(android.R.color.white))

                background.setCardBackgroundColor(context.getColor(R.color.spam_red))
                background.setOnClickListener {
                    Toast.makeText(context, "This link spam!", Toast.LENGTH_SHORT).show()
                }
                //body.setTextColor(context.getColor(R.color.black))
            } else {
                body.autoLinkMask = Linkify.ALL
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