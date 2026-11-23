package com.androidbook.bookmarket.chapter02

internal fun variableExamples(output: (String) -> Unit) {
    output("[변수]")
    var myAge = 20
    output("나의 나이는 $myAge")
    myAge = 25
    output("나의 나이는 $myAge")
    val myName = "홍길동"
    output("나의 이름은 $myName")
    lateinit var message: String
    message = "Kotlin"
    output("lateinit: $message")
}
