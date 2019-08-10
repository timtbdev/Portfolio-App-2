package me.tumur.portfolio.screens.experience.detail

import android.content.Context
import android.content.pm.ActivityInfo
import android.location.Address
import android.location.Geocoder
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions
import me.tumur.portfolio.R
import me.tumur.portfolio.databinding.FragmentExperienceDetailBinding
import me.tumur.portfolio.repository.database.model.LocationModel
import me.tumur.portfolio.repository.database.model.button.ButtonModel
import me.tumur.portfolio.repository.database.model.resource.ResourceModel
import me.tumur.portfolio.repository.database.model.task.TaskModel
import me.tumur.portfolio.utils.adapters.listItemAdapters.experience.resource.ResourceAdapter
import me.tumur.portfolio.utils.adapters.listItemAdapters.experience.task.TaskAdapter
import me.tumur.portfolio.utils.adapters.listItemAdapters.portfolio.button.ButtonAdapter
import me.tumur.portfolio.utils.adapters.listItemAdapters.portfolio.button.ButtonClickListener
import me.tumur.portfolio.utils.constants.Constants
import me.tumur.portfolio.utils.state.Empty
import me.tumur.portfolio.utils.state.NotEmpty
import org.koin.android.ext.android.inject
import java.io.IOException

/**
 * An fragment that inflates a portfolio detail layout.
 */
class ExperienceDetailFragment : Fragment(), OnMapReadyCallback, GoogleMap.OnMarkerClickListener {
    /** VARIABLES * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */

    /** Create a new instance */
    companion object {
        fun newInstance() = ExperienceDetailFragment()
    }

    /** Context */

    private val ctx: Context by inject()

    /** ViewModel */

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

    /** Map */
    private var mMap: GoogleMap? = null

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

        /** Set map fragment */
        val mapFragment = childFragmentManager.findFragmentById(R.id.experience_item_detail_map) as SupportMapFragment?
        mapFragment?.getMapAsync(this)


        /** Set observers and adapters */
        setAdapters()

        binding.apply {
            this.lifecycleOwner = viewLifecycleOwner
            this.model = viewModel
        }
        return binding.root
    }

    override fun onMapReady(googleMap: GoogleMap?) {
        googleMap?.let {
            mMap = it
            mMap?.let { map ->
                map.uiSettings.isZoomControlsEnabled = true
                map.setOnMarkerClickListener(this)
            }
        }
    }

    override fun onMarkerClick(marker: Marker?) = false

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

        /** Set Resource Adapter */
        val resourceAdapter = ResourceAdapter(ButtonClickListener(viewModel::setUrl))
        val layoutManagerResource = LinearLayoutManager(context)
        layoutManagerResource.orientation = LinearLayoutManager.HORIZONTAL
        val resourceList = binding.experienceItemDetailResource

        resourceList.apply {
            this.layoutManager = layoutManagerResource
            this.hasFixedSize()
            this.adapter = resourceAdapter
        }

        /** Set observers */
        setObservers(buttonAdapter, taskAdapter, resourceAdapter)
    }

    /** Observers */
    private fun setObservers(buttonAdapter: ButtonAdapter, taskAdapter: TaskAdapter, resourceAdapter: ResourceAdapter) {

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

        /** Set observer for resource */
        val observerResource = Observer<PagedList<ResourceModel>> { task ->
            task?.let {
                resourceAdapter.submitList(it)
            }
        }
        viewModel.resource.observe(viewLifecycleOwner, observerResource)

        /** Set observer for check resource table */
        val observerCheckResourceTable = Observer<Int> { task ->
            task?.let {
                if (it > 0) viewModel.setResourceState(NotEmpty) else viewModel.setResourceState(Empty)
            }
        }
        viewModel.checkResourceTable.observe(viewLifecycleOwner, observerCheckResourceTable)

        /** Set observer for url */
        val observerUrl = Observer<String> {
            it?.let {
                startCustomTab(it)
            }
        }
        viewModel.url.observe(viewLifecycleOwner, observerUrl)

        /** Set observer for location */
        val observerLocation = Observer<LocationModel> {
            it?.let { location ->
                location.latitude?.let { lat ->
                    location.longitude?.let { long ->

                        val currentLatLng = LatLng(lat, long)
                        val title = getAddress(currentLatLng)
                        placeMarkerOnMap(title, currentLatLng)
                        mMap?.let { map ->
                            map.animateCamera(CameraUpdateFactory.newLatLngZoom(currentLatLng, 12f))
                        }
                    }
                }
            }
        }
        viewModel.location.observe(viewLifecycleOwner, observerLocation)
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

    /** Place market on a map */
    private fun placeMarkerOnMap(title: String, location: LatLng) {
        val markerOptions = MarkerOptions().position(location)
        markerOptions.title(title)
        mMap?.let {
            it.addMarker(markerOptions)
        }
    }

    /** Get address */
    private fun getAddress(latLng: LatLng): String {
        // 1
        val geocoder = Geocoder(ctx)
        val addresses: List<Address>?
        val address: Address?
        var addressText = ""

        try {
            // 2
            addresses = geocoder.getFromLocation(latLng.latitude, latLng.longitude, 1)
            // 3
            if (null != addresses && !addresses.isEmpty()) {
                address = addresses[0]
                for (i in 0 until address.maxAddressLineIndex) {
                    addressText += if (i == 0) address.getAddressLine(i) else "\n" + address.getAddressLine(i)
                }
            }
        } catch (e: IOException) {
            Log.e(Constants.FRAGMENT_EXPERIENCE, e.localizedMessage)
        }

        return addressText
    }
}