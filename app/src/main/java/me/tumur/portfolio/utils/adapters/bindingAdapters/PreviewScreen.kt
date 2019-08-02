package me.tumur.portfolio.utils.adapters.bindingAdapters

import androidx.databinding.BindingAdapter
import androidx.lifecycle.LiveData
import androidx.viewpager.widget.ViewPager
import me.tumur.portfolio.screens.portfolio.detail.preview.PreviewFragmentViewModel

/** BINDING ADAPTERS FOR PREVIEW SCREEN */

/** OnPage listener */
@BindingAdapter("onScreenShotScrolled")
fun setPreviewViewPagerPageChangeListener(viewPager: ViewPager, viewModel: PreviewFragmentViewModel) {
    viewPager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
        override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
            viewModel.setCurrentItem(position)
        }

        override fun onPageScrollStateChanged(state: Int) {}
        override fun onPageSelected(position: Int) {}
    })
}

/** Current item */
@BindingAdapter("scrollToScreenShot", "smoothScrollScreenShot", requireAll = false)
fun setPreviewViewPagerCurrentItem(viewPager: ViewPager, scrollToItem: LiveData<Int>?, smoothScroll: Boolean = false) {
    scrollToItem?.value?.let { viewPager.setCurrentItem(it, smoothScroll) }
}