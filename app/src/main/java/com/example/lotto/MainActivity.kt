package com.example.lotto

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.PopupMenu
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.*

class MainActivity : AppCompatActivity() {
    private val selectedOptions = arrayOf(1,1,1,1,1,1)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        firstNumButton.setOnClickListener {
            showPopupMenu(it, 0)
        }
        secondNumButton.setOnClickListener {
            showPopupMenu(it, 1)
        }
        thirdNumButton.setOnClickListener {
            showPopupMenu(it, 2)
        }
        fourthNumButton.setOnClickListener {
            showPopupMenu(it, 3)
        }
        fifthNumButton.setOnClickListener {
            showPopupMenu(it, 4)
        }
        sixthNumButton.setOnClickListener {
            showPopupMenu(it, 5)
        }
        executeButton.setOnClickListener {
            alert ("옵션${selectedOptions[0]}, 옵션${selectedOptions[1]}, 옵션${selectedOptions[2]}, 옵션${selectedOptions[3]}, 옵션${selectedOptions[4]}, 옵션${selectedOptions[5]}") {
                yesButton {
                    startActivity<ResultActivity>(
                        "option1" to selectedOptions[0],
                        "option2" to selectedOptions[1],
                        "option3" to selectedOptions[2],
                        "option4" to selectedOptions[3],
                        "option5" to selectedOptions[4],
                        "option6" to selectedOptions[5])
                }
                noButton { }
            }.show()
        }
    }

    private fun showPopupMenu(view: View, index: Int){
        val popupMenu = PopupMenu(applicationContext, view)
        menuInflater.inflate(R.menu.setting_menu, popupMenu.menu)
        popupMenu.setOnMenuItemClickListener { item ->
            switchOfOptionList(item.itemId, index)
            true
        }
        popupMenu.show()
    }

    private fun switchOfOptionList(itemID: Int, index: Int){
        when(itemID){
            R.id.random -> {
                selectedOptions[index] = 1
                toast("랜덤")
            }
            R.id.oddRandom -> {
                selectedOptions[index] = 2
                toast("홀수 중 랜덤")
            }
            R.id.evenRandom -> {
                selectedOptions[index] = 3
                toast("짝수 중 랜덤")
            }
            R.id.notAppear5WeeksRandom -> {
                selectedOptions[index] = 4
                toast("5주간 미출현번호 중 랜덤")
            }
            R.id.notAppear8WeeksRandom -> {
                selectedOptions[index] = 5
                toast("8주간 미출현번호 중 랜덤")
            }
            R.id.notAppear10WeeksRandom -> {
                selectedOptions[index] = 6
                toast("10주간 미출현번호 중 랜덤")
            }
            R.id.notAppear15WeeksRandom -> {
                selectedOptions[index] = 7
                toast("15주간 미출현번호 중 랜덤")
            }
            R.id.maxAppearNum -> {
                selectedOptions[index] = 8
                toast("역대 최다출현 7개 중 랜덤")
            }
            R.id.minAppearNum -> {
                selectedOptions[index] = 9
                toast("역대 최소출현 7개 중 랜덤")
            }
        }
    }

}