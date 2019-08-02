package me.tumur.portfolio.screens.portfolio.detail.preview.pager

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import me.tumur.portfolio.screens.welcome.pager.WelcomePagerFragment
import me.tumur.portfolio.utils.constants.Constants

class PreviewPagerAdapter(fm: FragmentManager) : FragmentStatePagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    override fun getCount(): Int = 5

    override fun getItem(i: Int): Fragment {
        val fragment = WelcomePagerFragment()
        fragment.arguments = Bundle().apply {
            // Our object is just an integer :-P
            putInt(Constants.POSITION, i)
        }
        return fragment
    }
}