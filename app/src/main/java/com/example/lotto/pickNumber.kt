package com.example.lotto

import android.util.Log
import org.jsoup.Jsoup
import kotlin.random.Random


fun pickRandomNumber(selectNumbers: List<Int>): Int {
    var randomNumber: Int

    do {
        randomNumber = Random.nextInt(1, 46)
    } while (selectNumbers.contains(randomNumber))

    return randomNumber
}

fun pickRandomOddNumber(selectNumbers: List<Int>): Int {
    var randomOddNumber: Int

    do {
        randomOddNumber = Random.nextInt(1, 46)
    } while (selectNumbers.contains(randomOddNumber) || randomOddNumber % 2 == 0)

    return randomOddNumber
}

fun pickRandomEvenNumber(selectNumbers: List<Int>): Int {
    var randomEvenNumber: Int

    do {
        randomEvenNumber = Random.nextInt(1, 46)
    } while (selectNumbers.contains(randomEvenNumber) || randomEvenNumber % 2 == 1)

    return randomEvenNumber
}

fun pickRandomMaxAppear5Weeks(selectNumbers: List<Int>): Int {
    TODO("'https://dhlottery.co.kr/gameResult.do?method=statGroupNum'에서 5주간 많이 나온 번호대 중 랜덤으로 선별")
}

public fun pickRandomMaxAppear10Weeks(selectNumbers: List<Int>): Int {
    TODO("'https://dhlottery.co.kr/gameResult.do?method=statGroupNum'에서 10주간 많이 나온 번호대 중 랜덤으로 선별")
}

public fun pickRandomMaxAppear15Weeks(selectNumbers: List<Int>): Int {
    TODO("'https://dhlottery.co.kr/gameResult.do?method=statGroupNum'에서 15주간 많이 나온 번호대 중 랜덤으로 선별")
}

fun pickRandomNotAppear5Weeks(selectNumbers: List<Int>): Int {
    val doc = Jsoup.connect("https://dhlottery.co.kr/gameResult.do?method=noViewNumber").get()
    val spanTag = doc.select("span.ball_645").text()
    val notAppearNumber = spanTag.split(" ")
    var randomNotAppearNumber: Int

    do {
        randomNotAppearNumber = notAppearNumber.random().toInt()
    } while (selectNumbers.contains(randomNotAppearNumber))

    return randomNotAppearNumber
}

public fun pickRandomNotAppear10Weeks(selectNumbers: List<Int>): Int {
    TODO("'https://dhlottery.co.kr/gameResult.do?method=noViewNumber'에서 10주간 출현하지 않은 번호 중에서 랜덤으로 선별")
}

public fun pickRandomNotAppear15Weeks(selectNumbers: List<Int>): Int {
    TODO("'https://dhlottery.co.kr/gameResult.do?method=noViewNumber'에서 15주간 출현하지 않은 번호 중에서 랜덤으로 선별")
}

fun pickMaxAppearNumber(selectNumbers: List<Int>): Int{
    val doc = Jsoup.connect("https://dhlottery.co.kr/gameResult.do?method=statByNumber").get()
    val numberOfHistory = mutableListOf<Int>()
    val maxAppearNumber = mutableListOf<Int>()
    var randomMaxAppearNumber: Int

    for (number in 1..45){
        numberOfHistory.add(doc.select("tr")[number + 2].select("td")[2].text().toInt())
    }

    for (i in 1..7) {
        val indexOfNumberOfHistory = numberOfHistory.indexOf(numberOfHistory.max())
        maxAppearNumber.add(indexOfNumberOfHistory + 1)
        numberOfHistory[indexOfNumberOfHistory] = -1
    }

    do {
        randomMaxAppearNumber = maxAppearNumber.random()
    } while (selectNumbers.contains(randomMaxAppearNumber))

    return randomMaxAppearNumber
}

fun pickMinAppearNumber(selectNumbers: List<Int>): Int{
    val doc = Jsoup.connect("https://dhlottery.co.kr/gameResult.do?method=statByNumber").get()
    val numberOfHistory = mutableListOf<Int>()
    val minAppearNumber = mutableListOf<Int>()
    var randomMinAppearNumber: Int

    for (number in 1..45){
        numberOfHistory.add(doc.select("tr")[number + 2].select("td")[2].text().toInt())
    }

    for (i in 1..7) {
        val indexOfNumberOfHistory = numberOfHistory.indexOf(numberOfHistory.min())
        minAppearNumber.add(indexOfNumberOfHistory + 1)
        numberOfHistory[indexOfNumberOfHistory] = numberOfHistory.max()!!
    }

    do {
        randomMinAppearNumber = minAppearNumber.random()
    } while (selectNumbers.contains(randomMinAppearNumber))

    Log.d("태그", "pickMinAppearNumber: ")
    return randomMinAppearNumber
}

fun <T> List<T>.random(): T{
    return get(Random.nextInt(size))
}