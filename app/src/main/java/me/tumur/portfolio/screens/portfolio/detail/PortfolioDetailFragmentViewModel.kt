package me.tumur.portfolio.screens.portfolio.detail

import android.content.Context
import androidx.lifecycle.*
import androidx.paging.PagedList
import androidx.paging.toLiveData
import kotlinx.coroutines.Dispatchers
import me.tumur.portfolio.R
import me.tumur.portfolio.repository.database.model.button.ButtonModel
import me.tumur.portfolio.repository.database.model.category.CategoryModel
import me.tumur.portfolio.repository.database.model.screenshot.ScreenShotModel
import me.tumur.portfolio.repository.repo.Repository
import org.koin.core.KoinComponent
import org.koin.core.inject

class PortfolioDetailFragmentViewModel : ViewModel(), KoinComponent {

    /** VARIABLES * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */

    /** Repository */
    private val repo: Repository by inject()

    /** Context */
    private val context: Context by inject()

    /** Portfolio id */
    private val _id = MutableLiveData<String>()
    val id: LiveData<String> = _id

    /** Button -> clicked */
    private val _buttonUrl = MutableLiveData<String>()
    val buttonUrl: LiveData<String> = _buttonUrl

    /** Screenshot -> clicked */
    private val _screenShotUrl = MutableLiveData<String>()
    val screenShotUrl: LiveData<String> = _screenShotUrl

    /** Toast message */
    private val msgSaved = context.getString(R.string.toast_saved)


    /** Portfolio item data */
    val portfolio = id.switchMap { id ->
        liveData(context = viewModelScope.coroutineContext + Dispatchers.IO) {
            emitSource(repo.getPortfolioItem(id))
        }
    }

    /** Button data */
    private val configButton = PagedList.Config.Builder()
        .setPageSize(3)
        .setEnablePlaceholders(true)
        .setInitialLoadSizeHint(3)
        .build()

    val button: LiveData<PagedList<ButtonModel>> =
        id.switchMap { id -> repo.getButtonList(id).toLiveData(configButton) }

    /** Category data */
    val category: LiveData<PagedList<CategoryModel>> =
        portfolio.switchMap { portfolio -> repo.getCategoryList(portfolio.categoryType).toLiveData(configButton) }

    /** Screenshot data */
    private val configScreenShot = PagedList.Config.Builder()
        .setPageSize(5)
        .setEnablePlaceholders(true)
        .setInitialLoadSizeHint(5)
        .build()

    val screenshot: LiveData<PagedList<ScreenShotModel>> =
        id.switchMap { id -> repo.getScreenShotList(id).toLiveData(configScreenShot) }

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
    fun setScreenShotUrl(url: String) {
        _screenShotUrl.value = url
    }
}