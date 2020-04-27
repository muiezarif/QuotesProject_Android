package com.muiezarif.quotesproject.activities

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import androidx.databinding.DataBindingUtil
import com.muiezarif.quotesproject.R
import com.muiezarif.quotesproject.databinding.ActivitySplashBinding
import com.muiezarif.quotesproject.intentCallForwardRightToLeftAnim

class SplashActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySplashBinding
    val context: Context = this

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_splash)
        Handler().postDelayed(
            { intentCallForwardRightToLeftAnim(context, HomeActivity::class.java) },
            SPLASH_DISPLAY_LENGTH.toLong()
        )
    }

    companion object {
        const val SPLASH_DISPLAY_LENGTH = 3000
    }
}
