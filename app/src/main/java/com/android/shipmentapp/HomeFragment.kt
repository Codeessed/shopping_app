package com.android.shipmentapp

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnticipateInterpolator
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.TRANSLATION_X
import com.android.TRANSLATION_Y
import com.android.adapter.AvailableVehicleAdapter
import com.android.createAnim
import com.android.shipmentapp.databinding.HomeFragmentBinding
import com.android.startAnimSet

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
        startAnimSet(
            binding.homeMainBody.createAnim(TRANSLATION_Y, 100.0f, 0.0f, 1000L),
            binding.availableVehicleRecycler.createAnim(TRANSLATION_X, 100.0f, 0.0f, 1000L)
        )
        setUpVehicleRecycler()
    }

}