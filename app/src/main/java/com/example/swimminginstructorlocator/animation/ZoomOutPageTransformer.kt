package com.example.swimminginstructorlocator.animation

import android.view.View
import androidx.viewpager2.widget.ViewPager2
import com.example.swimminginstructorlocator.databinding.ActivityOnBoardingBinding
import kotlin.math.abs

class ZoomOutPageTransformer : ViewPager2.PageTransformer {
    override fun transformPage(page: View, position: Float) {
        val pageWidth = page.width
        val pageHeight = page.height
        val scaleFactor = MIN_SCALE.coerceAtLeast(1 - abs(position))
        val verticalMargin = pageHeight * (1 - scaleFactor) / 2
        val horizontalMargin = pageWidth * (1 - scaleFactor) / 2

        page.translationX = if (position < 0) {
            horizontalMargin - verticalMargin / 2
        } else {
            horizontalMargin + verticalMargin / 2
        }
        page.scaleX = scaleFactor
        page.scaleY = scaleFactor
        page.alpha = (MIN_ALPHA +
                (((scaleFactor - MIN_SCALE) / (1 - MIN_SCALE)) * (1 - MIN_ALPHA)))
    }

    companion object {
        private const val MIN_SCALE = 0.9f
        private const val MIN_ALPHA = 0.8f
    }
}