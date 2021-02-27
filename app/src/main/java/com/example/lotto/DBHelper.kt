package com.example.lotto

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteException
import android.database.sqlite.SQLiteOpenHelper

class DBHelper(context: Context): SQLiteOpenHelper(context, DB_NAME, null, DB_VERSION) {
    companion object {
        private val DB_NAME = "ImageDB"
        private val DB_VERSION = 1
        private val TABLE_NAME = "Images"
        private val ID = "id"
        private val IMAGE_BYTE = "image_byte"
    }

    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL("CREATE TABLE $TABLE_NAME($ID INTEGER PRIMARY KEY AUTOINCREMENT, $IMAGE_BYTE BLOB)")
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.execSQL("DROP TABLE IF EXISTS $TABLE_NAME")
        onCreate(db)
    }

    fun getAllByteArray(): List<ByteArray> {
        val byteArrayList = mutableListOf<ByteArray>()
        val db = readableDatabase
        val cursor = db.rawQuery("SELECT * FROM $TABLE_NAME", null)

        if (cursor != null){
           if (cursor.moveToFirst()) {
               do {
                   byteArrayList.add(cursor.getBlob(cursor.getColumnIndex(IMAGE_BYTE)))
               } while (cursor.moveToNext())
           }
        }

        cursor.close()
        db.close()

        return byteArrayList
    }

    fun addFavoriteNumbers(image: ByteArray): Boolean {
        val db = writableDatabase
        val values = ContentValues()
        values.put(IMAGE_BYTE, image)

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