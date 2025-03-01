package com.dienty.feature.detail

import androidx.lifecycle.viewModelScope
import com.dienty.common.usecase.usecase.GetDetailUseCase
import com.dienty.common.model.entity.DetailEntity
import com.dienty.core.network.model.BaseResult
import com.dienty.core.structure.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel  @Inject constructor(
    private val getDetailUseCase: GetDetailUseCase
) : BaseViewModel<DetailViewModel.Intent, DetailViewModel.Effect>() {

    private val _detailFlow = MutableStateFlow<DetailEntity?>(null)
    val detailFlow = _detailFlow.asStateFlow().filterNotNull()

    sealed interface Intent {
        data class GetDetail(val username: String) : Intent
    }

    sealed interface Effect

    private fun getDetail(username: String) {
        viewModelScope.launch {
            getDetailUseCase(username)
                .onStart { showLoading() }
                .catch { showError(it) }
                .onCompletion { hideLoading() }
                .collect { state ->
                    when (state) {
                        is BaseResult.Failure -> showError(state.exception)

                        is BaseResult.Success -> _detailFlow.value = state.data

                        is BaseResult.SuccessNoData -> showError(Throwable("No data."))
                    }
                }
        }
    }

    override suspend fun processIntent(intent: Intent) {
        when (intent) {
            is Intent.GetDetail -> getDetail(intent.username)
        }
    }
}