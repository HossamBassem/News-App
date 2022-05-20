package com.route.newsapp

import android.os.Bundle
import android.view.Menu
import android.view.View
import android.widget.ImageView
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.navigation.NavigationView

import androidx.drawerlayout.widget.DrawerLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.google.android.material.appbar.AppBarLayout

import com.route.newsapp.ui.categoriesFragment
import com.route.newsapp.ui.Category
import com.route.newsapp.ui.NewsFragment

//import com.route.newsapp.ui.NewsFragment
//import com.route.newappc35fri.ui.SettingsFragment

class HomeActivity : AppCompatActivity() {

    val categoriesFragment=com.route.newsapp.ui.categoriesFragment()
    lateinit var drawerLayout: DrawerLayout
    lateinit var appBarLayout: AppBarLayout
    lateinit var drawerIcon: ImageView
    lateinit var settings: View
    lateinit var categories: View
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        // drawerLayout = findViewById(R.id.drawer_layout)
        //drawerIcon = findViewById(R.id.drawer_icon)
        //categories = findViewById(R.id.categories)
        // settings = findViewById(R.id.settings)
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
     /*   drawerIcon.setOnClickListener {
            drawerLayout.openDrawer()
        }
        categories.setOnClickListener {
            pushFragment(categoriesFragment)
        }
        settings.setOnClickListener {
            pushFragment(SettingsFragment())
       */ }






    fun pushFragment(fragment: Fragment, addToBackStack: Boolean = false) {
        val fragTransction = supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_Container, fragment)
        if (addToBackStack)
            fragTransction.addToBackStack("");
        fragTransction.commit()
        // drawerLayout.close()
    }
}
