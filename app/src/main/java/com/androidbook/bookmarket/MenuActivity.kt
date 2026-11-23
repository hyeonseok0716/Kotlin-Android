package com.androidbook.bookmarket

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.navigation.NavigationView

class MenuActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {
    private var index = 0
    private lateinit var dLayout: DrawerLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_menu)
        dLayout = findViewById(R.id.menu)
        ViewCompat.setOnApplyWindowInsetsListener(dLayout) { _, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            dLayout.getChildAt(0).setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            findViewById<NavigationView>(R.id.navigation).setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)
        toolbar.setNavigationContentDescription(R.string.open_navigation)
        toolbar.setNavigationOnClickListener { dLayout.openDrawer(GravityCompat.START) }
        findViewById<NavigationView>(R.id.navigation).setNavigationItemSelectedListener(this)
        val drawerBack = object : OnBackPressedCallback(false) {
            override fun handleOnBackPressed() {
                dLayout.closeDrawer(GravityCompat.START)
            }
        }
        onBackPressedDispatcher.addCallback(this, drawerBack)
        dLayout.addDrawerListener(object : DrawerLayout.SimpleDrawerListener() {
            override fun onDrawerOpened(drawerView: View) {
                drawerBack.isEnabled = true
            }
            override fun onDrawerClosed(drawerView: View) {
                drawerBack.isEnabled = false
            }
        })
        index = savedInstanceState?.getInt("banner_index") ?: 0
        updateBanner()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.putInt("banner_index", index)
        super.onSaveInstanceState(outState)
    }

    fun onBookListClick(view: View) {
        Toast.makeText(view.context, R.string.books_title, Toast.LENGTH_SHORT).show()
        startActivity(Intent(this, BooksActivity::class.java))
    }

    fun onEBookClick(view: View) {
        Toast.makeText(view.context, R.string.menu_ebook, Toast.LENGTH_SHORT).show()
    }

    fun onCustomerInquiryClick(view: View) {
        Toast.makeText(view.context, R.string.menu_customer, Toast.LENGTH_SHORT).show()
    }

    fun onMyPageClick(view: View) {
        startActivity(Intent(this, com.androidbook.bookmarket.mypage.MyPageActivity::class.java))
    }

    fun onMarketInfoClick(view: View) {
        Toast.makeText(view.context, R.string.menu_market, Toast.LENGTH_SHORT).show()
    }

    fun onSettingClick(view: View) {
        Toast.makeText(view.context, R.string.menu_settings, Toast.LENGTH_SHORT).show()
    }

    fun onBannerClick(view: View) {
        index = if (index == 0) 1 else 0
        (view as ImageView).setImageResource(if (index == 0) R.drawable.menu_banner1 else R.drawable.menu_banner2)
    }

    private fun updateBanner() {
        findViewById<ImageView>(R.id.imageView2).setImageResource(
            if (index == 0) R.drawable.menu_banner1 else R.drawable.menu_banner2
        )
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        Toast.makeText(this, item.title, Toast.LENGTH_SHORT).show()
        dLayout.closeDrawer(GravityCompat.START)
        return true
    }
}
