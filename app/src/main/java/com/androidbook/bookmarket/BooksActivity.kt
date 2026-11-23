package com.androidbook.bookmarket

import android.os.Bundle
import android.content.Intent
import android.view.Menu
import android.view.MenuItem
import android.widget.SearchView
import androidx.appcompat.widget.Toolbar
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

        setSupportActionBar(findViewById<Toolbar>(R.id.toolbar))
        supportActionBar?.setTitle(R.string.books_title)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val book1Obj = findViewById<ImageView>(R.id.book1)
        val book2Obj = findViewById<ImageView>(R.id.book2)
        val book3Obj = findViewById<ImageView>(R.id.book3)
        val book4Obj = findViewById<ImageView>(R.id.book4)

        book1Obj.setOnClickListener {
            Toast.makeText(this, R.string.book_algorithm, Toast.LENGTH_LONG).show()
            BookItem1()
        }
        book2Obj.setOnClickListener {
            Toast.makeText(this, R.string.book_mysql, Toast.LENGTH_LONG).show()
            BookItem2()
        }
        book3Obj.setOnClickListener {
            Toast.makeText(this, R.string.book_php, Toast.LENGTH_LONG).show()
            BookItem3()
        }
        book4Obj.setOnClickListener {
            Toast.makeText(this, R.string.book_html, Toast.LENGTH_LONG).show()
            BookItem4()
        }
    }

    fun BookItem1(){

        val intent : Intent = Intent(this, BookActivity::class.java)
        intent.putExtra("bookid", "BOOK1234")
        intent.putExtra("name", "쉽게 배우는 알고리즘")
        intent.putExtra("price", "22000")
        intent.putExtra("date", "2024-01-03")
        intent.putExtra("writer", "문병로")
        intent.putExtra("description", "알고리즘에 대한 지식을 기반으로 제대로 된 프로그래밍을 하는 이들뿐 아니라, 알고리즘 속에 깃든 여러 가지 생각하는 방법, 자료구조, 테크닉을 통해 체계적으로 생각하는 훈련을 하고자 하는 모든 이를 대상으로 한다. ")
        intent.putExtra("category", "IT/모바일")
        startActivity(intent)

    }
    fun BookItem2(){

        val intent : Intent = Intent(this, BookActivity::class.java)
        intent.putExtra("bookid", "BOOK1235")
        intent.putExtra("name", "난생처음 MySQL")
        intent.putExtra("price", "34000")
        intent.putExtra("date", "2024-01-17")
        intent.putExtra("writer", "배은영")
        intent.putExtra("description", "175개의 다양한 실전 예제를 통해 SQL의 기초와 쿼리 작성법을 배웁니다. 무역회사 데이터베이스를 기반으로 누적되는 시나리오를 기반으로 누적되는 시나리오를 연습할 수 있습니다.")
        intent.putExtra("category", "IT/모바일")
        startActivity(intent)

    }
    fun BookItem3(){

        val intent : Intent = Intent(this, BookActivity::class.java)
        intent.putExtra("bookid", "BOOK1236")
        intent.putExtra("name", "쉽게 배우는 PHP 웹 프로그래밍")
        intent.putExtra("price", "30000")
        intent.putExtra("date", "2024-06-30")
        intent.putExtra("writer", "송미영")
        intent.putExtra("description", "‘PHP의 이론적 개념 → 기본 실습 → 응용 실습’ 순의 단계별 학습이 가능합니다. 각 장마다 제공하는 프로젝트 실습을 제작하며 북마켓 쇼핑몰을 완성하도록 구성하여 본문에서 배운 내용이 어디에 어떻게 적용되는지 확인할 수 있습니다.")
        intent.putExtra("category", "IT/모바일")
        startActivity(intent)

    }
    fun BookItem4(){

        val intent : Intent = Intent(this, BookActivity::class.java)
        intent.putExtra("bookid", "BOOK1237")
        intent.putExtra("name", "미라클 HTML5+CSS3+자바스크립트")
        intent.putExtra("price", "28000")
        intent.putExtra("date", "2024-07-11")
        intent.putExtra("writer", "조성호")
        intent.putExtra("description", "다채로운 예제를 통해 HTML5, CSS3, 자바스크립트를 설명합니다. 친절한 본문 설명과 더불어 캐릭터의 친근한 대화로 핵심 내용을 짚어주고, 실습하는 재미를 느낄 수 있는 동적인 예제와 시각적 요소를 충분히 활용한 예제를 다수 제공합니다.")
        intent.putExtra("category", "IT/모바일")
        startActivity(intent)

    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_books, menu)
        val searchView = menu.findItem(R.id.menu_search).actionView as SearchView
        searchView.queryHint = getString(R.string.book_search_hint)
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                Toast.makeText(this@BooksActivity, query, Toast.LENGTH_SHORT).show()
                searchView.clearFocus()
                return true
            }
            override fun onQueryTextChange(newText: String): Boolean = false
        })
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
            R.id.main_video, R.id.menu_customer, R.id.menu_mypage -> {
                Toast.makeText(this, item.title, Toast.LENGTH_SHORT).show()
            }
            else -> return super.onOptionsItemSelected(item)
        }
        return true
    }
}
