package com.androidbook.bookmarket

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class BookActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_book)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.book)) { view, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            view.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        setSupportActionBar(findViewById<Toolbar>(R.id.toolbar))
        supportActionBar?.setTitle(R.string.book_details_title)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        findViewById<TextView>(R.id.book_tv01).text = intent.getStringExtra("bookid")
        findViewById<TextView>(R.id.book_tv02).text = intent.getStringExtra("name")
        findViewById<TextView>(R.id.book_tv03).text = intent.getStringExtra("price")
        findViewById<TextView>(R.id.book_tv04).text = intent.getStringExtra("date")
        findViewById<TextView>(R.id.book_tv05).text = intent.getStringExtra("writer")
        findViewById<TextView>(R.id.book_tv06).text = intent.getStringExtra("description")
        findViewById<TextView>(R.id.book_tv07).text = intent.getStringExtra("category")
        val image = when (intent.getStringExtra("bookid")) {
            "BOOK1234" -> R.drawable.books_book1
            "BOOK1235" -> R.drawable.books_book2
            "BOOK1236" -> R.drawable.books_book3
            "BOOK1237" -> R.drawable.books_book4
            else -> null
        }
        val cover = findViewById<ImageView>(R.id.imageView1)
        if (image != null) cover.setImageResource(image)
        cover.contentDescription = intent.getStringExtra("name")
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_book, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> finish()
            R.id.menu_home -> {
                Toast.makeText(this, item.title, Toast.LENGTH_SHORT).show()
                startActivity(Intent(this, MainActivity::class.java).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_SINGLE_TOP))
                finish()
            }
            R.id.menu_list -> {
                Toast.makeText(this, item.title, Toast.LENGTH_SHORT).show()
                startActivity(Intent(this, BooksActivity::class.java).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_SINGLE_TOP))
                finish()
            }
            R.id.menu_cart -> Toast.makeText(this, item.title, Toast.LENGTH_SHORT).show()
            else -> return super.onOptionsItemSelected(item)
        }
        return true
    }
}
