package com.android.shipmentapp

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnticipateInterpolator
import android.view.animation.AnticipateOvershootInterpolator
import android.view.animation.BounceInterpolator
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.adapter.AvailableVehicleAdapter
import com.android.adapter.ShipmentAdapter
import com.android.shipmentapp.databinding.HomeFragmentBinding

class HomeFragment: Fragment() {

    private var _binding: HomeFragmentBinding? = null
    private val binding get() =  _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = HomeFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


    }

    private fun setUpVehicleRecycler(){
        val vehicleRecycler = binding.availableVehicleRecycler
        val vehicleAdapter = AvailableVehicleAdapter()
        vehicleRecycler.adapter = vehicleAdapter
        vehicleRecycler.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
    }

    override fun onResume() {
        super.onResume()
        val alphaAnim = ObjectAnimator.ofFloat(binding.homeMainBody, "translationY", 100.0f, 0.0f).apply {
            duration = 1000
            interpolator = AnticipateInterpolator()
        }

        val alphaAnim2 = ObjectAnimator.
            ofFloat(binding.availableVehicleRecycler, "translationX", 100.0f, 0.0f).apply {
            duration = 1000
            interpolator = AnticipateInterpolator()
        }
        startAnimSet(alphaAnim, alphaAnim2)

        setUpVehicleRecycler()
    }

    private fun startAnimSet(vararg animations: ObjectAnimator) {
        val animationSet = AnimatorSet()
        animationSet.playTogether(animations.asList())
        animationSet.start()
    }

}