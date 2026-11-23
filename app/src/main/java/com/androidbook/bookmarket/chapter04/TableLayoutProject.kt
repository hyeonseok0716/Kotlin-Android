package com.androidbook.bookmarket.chapter04

import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.androidbook.bookmarket.R

class TableLayoutProject : AppCompatActivity() {
    private lateinit var etObjA: EditText
    private lateinit var etObjB: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_table_layout_project)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { view, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            view.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        etObjA = findViewById(R.id.editText1)
        etObjB = findViewById(R.id.editText2)
    }

    fun onClickChoice(view: View) {
        val message = if (isCorrectCount(etObjA.text.toString(), etObjB.text.toString())) {
            R.string.count_correct
        } else {
            R.string.count_incorrect
        }
        val keyboard = getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
        keyboard.hideSoftInputFromWindow(view.windowToken, 0)
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}

internal fun isCorrectCount(turtles: String, fish: String): Boolean {
    return turtles.toIntOrNull() == 2 && fish.toIntOrNull() == 5
}
