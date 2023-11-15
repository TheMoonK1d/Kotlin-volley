package com.example.api

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.view.menu.MenuView.ItemView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class UserAdapter(private val userList: ArrayList<User>): RecyclerView.Adapter<UserAdapter.viewHolder>(){
    private lateinit var cnx: Context
    var onItemClicked :  ((User) -> Unit)? = null
    class viewHolder(itemView: View):RecyclerView.ViewHolder(itemView){

        val img = itemView.findViewById<ImageView>(R.id.imageView4)
        val name = itemView.findViewById<TextView>(R.id.name)
        val email = itemView.findViewById<TextView>(R.id.email)
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): viewHolder {
        cnx = parent.context
        val view = LayoutInflater.from(cnx).inflate(R.layout.item,parent, false)
        return viewHolder(view)
    }

    override fun getItemCount(): Int {
        return userList.size
    }

    override fun onBindViewHolder(holder: viewHolder, position: Int) {
        val user = userList[position]

        holder.name.text = user.fName + user.lName
        holder.email.text = user.emailId
        Glide.with(cnx).load(user.pic).into(holder.img)

        holder.itemView.setOnClickListener {
            Log.d("Touched!!!!", holder.name.toString() )
            onItemClicked?.invoke(user)
        }

    }


}