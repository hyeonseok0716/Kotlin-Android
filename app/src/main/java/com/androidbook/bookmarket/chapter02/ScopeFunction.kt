package com.androidbook.bookmarket.chapter02

internal fun scopeExamples(output: (String) -> Unit) {
    output("[기초 스코프 함수]")
    val name: String? = "Kotlin"
    name?.let {
        output("이름의 길이는 ${it.length}입니다.")
    }
    val message: String? = "Hello, Kotlin"
    val messageLength = message?.run {
        output("이름은 $this 입니다.")
        length
    }
    output("길이: $messageLength")
    val result = with(StringBuilder()) {
        append("Hello, ")
        append("Kotlin!")
        toString()
    }
    output(result)
    val person = ScopePerson().apply {
        this.name = "John"
        age = 25
    }
    output(person.name)
    val number = mutableListOf(1, 2, 3).also {
        output("리스트의 크기: ${it.size}")
        it.add(4)
    }
    output(number.toString())
}

private class ScopePerson {
    var name: String = ""
    var age: Int = 0
}
