package com.example.api

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley


class MainActivity : AppCompatActivity() {
    var userList = arrayListOf<User>()
    val api = "https://reqres.in/api/users"
    var recyclerView : RecyclerView? = null
    var userAdapter: UserAdapter? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        recyclerView = findViewById(R.id.view)
        val queue = Volley.newRequestQueue(this)
        val request = JsonObjectRequest(com.android.volley.Request.Method.GET, api, null,
            { res ->
                val jsonArray = res.getJSONArray("data")
                for(i in 0 until  jsonArray.length()){
                    val jsonOnj = jsonArray.getJSONObject(i)
                    val user = User(
                        jsonOnj.getInt("id"),
                        jsonOnj.getString("email"),
                        jsonOnj.getString("first_name"),
                        jsonOnj.getString("last_name"),
                        jsonOnj.getString("avatar")
                    )
                    userList.add(user)
                }
                recyclerView?.layoutManager = LinearLayoutManager(this)
                userAdapter = UserAdapter(userList)
                recyclerView?.adapter = userAdapter
                userAdapter!!.onItemClicked = {
                    var intent = Intent(this, DetailActivity::class.java)
                    intent.putExtra("user", it)
                    startActivity(intent)
                }
            },
            { err ->
                Log.d("Error", err.message.toString())
            }
        )
        queue.add(request)
    }
}