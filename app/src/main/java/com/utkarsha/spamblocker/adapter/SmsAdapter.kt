package com.utkarsha.spamblocker.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Adapter
import com.bumptech.glide.Glide
import com.utkarsha.spamblocker.R
import com.utkarsha.spamblocker.model.Sms


class SmsAdapter(private val message : List<Sms>) : Adapter<SmsAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_sms, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val sms = message[position]

        holder.apply {
            title.text = sms.title
            body.text = sms.body
            Glide.with(image.context).load(sms.image).into(image)
        }
    }

    override fun getItemCount(): Int {
        return message.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val title = itemView.findViewById<TextView>(R.id.item_sms_title)
        val body = itemView.findViewById<TextView>(R.id.item_sms_body)
        val image = itemView.findViewById<ImageView>(R.id.item_sms_image)
    }
}