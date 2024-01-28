package com.android.shipmentapp

import android.animation.ObjectAnimator
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnticipateInterpolator
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.adapter.ShipmentAdapter
import com.android.shipmentapp.databinding.ShipmentFragmentBinding

class ShipmentFragment: Fragment() {

    private var _binding: ShipmentFragmentBinding? = null
    private val binding get() =  _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = ShipmentFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setUpShipmentRecycler()

    }

    private fun setUpShipmentRecycler(){
        val shipmentRecycler = binding.shipmentRecycler
        val shipmentAdapter = ShipmentAdapter()
        shipmentRecycler.adapter = shipmentAdapter
        shipmentRecycler.layoutManager = LinearLayoutManager(requireContext())
        val alphaAnim = ObjectAnimator.ofFloat(binding.shipmentBody, "translationY", 300.0f, 0.0f).apply {
            duration = 2000
//            repeatCount = 3
//            startDelay = 2000
//            repeatMode = ObjectAnimator.REVERSE
            interpolator = AnticipateInterpolator()
        }

        alphaAnim.start()
    }

}