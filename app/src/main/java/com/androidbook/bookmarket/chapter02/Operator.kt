package com.androidbook.bookmarket.chapter02

internal fun operatorExamples(output: (String) -> Unit) {
    output("[연산자]")
    val x = 4
    val y = 2
    output("x + y = ${x + y}")
    output("x - y = ${x - y}")
    output("x / y = ${x / y}")
    output("x * y = ${x * y}")
    output("x % y = ${x % y}")
    output("x > y = ${x > y}")
    output("x < y = ${x < y}")
    output("x >= y = ${x >= y}")
    output("x <= y = ${x <= y}")
    output("x == y = ${x == y}")
    output("x != y = ${x != y}")
    val a = 5
    var b = 10
    b += a
    output("y += x => y = $b")
    b -= a
    output("y -= x => y = $b")
    b *= a
    output("y *= x => y = $b")
    b /= a
    output("y /= x => y = $b")
    b %= a
    output("y %= x => y = $b")
    var number = 1
    output("++x = ${++number}")
    output("--x = ${--number}")
    val first = true
    val second = false
    output("x && y = ${first && second}")
    output("x || y = ${first || second}")
    output("!y = ${!second}")
}
