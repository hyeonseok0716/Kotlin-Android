package com.androidbook.bookmarket.chapter04

import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test

class TableLayoutProjectTest {
    @Test
    fun acceptsThePictureCounts() {
        assertTrue(isCorrectCount("2", "5"))
    }

    @Test
    fun rejectsAnIncorrectCountInEitherRow() {
        assertFalse(isCorrectCount("1", "5"))
        assertFalse(isCorrectCount("2", "4"))
        assertFalse(isCorrectCount("5", "2"))
    }

    @Test
    fun handlesEmptyAndInvalidNumbersWithoutCrashing() {
        assertFalse(isCorrectCount("", ""))
        assertFalse(isCorrectCount("2", ""))
        assertFalse(isCorrectCount("abc", "5"))
        assertFalse(isCorrectCount("2.0", "5"))
        assertFalse(isCorrectCount("2147483648", "5"))
    }
}
