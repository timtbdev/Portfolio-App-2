package me.tumur.portfolio.repository.network

import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import me.tumur.portfolio.repository.database.dao.button.ButtonDao
import me.tumur.portfolio.repository.database.dao.category.CategoryDao
import me.tumur.portfolio.repository.database.dao.experience.ExperienceDao
import me.tumur.portfolio.repository.database.dao.location.LocationDao
import me.tumur.portfolio.repository.database.dao.portfolio.PortfolioDao
import me.tumur.portfolio.repository.database.dao.profile.AboutDao
import me.tumur.portfolio.repository.database.dao.profile.ProfileDao
import me.tumur.portfolio.repository.database.dao.profile.SocialDao
import me.tumur.portfolio.repository.database.dao.resource.ResourceDao
import me.tumur.portfolio.repository.database.dao.screenshot.ScreenShotDao
import me.tumur.portfolio.repository.database.dao.settings.AppDao
import me.tumur.portfolio.repository.database.dao.task.TaskDao
import me.tumur.portfolio.repository.database.dao.welcome.WelcomeDao
import org.koin.core.KoinComponent
import org.koin.core.inject
import retrofit2.HttpException

class DbRefresh(context: Context, params: WorkerParameters): CoroutineWorker(context, params), KoinComponent{

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
    private val categoryDao: CategoryDao by inject()
    private val screenShotDao: ScreenShotDao by inject()
    private val locationDao: LocationDao by inject()
    private val resourceDao: ResourceDao by inject()

    /** NETWORK API ------------------------------------------------------------------------------------------------- */
    private val api: RestApi by inject()

    override suspend fun doWork(): Result {
        return try {
            /** Start fetch data from network's api */

            /** Api response */
            val httpResponseAll = api.getAll()

            /** Update the database */
            if(httpResponseAll.isSuccessful) {

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

                /** Update category table */
                httpResponseAll.body()?.category?.let {
                    categoryDao.update(it)
                }

                /** Update screenshot table */
                httpResponseAll.body()?.screenshot?.let {
                    screenShotDao.update(it)
                }

                /** Update location table */
                httpResponseAll.body()?.location?.let {
                    locationDao.update(it)
                }

                /** Update resource table */
                httpResponseAll.body()?.resource?.let {
                    resourceDao.update(it)
                }

                Result.success()
            } else {
                Result.retry()
            }
        } catch (e: HttpException) {
            Result.retry()
        }
    }
}