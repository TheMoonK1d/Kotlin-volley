package com.example.api

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide

class DetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        var user =intent.getParcelableExtra<User>("user")
        if (user != null){
            val name : TextView = findViewById(R.id.name)
            val email : TextView = findViewById(R.id.email)
            val image : ImageView = findViewById(R.id.profile)

            name.text = user.fName +" "+ user.fName
            email.text = user.emailId
            Glide.with(this).load(user.pic).into(image)
            //user.pic?.let { image.setImageResource(it.toInt()) }
        }


    }
}

