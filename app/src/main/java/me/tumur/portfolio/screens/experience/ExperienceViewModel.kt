package me.tumur.portfolio.screens.experience

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import androidx.paging.toLiveData
import me.tumur.portfolio.repository.database.dao.experience.ExperienceDao
import me.tumur.portfolio.repository.database.model.experience.ExperienceModel
import me.tumur.portfolio.utils.constants.DbConstants
import org.koin.core.KoinComponent
import org.koin.core.inject

class ExperienceViewModel : ViewModel(), KoinComponent {

    /** VARIABLES * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */

    /** Repository */
    private val experienceDao: ExperienceDao by inject()

    /** Selected item id */
    private val _selectedItem = MutableLiveData<ExperienceModel>()
    val selectedItem: LiveData<ExperienceModel> = _selectedItem

    /** Portfolio pager data */
    private val config = PagedList.Config.Builder()
        .setPageSize(10)
        .setEnablePlaceholders(true)
        .setInitialLoadSizeHint(5)
        .build()

    val data: LiveData<PagedList<ExperienceModel>> =
        experienceDao.getListItems(DbConstants.PERSON_ID).toLiveData(config)

    /** FUNCTIONS * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */

    /**
     * Set selected item
     * */
    fun setSelectedItem(item: ExperienceModel?) {
        _selectedItem.value = item
    }
}