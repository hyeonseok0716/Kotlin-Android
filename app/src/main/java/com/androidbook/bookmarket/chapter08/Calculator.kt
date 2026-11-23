package com.androidbook.bookmarket.chapter08

import java.math.BigDecimal
import java.math.RoundingMode

internal class Calculator {
    var display = "0"
        private set
    var expression = ""
        private set
    private var first: BigDecimal? = null
    private var operator: String? = null
    private var replaceEntry = true
    private var error = false

    fun press(key: String) {
        when (key) {
            "AC" -> reset()
            "C" -> {
                if (error) reset()
                display = "0"
                replaceEntry = false
                if (operator == null) expression = ""
            }
            "⬅" -> {
                if (error) reset()
                if (!replaceEntry) display = display.dropLast(1).ifEmpty { "0" }
            }
            "." -> {
                if (error) reset()
                if (replaceEntry) {
                    if (operator == null) expression = ""
                    display = "0."
                    replaceEntry = false
                } else if (!display.contains('.')) {
                    display += "."
                }
            }
            "+", "-", "x", "÷" -> selectOperator(key)
            "=" -> equalsResult()
            in "0".."9" -> {
                if (error) reset()
                if (replaceEntry) {
                    if (operator == null) expression = ""
                    display = key
                } else {
                    display = if (display == "0") key else display + key
                }
                replaceEntry = false
            }
        }
    }

    private fun reset() {
        display = "0"
        expression = ""
        first = null
        operator = null
        replaceEntry = true
        error = false
    }

    private fun selectOperator(next: String) {
        if (error) return
        val current = display.toBigDecimal()
        val pending = operator
        if (pending != null && !replaceEntry) {
            val result = calculate(first ?: current, current, pending) ?: return showError()
            first = result
            display = format(result)
        } else {
            first = current
        }
        operator = next
        expression = "${format(first!!)} $next"
        replaceEntry = true
    }

    private fun equalsResult() {
        val pending = operator ?: return
        if (replaceEntry) return
        val left = first ?: return
        val right = display.toBigDecimal()
        val result = calculate(left, right, pending) ?: return showError()
        display = format(result)
        expression = "${format(left)} $pending ${format(right)} = $display"
        first = result
        operator = null
        replaceEntry = true
    }

    private fun calculate(left: BigDecimal, right: BigDecimal, operation: String): BigDecimal? {
        return when (operation) {
            "+" -> left + right
            "-" -> left - right
            "x" -> left * right
            "÷" -> if (right.signum() == 0) null else left.divide(right, 9, RoundingMode.HALF_UP)
            else -> null
        }
    }

    private fun showError() {
        display = "오류"
        expression = ""
        first = null
        operator = null
        replaceEntry = true
        error = true
    }

    private fun format(number: BigDecimal): String = number.stripTrailingZeros().toPlainString()
}
