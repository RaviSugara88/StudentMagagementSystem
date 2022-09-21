package com.ravitech.ignounew

import android.os.Bundle
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.ravitech.ignounew.databinding.ActivityMainBinding
import com.ravitech.ignounew.ui.LoginActivity
import com.ravitech.ignounew.uital.xtnNavigate2

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private var textToolbar:String =""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navView: BottomNavigationView = binding.navView

        val navController = findNavController(R.id.nav_host_fragment_activity_main)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.navigation_home, R.id.navigation_dashboard, R.id.navigation_notifications
            )
        )

        navController.addOnDestinationChangedListener { _, destination, _ ->
            if (destination.id == R.id.navigation_dashboard) {
                textToolbar = getString(R.string.title_dashboard)
            } else if (destination.id == R.id.navigation_notifications) {
                textToolbar = getString(R.string.event)
            } else if (destination.id == R.id.subjectFragment) {
                textToolbar = getString(R.string.subject)
            }else{
                textToolbar = getString(R.string.title_home)
            }
            binding.toolbar.textTitle.setText(textToolbar)
        }


//        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)

        binding.apply {
            toolbar.btnLogout.setOnClickListener {
                xtnNavigate2<LoginActivity>()
            }
        }
    }
}