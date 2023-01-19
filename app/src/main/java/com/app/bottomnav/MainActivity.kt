package com.app.bottomnav

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.Toast
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.NavOptions
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.app.bottomnav.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    var navController : NavController? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navView: BottomNavigationView = binding.navView

        navController = findNavController(R.id.nav_host_fragment_activity_main)

        navView.setupWithNavController(navController!!)

        // added more code
    }

    fun navigateToNavBarDestination(destinationId: Int) {
        binding.navView.setSelectedItemId(destinationId)
    }

    private var doubleBackToExitPressedOnce = false
    override fun onBackPressed() {
        if (navController?.currentDestination?.id != R.id.navigation_home){
            navController?.navigate(R.id.navigation_home,
                null,
                NavOptions.Builder()
                    .setPopUpTo(R.id.navigation_home, true)
                    .build()
            )
            return
        }

        if (doubleBackToExitPressedOnce) {
            super.onBackPressed()
            return
        }

        this.doubleBackToExitPressedOnce = true
        Toast.makeText(this, "Please click BACK again to exit", Toast.LENGTH_SHORT).show()

        Handler(Looper.getMainLooper()).postDelayed(Runnable {
            doubleBackToExitPressedOnce = false
        }, 2000)
    }
}