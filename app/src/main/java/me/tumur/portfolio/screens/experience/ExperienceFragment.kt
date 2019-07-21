package me.tumur.portfolio.screens.experience

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import me.tumur.portfolio.R
import me.tumur.portfolio.databinding.FragmentExperienceBinding

/**
 * An fragment that inflates a experience layout.
 */
class ExperienceFragment : Fragment() {

    /** ------------------------------------- 1. VARIABLES ---------------------------------------------------------- */

    /**
     * 1.1 Create new instance
     */

    companion object {
        fun newInstance() = ExperienceFragment()
    }

    /**
     * 1.2 ViewModel
     */
    val vmodel: ExperienceViewModel by viewModels()

    /**
     * 1.3 Databinding
     */
    private lateinit var binding: FragmentExperienceBinding

    /** ------------------------------------- 2. CREATE ------------------------------------------------------------- */

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        /** Binding data to layout */
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_experience, container, false)
        binding.apply {
            this.lifecycleOwner = viewLifecycleOwner
            this.model = vmodel
        }
        return binding.root
    }
}