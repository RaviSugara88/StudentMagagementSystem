package com.ravitech.ignounew.ui.splash

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.ravitech.ignounew.R
import com.ravitech.ignounew.ui.LoginActivity
import com.ravitech.ignounew.uital.xtnNavigate2
import kotlinx.coroutines.delay

@SuppressLint("CustomSplashScreen")
class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        /*no need to declare coroutine*/
        lifecycleScope.launchWhenCreated {
            delay(2000)
            xtnNavigate2<LoginActivity>()
           // findNavController().navigate(R.id.action_splashFragment_to_loginFragment)
        }

    }
}