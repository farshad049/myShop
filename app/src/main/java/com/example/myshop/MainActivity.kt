package com.example.myshop

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.myshop.databinding.ActivityMainBinding
import com.google.android.material.navigation.NavigationView

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    lateinit var navController: NavController
    private lateinit var appBarConfiguration: AppBarConfiguration



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //implement splash screen, it should be right here
        val splashScreen = installSplashScreen()
        binding=ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //enable the nav controller
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.navHostFragment) as NavHostFragment
        navController = navHostFragment.navController

        //enable the action bar
        appBarConfiguration= AppBarConfiguration(
             navController.graph,
            //this is for setting the drawer layout
            drawerLayout = binding.drawerLayout)
        setupActionBarWithNavController(navController,appBarConfiguration)

        //enable navigation drawer
        findViewById<NavigationView>(R.id.nav_view).setupWithNavController(navController)



    }//FUN

    //enable back button on action bar
    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }






    override fun onDestroy() {
        super.onDestroy()
        binding == null
    }
}