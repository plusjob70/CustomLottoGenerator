package com.example.lotto

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_favorite_numbers.*

class FavoriteNumbersActivity : AppCompatActivity() {
    private var dbHelper: DBHelper? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_favorite_numbers)

        dbHelper = DBHelper(this)
        val imageMap = dbHelper!!.getAllByteArray()

        val imageList = mutableListOf<Image>()
        imageMap.forEach{ (key, value) ->
            imageList.add(Image(key, value))
        }

        val adapter = FavNumAdapter(this, imageList)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.setHasFixedSize(true)
    }
}