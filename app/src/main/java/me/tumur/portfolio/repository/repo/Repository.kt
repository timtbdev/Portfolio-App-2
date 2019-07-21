package me.tumur.portfolio.repository.repo

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import me.tumur.portfolio.repository.database.model.portfolio.PortfolioModel
import me.tumur.portfolio.repository.database.model.profile.AboutModel
import me.tumur.portfolio.repository.database.model.profile.ProfileModel
import me.tumur.portfolio.repository.database.model.profile.SocialModel
import me.tumur.portfolio.repository.database.model.settings.AppModel
import me.tumur.portfolio.repository.database.model.welcome.WelcomeModel
import me.tumur.portfolio.repository.network.Result

interface Repository {

    /** MAIN SCREEN ------------------------------------------------------------------------------------------------- */

    /**
     * Fetch data from network and update the database
     */
    suspend fun fetchAll(): Result

    /** WELCOME SCREEN ---------------------------------------------------------------------------------------------- */

    /**
     * Content that can be shown on the welcome screen.
     */
    suspend fun getWelcomeById(id: String): LiveData<WelcomeModel>
    suspend fun getWelcome(): LiveData<List<WelcomeModel>>
    suspend fun checkWelcome(): Int

    /** PROFILE SCREEN ---------------------------------------------------------------------------------------------- */

    /**
     * Content that can be shown on the profile screen.
     */
    suspend fun getProfile(id: String): LiveData<ProfileModel>

    /**
     * Content that can be shown on the profile screen.
     */
    suspend fun getAbout(id: String): LiveData<List<AboutModel>>

    /**
     * Content that can be shown on the profile screen.
     */
    suspend fun getSocial(id: String): LiveData<List<SocialModel>>

    /** APP DIALOG -------------------------------------------------------------------------------------------------- */

    /**
     * Content that can be shown on the app info dialog.
     */
    suspend fun getApp(): LiveData<List<AppModel>>

    /** PORTFOLIO SCREEN -------------------------------------------------------------------------------------------- */

    /**
     * Content that can be shown on the portfolio screen.
     */
    suspend fun getPortfolioList(id: String): LiveData<List<PortfolioModel>>

    /**
     * Content that can be shown on the portfolio screen.
     */
    suspend fun getPortfolioItem(id: String): LiveData<PortfolioModel>

    /**
     * Content that can be shown on the portfolio screen.
     */
    suspend fun getPortfolioTabData(id: String, tab: String): LiveData<List<PortfolioModel>>

    /**
     * Content that can be shown on the portfolio screen.
     */
    fun getPortfolioPagedTabData(id: String, tab: String): DataSource.Factory<Int, PortfolioModel>


//    /**
//     * Content that can be shown on the portfolio search screen.
//     */
//    suspend fun search(query: String): LiveData<List<PortfolioModel>>


}