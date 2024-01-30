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
import com.android.TWO_SECONDS
import com.android.createAnim
import com.android.shipmentapp.databinding.CalculateFragmentBinding
import com.android.shipmentapp.databinding.CheckoutFragmentBinding
import com.android.startAnimSet
import com.android.startSequenceAnimSet
import com.google.android.material.chip.Chip
import kotlinx.coroutines.delay

class CheckoutFragment: Fragment() {

    private var _binding: CheckoutFragmentBinding? = null
    private val binding get() =  _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = CheckoutFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val anim =  ValueAnimator.ofInt(0, 1460)
       anim.addUpdateListener {
            binding.estimatedAmt.text = it.animatedValue.toString()
        }
        anim.apply {
            startDelay = 500L
            duration = 1500
            start()
        }

        startAnimSet(
            binding.estimatedImg.createAnim(SCALE_Y, 0.0f, 1.0f, 500L),
            binding.estimatedText.createAnim(SCALE_Y, 0.0f, 1.0f, 500L),
            binding.estimatedDesc.createAnim(SCALE_Y, 0.0f, 1.0f, 500L),
            binding.estimatedBody.createAnim(TRANSLATION_Y, 300.0f, 0.0f, 500L),
            binding.estimatedAmtText.createAnim(TRANSLATION_Y, 0.0f, 1.0f, 1000L),
            )

        startAnimSet(
            binding.estimateBtn.createAnim(TRANSLATION_Y, 500.0f, 0.0f, 500L),
            delay = 300L
        )




        binding.estimateBtn.setOnClickListener {
            findNavController().popBackStack()
        }

    }

}