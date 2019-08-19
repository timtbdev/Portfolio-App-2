package me.tumur.portfolio.screens.portfolio.detail

import androidx.lifecycle.*
import androidx.paging.PagedList
import androidx.paging.toLiveData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import me.tumur.portfolio.repository.database.dao.button.ButtonDao
import me.tumur.portfolio.repository.database.dao.category.CategoryDao
import me.tumur.portfolio.repository.database.dao.favorite.FavoriteDao
import me.tumur.portfolio.repository.database.dao.portfolio.PortfolioDao
import me.tumur.portfolio.repository.database.dao.screenshot.ScreenShotDao
import me.tumur.portfolio.repository.database.model.button.ButtonModel
import me.tumur.portfolio.repository.database.model.category.CategoryModel
import me.tumur.portfolio.repository.database.model.favorite.FavoriteModel
import me.tumur.portfolio.repository.database.model.portfolio.PortfolioModel
import me.tumur.portfolio.repository.database.model.screenshot.ScreenShotModel
import org.koin.core.KoinComponent
import org.koin.core.inject
import java.util.*

class PortfolioDetailFragmentViewModel : ViewModel(), KoinComponent {

    /** VARIABLES * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */

    /** Repository */
    private val portfolioDao: PortfolioDao by inject()
    private val buttonDao: ButtonDao by inject()
    private val categoryDao: CategoryDao by inject()
    private val screenshotDao: ScreenShotDao by inject()
    private val favoriteDao: FavoriteDao by inject()

    /** Portfolio id */
    private val _id = MutableLiveData<String>()
    val id: LiveData<String> = _id

    /** Button -> clicked */
    private val _buttonUrl = MutableLiveData<String>()
    val buttonUrl: LiveData<String> = _buttonUrl

    /** Screenshot -> clicked */
    private val _clickedScreenShot = MutableLiveData<ScreenShotModel>()
    val clickedScreenShot: LiveData<ScreenShotModel> = _clickedScreenShot

    /** Video Url */
    private val _videoUrl = MutableLiveData<String>()
    val videoUrl: LiveData<String> = _videoUrl

    /** Portfolio item data */
    val portfolio = id.switchMap { id ->
        liveData(context = viewModelScope.coroutineContext + Dispatchers.IO) {
            emitSource(portfolioDao.getSingleItem(id))
        }
    }

    /** Is it favorite? */
    val favorite = id.switchMap { id ->
        liveData(context = viewModelScope.coroutineContext + Dispatchers.IO) {
            emitSource(favoriteDao.existSingleItem(id))
        }
    }

    /** Button data */
    private val configButton = PagedList.Config.Builder()
        .setPageSize(3)
        .setEnablePlaceholders(true)
        .setInitialLoadSizeHint(3)
        .build()

    val button: LiveData<PagedList<ButtonModel>> =
        id.switchMap { id -> buttonDao.getListItems(id).toLiveData(configButton) }

    /** Category data */
    val category: LiveData<PagedList<CategoryModel>> =
        portfolio.switchMap { portfolio -> categoryDao.getListItems(portfolio.categoryType).toLiveData(configButton) }

    /** Screenshot data */
    private val configScreenShot = PagedList.Config.Builder()
        .setPageSize(5)
        .setEnablePlaceholders(true)
        .setInitialLoadSizeHint(5)
        .build()

    val screenshot: LiveData<PagedList<ScreenShotModel>> =
        id.switchMap { id -> screenshotDao.getPagedListItems(id).toLiveData(configScreenShot) }

    /** FUNCTIONS * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */

    /**
     * Set portfolio id
     * */
    fun setPortfolioId(id: String) {
        _id.value = id
    }

    /**
     * Set clicked button
     * */
    fun setButtonUrl(url: String) {
        _buttonUrl.value = url
    }

    /**
     * Set clicked screenshot
     * */
    fun setClickedScreenShot(model: ScreenShotModel?) {
        model?.let {
            _clickedScreenShot.value = model
        }
    }

    /**
     * Set video url
     * */
    fun setVideoUrl(url: String?) {
        _videoUrl.value = url
    }

    /**
     * Save as favorite
     * */
    fun saveToFavorite(item: PortfolioModel) {
        val favorite = FavoriteModel(
            id = item.id,
            ownerId = item.ownerId,
            title = item.title,
            subTitle = item.subTitle,
            logo = item.logo,
            logoDescription = item.logoDescription,
            coverImage = item.coverImage,
            imageDescription = item.imageDescription,
            text = item.text,
            info = item.info,
            dateFrom = item.dateFrom,
            dateTo = item.dateTo,
            header = item.header,
            categoryType = item.categoryType,
            videoUrl = item.videoUrl,
            order = item.order,
            date = Calendar.getInstance().time
        )

        viewModelScope.launch(Dispatchers.IO) {
            /** Insert a portfolio item in to favorite table */
            favoriteDao.insert(favorite)
        }
    }

    /**
     * Remove from favorite
     * */
    fun removeFromFavorite(id: String) {
        viewModelScope.launch(Dispatchers.IO) {
            /** Remove a favorite item */
            favoriteDao.deleteSingleItem(id)
        }
    }
}