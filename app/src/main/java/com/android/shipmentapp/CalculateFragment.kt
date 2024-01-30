package com.android.shipmentapp

import android.animation.ValueAnimator
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.children
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.android.SCALE_X
import com.android.SCALE_Y
import com.android.TRANSLATION_X
import com.android.TRANSLATION_Y
import com.android.createAnim
import com.android.shipmentapp.databinding.CalculateFragmentBinding
import com.android.startAnimSet
import com.android.startSequenceAnimSet
import com.google.android.material.chip.Chip

class CalculateFragment: Fragment() {

    private var _binding: CalculateFragmentBinding? = null
    private val binding get() =  _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = CalculateFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val chipGroup = binding.chipGroup

        binding.calculateBtn.setOnClickListener {
            findNavController().navigate(R.id.action_calculateFragment_to_checkoutFragment)
        }

        startAnimSet(
            binding.calculateTitle.createAnim(TRANSLATION_Y, 100.0f, 10.0f, 1000L),
            binding.calculateBack.createAnim(TRANSLATION_X, -100.0f, 0.0f, 1000L),
            binding.calculateTopBar.createAnim(TRANSLATION_Y, 0.0f, -50.0f, 1000L),
        )

        for (view in binding.chipGroup.children) {
            val chip = view as Chip
            chip.setOnCheckedChangeListener { buttonView, _ ->
                val index = chipGroup.indexOfChild(buttonView)
                chipGroup.removeView(buttonView)
                chipGroup.addView(buttonView, index)
            }
        }

    }

}