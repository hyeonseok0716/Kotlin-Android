package com.androidbook.bookmarket.chapter02

internal fun dataTypeExamples(output: (String) -> Unit) {
    output("[자료형과 형 변환]")
    val numOne = 1
    val numTwo = 3000000000
    val myByte: Byte = 1
    val myShort: Short = 10
    val myInt: Int = 20
    val myLong = 25L
    output("Int: $numOne")
    output("Long: $numTwo")
    output("Byte: $myByte")
    output("Short: $myShort")
    output("Int: $myInt")
    output("Long: $myLong")
    val myFloat = 30.2F
    val myDouble = 35.4
    output("Float: $myFloat")
    output("Double: $myDouble")
    val myBoolean: Boolean = true
    output("Boolean: $myBoolean")
    val myChar1: Char = 'K'
    val myChar2: Char = 'o'
    val myChar3: Char = 't'
    val myChar4: Char = 'l'
    val myChar5: Char = 'i'
    val myChar6: Char = 'n'
    output("Char: $myChar1$myChar2$myChar3$myChar4$myChar5$myChar6")
    val myString1: String = "Kotlin\n"
    val myString2: String = "Java"
    output("String: $myString1")
    output("String: $myString2")
    val myArray: IntArray = intArrayOf(1, 2, 3, 4, 5)
    output("배열의 3번째 값: ${myArray[2]}")
    val myX: Int = 100
    val myY: Long = myX.toLong()
    output("Int: $myX")
    output("Long으로 변환: $myY")
}
