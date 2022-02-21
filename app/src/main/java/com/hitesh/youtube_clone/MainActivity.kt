package com.hitesh.youtube_clone

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
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
}