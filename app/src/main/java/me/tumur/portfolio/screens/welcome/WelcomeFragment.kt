package me.tumur.portfolio.screens.welcome

import android.content.pm.ActivityInfo
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.addCallback
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.viewpager.widget.ViewPager
import kotlinx.android.synthetic.main.button_welcome_screen.view.*
import kotlinx.android.synthetic.main.screen_welcome.view.*
import me.tumur.portfolio.R
import me.tumur.portfolio.databinding.FragmentWelcomeBinding
import me.tumur.portfolio.screens.welcome.pager.WelcomePagerAdapter

/**
 * An fragment that inflates a welcome layout.
 */
class WelcomeFragment : Fragment() {

    /** VARIABLES * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */

    /** Create a new instance */
    companion object {
        fun newInstance() = WelcomeFragment()
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

    /**
     * Lazily create a ViewModel the first time the system calls an activity's onCreate() method.
     * Re-created fragments receive the same ViewModel instance created by the parent fragment.
     * */
    private val viewModel by lazy { ViewModelProvider(this).get(WelcomeViewModel::class.java) }

    /** Databinding */
    private lateinit var binding: FragmentWelcomeBinding

    /** View pager' adapter */
    private lateinit var welcomePagerAdapter: WelcomePagerAdapter


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
        activity?.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT

        /** Data binding */
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_welcome, container, false)
        binding.apply {
            // Set the lifecycleOwner so DataBinding can observe LiveData
            this.lifecycleOwner = viewLifecycleOwner
            // Set the viewmodel so layout can display data
            this.model = viewModel
        }


        /** Observer for skip and next button onClick ---------------------------------------------------------------*/
        val buttonStateObserver = Observer<Boolean> { state ->
            if(state){

                val currentPage = viewModel.currentItem.value
                val lastPage = welcomePagerAdapter.count -1

                when( currentPage == lastPage){
                    true -> {
                        this.findNavController().navigate(WelcomeFragmentDirections.actionToProfileScreen())
                        viewModel.setFirstRunAs(false)
                    }
                    else -> {
                        currentPage?.plus(1)?.let { viewModel.setScrollToItem(it) }
                    }
                }
            }
        }
        viewModel.onClicked.observe(viewLifecycleOwner, buttonStateObserver)

        /** Set view pager */
        setWelcomeScreenViewPager()

        return binding.root
    }

    /** FUNCTIONS * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */

    /**
     * Setup welcome screen's view pager with adapter
     */
    private fun setWelcomeScreenViewPager() {

        /** View pager */
        val viewPager = binding.welcomeScreen.welcome_screen_view_pager
        /** Skip button */
        val skipButton = binding.welcomeScreen.welcome_screen_btn
        /** View pager's indicator */
        val viewPagerIndicator = binding.welcomeScreen.welcome_screen_page_indicator

        /** Set view pager' adapter */
        welcomePagerAdapter = WelcomePagerAdapter(childFragmentManager)
        viewPager.adapter = welcomePagerAdapter
        /** Set view pager' indicator */
        viewPagerIndicator.setViewPager(viewPager)
        /** Setup button as Viewpager change listener to motion layout */
        viewPager.addOnPageChangeListener(skipButton as ViewPager.OnPageChangeListener)

        /**
         * Setup view pager's back button
         * Handling back button from Fragment for ViewPager scrolling. This callback
         * will only be called when MyFragment is at least Started.
         * */
        requireActivity().onBackPressedDispatcher.addCallback(this) {
            val currentItem = viewModel.currentItem.value
            if( currentItem != null && currentItem > 0) {
                this.isEnabled = true
                viewModel.setScrollToItem(currentItem - 1)
            } else this.isEnabled = false
        }

    }
}