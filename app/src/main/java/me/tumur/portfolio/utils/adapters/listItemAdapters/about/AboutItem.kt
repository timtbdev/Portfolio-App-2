package me.tumur.portfolio.utils.adapters.listItemAdapters.about

import androidx.recyclerview.widget.RecyclerView
import me.tumur.portfolio.repository.database.model.profile.AboutModel

/**
 * Sealed class for composited list data
 * to differentiate [AboutItemViewHolder] and [AboutHeaderViewHolder]
 * for [RecyclerView]
 * */
sealed class AboutItem {
    data class About(val about: AboutModel): AboutItem() {
        override val id = about.id
    }

    data class Header(val header: String): AboutItem() {
        override val id = header
    }

    abstract val id: String
}