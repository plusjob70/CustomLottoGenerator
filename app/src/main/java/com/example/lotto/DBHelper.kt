package com.example.lotto

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DBHelper(context: Context): SQLiteOpenHelper(context, DB_NAME, null, DB_VERSION) {
    companion object {
        private const val DB_NAME = "ImageDB"
        private const val DB_VERSION = 1
        private const val TABLE_NAME = "Images"
        private const val ID = "id"
        private const val IMAGE_BYTE = "image_byte"
    }

    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL("CREATE TABLE $TABLE_NAME($ID INTEGER PRIMARY KEY AUTOINCREMENT, $IMAGE_BYTE BLOB)")
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.execSQL("DROP TABLE IF EXISTS $TABLE_NAME")
        onCreate(db)
    }

    fun getAllByteArray(): Map<Int, ByteArray> {
        val map = mutableMapOf<Int, ByteArray>()

        val db = readableDatabase
        val cursor = db.rawQuery("SELECT * FROM $TABLE_NAME", null)

        if (cursor != null){
           if (cursor.moveToFirst()) {
               do {
                   map[cursor.getInt(cursor.getColumnIndex(ID))] = cursor.getBlob(cursor.getColumnIndex(IMAGE_BYTE))
               } while (cursor.moveToNext())
           }
        }

        cursor.close()
        db.close()

        return map
    }

    fun addFavoriteNumbers(image: ByteArray): Boolean {
        val db = writableDatabase
        val values = ContentValues()

        values.put(IMAGE_BYTE, image)

        val isSuccess = db.insert(TABLE_NAME, null, values)
        db.close()

        return (Integer.parseInt("$isSuccess") != -1)
    }

    fun deleteFavoriteNumbers(id: Int) {
        val db = writableDatabase
        db.execSQL("DELETE FROM $TABLE_NAME WHERE id = $id")
        db.close()
    }
}