package me.tumur.portfolio.repository.database

import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import com.squareup.moshi.adapters.Rfc3339DateJsonAdapter
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import me.tumur.portfolio.repository.database.dao.button.ButtonDao
import me.tumur.portfolio.repository.database.dao.category.CategoryDao
import me.tumur.portfolio.repository.database.dao.experience.ExperienceDao
import me.tumur.portfolio.repository.database.dao.portfolio.PortfolioDao
import me.tumur.portfolio.repository.database.dao.profile.AboutDao
import me.tumur.portfolio.repository.database.dao.profile.ProfileDao
import me.tumur.portfolio.repository.database.dao.profile.SocialDao
import me.tumur.portfolio.repository.database.dao.resource.ResourceDao
import me.tumur.portfolio.repository.database.dao.screenshot.ScreenShotDao
import me.tumur.portfolio.repository.database.dao.settings.AppDao
import me.tumur.portfolio.repository.database.dao.task.TaskDao
import me.tumur.portfolio.repository.database.dao.welcome.WelcomeDao
import me.tumur.portfolio.repository.database.model.button.ButtonModel
import me.tumur.portfolio.repository.database.model.category.CategoryModel
import me.tumur.portfolio.repository.database.model.experience.ExperienceModel
import me.tumur.portfolio.repository.database.model.portfolio.PortfolioModel
import me.tumur.portfolio.repository.database.model.profile.AboutModel
import me.tumur.portfolio.repository.database.model.profile.ProfileModel
import me.tumur.portfolio.repository.database.model.profile.SocialModel
import me.tumur.portfolio.repository.database.model.resource.ResourceModel
import me.tumur.portfolio.repository.database.model.screenshot.ScreenShotModel
import me.tumur.portfolio.repository.database.model.settings.AppModel
import me.tumur.portfolio.repository.database.model.task.TaskModel
import me.tumur.portfolio.repository.database.model.welcome.WelcomeModel
import me.tumur.portfolio.utils.constants.DbConstants
import okio.buffer
import okio.source
import org.koin.core.KoinComponent
import org.koin.core.inject
import timber.log.Timber
import java.util.*

