package com.example.lotto

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class FavoriteNumbersActivity : AppCompatActivity() {
    private var dbHelper: DBHelper? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_favorite_numbers)
        dbHelper = DBHelper(this)

        val numberList = dbHelper!!.getAllNumbers()

    }
}