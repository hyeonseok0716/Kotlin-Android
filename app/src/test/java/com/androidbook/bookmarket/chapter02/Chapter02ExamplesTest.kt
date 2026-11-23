package com.androidbook.bookmarket.chapter02

import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test

class Chapter02ExamplesTest {
    @Test
    fun allExamplesRunThroughAlso() {
        val lines = mutableListOf<String>()
        Chapter02Examples.runAll { lines.add(it) }

        assertEquals(listOf(
            "[변수]", "[자료형과 형 변환]", "[연산자]", "[조건문]", "[반복문]",
            "[함수]", "[객체 지향 프로그래밍]", "[컬렉션]", "[널 처리]", "[기초 스코프 함수]"
        ), lines.filter { it.startsWith("[") && !it.startsWith("[1,") })
        assertTrue(lines.contains("Tuesday"))
        assertTrue(lines.contains("숫자 -10 은 음수이고 짝수"))
        assertTrue(lines.contains("26 % 3 = 2"))
        assertTrue(lines.contains("super.sound() 호출 결과: 동물 소리"))
        assertTrue(lines.contains("변경된 셋: [6, 10, 2, 5]"))
        assertEquals(listOf("리스트의 크기: 3", "[1, 2, 3, 4]"), lines.takeLast(2))
    }

    @Test
    fun nullExamplesDemonstrateFailureAndContinue() {
        val lines = mutableListOf<String>()
        nullExamples { lines.add(it) }

        assertTrue(lines.contains("안전 호출: 4"))
        assertTrue(lines.contains("null 안전 호출: null"))
        assertTrue(lines.contains("엘비스 연산자: 0"))
        assertTrue(lines.contains("강제 호출: NullPointerException"))
        assertFalse(lines.any { it.startsWith("null일 때 실행되지 않는 let:") })
    }

    @Test
    fun scopeFunctionsProducePdfResults() {
        val lines = mutableListOf<String>()
        scopeExamples { lines.add(it) }

        assertEquals(listOf(
            "[기초 스코프 함수]",
            "이름의 길이는 6입니다.",
            "이름은 Hello, Kotlin 입니다.",
            "길이: 13",
            "Hello, Kotlin!",
            "John",
            "리스트의 크기: 3",
            "[1, 2, 3, 4]"
        ), lines)
    }
}
