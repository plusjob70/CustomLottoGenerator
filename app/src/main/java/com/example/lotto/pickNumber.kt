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

fun pickRandomNotAppear8To15Weeks(selectNumbers: List<Int>, period: Int): Int {
    val doc = Jsoup.connect("https://dhlottery.co.kr//gameResult.do?method=noViewNumber&sltPeriod=$period").get()
    val spanTag = doc.select("span.ball_645").text()
    val notAppearNumber = spanTag.split(" ")
    val notAppearNumberToIntType = mutableListOf<Int>()
    var randomNotAppearNumber: Int

    if (notAppearNumber.contains("")) {
        Log.d("태그", "미출현 번호 없음, 랜덤시행")
        return pickRandomNumber(selectNumbers)
    }
    notAppearNumber.forEach { notAppearNumberToIntType.add(it.toInt()) }

    if (selectNumbers.containsAll(notAppearNumberToIntType)) {
        return pickRandomNumber(selectNumbers)
    }

    do {
        randomNotAppearNumber = notAppearNumber.random().toInt()
    } while (selectNumbers.contains(randomNotAppearNumber))

    return randomNotAppearNumber
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

    return randomMinAppearNumber
}

fun <T> List<T>.random(): T{
    return get(Random.nextInt(size))
}