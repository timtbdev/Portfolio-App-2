package me.tumur.portfolio.utils.adapters.listItemAdapters.social

import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import me.tumur.portfolio.repository.database.model.profile.SocialModel

/**
 * An adapter that provides a list of [SocialModel] to a [RecyclerView]
 * */

class SocialAdapter(private val clickListener: SocialClickListener): ListAdapter<SocialModel, SocialViewHolder>(SocialDiffCallBack()) {

    /**
     * Part of the RecyclerView adapter, called when RecyclerView needs a new [SocialViewHolder]
     *
     * A [SocialViewHolder] holds the view for the [RecyclerView] as well as providing information
     * to the RecyclerView such as where on the screen it was last drawn during scrolling.
     * */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SocialViewHolder {
        return SocialViewHolder.from(parent)
    }

    /**
     * Part of the RecyclerView adapter, called when the RecyclerView needs to show an item.
     *
     * The [SocialViewHolder] passed may be recycled so make sure that this sets any properties
     * that may be have been set previously
     * */
    override fun onBindViewHolder(holder: SocialViewHolder, position: Int) {
        val socialItem = getItem(position)
        holder.bind(socialItem, clickListener)

    }
}