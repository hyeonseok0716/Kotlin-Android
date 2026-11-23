package com.androidbook.bookmarket.mypage

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.EditText
import android.widget.ListView
import android.widget.SearchView
import android.widget.SimpleAdapter
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.androidbook.bookmarket.R
import java.time.LocalDate

class MemoActivity : AppCompatActivity() {
    private lateinit var db: DatabaseHelperMemo
    private lateinit var listView: ListView
    private var memos = arrayListOf<HashMap<String, String>>()
    private var selectedId: Int? = null
    private var query = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_memo)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.memo)) { view, insets ->
            val bars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            view.setPadding(bars.left, bars.top, bars.right, bars.bottom)
            insets
        }
        setSupportActionBar(findViewById<Toolbar>(R.id.toolbar))
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        db = DatabaseHelperMemo(this)
        listView = findViewById(R.id.memo_list)
        listView.setOnItemClickListener { _, _, position, _ -> selectedId = memos[position]["id"]?.toIntOrNull() }
        findViewById<Button>(R.id.btnInsert).setOnClickListener { editMemo(null) }
        findViewById<Button>(R.id.btnUpdate).setOnClickListener {
            val memo = memos.firstOrNull { it["id"]?.toIntOrNull() == selectedId }
            if (memo == null) promptSelection() else editMemo(memo)
        }
        findViewById<Button>(R.id.btnDelete).setOnClickListener {
            val id = selectedId
            if (id == null) promptSelection() else AlertDialog.Builder(this)
                .setTitle("메모장 삭제")
                .setMessage("선택한 메모를 삭제하겠습니까?")
                .setPositiveButton("예") { _, _ -> db.deleteData(id); refresh() }
                .setNegativeButton("아니요", null)
                .show()
        }
        refresh()
    }

    private fun promptSelection() {
        Toast.makeText(this, "항목을 선택하세요", Toast.LENGTH_SHORT).show()
    }

    private fun editMemo(memo: HashMap<String, String>?) {
        val view = layoutInflater.inflate(R.layout.activity_memo_dialog, null)
        val title = view.findViewById<EditText>(R.id.title)
        val content = view.findViewById<EditText>(R.id.content)
        title.setText(memo?.get("title").orEmpty())
        content.setText(memo?.get("content").orEmpty())
        val dialog = AlertDialog.Builder(this).setView(view).create()
        view.findViewById<Button>(R.id.insert).apply {
            text = if (memo == null) "등록" else "수정"
            setOnClickListener {
                val date = LocalDate.now().toString()
                if (memo == null) db.insertData(date, title.text.toString(), content.text.toString())
                else db.updateData(memo["id"]!!.toInt(), date, title.text.toString(), content.text.toString())
                dialog.dismiss()
                refresh()
            }
        }
        view.findViewById<Button>(R.id.cancel).setOnClickListener { dialog.dismiss() }
        dialog.show()
    }

    private fun refresh() {
        memos = db.readData(query)
        selectedId = null
        listView.adapter = SimpleAdapter(this, memos, R.layout.activity_memo_item,
            arrayOf("date", "title", "content"), intArrayOf(R.id.tvDate, R.id.tvTitle, R.id.tvContent))
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_search, menu)
        val search = menu.findItem(R.id.search).actionView as SearchView
        search.queryHint = "메모 검색"
        search.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(text: String?): Boolean {
                query = text.orEmpty().trim()
                refresh()
                search.clearFocus()
                return true
            }
            override fun onQueryTextChange(text: String?): Boolean = false
        })
        search.setOnCloseListener { query = ""; refresh(); false }
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) { finish(); return true }
        return super.onOptionsItemSelected(item)
    }

    override fun onDestroy() {
        db.close()
        super.onDestroy()
    }
}
