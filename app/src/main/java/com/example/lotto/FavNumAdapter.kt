package com.example.lotto

import android.content.Context
import android.database.Cursor
import android.view.View
import android.view.ViewGroup
import android.widget.Adapter
import android.widget.BaseAdapter
import android.widget.CursorAdapter
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView

class FavNumAdapter(val numberList: List<Int>) : RecyclerView.Adapter<FavNumAdapter.Holder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        TODO("Not yet implemented")
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        TODO("Not yet implemented")
    }

    override fun getItemCount(): Int {
        TODO("Not yet implemented")
    }

    inner class Holder(itemView: View?) : RecyclerView.ViewHolder(itemView!!) {
        val ballsImage = itemView?.findViewById<ImageView>(R.id.ballsImageView)

        fun showImage (){

        }
    }
}



