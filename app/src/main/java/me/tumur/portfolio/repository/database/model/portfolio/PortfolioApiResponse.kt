package me.tumur.portfolio.repository.database.model.portfolio

import com.squareup.moshi.Json
import me.tumur.portfolio.repository.database.model.button.ButtonModel
import me.tumur.portfolio.utils.constants.DbConstants

data class PortfolioApiResponse (

    @Json(name = DbConstants.PORTFOLIO)
    val portfolio: List<PortfolioModel>,

    @Json(name = DbConstants.BUTTON)
    val button: List<ButtonModel>
)