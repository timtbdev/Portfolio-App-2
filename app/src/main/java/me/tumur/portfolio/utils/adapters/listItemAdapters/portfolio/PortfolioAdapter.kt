package me.tumur.portfolio.utils.adapters.listItemAdapters.portfolio

import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.RecyclerView
import me.tumur.portfolio.repository.database.model.portfolio.PortfolioModel
import me.tumur.portfolio.utils.adapters.listItemAdapters.social.SocialViewHolder

/**
 * An adapter that provides a list of [PortfolioModel] to a [RecyclerView]
 * */

class PortfolioAdapter(private val clickListener: PortfolioClickListener) : PagedListAdapter<PortfolioModel, PortfolioViewHolder>(PortfolioDiffCallBack()) {

    /**
     * Part of the RecyclerView adapter, called when RecyclerView needs a new [SocialViewHolder]
     *
     * A [PortfolioViewHolder] holds the view for the [RecyclerView] as well as providing information
     * to the RecyclerView such as where on the screen it was last drawn during scrolling.
     * */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PortfolioViewHolder {
        return PortfolioViewHolder.from(parent)
    }

    /**
     * Part of the RecyclerView adapter, called when the RecyclerView needs to show an item.
     *
     * The [PortfolioViewHolder] passed may be recycled so make sure that this sets any properties
     * that may be have been set previously
     * */
    override fun onBindViewHolder(holder: PortfolioViewHolder, position: Int) {
        val portfolioItem = getItem(position)
        holder.bind(portfolioItem, clickListener)
    }
}