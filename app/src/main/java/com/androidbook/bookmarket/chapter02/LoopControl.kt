package com.androidbook.bookmarket.chapter02

internal fun loopExamples(output: (String) -> Unit) {
    output("[반복문]")
    for (i in 1..5) {
        output("for: $i")
    }
    for (i in 5 downTo 1) {
        output("downTo: $i")
    }
    for (i in 5 downTo 1 step 2) {
        output("step: $i")
    }
    val numbers = arrayOf(1, 2, 3, 4, 5)
    for (i in numbers) {
        if (i % 2 == 1) {
            output("배열의 홀수: $i")
        }
    }
    for (i in numbers.indices) {
        output("배열[$i]: ${numbers[i]}")
    }
    var i = 0
    while (i <= 5) {
        output("while: $i")
        i++
    }
    var j = 0
    do {
        output("do-while: $j")
        j++
    } while (j <= 5)
}
