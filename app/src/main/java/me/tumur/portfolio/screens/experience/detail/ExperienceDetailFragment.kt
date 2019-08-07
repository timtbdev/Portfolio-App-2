package me.tumur.portfolio.screens.experience.detail

import android.content.pm.ActivityInfo
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.browser.customtabs.CustomTabsIntent
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.navArgs
import androidx.paging.PagedList
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import me.tumur.portfolio.R
import me.tumur.portfolio.databinding.FragmentExperienceDetailBinding
import me.tumur.portfolio.repository.database.model.button.ButtonModel
import me.tumur.portfolio.repository.database.model.task.TaskModel
import me.tumur.portfolio.screens.MainViewModel
import me.tumur.portfolio.utils.adapters.listItemAdapters.experience.task.TaskAdapter
import me.tumur.portfolio.utils.adapters.listItemAdapters.portfolio.button.ButtonAdapter
import me.tumur.portfolio.utils.adapters.listItemAdapters.portfolio.button.ButtonClickListener

/**
 * An fragment that inflates a portfolio detail layout.
 */
class ExperienceDetailFragment : Fragment() {

    /** VARIABLES * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */

    /** Create a new instance */
    companion object {
        fun newInstance() = ExperienceDetailFragment()
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
    private val viewModel: ExperienceDetailFragmentViewModel by viewModels()

    /**
     * Databinding
     */
    private lateinit var binding: FragmentExperienceDetailBinding

    /** Safe args */
    private val args: ExperienceDetailFragmentArgs by navArgs()

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
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_experience_detail, container, false)

        /** Set experience item id for detail screen */
        args.id?.let {
            viewModel.setExperienceItemId(it)
        }

        /** Set observers and adapters */
        setAdapters()

        binding.apply {
            this.lifecycleOwner = viewLifecycleOwner
            this.model = viewModel
        }
        return binding.root
    }

    /** FUNCTIONS * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */

    /** Adapters */
    private fun setAdapters() {

        /** Set Button Adapter */
        val buttonAdapter = ButtonAdapter(ButtonClickListener(viewModel::setUrl))
        val layoutManager = GridLayoutManager(activity, 2)
        val buttonList = binding.experienceItemDetailButton

        buttonList.apply {
            this.layoutManager = layoutManager
            this.hasFixedSize()
            this.adapter = buttonAdapter
        }

        /** Set Task Adapter */
        val taskAdapter = TaskAdapter()
        val layoutManagerTask = LinearLayoutManager(context)
        layoutManagerTask.orientation = LinearLayoutManager.VERTICAL
        val taskList = binding.experienceItemDetailTask

        taskList.apply {
            this.layoutManager = layoutManagerTask
            this.hasFixedSize()
            this.adapter = taskAdapter
        }

        /** Set observers */
        setObservers(buttonAdapter, taskAdapter)
    }

    /** Observers */
    private fun setObservers(buttonAdapter: ButtonAdapter, taskAdapter: TaskAdapter) {

        /** Set observer for button */
        val observerButton = Observer<PagedList<ButtonModel>> { button ->
            button?.let {
                buttonAdapter.submitList(it)
            }
        }
        viewModel.button.observe(viewLifecycleOwner, observerButton)

        /** Set observer for task */
        val observerTask = Observer<PagedList<TaskModel>> { task ->
            task?.let {
                taskAdapter.submitList(it)
            }
        }
        viewModel.task.observe(viewLifecycleOwner, observerTask)

        /** Set observer for url */
        val observerUrl = Observer<String> {
            it?.let {
                startCustomTab(it)
            }
        }
        viewModel.url.observe(viewLifecycleOwner, observerUrl)
    }

    /**
     * Starts custom tab
     * as an new intent
     * */
    private fun startCustomTab(url: String?) {

        url?.let {
            /** Chrome custom tab  */
            val builder = CustomTabsIntent.Builder().apply {
                this.setToolbarColor(ContextCompat.getColor(context!!, R.color.colorPrimary))
                this.setShowTitle(true)
            }
            builder.build().launchUrl(context, (Uri.parse(url)))
        }
    }
}