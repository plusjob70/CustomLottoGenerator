package com.example.lotto

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DBHelper(context: Context): SQLiteOpenHelper(context, DB_NAME, null, DB_VERSION) {
    companion object {
        private val DB_NAME = "NumbersDB"
        private val DB_VERSION = 1
        private val TABLE_NAME = "Numbers"
        private val ID = "id"
        private val FIRST = "First_Number"
        private val SECOND = "Second_Number"
        private val THIRD = "Third_Number"
        private val FOURTH = "Fourth_Number"
        private val FIFTH = "Fifth_Number"
        private val SIXTH = "Sixth_Number"
    }

    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL("CREATE TABLE $TABLE_NAME($ID INTEGER PRIMARY KEY AUTOINCREMENT, $FIRST INTEGER, $SECOND INTEGER, $THIRD INTEGER, $FOURTH INTEGER, $FIFTH INTEGER, $SIXTH INTEGER)")
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) { }

    fun getAllNumbers(): List<Int> {
        val numberList = mutableListOf<Int>()
        val db = readableDatabase
        val cursor = db.rawQuery("SELECT * FROM $TABLE_NAME", null)

        if (cursor != null){
           if (cursor.moveToFirst()) {
               do {
                   numberList.apply {
                       add(cursor.getInt(cursor.getColumnIndex(FIRST)))
                       add(cursor.getInt(cursor.getColumnIndex(SECOND)))
                       add(cursor.getInt(cursor.getColumnIndex(THIRD)))
                       add(cursor.getInt(cursor.getColumnIndex(FOURTH)))
                       add(cursor.getInt(cursor.getColumnIndex(FIFTH)))
                       add(cursor.getInt(cursor.getColumnIndex(SIXTH)))
                   }
               } while (cursor.moveToNext())
           }
        }

        cursor.close()
        db.close()

        return numberList
    }

    fun addFavoriteNumbers(numbers: Numbers): Boolean {
        val db = writableDatabase
        val values = ContentValues().apply {
            put(FIRST, numbers.first)
            put(SECOND, numbers.second)
            put(THIRD, numbers.third)
            put(FOURTH, numbers.fourth)
            put(FIFTH, numbers.fifth)
            put(SIXTH, numbers.sixth)
        }

        val isSuccess = db.insert(TABLE_NAME, null, values)
        db.close()

        return (Integer.parseInt("$isSuccess") != -1)
    }

    private fun deleteFavoriteNumbers(id: Int) {
        val db = writableDatabase
        db.execSQL("DELETE FROM $TABLE_NAME WHERE id = $id")
    }

    // 태이블 지우기 (테스트용)
    fun deleteTable(){
        val db = writableDatabase
        db.execSQL("DELETE FROM $TABLE_NAME")
    }
}