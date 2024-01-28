package com.android.shipmentapp

import android.animation.Animator
import android.animation.ObjectAnimator
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.animation.Animation
import android.view.animation.AnticipateInterpolator
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.navigation.NavController
import androidx.navigation.NavDestination
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
        bottomBar = binding.mainBottomNav
        val navHost = supportFragmentManager.findFragmentById(R.id.main_navHost_container)
                as NavHostFragment
        val navController = navHost.findNavController()
        navGraph = navController.navInflater.inflate(R.navigation.main_nav_graph)
        navGraph.setStartDestination(R.id.homeFragment)
        navController.addOnDestinationChangedListener { _, destination, _ ->
            when (destination) {
                navGraph.findNode(R.id.homeFragment) -> {
                    bottomBar.selectTabAt(0)
//                    bottomBar.isVisible = true
                    barVisibleAnim()
                }
                else -> {
                    barInvisibleAnim()
//                    bottomBar.isVisible = false
                }
            }
        }
        navController.graph = navGraph


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

    }


    private fun barVisibleAnim(){
        return ObjectAnimator.ofFloat(bottomBar, "translationY", 300.0f, 0.0f).apply {
            duration = 1000
            interpolator = AnticipateInterpolator()
        }.start()
    }

    private fun barInvisibleAnim(){
        return ObjectAnimator.ofFloat(bottomBar, "translationY", 0.0f, 300.0f).apply {
            duration = 1000
            interpolator = AnticipateInterpolator()
        }.start()
    }

    override fun onResume() {
        super.onResume()
    }
}