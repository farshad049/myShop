package com.example.myshop

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MotionEvent
import android.view.inputmethod.InputMethodManager
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.core.view.GravityCompat
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.*
import com.example.myshop.databinding.ActivityMainBinding
import com.google.android.material.navigation.NavigationView
import com.google.firebase.auth.FirebaseAuth

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    lateinit var navController: NavController
    private lateinit var appBarConfiguration: AppBarConfiguration
    val auth: FirebaseAuth = FirebaseAuth.getInstance()
    //private lateinit var viewModel: FireBaseViewModel
//    val sharedPrefs: SharedPreferences by lazy {
//        getSharedPreferences("${BuildConfig.APPLICATION_ID}_shared_preferences",Context.MODE_PRIVATE)
//
//    }








    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //implement splash screen, it should be right here
        val splashScreen = installSplashScreen()
        binding=ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)



        Constants.init(this)




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


        //set drawer on click listener fir sign out button
        binding.navView.setNavigationItemSelectedListener {
            when(it.itemId){
                R.id.logout ->{
                    auth.signOut()
                    Constants.sharedPreferences.edit().clear().apply()
                    true
                }
            }
            NavigationUI.onNavDestinationSelected(it, navController)
            binding.drawerLayout.closeDrawer(GravityCompat.START)
            true
        }



    }//FUN


    //enable back button on action bar
    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }

    //hide keyboard when touched outside
    override fun dispatchTouchEvent(ev: MotionEvent?): Boolean {
        if (currentFocus != null) {
            val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(currentFocus!!.windowToken, 0)
        }
        return super.dispatchTouchEvent(ev)
    }












    override fun onDestroy() {
        super.onDestroy()
        binding == null
    }
}