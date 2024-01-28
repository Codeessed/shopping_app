package com.android.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.android.shipmentapp.CalculateFragment
import com.android.shipmentapp.HomeFragment
import com.android.shipmentapp.ProfileFragment
import com.android.shipmentapp.ShipmentFragment

class PageViewpager(activity: FragmentActivity): FragmentStateAdapter(activity) {
    override fun getItemCount() = 4

    override fun createFragment(position: Int): Fragment {
        return when(position){
            0 -> HomeFragment()
            1 -> CalculateFragment()
            2 -> ShipmentFragment()
            else -> ProfileFragment()
        }
    }

}