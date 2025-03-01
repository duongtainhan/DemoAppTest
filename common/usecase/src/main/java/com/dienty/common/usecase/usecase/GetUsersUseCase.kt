package com.dienty.common.usecase.usecase

import androidx.annotation.VisibleForTesting
import com.dienty.common.model.entity.UserEntity
import com.dienty.common.repository.home.HomeRepository
import com.dienty.core.usecase.FlowPagingUseCase
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetUsersUseCase @Inject constructor(
    @get:VisibleForTesting(otherwise = VisibleForTesting.PROTECTED)
    internal val repository: HomeRepository
) : FlowPagingUseCase<Unit, UserEntity>() {

    override fun execute(params: Unit): Flow<androidx.paging.PagingData<UserEntity>> {
        return repository.getUsers()
    }
}