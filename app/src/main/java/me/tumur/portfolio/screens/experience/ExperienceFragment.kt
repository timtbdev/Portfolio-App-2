package me.tumur.portfolio.screens.experience

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import me.tumur.portfolio.R
import me.tumur.portfolio.databinding.FragmentExperienceBinding
import me.tumur.portfolio.screens.MainViewModel
import me.tumur.portfolio.utils.constants.Constants

/**
 * An fragment that inflates a experience layout.
 */
class ExperienceFragment : Fragment() {

    /** VARIABLES * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */

    /**
     * Create new instance
     */

    companion object {
        fun newInstance() = ExperienceFragment()
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

    val vmodel: ExperienceViewModel by viewModels()
    private val sharedViewModel: MainViewModel by activityViewModels()

    /**
     * Databinding
     */
    private lateinit var binding: FragmentExperienceBinding

    /** INITIALIZATION * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */

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

        /** Binding data to layout */
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_experience, container, false)
        binding.apply {
            this.lifecycleOwner = viewLifecycleOwner
            this.model = vmodel
        }

        /** Set fragment state in shared view model */
        sharedViewModel.setFragmentState(Constants.FRAGMENT_EXPERIENCE)

        return binding.root
    }

    /** FUNCTIONS * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */

}