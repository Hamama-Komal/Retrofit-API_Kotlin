package com.example.retrofitkotlin.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.retrofitkotlin.R
import com.example.retrofitkotlin.model.UsersItem

class UserAdapater(var context: Context, var list: List<UsersItem>) : RecyclerView.Adapter<UserAdapater.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val view = LayoutInflater.from(context).inflate(R.layout.list_item_layout, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = list[position]

        holder.user_name.text = item.login
        Glide.with(context).load(item.avatar_url).into(holder.profile_image);

    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view){

        var profile_image = view.findViewById<ImageView>(R.id.user_profile)
        var user_name = view.findViewById<TextView>(R.id.user_name)
    }
}