package com.test.batman.view.splash

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.widget.Toast
import com.test.batman.R
import com.test.batman.extensions.isConnected
import com.test.batman.view.dashboard.DashboardActivity
import kotlinx.android.synthetic.main.fragment_splash_screen.*

class SplashScreenActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)
        Handler().postDelayed({
            checkConnection()
        }, 3000)

        btnRetry.setOnClickListener {
            showTry(false)
            checkConnection()
        }
    }

    private fun checkConnection() {
        if (isConnected)
           startActivity(Intent(this, DashboardActivity::class.java))
        else
            showTry(true)
    }

    private fun showTry(visible: Boolean) {
        if (visible) {
            prcTry.visibility = View.GONE
            btnRetry.visibility = View.VISIBLE
            Toast.makeText(this, getString(R.string.no_internet), Toast.LENGTH_LONG).show()

        } else {
            prcTry.visibility = View.VISIBLE
            btnRetry.visibility = View.GONE
        }
    }

}