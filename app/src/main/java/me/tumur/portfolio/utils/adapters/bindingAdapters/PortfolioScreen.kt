package me.tumur.portfolio.utils.adapters.bindingAdapters

import androidx.databinding.BindingAdapter
import androidx.viewpager.widget.ViewPager
import me.tumur.portfolio.screens.portfolio.PortfolioViewModel

/** BINDING ADAPTERS FOR WELCOME SCREEN */

/** OnPage listener */
@BindingAdapter("onPortfolioPageScrolled")
fun setViewPagerPortfolioChangeListener(viewPager: ViewPager, viewModel: PortfolioViewModel) {
    viewPager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
        override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
            viewModel.setCurrentPositon(position)
        }
        override fun onPageScrollStateChanged(state: Int) { }
        override fun onPageSelected(position: Int) { }
    })
}