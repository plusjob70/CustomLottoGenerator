package com.example.lotto

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.ContextMenu
import android.view.MenuItem
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
        showFavNumButton.setOnClickListener {
            startActivity<FavoriteNumbersActivity>()
        }
        executeButton.setOnClickListener {
            alert ("${getString(R.string.first_number)} : ${getString(R.string.option)}${selectedOptions[0]}\n\n" +
                            "${getString(R.string.second_number)} : ${getString(R.string.option)}${selectedOptions[1]}\n\n" +
                            "${getString(R.string.third_number)} : ${getString(R.string.option)}${selectedOptions[2]}\n\n" +
                            "${getString(R.string.fourth_number)} : ${getString(R.string.option)}${selectedOptions[3]}\n\n" +
                            "${getString(R.string.fifth_number)} : ${getString(R.string.option)}${selectedOptions[4]}\n\n" +
                            "${getString(R.string.sixth_number)} : ${getString(R.string.option)}${selectedOptions[5]}") {
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

        registerForContextMenu(executeButton)
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
            // Random Number
            R.id.random -> {
                selectedOptions[index] = 1
                toast(getString(R.string.random))
            }
            // Random Among Odd Numbers
            R.id.oddRandom -> {
                selectedOptions[index] = 2
                toast(getString(R.string.random_odd))
            }
            // Random Among Even Numbers
            R.id.evenRandom -> {
                selectedOptions[index] = 3
                toast(getString(R.string.random_even))
            }
            // Random Among 5 Week Non-emergence Numbers
            R.id.notAppear5WeeksRandom -> {
                selectedOptions[index] = 4
                toast(getString(R.string.random_5week))
            }
            // Random Among 8 Week Non-emergence Numbers
            R.id.notAppear8WeeksRandom -> {
                selectedOptions[index] = 5
                toast(getString(R.string.random_8week))
            }
            // Random Among 10 Week Non-emergence Numbers
            R.id.notAppear10WeeksRandom -> {
                selectedOptions[index] = 6
                toast(getString(R.string.random_10week))
            }
            // Random Among 15 Week Non-emergence Numbers
            R.id.notAppear15WeeksRandom -> {
                selectedOptions[index] = 7
                toast(getString(R.string.random_15week))
            }
            // Random Among Most Frequently emergence Seven Numbers
            R.id.maxAppearNum -> {
                selectedOptions[index] = 8
                toast(getString(R.string.random_max))
            }
            // Random Among Least Emergence Seven Numbers
            R.id.minAppearNum -> {
                selectedOptions[index] = 9
                toast(getString(R.string.random_min))
            }
        }
    }

    override fun onCreateContextMenu(menu: ContextMenu?, v: View?, menuInfo: ContextMenu.ContextMenuInfo?) {
        super.onCreateContextMenu(menu, v, menuInfo)
        menuInflater.inflate(R.menu.number_statistics, menu)
    }

    override fun onContextItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.statistics5Week -> startActivity<StatisticsActivity>("period" to 5)
            R.id.statistics8Week -> startActivity<StatisticsActivity>("period" to 8)
            R.id.statistics10Week -> startActivity<StatisticsActivity>("period" to 10)
            R.id.statistics15Week -> startActivity<StatisticsActivity>("period" to 15)
            R.id.appearHistory -> startActivity<StatisticsActivity>("period" to 0)    // 역대 번호 출현
        }
        return super.onContextItemSelected(item)
    }
}