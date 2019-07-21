package me.tumur.portfolio.screens.profile.bottomsheet

import android.app.Dialog
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.browser.customtabs.CustomTabsIntent
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.navigation.navGraphViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import kotlinx.android.synthetic.main.fragment_profile_bottom_sheet.view.*
import me.tumur.portfolio.R
import me.tumur.portfolio.databinding.FragmentProfileBottomSheetBinding
import me.tumur.portfolio.repository.database.model.profile.SocialModel
import me.tumur.portfolio.screens.profile.ProfileViewModel
import me.tumur.portfolio.utils.adapters.listItemAdapters.social.SocialAdapter
import me.tumur.portfolio.utils.adapters.listItemAdapters.social.SocialClickListener
import me.tumur.portfolio.utils.constants.Constants

class ProfileBottomSheetFragment: BottomSheetDialogFragment() {

    /** VARIABLES * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */

    /** Create a new instance */
    companion object {
        fun newInstance() = ProfileBottomSheetFragment()
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
    private val viewModel: ProfileViewModel by navGraphViewModels(R.id.profile_nav)

    /** Databinding */
    private lateinit var binding: FragmentProfileBottomSheetBinding

    /** Bottom sheet dialog fragment */
    private lateinit var dialog: BottomSheetDialog

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

        /** Data binding */
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_profile_bottom_sheet, container, false)

        /** Social items */
        val socialAdapter = SocialAdapter(SocialClickListener { it -> viewModel.socialItemOnClick(it) })
        val layoutManagerSocial = LinearLayoutManager(context)
        layoutManagerSocial.orientation = LinearLayoutManager.VERTICAL
        val socialList = binding.profileBsSocialList.profile_bs_social_list

        socialList.apply {
            this.layoutManager = layoutManagerSocial
            this.hasFixedSize()
            this.adapter = socialAdapter
        }

        binding.apply {
            // Set the lifecycleOwner so DataBinding can observe LiveData
            this.lifecycleOwner = viewLifecycleOwner
            // Set the viewmodel so layout can display data
            this.model = viewModel
        }

        /** Observer for social adapter */
        val socialAdapterObserver = Observer<List<SocialModel>> { data ->
            data?.let {
                socialAdapter.submitList(data)
            }
        }
        viewModel.social.observe(viewLifecycleOwner, socialAdapterObserver)

        /** Observer for social item on click */
        val socialItemClickObserver = Observer<SocialModel> { item ->
                item?.let {
                    //Hide dialog and reset onclick value
                    dialog.dismiss()
                    viewModel.socialItemClicked()
                    // Set chrome custom tab
                    val builder = CustomTabsIntent.Builder()
                    builder.setToolbarColor(resources.getColor(R.color.colorPrimary))
                    builder.setShowTitle(true)
                    val intent = builder.build()
                    intent.launchUrl(context, Uri.parse(item.url))
                    this.findNavController().popBackStack()
                }
            }
        viewModel.socialItemOnClick.observe(viewLifecycleOwner, socialItemClickObserver)

        /** Observer for email item on click */
        val emailItemClickObserver = Observer<Boolean> { item ->

            if(item){
                //Set email intent
                val intent = Intent(Intent.ACTION_SENDTO)
                intent.data = Uri.parse(Constants.MAILTO) // only email apps should handle this
                intent.putExtra(Intent.EXTRA_EMAIL, Constants.EMAIL)
                intent.putExtra(Intent.EXTRA_SUBJECT, Constants.SUBJECT)
                if (intent.resolveActivity(activity!!.packageManager) != null) {
                    //Hide dialog and reset onclick value
                    dialog.dismiss()
                    viewModel.emailItemClicked()
                    // Start email intent
                    startActivity(intent)
                }
            }
        }
        viewModel.emailItemOnClick.observe(viewLifecycleOwner, emailItemClickObserver)

        return binding.root

    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        dialog = super.onCreateDialog(savedInstanceState) as BottomSheetDialog
        dialog.setOnShowListener {
            val d = it as BottomSheetDialog
            val sheet = d.findViewById<View>(com.google.android.material.R.id.design_bottom_sheet)
            val behavior = BottomSheetBehavior.from(sheet)
            behavior.state = BottomSheetBehavior.STATE_COLLAPSED
        }
        return dialog
    }
}