class DbPopulation(ctx: Context, params: WorkerParameters) : CoroutineWorker(ctx, params),
    KoinComponent {

    /** Database API */
    private val welcomeDao: WelcomeDao by inject()
    private val profileDao: ProfileDao by inject()
    private val aboutDao: AboutDao by inject()
    private val socialDao: SocialDao by inject()
    private val buttonDao: ButtonDao by inject()
    private val taskDao: TaskDao by inject()
    private val portfolioDao: PortfolioDao by inject()
    private val experienceDao: ExperienceDao by inject()
    private val categoryDao: CategoryDao by inject()
    private val screenshotDao: ScreenShotDao by inject()
    private val resourceDao: ResourceDao by inject()
    private val appDao: AppDao by inject()

    /**
     * WorkManager function to populate database from existing json files from assets folder
     */
    override suspend fun doWork(): Result {

        /** Json adapters */
        val listTypeWelcome = Types.newParameterizedType(List::class.java, WelcomeModel::class.java)
        val listTypeProfile = Types.newParameterizedType(List::class.java, ProfileModel::class.java)
        val listTypeAbout = Types.newParameterizedType(List::class.java, AboutModel::class.java)
        val listTypeSocial = Types.newParameterizedType(List::class.java, SocialModel::class.java)
        val listTypeButton = Types.newParameterizedType(List::class.java, ButtonModel::class.java)
        val listTypeTask = Types.newParameterizedType(List::class.java, TaskModel::class.java)
        val listTypePortfolio =
            Types.newParameterizedType(List::class.java, PortfolioModel::class.java)
        val listTypeExperience =
            Types.newParameterizedType(List::class.java, ExperienceModel::class.java)
        val listTypeCategory =
            Types.newParameterizedType(List::class.java, CategoryModel::class.java)
        val listTypeScreenShot =
            Types.newParameterizedType(List::class.java, ScreenShotModel::class.java)
        val listTypeResource =
            Types.newParameterizedType(List::class.java, ResourceModel::class.java)
        val listTypeApp = Types.newParameterizedType(List::class.java, AppModel::class.java)

        /** Input stream */
        val inputStreamWelcome = applicationContext.assets.open(DbConstants.WELCOME_JSON)
        val inputStreamProfile = applicationContext.assets.open(DbConstants.PROFILE_JSON)
        val inputStreamAbout = applicationContext.assets.open(DbConstants.ABOUT_JSON)
        val inputStreamSocial = applicationContext.assets.open(DbConstants.SOCIAL_JSON)
        val inputStreamButton = applicationContext.assets.open(DbConstants.BUTTON_JSON)
        val inputStreamTask = applicationContext.assets.open(DbConstants.TASK_JSON)
        val inputStreamPortfolio = applicationContext.assets.open(DbConstants.PORTFOLIO_JSON)
        val inputStreamExperience = applicationContext.assets.open(DbConstants.EXPERIENCE_JSON)
        val inputStreamCategory = applicationContext.assets.open(DbConstants.CATEGORY_JSON)
        val inputStreamScreenShot = applicationContext.assets.open(DbConstants.SCREENSHOT_JSON)
        val inputStreamResource = applicationContext.assets.open(DbConstants.RESOURCE_JSON)
        val inputStreamApp = applicationContext.assets.open(DbConstants.APP_JSON)

        /** Buffer */
        val sourceWelcome = inputStreamWelcome.source()
        val sourceProfile = inputStreamProfile.source()
        val sourceAbout = inputStreamAbout.source()
        val sourceSocial = inputStreamSocial.source()
        val sourceButton = inputStreamButton.source()
        val sourceTask = inputStreamTask.source()
        val sourcePortfolio = inputStreamPortfolio.source()
        val sourceExperience = inputStreamExperience.source()
        val sourceCategory = inputStreamCategory.source()
        val sourceScreenShot = inputStreamScreenShot.source()
        val sourceResource = inputStreamResource.source()
        val sourceApp = inputStreamApp.source()


        /** Moshi */
        val moshi = Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .add(Date::class.java, Rfc3339DateJsonAdapter().nullSafe())
            .build()

        /** Result */

        return try {

            /** Convert into Json adapters */
            val adapterWelcome: JsonAdapter<List<WelcomeModel>> = moshi.adapter(listTypeWelcome)
            val adapterProfile: JsonAdapter<List<ProfileModel>> = moshi.adapter(listTypeProfile)
            val adapterAbout: JsonAdapter<List<AboutModel>> = moshi.adapter(listTypeAbout)
            val adapterSocial: JsonAdapter<List<SocialModel>> = moshi.adapter(listTypeSocial)
            val adapterButton: JsonAdapter<List<ButtonModel>> = moshi.adapter(listTypeButton)
            val adapterTask: JsonAdapter<List<TaskModel>> = moshi.adapter(listTypeTask)
            val adapterPortfolio: JsonAdapter<List<PortfolioModel>> =
                moshi.adapter(listTypePortfolio)
            val adapterExperience: JsonAdapter<List<ExperienceModel>> =
                moshi.adapter(listTypeExperience)
            val adapterCategory: JsonAdapter<List<CategoryModel>> = moshi.adapter(listTypeCategory)
            val adapterScreenShot: JsonAdapter<List<ScreenShotModel>> =
                moshi.adapter(listTypeScreenShot)
            val adapterResource: JsonAdapter<List<ResourceModel>> = moshi.adapter(listTypeResource)
            val adapterApp: JsonAdapter<List<AppModel>> = moshi.adapter(listTypeApp)

            /** Read from Json buffers */
            val welcome = adapterWelcome.fromJson(sourceWelcome.buffer())
            val profile = adapterProfile.fromJson(sourceProfile.buffer())
            val about = adapterAbout.fromJson(sourceAbout.buffer())
            val social = adapterSocial.fromJson(sourceSocial.buffer())
            val button = adapterButton.fromJson(sourceButton.buffer())
            val task = adapterTask.fromJson(sourceTask.buffer())
            val portfolio = adapterPortfolio.fromJson(sourcePortfolio.buffer())
            val experience = adapterExperience.fromJson(sourceExperience.buffer())
            val category = adapterCategory.fromJson(sourceCategory.buffer())
            val screenshot = adapterScreenShot.fromJson(sourceScreenShot.buffer())
            val resource = adapterResource.fromJson(sourceResource.buffer())
            val app = adapterApp.fromJson(sourceApp.buffer())

            /** Update the tables  */

            welcome?.let { welcomeDao.insert(it) }
            profile?.let { profileDao.insert(it) }
            about?.let { aboutDao.insert(it) }
            social?.let { socialDao.insert(it) }
            button?.let { buttonDao.insert(it) }
            task?.let { taskDao.insert(it) }
            portfolio?.let { portfolioDao.insert(it) }
            experience?.let { experienceDao.insert(it) }
            category?.let { categoryDao.insert(it) }
            screenshot?.let { screenshotDao.insert(it) }
            resource?.let { resourceDao.insert(it) }
            app?.let { appDao.insert(it) }
            Result.success()

        } catch (ex: Exception) {
            Timber.tag("Population").d(ex.message.toString())
            Result.failure()

        } finally {

            /** Close buffers */
            sourceWelcome.close()
            sourceProfile.close()
            sourceAbout.close()
            sourceSocial.close()
            sourceButton.close()
            sourceTask.close()
            sourcePortfolio.close()
            sourceExperience.close()
            sourceCategory.close()
            sourceScreenShot.close()
            sourceResource.close()
            sourceApp.close()
        }

    }
}