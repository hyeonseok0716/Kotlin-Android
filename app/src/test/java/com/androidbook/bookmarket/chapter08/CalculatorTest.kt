package com.androidbook.bookmarket.chapter08

import org.junit.Assert.assertEquals
import org.junit.Test

class CalculatorTest {
    @Test
    fun calculatesAndDisplaysTheEquation() {
        val calculator = Calculator()
        listOf("5", "0", "+", "6", "=").forEach(calculator::press)
        assertEquals("56", calculator.display)
        assertEquals("50 + 6 = 56", calculator.expression)
    }

    @Test
    fun dividesToNineDecimalPlaces() {
        val calculator = Calculator()
        listOf("4", "4", "÷", "3", "=").forEach(calculator::press)
        assertEquals("14.666666667", calculator.display)
    }

    @Test
    fun backspaceChangesTheOperandUsedInCalculation() {
        val calculator = Calculator()
        listOf("1", "0", "+", "2", "5", "⬅", "=").forEach(calculator::press)
        assertEquals("12", calculator.display)
    }

    @Test
    fun clearEntryKeepsTheFirstOperandAndAllClearResetsIt() {
        val calculator = Calculator()
        listOf("8", "+", "9", "C", "2", "=").forEach(calculator::press)
        assertEquals("10", calculator.display)
        calculator.press("AC")
        assertEquals("0", calculator.display)
        assertEquals("", calculator.expression)
    }

    @Test
    fun acceptsOneDecimalPointAndUsesExactDecimalArithmetic() {
        val calculator = Calculator()
        listOf(".", "1", ".", "+", ".", "2", "=").forEach(calculator::press)
        assertEquals("0.3", calculator.display)
    }

    @Test
    fun replacesPendingOperatorAndSupportsChaining() {
        val calculator = Calculator()
        listOf("9", "+", "-", "2", "x", "3", "=").forEach(calculator::press)
        assertEquals("21", calculator.display)
        listOf("+", "1", "=").forEach(calculator::press)
        assertEquals("22", calculator.display)
        calculator.press("7")
        assertEquals("7", calculator.display)
        assertEquals("", calculator.expression)
    }

    @Test
    fun handlesDivisionByZeroAndRecoversWithNewInput() {
        val calculator = Calculator()
        listOf("5", "÷", "0", "=").forEach(calculator::press)
        assertEquals("오류", calculator.display)
        listOf("2", "+", "3", "=").forEach(calculator::press)
        assertEquals("5", calculator.display)
    }

    @Test
    fun incompleteEquationsAndDeletingAllDigitsDoNotCrash() {
        val calculator = Calculator()
        listOf("=", "⬅", "1", "⬅", "⬅", "+", "=").forEach(calculator::press)
        assertEquals("0", calculator.display)
    }
}
