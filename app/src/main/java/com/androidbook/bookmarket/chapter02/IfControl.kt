package com.androidbook.bookmarket.chapter02

internal fun ifControlExamples(output: (String) -> Unit) {
    output("[조건문]")
    val positive = 3
    if (positive > 0) {
        output("숫자 $positive 은 양수")
    }
    val even = 10
    if (even % 2 == 0) {
        output("숫자 $even 은 짝수")
    } else {
        output("숫자 $even 은 홀수")
    }
    val num = -10
    val sign = if (num > 0) {
        "숫자 $num 은 양수"
    } else if (num == 0) {
        "숫자 $num 은 0"
    } else {
        "숫자 $num 은 음수"
    }
    output(sign)
    val result = if (num > 0) {
        if (num % 2 == 0) {
            "숫자 $num 은 양수이고 짝수"
        } else {
            "숫자 $num 은 양수이고 홀수"
        }
    } else {
        if (num % 2 == 0) {
            "숫자 $num 은 음수이고 짝수"
        } else {
            "숫자 $num 은 음수이고 홀수"
        }
    }
    output(result)
}
