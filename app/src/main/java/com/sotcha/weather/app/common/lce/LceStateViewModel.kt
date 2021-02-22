package com.sotcha.weather.app.common.lce

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineExceptionHandler

/**
 * This view model is used to represent the current state of the UI for Lce (LOAD/CONTENT/ERROR)
 */
abstract class LceStateViewModel : ViewModel() {

    val uiState: LiveData<LceState>
        get() = _uiState

    protected var _uiState = MutableLiveData<LceState>()

    protected val exceptionHandler = CoroutineExceptionHandler { _, exception ->
        _uiState.value = Error(exception)
    }

}