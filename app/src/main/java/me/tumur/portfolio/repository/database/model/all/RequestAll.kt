package me.tumur.portfolio.repository.database.model.all

import com.squareup.moshi.Json
import me.tumur.portfolio.repository.database.model.LocationModel
import me.tumur.portfolio.repository.database.model.button.ButtonModel
import me.tumur.portfolio.repository.database.model.category.CategoryModel
import me.tumur.portfolio.repository.database.model.experience.ExperienceModel
import me.tumur.portfolio.repository.database.model.portfolio.PortfolioModel
import me.tumur.portfolio.repository.database.model.profile.AboutModel
import me.tumur.portfolio.repository.database.model.profile.ProfileModel
import me.tumur.portfolio.repository.database.model.profile.SocialModel
import me.tumur.portfolio.repository.database.model.screenshot.ScreenShotModel
import me.tumur.portfolio.repository.database.model.settings.AppModel
import me.tumur.portfolio.repository.database.model.task.TaskModel
import me.tumur.portfolio.repository.database.model.welcome.WelcomeModel
import me.tumur.portfolio.utils.constants.DbConstants

data class RequestAll (

    @Json(name = DbConstants.WELCOME)
    val welcome: List<WelcomeModel>,

    @Json(name = DbConstants.PROFILE)
    val profile: List<ProfileModel>,

    @Json(name = DbConstants.SOCIAL)
    val social: List<SocialModel>,

    @Json(name = DbConstants.ABOUT)
    val about: List<AboutModel>,

    @Json(name = DbConstants.APP)
    val app: List<AppModel>,

    @Json(name = DbConstants.PORTFOLIO)
    val portfolio: List<PortfolioModel>,

    @Json(name = DbConstants.EXPERIENCE)
    val experience: List<ExperienceModel>,

    @Json(name = DbConstants.BUTTON)
    val button: List<ButtonModel>,

    @Json(name = DbConstants.TASK)
    val task: List<TaskModel>,

    @Json(name = DbConstants.CATEGORY)
    val category: List<CategoryModel>,

    @Json(name = DbConstants.SCREENSHOT)
    val screenshot: List<ScreenShotModel>,

    @Json(name = DbConstants.LOCATION)
    val location: List<LocationModel>
)