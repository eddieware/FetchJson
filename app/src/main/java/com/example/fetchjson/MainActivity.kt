package com.example.fetchjson

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.GsonBuilder
import kotlinx.android.synthetic.main.activity_main.*
import okhttp3.*
import java.io.IOException

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView.layoutManager= LinearLayoutManager(this)
        //recyclerView.adapter= MainAdapter()

        fetchJson()

    }

    private fun fetchJson() {
        println("Attempting to Fetch JSON")
        val url ="https://api.letsbuildthatapp.com/youtube/home_feed"
        val request = Request.Builder().url(url).build()

        val client= OkHttpClient()
        client.newCall(request).enqueue(object :Callback{
            override fun onResponse(call: Call, response: Response) {
                val body = response?.body?.string()
                println(body) //debug point para ver las variable

                val gson = GsonBuilder().create()

                val homeFeed= gson.fromJson(body,HomeFeed::class.java)
                runOnUiThread {
                    recyclerView.adapter=MainAdapter(homeFeed)
                }
            }

            override fun onFailure(call: Call, e: IOException) {
                println("Excecute request failed")
            }

        })

        }

}

class HomeFeed(val videos:List<Video>)
class Video(val id:Int,val name: String,val link:String,val imageUrl:String,
            val numberOfViews:Int, val channel:Channel)
class Channel(val name:String, val profileImageUrl:String)
