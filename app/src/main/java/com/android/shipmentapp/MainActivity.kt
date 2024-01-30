package com.android.shipmentapp

import android.animation.Animator
import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.animation.Animation
import android.view.animation.AnticipateInterpolator
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.RelativeLayout
import android.widget.Toast
import androidx.appcompat.app.AppCompatDelegate
import androidx.cardview.widget.CardView
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
import com.android.ALPHA
import com.android.SCALE_X
import com.android.SCALE_Y
import com.android.TRANSLATION_X
import com.android.TRANSLATION_Y
import com.android.TWO_SECONDS
import com.android.createAnim
import com.android.shipmentapp.databinding.ActivityMainBinding
import com.android.startAnimSet
import com.google.android.material.bottomappbar.BottomAppBar
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.google.android.material.textfield.TextInputEditText
import nl.joery.animatedbottombar.AnimatedBottomBar

class MainActivity : AppCompatActivity() {


    private lateinit var binding: ActivityMainBinding
    private lateinit var navGraph: NavGraph
    private lateinit var bottomBar: AnimatedBottomBar
    private lateinit var topbar: LinearLayout
    private lateinit var homeTopbar: LinearLayout
    private lateinit var searchBar: CardView
    private lateinit var backBtn: ImageView
    private lateinit var homeTopDetails: LinearLayout
    private lateinit var searchBody: LinearLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        setContentView(binding.root)
        bottomBar = binding.mainBottomNav
        topbar = binding.topBar
        homeTopbar = binding.homeTopBar
        searchBar = binding.searchView
        backBtn = binding.backBtn
        searchBody = binding.homeSearchBody
        homeTopDetails = binding.homeTopDetails
        val navHost = supportFragmentManager.findFragmentById(R.id.main_navHost_container)
                as NavHostFragment
        val navController = navHost.findNavController()
        navGraph = navController.navInflater.inflate(R.navigation.main_nav_graph)
        navGraph.setStartDestination(R.id.homeFragment)



        navController.addOnDestinationChangedListener { _, destination, _ ->
            topbar.isVisible = true
            when (destination.id) {
                R.id.homeFragment -> {
                    bottomBar.selectTabAt(0)
                    bottomBar.isEnabled = true
                    backBtn.isVisible = false
                    startAnimSet(
                        homeTopDetails.createAnim(TRANSLATION_Y, -100.0f, 0.0f, 1000L),
                        searchBody.createAnim(TRANSLATION_Y, 0.0f, 1.0f, 1000L),
//                        searchBar.createAnim(SCALE_X, 1.0f, 1.5f, 1000L),
                        backBtn.createAnim(TRANSLATION_X, 100.0f, 0.0f, 1000L),
                        bottomBar.createAnim(TRANSLATION_Y, 300f, 0.0f, 1000L),
                        topbar.createAnim(TRANSLATION_Y, -300.0f, 0.0f, 1000L)
                    )
                }
                else -> {
                    backBtn.isVisible = true
                      bottomBar.isEnabled = false
                    when(destination.id){
                        R.id.shipmentFragment -> {
                            startAnimSet(
//                                searchBar.createAnim(SCALE_X, 1.0f, 2f, 1000L),
                                backBtn.createAnim(TRANSLATION_X, -100.0f, 0.0f, 1000L),
                                bottomBar.createAnim(TRANSLATION_Y, 0.0f, 300.0f, 1000L),
                                homeTopDetails.createAnim(TRANSLATION_Y, 0.0f, -100.0f, 1000L),
//                                searchBar.createAnim(SCALE_X, 1.0f, 0.8f, 1000L),
                                topbar.createAnim(TRANSLATION_Y, 0.0f, -100.0f, 1000L)
                            )
                        }
                        R.id.calculateFragment -> {
                            topbar.isVisible = false
                            startAnimSet(
                                searchBar.createAnim(SCALE_X, 1.0f, 2f, 1000L),
                                backBtn.createAnim(TRANSLATION_X, -100.0f, 0.0f, 1000L),
                                bottomBar.createAnim(TRANSLATION_Y, 0.0f, 300.0f, 1000L),
                                homeTopDetails.createAnim(TRANSLATION_Y, 0.0f, -100.0f, 1000L),
                                searchBar.createAnim(SCALE_X, 1.5f, 1.0f, 1000L),
                                topbar.createAnim(TRANSLATION_Y, 0.0f, -100.0f, 1000L)
                            )
                        }
                        R.id.checkoutFragment -> {
                            topbar.isVisible = false
                        }
                        R.id.profileFragment -> {
                            topbar.isVisible = false
                        }
                    }
                }
            }
        }
        navController.graph = navGraph

        binding.backBtn.setOnClickListener {
            navController.popBackStack()
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

    }

    private fun toSearchAnim(){
        return ObjectAnimator.ofFloat(bottomBar, "translationY", 300.0f, 0.0f).apply {
            duration = 1000
            interpolator = AnticipateInterpolator()
        }.start()
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

    private fun clearTopBars(){
        topbar.isVisible = false
    }

    override fun onResume() {
        super.onResume()

    }
}