package me.tumur.portfolio.repository.database.model.all

import com.google.gson.annotations.SerializedName
import me.tumur.portfolio.repository.database.model.LocationModel
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

data class RequestAll (

    @SerializedName(DbConstants.WELCOME)
    val welcome: List<WelcomeModel>,

    @SerializedName(DbConstants.PROFILE)
    val profile: List<ProfileModel>,

    @SerializedName(DbConstants.SOCIAL)
    val social: List<SocialModel>,

    @SerializedName(DbConstants.ABOUT)
    val about: List<AboutModel>,

    @SerializedName(DbConstants.APP)
    val app: List<AppModel>,

    @SerializedName(DbConstants.PORTFOLIO)
    val portfolio: List<PortfolioModel>,

    @SerializedName(DbConstants.EXPERIENCE)
    val experience: List<ExperienceModel>,

    @SerializedName(DbConstants.BUTTON)
    val button: List<ButtonModel>,

    @SerializedName(DbConstants.TASK)
    val task: List<TaskModel>,

    @SerializedName(DbConstants.CATEGORY)
    val category: List<CategoryModel>,

    @SerializedName(DbConstants.SCREENSHOT)
    val screenshot: List<ScreenShotModel>,

    @SerializedName(DbConstants.LOCATION)
    val location: List<LocationModel>,

    @SerializedName(DbConstants.RESOURCE)
    val resource: List<ResourceModel>
)