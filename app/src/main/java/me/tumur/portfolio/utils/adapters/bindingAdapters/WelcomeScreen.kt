package me.tumur.portfolio.utils.adapters.bindingAdapters

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.lifecycle.LiveData
import androidx.vectordrawable.graphics.drawable.AnimatedVectorDrawableCompat
import androidx.viewpager.widget.ViewPager
import me.tumur.portfolio.R
import me.tumur.portfolio.screens.welcome.WelcomeViewModel

/** BINDING ADAPTERS FOR WELCOME SCREEN */

/** Pager Adapter Icon */
@BindingAdapter("order", "scrolledPagerItem", "pagerPosition")
fun setPagerIcon(view: ImageView, order: Int, scrolledPagerItem: Int, pagerPosition: Int) {
    val pagerIcon = when(order){
        1 -> R.drawable.ic_welcome_screen_icon_1_avd
        2 -> R.drawable.ic_welcome_screen_icon_2_avd
        else -> R.drawable.ic_welcome_screen_icon_3_avd
    }
    val avdPagerIcon = AnimatedVectorDrawableCompat.create(view.context, pagerIcon)!!
    if (scrolledPagerItem == pagerPosition){
        view.setImageDrawable(avdPagerIcon)
        avdPagerIcon.start()
    } else {
        view.setImageResource(pagerIcon)
    }
}

/** OnPage listener */
@BindingAdapter("onPageScrolled")
fun setViewPagerPageChangeListener(viewPager: ViewPager, viewModel: WelcomeViewModel) {
    viewPager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
        override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
            viewModel.setCurrentItem(position)
        }
        override fun onPageScrollStateChanged(state: Int) { }
        override fun onPageSelected(position: Int) { }
    })
}

/** Current item */
@BindingAdapter("scrollToItem", "smoothScroll", requireAll = false)
fun setViewPagerCurrentItem(viewPager: ViewPager, scrollToItem: LiveData<Int>?, smoothScroll: Boolean = false) {
    scrollToItem?.value?.let {  viewPager.setCurrentItem(it, smoothScroll)}
}