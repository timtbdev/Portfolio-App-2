package me.tumur.portfolio.screens.portfolio.detail.preview.pager

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import me.tumur.portfolio.repository.database.model.screenshot.ScreenShotModel
import me.tumur.portfolio.utils.state.PreviewState
import me.tumur.portfolio.utils.state.ProgressBar
import org.koin.core.KoinComponent

class PreviewPagerViewModel : ViewModel(), KoinComponent {

    /** VARIABLES * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */

    /** Position */
    private val _position = MutableLiveData<Int>()
    val position: LiveData<Int> = _position

    /** Screenshot */
    private val _data = MutableLiveData<ScreenShotModel>()
    val data: LiveData<ScreenShotModel> = _data

    /** State */
    private val _state = MutableLiveData<PreviewState>().apply { value = ProgressBar }
    val state: LiveData<PreviewState> = _state

    /** FUNCTIONS * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */

    /**
     * Set position
     * */
    fun setPosition(position: Int) {
        _position.value = position
    }

    /**
     * Set position
     * */
    fun setData(screenshot: ScreenShotModel) {
        _data.value = screenshot
    }

    /**
     * Set state
     * */
    fun setState(state: PreviewState) {
        _state.value = state
    }
}