package com.androidbook.bookmarket

import android.os.Bundle
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class BooksActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_books)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.books)) { view, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            view.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val book1Obj = findViewById<ImageView>(R.id.book1)
        val book2Obj = findViewById<ImageView>(R.id.book2)
        val book3Obj = findViewById<ImageView>(R.id.book3)
        val book4Obj = findViewById<ImageView>(R.id.book4)

        book1Obj.setOnClickListener {
            Toast.makeText(this, R.string.book_algorithm, Toast.LENGTH_LONG).show()
        }
        book2Obj.setOnClickListener {
            Toast.makeText(this, R.string.book_mysql, Toast.LENGTH_LONG).show()
        }
        book3Obj.setOnClickListener {
            Toast.makeText(this, R.string.book_php, Toast.LENGTH_LONG).show()
        }
        book4Obj.setOnClickListener {
            Toast.makeText(this, R.string.book_html, Toast.LENGTH_LONG).show()
        }
    }
}
