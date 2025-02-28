package com.dienty.common.domain.usecase

import androidx.annotation.VisibleForTesting
import com.dienty.common.model.entity.DetailEntity
import com.dienty.common.repository.detail.DetailRepository
import com.dienty.core.network.model.BaseResult
import com.dienty.core.usecase.DataStateUseCase
import kotlinx.coroutines.flow.FlowCollector
import javax.inject.Inject

class GetDetailUseCase @Inject constructor(
    @get:VisibleForTesting(otherwise = VisibleForTesting.PROTECTED)
    internal val repository: DetailRepository
) : DataStateUseCase<String, DetailEntity>() {

    override suspend fun FlowCollector<BaseResult<DetailEntity>>.execute(params: String) {
        emit(repository.getDetail(params))
    }


}