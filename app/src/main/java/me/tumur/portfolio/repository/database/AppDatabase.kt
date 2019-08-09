package me.tumur.portfolio.repository.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import me.tumur.portfolio.repository.database.dao.button.ButtonDao
import me.tumur.portfolio.repository.database.dao.category.CategoryDao
import me.tumur.portfolio.repository.database.dao.experience.ExperienceDao
import me.tumur.portfolio.repository.database.dao.favorite.FavoriteDao
import me.tumur.portfolio.repository.database.dao.location.LocationDao
import me.tumur.portfolio.repository.database.dao.portfolio.PortfolioDao
import me.tumur.portfolio.repository.database.dao.profile.AboutDao
import me.tumur.portfolio.repository.database.dao.profile.ProfileDao
import me.tumur.portfolio.repository.database.dao.profile.SocialDao
import me.tumur.portfolio.repository.database.dao.screenshot.ScreenShotDao
import me.tumur.portfolio.repository.database.dao.settings.AppDao
import me.tumur.portfolio.repository.database.dao.task.TaskDao
import me.tumur.portfolio.repository.database.dao.welcome.WelcomeDao
import me.tumur.portfolio.repository.database.model.LocationModel
import me.tumur.portfolio.repository.database.model.button.ButtonModel
import me.tumur.portfolio.repository.database.model.category.CategoryModel
import me.tumur.portfolio.repository.database.model.experience.ExperienceModel
import me.tumur.portfolio.repository.database.model.favorite.FavoriteModel
import me.tumur.portfolio.repository.database.model.portfolio.PortfolioModel
import me.tumur.portfolio.repository.database.model.profile.AboutModel
import me.tumur.portfolio.repository.database.model.profile.ProfileModel
import me.tumur.portfolio.repository.database.model.profile.SocialModel
import me.tumur.portfolio.repository.database.model.screenshot.ScreenShotModel
import me.tumur.portfolio.repository.database.model.settings.AppModel
import me.tumur.portfolio.repository.database.model.task.TaskModel
import me.tumur.portfolio.repository.database.model.welcome.WelcomeModel

@Database(
    version = 52,
    entities = [WelcomeModel::class, ProfileModel::class, SocialModel::class, AboutModel::class, AppModel::class, PortfolioModel::class, ExperienceModel::class, TaskModel::class, ButtonModel::class, CategoryModel::class, ScreenShotModel::class, FavoriteModel::class, LocationModel::class],
    exportSchema = false
)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {
    abstract val welcomeDao: WelcomeDao
    abstract val profileDao: ProfileDao
    abstract val socialDao: SocialDao
    abstract val aboutDao: AboutDao
    abstract val appDao: AppDao
    abstract val portfolioDao: PortfolioDao
    abstract val experienceDao: ExperienceDao
    abstract val taskDao: TaskDao
    abstract val buttonDao: ButtonDao
    abstract val categoryDao: CategoryDao
    abstract val screenShotDao: ScreenShotDao
    abstract val favoriteDao: FavoriteDao
    abstract val locationDao: LocationDao
}