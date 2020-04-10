package com.example.fetchjson

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.video_row.view.*

class MainAdapter : RecyclerView.Adapter<CustomViewHolder>(){
    val videoTitles = listOf("First Title", "SecondTitle","3rd")

    override fun getItemCount(): Int {
        return videoTitles.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolder {

        val layoutInflater= LayoutInflater.from(parent?.context)
        val cellForRow = layoutInflater.inflate(R.layout.video_row,parent,false)
        return CustomViewHolder(cellForRow)
    }

    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {
        val videoTitles=videoTitles.get(position)
        holder?.view.textView_video_title?.text=videoTitles

    }
}

class CustomViewHolder(val view: View): RecyclerView.ViewHolder(view){

}