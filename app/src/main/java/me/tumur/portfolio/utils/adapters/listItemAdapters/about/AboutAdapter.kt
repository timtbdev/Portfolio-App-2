package me.tumur.portfolio.utils.adapters.listItemAdapters.about

import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import me.tumur.portfolio.repository.database.model.profile.AboutModel
import me.tumur.portfolio.utils.constants.Constants

/**
 * An adapter that provides a list of [AboutModel] to a [RecyclerView]
 * */

class AboutAdapter : ListAdapter<AboutItem, RecyclerView.ViewHolder>(AboutDiffCallBack()) {

    /**
     * Coroutine scope
     * */
    private val adapterScope = CoroutineScope(Dispatchers.Default)

    /**
     * Composition of about item text and header
     * in to a separate list on background thread
     * and submit this list on main thread to the [RecyclerView]
     * */
    fun addHeaderAndSubmitList(list: List<AboutModel>?) {
        adapterScope.launch {

            val compositedList = mutableListOf<AboutItem>()

            list?.let {
                for(item in it ){

                    compositedList.add(AboutItem.Header(item.header))

                    val itemsSortedByHeader = list.filter { listItem -> listItem.header == item.header }.map { about -> AboutItem.About(about) }
                    compositedList.addAll(itemsSortedByHeader)
                }
            }
            withContext(Dispatchers.Main) {
                submitList(compositedList.distinct())
            }
        }
    }

    /**
     * Part of the RecyclerView adapter, called when RecyclerView needs a new [AboutHeaderViewHolder] or [AboutItemViewHolder]
     *
     * [AboutItemViewHolder] and [AboutHeaderViewHolder] hold the views for the [RecyclerView] as well as providing information
     * to the RecyclerView such as where on the screen it was last drawn during scrolling.
     * */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            Constants.HEADER -> AboutHeaderViewHolder.from(parent)
            Constants.ITEM -> AboutItemViewHolder.from(parent)
            else -> throw ClassCastException("Unknown viewType ${viewType}")
        }
    }

    /**
     * Part of the RecyclerView adapter, called when the RecyclerView needs to show an item.
     *
     * The [AboutItemViewHolder] and [AboutHeaderViewHolder] passed may be recycled so make sure that this sets any properties
     * that may be have been set previously
     * */
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is AboutItemViewHolder -> {
                val item = getItem(position) as AboutItem.About
                holder.bind(item)
            }
            is AboutHeaderViewHolder -> {
                val header = getItem(position) as AboutItem.Header
                holder.bind(header)
            }
        }

    }

    /**
     * Get a correct item view type
     * based on composited list of data
     * */
    override fun getItemViewType(position: Int): Int {
        return when (getItem(position)) {
            is AboutItem.Header -> Constants.HEADER
            is AboutItem.About -> Constants.ITEM
        }
    }
}