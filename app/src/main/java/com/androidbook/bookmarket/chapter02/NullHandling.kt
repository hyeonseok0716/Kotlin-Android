package com.androidbook.bookmarket.chapter02

internal fun nullExamples(output: (String) -> Unit) {
    output("[널 처리]")
    val nonNullable: String = "John"
    output("Non-nullable: $nonNullable")
    var name: String? = "John"
    output("Nullable: $name")
    output("안전 호출: ${name?.length}")
    name?.let {
        output("이름의 길이는 ${it.length}입니다.")
    }
    name = null
    output("null 대입: $name")
    output("null 안전 호출: ${name?.length}")
    output("엘비스 연산자: ${name?.length ?: 0}")
    try {
        val length = name!!.length
        output("강제 호출: $length")
    } catch (error: NullPointerException) {
        output("강제 호출: NullPointerException")
    }
    name?.let {
        output("null일 때 실행되지 않는 let: $it")
    }
}
