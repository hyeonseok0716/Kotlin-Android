package com.androidbook.bookmarket.chapter08

import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.androidbook.bookmarket.R

class CustomDialogProject : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_custom_dialog_project)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { view, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            view.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        findViewById<Button>(R.id.button).setOnClickListener { showCalculator() }
    }

    private fun showCalculator() {
        val dialogView = layoutInflater.inflate(R.layout.dialog_calculate, null)
        val calculator = Calculator()
        val tvResult = dialogView.findViewById<TextView>(R.id.tvResult)
        val tvInput = dialogView.findViewById<TextView>(R.id.tvIntput)
        val keys = intArrayOf(
            R.id.btnAllCRL, R.id.btnCRL, R.id.btnMultiply, R.id.btnDivide,
            R.id.btnSeven, R.id.btnEight, R.id.btnNine, R.id.btnSubtract,
            R.id.btnFour, R.id.btnFive, R.id.btnSix, R.id.btnPlus,
            R.id.btnOne, R.id.btnTwo, R.id.btnThree, R.id.btnEqual,
            R.id.btnBack, R.id.btnZero, R.id.btnDot
        )
        tvResult.text = calculator.display
        for (id in keys) {
            dialogView.findViewById<ImageButton>(id).setOnClickListener { key ->
                calculator.press(key.tag.toString())
                tvInput.text = calculator.expression
                tvResult.text = calculator.display
            }
        }
        val dialog = AlertDialog.Builder(this).setView(dialogView).create()
        dialogView.findViewById<Button>(R.id.buttonclose).setOnClickListener { dialog.dismiss() }
        dialog.show()
        val width = minOf((resources.displayMetrics.widthPixels * 0.9).toInt(), (360 * resources.displayMetrics.density).toInt())
        dialog.window?.setBackgroundDrawableResource(android.R.color.transparent)
        dialog.window?.setLayout(width, (484 * resources.displayMetrics.density).toInt())
    }
}
