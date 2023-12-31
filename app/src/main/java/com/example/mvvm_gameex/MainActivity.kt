package com.example.mvvm_gameex

import android.os.Bundle
import android.util.Log
import android.view.Gravity
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.navigation.NavigationView

class MainActivity : AppCompatActivity() {

    private val drawerLayout: DrawerLayout by lazy{
        findViewById(R.id.drawerLayout)
    }

    private val navController by lazy {
        val navHostFragment =  supportFragmentManager.findFragmentById(R.id.navHost) as NavHostFragment
        navHostFragment.findNavController()
    }

    private val appBarConfiguration: AppBarConfiguration by lazy {
        AppBarConfiguration(setOf(R.id.mainFragment,R.id.HistoryFragment,R.id.userProfileFragment), drawerLayout)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupDrawerLayout()
    }

    fun openProfilePage() {
        navController.navigate(R.id.userProfilePage)
    }
    fun openDreamsListPage() {
        navController.navigate(R.id.dreamsList)
    }

    private fun setupDrawerLayout(){
        val navigationView: NavigationView = findViewById(R.id.navView)
        //setupActionBarWithNavController(navController, appBarConfiguration)
        navigationView.setupWithNavController(navController)
    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }
}