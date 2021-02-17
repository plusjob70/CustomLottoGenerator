package com.example.lotto

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.android.synthetic.main.activity_result.*
import org.jetbrains.anko.doAsync

class ResultActivity : AppCompatActivity() {
    private val selectNumbers = mutableListOf<Int>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)

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
                    7 -> selectNumbers.add(pickRandomNotAppear5Weeks(selectNumbers))
                    10 -> selectNumbers.add(pickMaxAppearNumber(selectNumbers))
                    11 -> selectNumbers.add(pickMinAppearNumber(selectNumbers))
                    else -> selectNumbers.add(pickRandomNumber(selectNumbers))
                }

            }
            selectNumbers.sort()
            resultTextView.text = "${selectNumbers[0]}, ${selectNumbers[1]}, ${selectNumbers[2]}, ${selectNumbers[3]}, ${selectNumbers[4]}, ${selectNumbers[5]}"
        }
    }

    override fun onDestroy() {
        selectNumbers.clear()
        super.onDestroy()
    }

}