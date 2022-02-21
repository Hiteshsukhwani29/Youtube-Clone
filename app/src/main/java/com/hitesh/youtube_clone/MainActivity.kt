package com.hitesh.youtube_clone

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import android.widget.Toolbar
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.hitesh.youtube_clone.fragment.*

class MainActivity : AppCompatActivity() {

    lateinit var bottmNavigationView: BottomNavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setSupportActionBar(findViewById(R.id.toolbar))
        supportActionBar?.title =""

        bottmNavigationView = findViewById(R.id.bottom_nav)

        setCurrentFragment(Home())

        bottmNavigationView.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.home -> setCurrentFragment(Home())
                R.id.explore -> setCurrentFragment(Explore())
                R.id.subscription -> setCurrentFragment(Subscriptions())
                R.id.notification -> setCurrentFragment(Notifications())
                R.id.library -> setCurrentFragment(Library())
            }
            true
        }

    }

    private fun setCurrentFragment(fragment: Fragment) =
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.fragment_container1, fragment)
            commit()
        }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.toolbar_menu,menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.createvideo -> Toast.makeText(this,"Create Video",Toast.LENGTH_SHORT).show()
            R.id.search -> Toast.makeText(this,"Search",Toast.LENGTH_SHORT).show()
            R.id.profile -> Toast.makeText(this,"My Account",Toast.LENGTH_SHORT).show()
        }
        return true
    }
}