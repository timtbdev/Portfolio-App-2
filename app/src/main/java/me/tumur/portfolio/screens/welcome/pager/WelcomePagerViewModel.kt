package me.tumur.portfolio.screens.welcome.pager

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import me.tumur.portfolio.repository.database.model.welcome.WelcomeModel
import me.tumur.portfolio.repository.repo.Repository
import org.koin.core.KoinComponent
import org.koin.core.inject

class WelcomePagerViewModel: ViewModel(), KoinComponent{

    /** VARIABLES * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */

    /** Repository */
    private val repo: Repository by inject()

    /** Pager' position */
    private val _position = MutableLiveData<Int>()
    val position: LiveData<Int> = _position

    /** Screen's data */
    private val _data = MutableLiveData<WelcomeModel>()
    val data: LiveData<WelcomeModel> = _data

    /** FUNCTIONS * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */

    /**
     * Set pager's position
     * */
    fun setPosition(position: Int){
        _position.value = position
    }

    /**
     * Set screen's data
     * */
    fun setData(model: WelcomeModel){
        _data.apply { value = model }
    }

}