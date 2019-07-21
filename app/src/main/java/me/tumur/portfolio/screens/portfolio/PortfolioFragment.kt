package me.tumur.portfolio.screens.portfolio

import android.content.pm.ActivityInfo
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.SavedStateViewModelFactory
import me.tumur.portfolio.R
import me.tumur.portfolio.databinding.FragmentPortfolioBinding
import me.tumur.portfolio.screens.portfolio.pager.PortfolioPagerAdapter


/**
 * An fragment that inflates a portfolio layout.
 */
class PortfolioFragment : Fragment() {

    /** VARIABLES * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */

    /** Create a new instance */
    companion object {
        fun newInstance() = PortfolioFragment()
    }

    /** ViewModel */

    /**
     * Returns a property delegate to access ViewModel
     * by default scoped to this Fragment:
     * Default scope may be overridden with parameter ownerProducer:
     * This property can be accessed only after
     * this Fragment is attached i.e.,after Fragment.onAttach,
     * and access prior to that will result in IllegalArgumentException.
     * */
    val viewModel: PortfolioViewModel by viewModels{ SavedStateViewModelFactory(this) }

    /**
     * Databinding
     */
    private lateinit var binding: FragmentPortfolioBinding

    /** View pager' adapter */
    private lateinit var portfolioPagerAdapter: PortfolioPagerAdapter

    /** INITIALIZATION * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */

    /**
     * Called to have the fragment instantiate its user interface view.
     *
     * <p>If you return a View from here, you will later be called in
     * {@link #onDestroyView} when the view is being released.
     *
     * @param inflater The LayoutInflater object that can be used to inflate
     * any views in the fragment,
     * @param container If non-null, this is the parent view that the fragment's
     * UI should be attached to.  The fragment should not add the view itself,
     * but this can be used to generate the LayoutParams of the view.
     * @param savedInstanceState If non-null, this fragment is being re-constructed
     * from a previous saved state as given here.
     *
     * @return Return the View for the fragment's UI.
     */

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        /** Lock fragment in portrait screen orientation */
        activity?.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED

        /** Data binding */
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_portfolio, container, false)

        binding.apply {
            this.lifecycleOwner = viewLifecycleOwner
            this.model = viewModel
        }
        setViewPager()
        return binding.root
    }

    /** FUNCTIONS * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */

    /**
     * Setup welcome screen's view pager with adapter
     */
    private fun setViewPager() {
        /** View pager */
        val viewPager = binding.portfolioScreenViewPager
        /** View pager's indicator */
        val viewPagerTab = binding.portfolioScreenTabLayout
        /** Set view pager' adapter */
        portfolioPagerAdapter = PortfolioPagerAdapter(childFragmentManager)
        viewPager.adapter = portfolioPagerAdapter
        /** Set view pager' tab */
        viewPagerTab.setupWithViewPager(viewPager)
    }
}