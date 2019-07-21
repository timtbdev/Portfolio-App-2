package me.tumur.portfolio.screens.welcome.pager

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import me.tumur.portfolio.utils.constants.Constants.POSITION

class WelcomePagerAdapter(fm: FragmentManager) : FragmentStatePagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    override fun getCount(): Int = 3

    override fun getItem(i: Int): Fragment {
        val fragment = WelcomePagerFragment()
        fragment.arguments = Bundle().apply {
            // Our object is just an integer :-P
            putInt(POSITION, i)
        }
        return fragment
    }
}