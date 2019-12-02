package com.example.tradefast.configuracion

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.Window
import android.view.WindowManager
import com.example.tradefast.R
import com.example.tradefast.sesion.Login
import java.lang.Exception
import java.util.*

class SplashScreem : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screem)

        val b = object : Thread() {
            override fun run() {
                try {
                    Thread.sleep(5000)
                    val i = Intent(baseContext, Login::class.java)
                    startActivity(i)

                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }

        }
        b.start()
    }
}
