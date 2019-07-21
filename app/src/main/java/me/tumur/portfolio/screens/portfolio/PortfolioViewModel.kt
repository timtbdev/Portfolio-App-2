package me.tumur.portfolio.screens.portfolio

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import org.koin.core.KoinComponent

class PortfolioViewModel(state : SavedStateHandle): ViewModel(), KoinComponent{

    /** VARIABLES * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */


    /** Saved state */
    private val savedStateHandle = state

    /** Current pager position */
    private val _currentPosition = MutableLiveData<Int>()
    val currentPosition: LiveData<Int> = _currentPosition

    /** FUNCTIONS * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */

    /**
     * Set selected portfolio pager position
     * */
    fun setCurrentPositon(position: Int){
        _currentPosition.apply { value = position }
    }
}