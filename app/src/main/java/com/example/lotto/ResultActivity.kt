package com.example.lotto

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Gravity
import android.widget.Toast
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdView
import com.google.android.gms.ads.MobileAds
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_result.*
import org.jetbrains.anko.doAsync

class ResultActivity : AppCompatActivity() {
    private val selectNumbers = mutableListOf<Int>()
    private var selectNumbersImageID = mutableListOf<Int>()
    private val sortSelectNumbersImageID = mutableListOf<Int>()
    private var dbHelper: DBHelper? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)

        MobileAds.initialize(this, getString(R.string.ad_app_id))

        dbHelper = DBHelper(this)
        var sortFlag = 1
        val selectedOptions = arrayOf(intent.getIntExtra("option1", 1),
                                      intent.getIntExtra("option2", 1),
                                      intent.getIntExtra("option3", 1),
                                      intent.getIntExtra("option4", 1),
                                      intent.getIntExtra("option5", 1),
                                      intent.getIntExtra("option6", 1))

        doAsync {
            for (index in 0..5) {
                when (selectedOptions[index]) {
                    1 -> selectNumbers.add(pickRandomNumber(selectNumbers))
                    2 -> selectNumbers.add(pickRandomOddNumber(selectNumbers))
                    3 -> selectNumbers.add(pickRandomEvenNumber(selectNumbers))
                    4 -> selectNumbers.add(pickRandomNotAppear5Weeks(selectNumbers))
                    5 -> selectNumbers.add(pickRandomNotAppear8To15Weeks(selectNumbers, 8))
                    6 -> selectNumbers.add(pickRandomNotAppear8To15Weeks(selectNumbers, 10))
                    7 -> selectNumbers.add(pickRandomNotAppear8To15Weeks(selectNumbers, 15))
                    8 -> selectNumbers.add(pickMaxAppearNumber(selectNumbers))
                    9 -> selectNumbers.add(pickMinAppearNumber(selectNumbers))
                    else -> selectNumbers.add(pickRandomNumber(selectNumbers))
                }
            }

            selectNumbers.forEach { selectNumbersImageID.add(ballNumberToImageID(it)) }
            selectNumbers.sort()
            selectNumbers.forEach { sortSelectNumbersImageID.add(ballNumberToImageID(it)) }

            // Change Button Text If Button Clicked
            showBalls(0)
            sortButton.setOnClickListener {
                if (sortFlag == 1) {
                    showBalls(1)
                    sortButton.text = getString(R.string.restore)
                    sortFlag = 0
                } else {
                    showBalls(0)
                    sortButton.text = getString(R.string.sort)
                    sortFlag = 1
                }
            }

            // Add This Numbers To My Numbers
            addFavNumButton.setOnClickListener {
                if (sortSelectNumbersImageID.size == 6) {
                    val numberList = listOf<Int>(sortSelectNumbersImageID[0],
                                                 sortSelectNumbersImageID[1],
                                                 sortSelectNumbersImageID[2],
                                                 sortSelectNumbersImageID[3],
                                                 sortSelectNumbersImageID[4],
                                                 sortSelectNumbersImageID[5])
                    val byteArray = getByteArrayFromBitmap(joinImages(numberList))

                    if (dbHelper!!.addFavoriteNumbers(byteArray)) {
                        Log.d("태그", "성공")
                        Toast.makeText(applicationContext, getString(R.string.add_my_numbers), Toast.LENGTH_SHORT).show()
                    }else{
                        Log.d("태그", "실패")
                    }
                }
            }
        }
    }

    override fun onDestroy() {
        selectNumbers.clear()
        super.onDestroy()
    }

    private fun ballNumberToImageID(number: Int): Int {
        return resources.getIdentifier("ball$number", "drawable", packageName)
    }

    private fun showBalls(isSort: Int) {
        when (isSort) {
            0 -> runOnUiThread {
                    firstBallView.setImageResource(selectNumbersImageID[0])
                    secondBallView.setImageResource(selectNumbersImageID[1])
                    thirdBallView.setImageResource(selectNumbersImageID[2])
                    fourthBallView.setImageResource(selectNumbersImageID[3])
                    fifthBallView.setImageResource(selectNumbersImageID[4])
                    sixthBallView.setImageResource(selectNumbersImageID[5])
                }
            1 -> runOnUiThread {
                    firstBallView.setImageResource(sortSelectNumbersImageID[0])
                    secondBallView.setImageResource(sortSelectNumbersImageID[1])
                    thirdBallView.setImageResource(sortSelectNumbersImageID[2])
                    fourthBallView.setImageResource(sortSelectNumbersImageID[3])
                    fifthBallView.setImageResource(sortSelectNumbersImageID[4])
                    sixthBallView.setImageResource(sortSelectNumbersImageID[5])
            }
        }
    }

    override fun onResume() {
        super.onResume()
        val adView2 : AdView = findViewById(R.id.adView2)
        val adRequest = AdRequest.Builder().build()
        adView2.loadAd(adRequest)
    }
}

