package me.tumur.portfolio.screens.portfolio

import android.content.pm.ActivityInfo
import android.os.Bundle
import android.view.*
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.paging.PagedList
import androidx.recyclerview.widget.LinearLayoutManager
import me.tumur.portfolio.R
import me.tumur.portfolio.databinding.FragmentPortfolioBinding
import me.tumur.portfolio.repository.database.model.portfolio.PortfolioModel
import me.tumur.portfolio.screens.MainViewModel
import me.tumur.portfolio.utils.adapters.listItemAdapters.portfolio.PortfolioAdapter
import me.tumur.portfolio.utils.adapters.listItemAdapters.portfolio.PortfolioClickListener
import me.tumur.portfolio.utils.constants.Constants


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
    private val sharedViewModel: MainViewModel by activityViewModels()

    /**
     * Lazily create a ViewModel the first time the system calls an activity's onCreate() method.
     * Re-created fragments receive the same ViewModel instance created by the parent fragment.
     * */
    private val viewModel: PortfolioViewModel by viewModels()

    /**
     * Databinding
     */
    private lateinit var binding: FragmentPortfolioBinding

    /**
     * Pull to refresh layout
     */
    private val pullToRefresh by lazy { binding.portfolioScreenRefresh }

    private lateinit var portfolioMenu: Menu

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

        /** Set fragment state in shared view model */
        sharedViewModel.setFragmentState(Constants.FRAGMENT_PORTFOLIO)

        /** Portfolio items */
        val portfolioAdapter = PortfolioAdapter(PortfolioClickListener { viewModel.setSelectedItem(it) })
        val layoutManagerPortfolio = LinearLayoutManager(context)
        layoutManagerPortfolio.orientation = LinearLayoutManager.VERTICAL
        val portfolioList = binding.portfolioScreenList

        portfolioList.apply {
            this.layoutManager = layoutManagerPortfolio
            this.hasFixedSize()
            this.adapter = portfolioAdapter
        }

        binding.apply {
            this.lifecycleOwner = viewLifecycleOwner
            this.model = viewModel
        }

        /** Options menu */
        setHasOptionsMenu(true)

        /** Set observers */
        setPortfolioAdapter(portfolioAdapter)
        setRefreshObserver()

        /** Set listeners */
        setPortfolioItemClickListener()
        setPullToRefreshListener()

        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        portfolioMenu = menu
        inflater.inflate(R.menu.portfolio_list_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.menu_search -> {
                val portfolioItem = viewModel.selectedItem.value
                if (portfolioItem != null) {
                    val action =
                        PortfolioFragmentDirections.actionToPortfolioDetailScreen(portfolioItem.id, portfolioItem.title)
                    findNavController().navigate(action)
                    viewModel.setSelectedItem(null)
                } else {
                    findNavController().navigate(R.id.settings_screen)
                }
            }

            R.id.menu_refresh -> {
                viewModel.setRefreshStatus(true)
                viewModel.fetch()
            }
        }
        return true
    }

    /** FUNCTIONS * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */

    /**
     * Observer for portfolio adapters data
     * */
    private fun setPortfolioAdapter(portfolioAdapter: PortfolioAdapter) {
        val observer = Observer<PagedList<PortfolioModel>> { data ->
            data?.let {
                portfolioAdapter.submitList(it)
            }
        }
        viewModel.data.observe(viewLifecycleOwner, observer)
    }

    /**
     * Click listener for portfolio item
     * */
    private fun setPortfolioItemClickListener() {
        val observer = Observer<PortfolioModel> {
            it?.let {

                val menuAction = portfolioMenu.findItem(R.id.menu_search)
                onOptionsItemSelected(menuAction)
            }
        }
        viewModel.selectedItem.observe(viewLifecycleOwner, observer)
    }

    /**
     * Sets up a SwipeRefreshLayout.OnRefreshListener that is invoked when the user
     * performs a swipe-to-refresh gesture.
     * */
    private fun setPullToRefreshListener() {
        pullToRefresh.setOnRefreshListener {
            viewModel.fetch()
        }
    }

    /**
     * Set observer for refresh status
     * */
    private fun setRefreshObserver() {
        val observer = Observer<Boolean> { status ->
            if (!pullToRefresh.isRefreshing && status) {
                pullToRefresh.isRefreshing = status
                viewModel.fetch()
            } else if (pullToRefresh.isRefreshing && !status)
                pullToRefresh.isRefreshing = status
        }
        viewModel.isRefreshing.observe(viewLifecycleOwner, observer)
    }
}