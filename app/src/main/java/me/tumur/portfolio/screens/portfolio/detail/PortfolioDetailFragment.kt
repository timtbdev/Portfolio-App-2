package me.tumur.portfolio.screens.portfolio.detail

import android.content.pm.ActivityInfo
import android.net.Uri
import android.os.Bundle
import android.view.*
import androidx.browser.customtabs.CustomTabsIntent
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.navArgs
import androidx.paging.PagedList
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import me.tumur.portfolio.R
import me.tumur.portfolio.databinding.FragmentPortfolioDetailBinding
import me.tumur.portfolio.repository.database.model.button.ButtonModel
import me.tumur.portfolio.repository.database.model.category.CategoryModel
import me.tumur.portfolio.repository.database.model.screenshot.ScreenShotModel
import me.tumur.portfolio.utils.adapters.listItemAdapters.portfolio.button.ButtonAdapter
import me.tumur.portfolio.utils.adapters.listItemAdapters.portfolio.button.ButtonClickListener
import me.tumur.portfolio.utils.adapters.listItemAdapters.portfolio.category.CategoryAdapter
import me.tumur.portfolio.utils.adapters.listItemAdapters.portfolio.screenshot.ScreenShotAdapter
import me.tumur.portfolio.utils.adapters.listItemAdapters.portfolio.screenshot.ScreenShotClickListener
import timber.log.Timber


/**
 * An fragment that inflates a portfolio detail layout.
 */
class PortfolioDetailFragment : Fragment() {

    /** VARIABLES * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */

    /** Create a new instance */
    companion object {
        fun newInstance() = PortfolioDetailFragment()
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
    val viewModel: PortfolioDetailFragmentViewModel by viewModels()

    /**
     * Databinding
     */
    private lateinit var binding: FragmentPortfolioDetailBinding

    /** Adapter */
    private lateinit var buttonAdapter: ButtonAdapter
    private lateinit var categoryAdapter: CategoryAdapter
    private lateinit var screenShotAdapter: ScreenShotAdapter

    /** Safe args */
    private val args: PortfolioDetailFragmentArgs by navArgs()

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
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_portfolio_detail, container, false)

        /** Set portfolio id for detail screen */
        args.id?.let {
            viewModel.setPortfolioId(it)
        }

        /** Set options menu */
        setHasOptionsMenu(true)

        /** Set observers and adapters */
        setAdapters()
        setObservers()

        binding.apply {
            this.lifecycleOwner = viewLifecycleOwner
            this.model = viewModel
        }
        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        // Inflate menu resource file.
        inflater.inflate(R.menu.portfolio_list_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        Timber.tag("Menu").d(item.itemId.toString())
        when (item.itemId) {
            android.R.id.home -> {
//                val action = PortfolioDetailFragmentDirections.actionToPortfolioScreen()
//                findNavController().navigate(action)
            }
        }
        return true
    }

    /** FUNCTIONS * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */

    /** Adapters */
    private fun setAdapters() {
        /** Set Button Adapter */
        buttonAdapter = ButtonAdapter(ButtonClickListener { viewModel.setButtonUrl(it) })
        val layoutManager = GridLayoutManager(activity, 2)
        val buttonList = binding.portfolioItemDetailButton

        buttonList.apply {
            this.layoutManager = layoutManager
            this.hasFixedSize()
            this.adapter = buttonAdapter
        }

        /** Set Category Adapter */
        categoryAdapter = CategoryAdapter()
        val layoutManagerCategory = GridLayoutManager(activity, 3)
        val categoryList = binding.portfolioItemDetailCategory

        categoryList.apply {
            this.layoutManager = layoutManagerCategory
            this.hasFixedSize()
            this.adapter = categoryAdapter
        }

        /** Set Screenshot Adapter */
        screenShotAdapter = ScreenShotAdapter(ScreenShotClickListener { viewModel.setScreenShotUrl(it) })
        val layoutManagerScreenShot = LinearLayoutManager(context)
        layoutManagerScreenShot.orientation = LinearLayoutManager.HORIZONTAL
        val screenShotList = binding.portfolioItemDetailScreenshot

        screenShotList.apply {
            this.layoutManager = layoutManagerScreenShot
            this.hasFixedSize()
            this.adapter = screenShotAdapter
        }
    }

    /** Observers */
    private fun setObservers() {

        /** Set observer for button */
        val observerButton = Observer<PagedList<ButtonModel>> { data ->
            data?.let {
                buttonAdapter.submitList(it)
            }
        }
        viewModel.button.observe(viewLifecycleOwner, observerButton)

        /** Set observer for category */
        val observerCategory = Observer<PagedList<CategoryModel>> { data ->
            data?.let {
                categoryAdapter.submitList(it)
            }
        }
        viewModel.category.observe(viewLifecycleOwner, observerCategory)

        /** Set observer for screenshot */
        val observerScreenShot = Observer<PagedList<ScreenShotModel>> { data ->
            data?.let {
                screenShotAdapter.submitList(it)
            }
        }
        viewModel.screenshot.observe(viewLifecycleOwner, observerScreenShot)

        /** Set observer for button click listener */
        val observerButtonUrl = Observer<String> {
            it?.let {
                // Set chrome custom tab
                val builder = CustomTabsIntent.Builder()
                builder.setToolbarColor(ContextCompat.getColor(context!!, R.color.colorPrimary))
                builder.setShowTitle(true)
                val intent = builder.build()
                intent.launchUrl(context, Uri.parse(it))
            }
        }
        viewModel.buttonUrl.observe(viewLifecycleOwner, observerButtonUrl)

        /** Set observer for screenshot click listener */
        val observerScreenShotUrl = Observer<String> {
            it?.let {
                // TODO - Navigate to detailed portfolio fragment
            }
        }
        viewModel.screenShotUrl.observe(viewLifecycleOwner, observerScreenShotUrl)

//        /** Set observer for video url */
//        val observerVideoUrl = Observer<PortfolioModel> { portfolio ->
//            portfolio.videoUrl?.let {
//                play(it)
//            }
//        }
//        viewModel.portfolio.observe(viewLifecycleOwner, observerVideoUrl)
    }

//    /** Play a video */
//    private fun play(url: String){
//        val uri = Uri.parse(url)
//        context?.let {
//            Player(it, binding.player).play(uri)
//        }
//    }
}