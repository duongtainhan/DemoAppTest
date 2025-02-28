package com.dienty.core.structure.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import java.util.concurrent.atomic.AtomicBoolean

/**
 * Base class for viewModel.
 * Include channel for [Intent] processing and [Effect]
 */
abstract class BaseViewModel<Intent : Any, Effect : Any>(
    settings: ViewModelSetting = ViewModelSetting()
) : ViewModel() {

    private val _initialised = AtomicBoolean(false)
    val initialised get() = _initialised.get()

    private val _intentChannel = Channel<Intent>(settings.intentChannelSize)

    private val _effectChannel = Channel<Effect>(settings.effectChannelSize)

    private val _showLoadingFlow = MutableStateFlow<Boolean?>(null)
    val showLoadingFlow: Flow<Boolean> = _showLoadingFlow.asStateFlow().filterNotNull()

    private val _errorChannel = Channel<String>(settings.errorChannelSize)
    val errorFlow: Flow<String> = _errorChannel.receiveAsFlow()

    /**
     * Activities/Fragments must collect this flow to do effect like: show dialog, snackBar, navigate,...
     */
    val effectFlow: Flow<Effect> = _effectChannel.receiveAsFlow()

    // ------------------------------------ Function -----------------------------------------------

    /**
     * Child class must implement this function to call api, update state, update effect,...
     */
    protected abstract suspend fun processIntent(intent: Intent)

    /**
     * Call when Activities/Fragment is in first create callback
     */
    fun start() {
        if (_initialised.compareAndSet(false, true)) {
            onFirstStart()
            viewModelScope.launch {
                for (intent in _intentChannel) {
                    launch {
                        processIntent(intent)
                    }
                }
            }
        }
    }

    /**
     * Call from Activities/Fragments to send [Intent] to this ViewModel to process
     */
    fun emitIntent(intent: Intent) {
        viewModelScope.launch {
            _intentChannel.send(intent)
        }
    }

    /**
     * Call from this ViewModel/Activities/Fragments to notify Activities/Fragments to do effect like show dialog, snackBar, navigate,...
     */
    fun emitEffect(effect: Effect) {
        viewModelScope.launch {
            _effectChannel.send(effect)
        }
    }

    /**
     * Call when [start] run for the first time
     */
    protected open fun onFirstStart() {}

    fun showLoading() {
        viewModelScope.launch { _showLoadingFlow.emit(true) }
    }

    fun hideLoading() {
        viewModelScope.launch { _showLoadingFlow.emit(false) }
    }

    fun showError(exception: Throwable) {
        viewModelScope.launch {
            _errorChannel.send(exception.message ?: "Unknown error.")
        }
    }
}