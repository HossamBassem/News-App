package com.route.newsapp.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import com.route.newsapp.HomeActivity
import com.route.newsapp.R

class splashScreen : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.splash_screen)

        supportActionBar?.hide()
        Handler(Looper.getMainLooper())
            .postDelayed( { startHomeActivity() },2000)
    }
    fun startHomeActivity(){
        val intent = Intent( this, HomeActivity::class.java)
        startActivity(intent)
        finish()


    }
}
