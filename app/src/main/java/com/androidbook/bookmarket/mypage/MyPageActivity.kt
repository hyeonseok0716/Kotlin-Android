package com.androidbook.bookmarket.mypage

import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import android.provider.MediaStore
import android.widget.Button
import android.widget.ImageButton
import android.widget.ImageView
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.androidbook.bookmarket.R
import java.io.File
import java.io.FileOutputStream

class MyPageActivity : AppCompatActivity() {
    private lateinit var profile: ImageView

    private val camera = registerForActivityResult(ActivityResultContracts.TakePicturePreview()) { bitmap ->
        if (bitmap != null) saveProfile(bitmap)
    }
    private val gallery = registerForActivityResult(ActivityResultContracts.GetContent()) { uri ->
        if (uri != null) contentResolver.openInputStream(uri)?.use { stream ->
            BitmapFactory.decodeStream(stream)?.let(::saveProfile)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mypage)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.mypage)) { view, insets ->
            val bars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            view.setPadding(bars.left, bars.top, bars.right, bars.bottom)
            insets
        }
        profile = findViewById(R.id.loginimg)
        val file = File(filesDir, "profile.jpg")
        if (file.exists()) BitmapFactory.decodeFile(file.absolutePath)?.let(profile::setImageBitmap)
        findViewById<ImageButton>(R.id.profileCamera).setOnClickListener {
            AlertDialog.Builder(this)
                .setTitle("프로필 사진 설정")
                .setItems(arrayOf("카메라", "갤러리")) { _, choice ->
                    if (choice == 0) camera.launch(null) else gallery.launch("image/*")
                }.show()
        }
        findViewById<Button>(R.id.meno).setOnClickListener {
            startActivity(Intent(this, MemoActivity::class.java))
        }
    }

    private fun saveProfile(bitmap: Bitmap) {
        FileOutputStream(File(filesDir, "profile.jpg")).use {
            bitmap.compress(Bitmap.CompressFormat.JPEG, 90, it)
        }
        profile.setImageBitmap(bitmap)
    }
}
