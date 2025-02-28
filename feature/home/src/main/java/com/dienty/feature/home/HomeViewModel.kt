package com.dienty.feature.home

import com.dienty.common.domain.usecase.GetUsersUseCase
import com.dienty.core.structure.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getUsersUseCase: GetUsersUseCase
) : BaseViewModel<HomeViewModel.Intent, HomeViewModel.Effect>() {

    val pagingFlow = getUsersUseCase(Unit)

    sealed interface Intent
    sealed interface Effect
    override suspend fun processIntent(intent: Intent) {}
}