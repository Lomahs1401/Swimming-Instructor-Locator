package com.example.swimminginstructorlocator.ui.onboarding

import android.content.Intent
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.core.content.ContextCompat
import androidx.core.view.get
import androidx.core.view.size
import androidx.viewpager2.widget.ViewPager2
import com.example.swimminginstructorlocator.MainActivity
import com.example.swimminginstructorlocator.R
import com.example.swimminginstructorlocator.adapter.IntroSlideAdapter
import com.example.swimminginstructorlocator.databinding.ActivityOnBoardingBinding
import com.example.swimminginstructorlocator.utils.base.BaseViewBindingActivity

class OnBoardingActivity : BaseViewBindingActivity<ActivityOnBoardingBinding>() {

    private lateinit var introSlideAdapter: IntroSlideAdapter

    override fun createBindingActivity(): ActivityOnBoardingBinding {
        return ActivityOnBoardingBinding.inflate(layoutInflater)
    }

    override fun initData() {
        introSlideAdapter = getIntroSlideAdapter()
    }

    override fun initView() {
        binding.introSlider.adapter = introSlideAdapter

        setupIndicators()
        setCurrentIndicator(0)

        binding.btnNext.setOnClickListener {
            val currentItem = binding.introSlider.currentItem
            if (currentItem + 1 < introSlideAdapter.itemCount) {
                binding.introSlider.currentItem = currentItem + 1
            } else {
                Intent(applicationContext, MainActivity::class.java).also {
                    startActivity(it)
                }
            }
        }

        binding.tvSkipIntro.setOnClickListener {
            Intent(applicationContext, MainActivity::class.java).also {
                startActivity(it)
            }
        }

        binding.introSlider.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                setCurrentIndicator(position)
            }
        })
    }

    private fun getIntroSlideAdapter(): IntroSlideAdapter {
        return IntroSlideAdapter(
            listOf(
                IntroSlide(
                    getString(R.string.slogan_1),
                    getString(R.string.slogan_content_1),
                    R.drawable.slide_item_1
                ),
                IntroSlide(
                    getString(R.string.slogan_2),
                    getString(R.string.slogan_content_2),
                    R.drawable.slide_item_2
                ),
                IntroSlide(
                    getString(R.string.slogan_3),
                    getString(R.string.slogan_content_3),
                    R.drawable.slide_item_3
                )
            )
        )
    }

    private fun setupIndicators() {
        val indicators = arrayOfNulls<ImageView>(introSlideAdapter.itemCount)

        val layoutParams: LinearLayout.LayoutParams = LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.WRAP_CONTENT,
            LinearLayout.LayoutParams.WRAP_CONTENT
        )

        layoutParams.setMargins(
            resources.getDimensionPixelSize(R.dimen.dp_12), // Left
            resources.getDimensionPixelSize(R.dimen.dp_8), // Top
            resources.getDimensionPixelSize(R.dimen.dp_12), // Right
            resources.getDimensionPixelSize(R.dimen.dp_8) // Bottom
        )

        for (i in indicators.indices) {
            indicators[i] = ImageView(applicationContext)
            indicators[i].apply {
                this?.setImageDrawable(
                    ContextCompat.getDrawable(
                        applicationContext,
                        R.drawable.indicator_inactive
                    )
                )
                this?.layoutParams = layoutParams
            }
            binding.indicatorsContainer.addView(indicators[i])
        }
    }

    private fun setCurrentIndicator(index: Int) {
        val childCount = binding.indicatorsContainer.childCount
        for (i in 0 until childCount) {
            val imageView = binding.indicatorsContainer[i] as ImageView
            if (i == index) {
                imageView.setImageDrawable(
                    ContextCompat.getDrawable(
                        applicationContext,
                        R.drawable.indicator_active
                    )
                )
            } else {
                imageView.setImageDrawable(
                    ContextCompat.getDrawable(
                        applicationContext,
                        R.drawable.indicator_inactive
                    )
                )
            }
        }

        if (index == binding.introSlider.size + 1) {
            binding.btnNext.text = getString(R.string.get_started)
            binding.tvSkipIntro.visibility = View.INVISIBLE
        } else {
            binding.btnNext.text = getString(R.string.next)
            binding.tvSkipIntro.visibility = View.VISIBLE
        }
    }

    data class IntroSlide(val slogan: String, val content: String, val backgroundImg: Int)
}