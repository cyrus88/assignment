package com.assignment.features.splash

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.assignment.features.R
import android.content.Intent
import android.os.Handler
import android.os.Looper
import com.assignment.features.home.views.HomeActivity


@SuppressLint("CustomSplashScreen")
class SplashActivity : AppCompatActivity() {

    /** Duration of wait  */
    private val splashDisplayDuration = 2000

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        /* New Handler to start the Home-Activity
         * and close this Splash-Screen after some seconds.*/

        Handler(Looper.getMainLooper()).postDelayed({
            val mainIntent = Intent(this, HomeActivity::class.java)
            startActivity(mainIntent)
            finish()
        }, splashDisplayDuration.toLong())
    }
}