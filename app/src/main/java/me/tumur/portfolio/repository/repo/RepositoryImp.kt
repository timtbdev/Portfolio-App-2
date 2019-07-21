package me.tumur.portfolio.repository.repo

import androidx.lifecycle.liveData
import androidx.paging.DataSource
import me.tumur.portfolio.repository.database.dao.button.ButtonDao
import me.tumur.portfolio.repository.database.dao.experience.ExperienceDao
import me.tumur.portfolio.repository.database.dao.portfolio.PortfolioDao
import me.tumur.portfolio.repository.database.dao.profile.AboutDao
import me.tumur.portfolio.repository.database.dao.profile.ProfileDao
import me.tumur.portfolio.repository.database.dao.profile.SocialDao
import me.tumur.portfolio.repository.database.dao.settings.AppDao
import me.tumur.portfolio.repository.database.dao.task.TaskDao
import me.tumur.portfolio.repository.database.dao.welcome.WelcomeDao
import me.tumur.portfolio.repository.database.model.portfolio.PortfolioModel
import me.tumur.portfolio.repository.network.Failed
import me.tumur.portfolio.repository.network.RestApi
import me.tumur.portfolio.repository.network.Result
import me.tumur.portfolio.repository.network.Success
import org.koin.core.KoinComponent
import org.koin.core.inject
import java.io.IOException

class RepositoryImp : Repository, KoinComponent {

    /** VARIABLES * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */

    /** DATABASE API ------------------------------------------------------------------------------------------------ */
    private val welcomeDao: WelcomeDao by inject()
    private val profileDao: ProfileDao by inject()
    private val socialDao: SocialDao by inject()
    private val aboutDao: AboutDao by inject()
    private val appDao: AppDao by inject()
    private val portfolioDao: PortfolioDao by inject()
    private val experienceDao: ExperienceDao by inject()
    private val buttonDao: ButtonDao by inject()
    private val taskDao: TaskDao by inject()

    /** NETWORK API ------------------------------------------------------------------------------------------------- */
    private val api: RestApi by inject()

    /** 2.FUNCTIONS * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */

    /** MAIN SCREEN ------------------------------------------------------------------------------------------------- */

    /**
     * Fetch data from network and update the database,
     * if constraints are met
     * */

    override suspend fun fetchAll(): Result {

        return try {
            /** Start fetch data from network's api */
            val httpResponseAll = api.getAll()

            /** Update the database */
            if (httpResponseAll.isSuccessful) {

                /** Update welcome table */
                httpResponseAll.body()?.welcome?.let {
                    welcomeDao.update(it)
                }

                /** Update profile table */
                httpResponseAll.body()?.profile?.let {
                    profileDao.update(it)
                }

                /** Update social table */
                httpResponseAll.body()?.social?.let {
                    socialDao.update(it)
                }

                /** Update about table */
                httpResponseAll.body()?.about?.let {
                    aboutDao.update(it)
                }

                /** Update portfolio table */
                httpResponseAll.body()?.portfolio?.let {
                    portfolioDao.update(it)
                }

                /** Update button table */
                httpResponseAll.body()?.button?.let {
                    buttonDao.update(it)
                }

                /** Update experience table */
                httpResponseAll.body()?.experience?.let {
                    experienceDao.update(it)
                }

                /** Update tasks table */
                httpResponseAll.body()?.task?.let {
                    taskDao.update(it)
                }

                /** Update app table */
                httpResponseAll.body()?.app?.let {
                    appDao.update(it)
                }
                Success
            } else Failed
        } catch (exception: IOException) {
            Failed
        }
    }

    /** WELCOME SCREEN ---------------------------------------------------------------------------------------------- */

    /**
     * Content of view pager that can be shown on the welcome screen.
     */

    override suspend fun getWelcomeById(id: String) = liveData {
        /**  Start the emission */
        emitSource(welcomeDao.getById(id))
    }

    override suspend fun getWelcome() = liveData {
        /**  Start the emission */
        emitSource(welcomeDao.get())
    }

    /**
     * This function will be used for FakeDao to populate Database
     */
    override suspend fun checkWelcome(): Int {
        return welcomeDao.check()
    }

    /** PROFILE SCREEN ---------------------------------------------------------------------------------------------- */

    /**
     * Content that can be shown on the profile screen.
     */
    override suspend fun getProfile(id: String) = liveData {
        /**  Start the emission */
        emitSource(profileDao.get(id))
    }

    /**
     * Content that can be shown on the profile screen.
     */
    override suspend fun getAbout(id: String) = liveData {
        /**  Start the emission */
        emitSource(aboutDao.get(id))
    }

    /**
     * Content that can be shown on the profile screen.
     */
    override suspend fun getSocial(id: String) = liveData {
        /**  Start the emission */
        emitSource(socialDao.get(id))
    }

    /** APP DIALOG -------------------------------------------------------------------------------------------- */

    /**
     * Content that can be shown on the app info dialog.
     * App info data
     */
    override suspend fun getApp() = liveData {
        /**  Start the emission */
        emitSource(appDao.get())
    }

    /** PORTFOLIO SCREEN -------------------------------------------------------------------------------------- */

    /**
     * Content that can be shown on the portfolio screen.
     */
    override suspend fun getPortfolioList(id: String) = liveData {
        /**  Start the emission */
        emitSource(portfolioDao.getListById(id))
    }

    /**
     * Content that can be shown on the portfolio screen.
     */
    override suspend fun getPortfolioItem(id: String) = liveData {
        /**  Start the emission */
        emitSource(portfolioDao.getItemById(id))
    }

    /**
     * Content that can be shown on the portfolio view pager screen.
     */
    override suspend fun getPortfolioTabData(id: String, tab: String) = liveData {
        /**  Start the emission */
        emitSource(portfolioDao.getDataByIdAndTab(id, tab))
    }

    /**
     * Content that can be shown on the portfolio view pager screen.
     */
    override fun getPortfolioPagedTabData(id: String, tab: String): DataSource.Factory<Int, PortfolioModel> {
        return portfolioDao.getPagedDataByIdAndTab(id, tab)
    }

//    /**
//     * Content that can be shown on the profile screen.
//     */
//    override suspend fun search(query: String) = liveData {
//        /**  Start the emission */
//        emitSource(portfolioDao.getByQuery(query))
//    }
}