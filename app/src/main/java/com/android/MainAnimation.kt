package com.android

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.util.Property
import android.view.View
import android.view.animation.AnticipateInterpolator
import android.view.animation.BounceInterpolator
import android.view.animation.Interpolator
import android.view.animation.LinearInterpolator
import androidx.core.animation.doOnEnd
import kotlinx.coroutines.delay

val ROTATION: Property<View, Float> = View.ROTATION
    val ALPHA: Property<View, Float> = View.ALPHA
    val TRANSLATION_Y: Property<View, Float> = View.TRANSLATION_Y
    val TRANSLATION_X: Property<View, Float> = View.TRANSLATION_X
    val SCALE_X: Property<View, Float> = View.SCALE_X
    val SCALE_Y: Property<View, Float> = View.SCALE_Y
    const val TWO_SECONDS = 2000L
    const val FOUR_SECONDS = 4000L
    const val ONE_SECOND = 1000L
    const val ONE_FLOAT = 1.0f
    const val HALF_FLOAT = 0.5f
    const val ZERO_FLOAT = 0.0f
    const val FULL_TOUR = 360.0f
    const val NEGATIVE_HALF_TOUR = -180.0f
    const val CAR_ROAD = -130.0f

    fun View.createAnim(
        property: Property<View, Float>,
        startValue: Float,
        endValue: Float,
        time: Long = FOUR_SECONDS,
        interpolate: Interpolator = LinearInterpolator(),
    ): ObjectAnimator {
        return ObjectAnimator.ofFloat(
            this,
            property,
            startValue,
            endValue
        ).apply {
            duration = time
            interpolator = interpolate
        }
    }

    fun startAnimSet(vararg animations: ObjectAnimator, delay: Long? = null) {
        val animationSet = AnimatorSet()
        for (animation in animations) {
            animationSet.playTogether(animation)
        }
        animationSet.startDelay = delay ?: 0L
        animationSet.start()
    }

    fun startSequenceAnimSet(vararg animations: ObjectAnimator) {
        val animationSet = AnimatorSet()
        for (animation in animations) {
            animationSet.playSequentially(animation)
        }
        animationSet.start()
    }

