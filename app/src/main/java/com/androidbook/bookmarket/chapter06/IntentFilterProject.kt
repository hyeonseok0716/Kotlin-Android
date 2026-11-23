package com.androidbook.bookmarket.chapter06

import android.content.ActivityNotFoundException
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.androidbook.bookmarket.R

class IntentFilterProject : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_intent_filter_project)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { view, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            view.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val etObj01 = findViewById<EditText>(R.id.editText1)
        val etObj02 = findViewById<EditText>(R.id.editText2)
        val etObj03 = findViewById<EditText>(R.id.editText3)

        findViewById<Button>(R.id.button).setOnClickListener {
            val intent = Intent(Intent.ACTION_SEND).apply {
                type = "message/rfc822"
                putExtra(Intent.EXTRA_EMAIL, arrayOf(etObj01.text.toString().trim()))
                putExtra(Intent.EXTRA_SUBJECT, etObj02.text.toString())
                putExtra(Intent.EXTRA_TEXT, etObj03.text.toString())
            }
            try {
                startActivity(Intent.createChooser(intent, getString(R.string.choose_email_app)))
            } catch (error: ActivityNotFoundException) {
                Toast.makeText(this, R.string.no_email_app, Toast.LENGTH_LONG).show()
            }
        }
    }
}
