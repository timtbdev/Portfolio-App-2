package me.tumur.portfolio.utils.adapters.listItemAdapters.portfolio

import me.tumur.portfolio.repository.database.model.portfolio.PortfolioModel

class PortfolioClickListener(val clickListener: (item: PortfolioModel) -> Unit) {
    fun onClick(item: PortfolioModel) = clickListener(item)
}