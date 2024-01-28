package com.android.shipmentapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.view.isVisible
import androidx.navigation.NavGraph
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import com.android.shipmentapp.databinding.ActivityMainBinding
import com.google.android.material.bottomappbar.BottomAppBar
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import nl.joery.animatedbottombar.AnimatedBottomBar

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var tab: TabLayout
    private lateinit var navGraph: NavGraph
    private lateinit var bottomBar: AnimatedBottomBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navHost = supportFragmentManager.findFragmentById(R.id.main_navHost_container)
                as NavHostFragment
        val navController = navHost.findNavController()
        navGraph = navController.navInflater.inflate(R.navigation.main_nav_graph)
        navGraph.setStartDestination(R.id.homeFragment)
        navController.graph = navGraph

        bottomBar = binding.mainBottomNav
        if(navGraph.id == R.id.homeFragment){
            bottomBar.selectTabAt(0)
        }

//        navController.addOnDestinationChangedListener { _, destination, _ ->
//            when(destination.id) {
//                R.id.homeFragment -> binding.mainBottomNav.isVisible = true
//                else -> binding.mainBottomNav.isVisible = false
//            }
//        }
//        bottomBar.setupWithNavController(navController)
        bottomBar.setOnTabSelectListener(object :AnimatedBottomBar.OnTabSelectListener{
            override fun onTabSelected(
                lastIndex: Int,
                lastTab: AnimatedBottomBar.Tab?,
                newIndex: Int,
                newTab: AnimatedBottomBar.Tab
            ) {
                when(newIndex){
                    1 -> navController.navigate(R.id.action_homeFragment_to_calculateFragment)
                    2 -> navController.navigate(R.id.action_homeFragment_to_shipmentFragment)
                    3 -> navController.navigate(R.id.action_homeFragment_to_profileFragment)
                    else -> Unit
                }
            }
        })
//        binding.mainBottomNav.setupWithNavController(navController)
//        setupWithNavController(this, navController)
//            .setupWithNavController(R.menu.bottom_menu, navController)


    }

    override fun onResume() {
        super.onResume()
    }
}