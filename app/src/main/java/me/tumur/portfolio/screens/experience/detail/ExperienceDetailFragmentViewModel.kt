package me.tumur.portfolio.screens.experience.detail

import androidx.lifecycle.*
import androidx.paging.PagedList
import androidx.paging.toLiveData
import kotlinx.coroutines.Dispatchers
import me.tumur.portfolio.repository.database.dao.button.ButtonDao
import me.tumur.portfolio.repository.database.dao.experience.ExperienceDao
import me.tumur.portfolio.repository.database.dao.location.LocationDao
import me.tumur.portfolio.repository.database.dao.resource.ResourceDao
import me.tumur.portfolio.repository.database.dao.task.TaskDao
import me.tumur.portfolio.repository.database.model.button.ButtonModel
import me.tumur.portfolio.repository.database.model.resource.ResourceModel
import me.tumur.portfolio.repository.database.model.task.TaskModel
import me.tumur.portfolio.utils.state.FavoriteState
import org.koin.core.KoinComponent
import org.koin.core.inject

class ExperienceDetailFragmentViewModel : ViewModel(), KoinComponent {

    /** VARIABLES * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */

    /** Repository */
    private val experienceDao: ExperienceDao by inject()
    private val buttonDao: ButtonDao by inject()
    private val taskDao: TaskDao by inject()
    private val locationDao: LocationDao by inject()
    private val resourceDao: ResourceDao by inject()

    /** Experience item id */
    private val _id = MutableLiveData<String>()
    val id: LiveData<String> = _id

    /** Experience item data */
    val data = id.switchMap { id ->
        liveData(context = viewModelScope.coroutineContext + Dispatchers.IO) {
            emitSource(experienceDao.getSingleItem(id))
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

    /** Task data */
    private val configTask = PagedList.Config.Builder()
        .setPageSize(10)
        .setEnablePlaceholders(true)
        .setInitialLoadSizeHint(10)
        .build()

    val task: LiveData<PagedList<TaskModel>> =
        id.switchMap { id -> taskDao.getListItems(id).toLiveData(configTask) }

    /** Resource data */
    private val configResource = PagedList.Config.Builder()
        .setPageSize(5)
        .setEnablePlaceholders(true)
        .setInitialLoadSizeHint(5)
        .build()

    val resource: LiveData<PagedList<ResourceModel>> =
        id.switchMap { id -> resourceDao.getListItems(id).toLiveData(configTask) }

    /** Resource state */
    private val _resourceState = MutableLiveData<FavoriteState>()
    val resourceState: LiveData<FavoriteState> = _resourceState

    /** Experience item data */
    val checkResourceTable = id.switchMap { id ->
        liveData(context = viewModelScope.coroutineContext + Dispatchers.IO) {
            emitSource(resourceDao.check(id))
        }
    }

    /** Location data */
    val location = id.switchMap { id ->
        liveData(context = viewModelScope.coroutineContext + Dispatchers.IO) {
            emitSource(locationDao.getSingleItem(id))
        }
    }

    /** Url -> clicked */
    private val _url = MutableLiveData<String>()
    val url: LiveData<String> = _url

    /** FUNCTIONS * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */

    /**
     * Set experience item id
     * */
    fun setExperienceItemId(id: String) {
        _id.value = id
    }

    /**
     * Set clicked url
     * */
    fun setUrl(url: String) {
        _url.value = url
    }

    /**
     * Set resource state
     * */
    fun setResourceState(state: FavoriteState) {
        _resourceState.value = state
    }
}