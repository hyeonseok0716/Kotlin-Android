package com.androidbook.bookmarket.mypage

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DatabaseHelperMemo(context: Context) : SQLiteOpenHelper(context, "Memo.db", null, 1) {
    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL("CREATE TABLE MemoTABLE (id INTEGER PRIMARY KEY AUTOINCREMENT, date TEXT, title TEXT, content TEXT)")
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS MemoTABLE")
        onCreate(db)
    }

    fun insertData(date: String, title: String, content: String) {
        writableDatabase.insert("MemoTABLE", null, ContentValues().apply {
            put("date", date)
            put("title", title)
            put("content", content)
        })
    }

    fun updateData(id: Int, date: String, title: String, content: String) {
        writableDatabase.update("MemoTABLE", ContentValues().apply {
            put("date", date)
            put("title", title)
            put("content", content)
        }, "id = ?", arrayOf(id.toString()))
    }

    fun deleteData(id: Int) {
        writableDatabase.delete("MemoTABLE", "id = ?", arrayOf(id.toString()))
    }

    fun readData(query: String = ""): ArrayList<HashMap<String, String>> {
        val result = arrayListOf<HashMap<String, String>>()
        val selection = if (query.isBlank()) null else "content LIKE ? OR title LIKE ?"
        val args = if (query.isBlank()) null else arrayOf("%$query%", "%$query%")
        readableDatabase.query("MemoTABLE", arrayOf("id", "date", "title", "content"), selection, args, null, null, "id DESC").use { cursor ->
            while (cursor.moveToNext()) {
                result.add(hashMapOf(
                    "id" to cursor.getInt(0).toString(),
                    "date" to cursor.getString(1).orEmpty(),
                    "title" to cursor.getString(2).orEmpty(),
                    "content" to cursor.getString(3).orEmpty()
                ))
            }
        }
        return result
    }
}
