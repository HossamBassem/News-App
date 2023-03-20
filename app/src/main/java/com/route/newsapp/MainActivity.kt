package com.route.newsapp

import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.ImageView
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import com.google.android.material.appbar.AppBarLayout
import com.google.android.material.navigation.NavigationView
import com.route.newsapp.ui.Category
import com.route.newsapp.ui.NewsFragment
import com.route.newsapp.ui.SettingsFragment
import com.route.newsapp.ui.categoriesFragment

//import com.route.newsapp.ui.NewsFragment
//import com.route.newappc35fri.ui.SettingsFragment

class HomeActivity : AppCompatActivity() {
    private lateinit var drawerLayout: DrawerLayout
    private lateinit var navigationView: NavigationView
    private lateinit var toggle: ActionBarDrawerToggle
    val categoriesFragment=com.route.newsapp.ui.categoriesFragment()
   val settingsFragment=SettingsFragment()

    lateinit var settings: View
    lateinit var categories: View
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val actionBarBackground = resources.getDrawable(R.drawable.tool_bar_background)
        supportActionBar?.setBackgroundDrawable(actionBarBackground)
        categories = findViewById(R.id.categories)
        settings = findViewById(R.id.settings)
        // <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>

        // initialize DrawerLayout and NavigationView
        drawerLayout = findViewById(R.id.drawer_layout)
        navigationView = findViewById(R.id.nav_view)

        // set up ActionBarDrawerToggle
        toggle = ActionBarDrawerToggle(this, drawerLayout, R.string.navigation_drawer_open, R.string.navigation_drawer_close)
        drawerLayout.addDrawerListener(toggle)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeButtonEnabled(true)


        // set up NavigationView listener
        navigationView.setNavigationItemSelectedListener { menuItem ->
            // handle Navigation Drawer menu item clicks here
            when (menuItem.itemId) {
                R.id.menu_item_1 -> {
                    // handle menu item 1 click
                    true
                }
                R.id.menu_item_2 -> {
                    // handle menu item 2 click
                    true
                }
                R.id.menu_item_3 -> {
                    // handle menu item 3 click
                    true
                }
                else -> false
            }
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // allow user to open and close Navigation Drawer by tapping ActionBar app icon
        return if (toggle.onOptionsItemSelected(item)) {
            true
        } else super.onOptionsItemSelected(item)
    }

    override fun onPostCreate(savedInstanceState: Bundle?) {
        super.onPostCreate(savedInstanceState)
        // synchronize ActionBarDrawerToggle state after onRestoreInstanceState has occurred
        toggle.syncState()

//<<<<<<<<<<<<<<<<<<<<<<<>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>


    pushFragment(categoriesFragment,true)
        categoriesFragment.onCategoryClickListner=object :categoriesFragment.OnCategoryClickListner{
            override fun onCategoryClick(category: Category) {
                pushFragment(NewsFragment(),true)
            }


        }


        categoriesFragment.onCategoryClickListner =
                object : categoriesFragment.OnCategoryClickListner {
                    override fun onCategoryClick(category: Category) {
                        pushFragment(NewsFragment.getInstance(category), true);
                    }
                }
        categories.setOnClickListener {
pushFragment(categoriesFragment, addToBackStack = false)
        }
        settings.setOnClickListener {
            pushFragment(settingsFragment,false)
        }
     }






    fun pushFragment(fragment: Fragment, addToBackStack: Boolean = false) {
        val fragTransction = supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_Container, fragment)
        if (addToBackStack)
            fragTransction.addToBackStack("");
        fragTransction.commit()
         drawerLayout.close()
    }
}
