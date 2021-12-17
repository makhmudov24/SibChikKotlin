package com.example.sibchickkt

import android.content.Context
import android.content.SharedPreferences
import android.graphics.Color
import android.net.ConnectivityManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import com.example.sibckickkt.tables.Categories
import com.example.sibckickkt.tables.Menu1Week
import com.example.sibckickkt.vm.MenuViewModel

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        checkIt = hasConnection(applicationContext)
        viewModel = ViewModelProvider(this)[MenuViewModel::class.java]
        val setLifecycle = LifeCycle()
        Companion.lifecycle = lifecycle
        Companion.lifecycle!!.addObserver(setLifecycle)
        sharedPreferences = getSharedPreferences("com.example.sibchecken", MODE_PRIVATE)
        Companion.fragmentManager = supportFragmentManager
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id = item.itemId
        val constraintLayout = findViewById<ConstraintLayout>(R.id.constraint)
        when (id) {
            R.id.white -> {
                constraintLayout.setBackgroundColor(Color.WHITE)
                return true
            }
            R.id.black -> {
                constraintLayout.setBackgroundColor(Color.GRAY)
                return true
            }
        }
        //headerView.setText(item.getTitle());
        return super.onOptionsItemSelected(item)
    }

    companion object {
        var sharedPreferences: SharedPreferences? = null
            private set
        var fragmentManager: FragmentManager? = null
        @JvmStatic
        var viewModel: MenuViewModel? = null
            private set
        var lifecycle: Lifecycle? = null
        var checkIt: Boolean? = null
            private set
        @JvmStatic
        var categories: List<Categories>? = null
        @JvmStatic
        var menu1Weeks: List<Menu1Week>? = null
        @JvmStatic
        var version = 0f
        fun hasConnection(context: Context): Boolean {
            val cm = context.getSystemService(CONNECTIVITY_SERVICE) as ConnectivityManager
            var wifiInfo = cm.getNetworkInfo(ConnectivityManager.TYPE_WIFI)
            if (wifiInfo != null && wifiInfo.isConnected) {
                return true
            }
            wifiInfo = cm.getNetworkInfo(ConnectivityManager.TYPE_MOBILE)
            if (wifiInfo != null && wifiInfo.isConnected) {
                return true
            }
            wifiInfo = cm.activeNetworkInfo
            return wifiInfo != null && wifiInfo.isConnected
        }

        @JvmStatic
        fun setFM() {
            Log.i("STAAAART", " setFM")
            fragmentManager!!.beginTransaction()
                .add(R.id.fragment_container, MenuFragment())
                .commit()
        }
    }
}