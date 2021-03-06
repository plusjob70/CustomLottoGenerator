package com.example.lotto

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton

class FavNumAdapter(private val context: Context) : RecyclerView.Adapter<FavNumAdapter.Holder>(){
    private val adapter = this
    private val dbHelper = DBHelper(context)
    private val imageMap = dbHelper.getAllByteArray()
    private val imageList = MutableList(imageMap.size) { index ->
            Image(imageMap.keys.toList()[index], imageMap.values.toList()[index])
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val view = LayoutInflater.from(context).inflate(R.layout.single_list, parent, false)
        return Holder(view)
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder?.bind(imageList[position])
    }

    override fun getItemCount(): Int {
        return imageList.size
    }

    inner class Holder(itemView: View?) : RecyclerView.ViewHolder(itemView!!) {
        private val ballsImage = itemView?.findViewById<ImageView>(R.id.ballsImageView)
        private val deleteButton = itemView?.findViewById<FloatingActionButton>(R.id.deleteFavFAB)

        fun bind (image: Image){
            ballsImage?.setImageBitmap(getBitmapFromByteArray(image.imageByte!!))
            deleteButton?.setOnClickListener {
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION){
                    dbHelper.deleteFavoriteNumbers(image.id)
                    imageList.removeAt(position)
                    adapter.notifyDataSetChanged()
                }
            }
        }
    }
}



