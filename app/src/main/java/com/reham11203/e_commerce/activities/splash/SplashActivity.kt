package com.reham11203.e_commerce.activities.splash

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import com.reham11203.data.utils.PrefsHelper
import com.reham11203.e_commerce.R
import com.reham11203.e_commerce.activities.auth.AuthActivity
import com.reham11203.e_commerce.activities.home.HomeActivity
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@SuppressLint("CustomSplashScreen")
@AndroidEntryPoint
class SplashActivity : AppCompatActivity() {

    @Inject
    lateinit var prefsHelper: PrefsHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        if (prefsHelper.getToken().isNullOrEmpty())
            navigateToAuthActivity()
        else
            navigateToHomeActivity()

    }

    private fun navigateToHomeActivity() {
        Handler(mainLooper).postDelayed(
            {
                val intent = Intent(this, HomeActivity::class.java)
                startActivity(intent)
                finish()
            }, 2000
        )
    }

    private fun navigateToAuthActivity() {
        Handler(Looper.getMainLooper()).postDelayed(
            {
                val intent = Intent(this, AuthActivity::class.java)
                startActivity(intent)
                finish()
            }, 2000
        )
    }
}