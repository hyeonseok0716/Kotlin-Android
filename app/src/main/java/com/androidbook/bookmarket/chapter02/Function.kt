package com.androidbook.bookmarket.chapter02

internal fun functionExamples(output: (String) -> Unit) {
    output("[함수]")
    val num1 = 3
    val num2 = 5
    output("$num1 * $num2 = ${mul(num1, num2)}")
    output("기본 매개변수: ${mul(num1)}")
    printProduct(num1, num2, output)
    output("이름 있는 인자: ${mul(a = 3, b = 5)}")
    val dividend = 26
    val divisor = 3
    output("$dividend % $divisor = ${dividend.rem(divisor)}")
}

private fun mul(a: Int, b: Int = 5): Int {
    val number = a * b
    return number
}

private fun printProduct(a: Int, b: Int, output: (String) -> Unit): Unit {
    val number = a * b
    output("Unit 함수: $a * $b = $number")
}
