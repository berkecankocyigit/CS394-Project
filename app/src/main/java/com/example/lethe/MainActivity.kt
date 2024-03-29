package com.example.lethe
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.navigation.NavigationView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.oAuthCredential

class MainActivity : AppCompatActivity() {

    private val drawerLayout: DrawerLayout by lazy {
        findViewById(R.id.drawerLayout)
    }

    private val navController by lazy {
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.navHost) as NavHostFragment
        navHostFragment.findNavController()
    }

    private val appBarConfiguration: AppBarConfiguration by lazy {
        AppBarConfiguration(setOf(R.id.mainFragment, R.id.HistoryFragment, R.id.userProfileFragment), drawerLayout)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupDrawerLayout()
    }

    fun openProfilePage() {
        val auth = FirebaseAuth.getInstance()
        auth.currentUser?.let {
            navController.navigate(R.id.userProfilePage)
        } ?: run {
            navController.navigate(R.id.loginPage)
        }

    }

    fun openDreamsListPage() {
        val auth = FirebaseAuth.getInstance()
        auth.currentUser?.let {
            navController.navigate(R.id.dreamsList)
        } ?: run {
            navController.navigate(R.id.loginPage)
        }
    }

    private fun setupDrawerLayout() {
        val navigationView: NavigationView = findViewById(R.id.navView)
        navigationView.setupWithNavController(navController)
    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }
}
