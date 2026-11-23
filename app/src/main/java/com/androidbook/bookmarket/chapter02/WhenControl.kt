package com.androidbook.bookmarket.chapter02

internal fun whenControlExamples(output: (String) -> Unit) {
    val day = 2
    val dayName = when (day) {
        1 -> "Monday"
        2 -> "Tuesday"
        3 -> "Wednesday"
        4 -> "Thursday"
        5 -> "Friday"
        6 -> "Saturday"
        7 -> "Sunday"
        else -> "Invalid day"
    }
    output(dayName)
    output(when (day) {
        1, 2, 3, 4, 5 -> "평일"
        6, 7 -> "주말"
        else -> "잘못된 요일"
    })
    output(when (day) {
        in 1..5 -> "평일 범위"
        !in 1..7 -> "요일 범위 밖"
        else -> "주말 범위"
    })
}
