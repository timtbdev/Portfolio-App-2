package me.tumur.portfolio.screens.portfolio.detail.preview.pager

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import me.tumur.portfolio.repository.database.model.screenshot.ScreenShotModel

class PreviewPagerViewModel : ViewModel() {

    /** VARIABLES * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */

    /** Pager' position */
    private val _position = MutableLiveData<Int>()
    val position: LiveData<Int> = _position

    /** Screenshot */
    private val _data = MutableLiveData<ScreenShotModel>()
    val data: LiveData<ScreenShotModel> = _data

    /** FUNCTIONS * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */

    /**
     * Set pager's position
     * */
    fun setPosition(position: Int) {
        _position.value = position
    }

    /**
     * Set screenshot
     * */
    fun setScreenShotData(item: ScreenShotModel?) {
        item?.let {
            _data.value = it
        }
    }
}