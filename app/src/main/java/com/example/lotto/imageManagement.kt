package com.example.lotto

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Canvas
import java.io.ByteArrayOutputStream

const val SPACE = 50f

fun joinImages(imageIdList: List<Int>): Bitmap {
    val lottoAppContext = LottoApp.applicationContext()

    val bmp1 = BitmapFactory.decodeResource(lottoAppContext.resources, imageIdList[0])
    val bmp2 = BitmapFactory.decodeResource(lottoAppContext.resources, imageIdList[1])
    val bmp3 = BitmapFactory.decodeResource(lottoAppContext.resources, imageIdList[2])
    val bmp4 = BitmapFactory.decodeResource(lottoAppContext.resources, imageIdList[3])
    val bmp5 = BitmapFactory.decodeResource(lottoAppContext.resources, imageIdList[4])
    val bmp6 = BitmapFactory.decodeResource(lottoAppContext.resources, imageIdList[5])

    val bmOverlay = Bitmap.createBitmap((bmp1.width + 100) * 6, bmp1.height, Bitmap.Config.ARGB_8888)
    val canvas = Canvas(bmOverlay)

    canvas.drawBitmap(bmp1, 0f, 0f, null)
    canvas.drawBitmap(bmp2, bmp1.width.toFloat() + SPACE, 0f, null)
    canvas.drawBitmap(bmp3, (bmp1.width.toFloat() + SPACE) * 2, 0f, null)
    canvas.drawBitmap(bmp4, (bmp1.width.toFloat() + SPACE) * 3, 0f, null)
    canvas.drawBitmap(bmp5, (bmp1.width.toFloat() + SPACE) * 4, 0f, null)
    canvas.drawBitmap(bmp6, (bmp1.width.toFloat() + SPACE) * 5, 0f, null)

    return bmOverlay
}

fun getByteArrayFromBitmap(bmp: Bitmap) : ByteArray{
    val stream = ByteArrayOutputStream()
    bmp.compress(Bitmap.CompressFormat.PNG, 100, stream)
    return stream.toByteArray()
}

fun getBitmapFromByteArray(byteArray: ByteArray) : Bitmap {
    return BitmapFactory.decodeByteArray(byteArray, 0, byteArray.size)
}