package com.juhyeon.calendar.domain.category

import com.juhyeon.calendar.domain.FlowNoParamUseCase
import com.juhyeon.calendar.domain.Result
import com.juhyeon.calendar.domain.annotaion.DefaultDispatcher
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetCategoryListUseCase @Inject constructor(
    private val categoryRepository: CategoryRepository,
    @param:DefaultDispatcher private val dispatcher: CoroutineDispatcher
) : FlowNoParamUseCase<List<Category>>(dispatcher) {

    override fun execute(): Flow<Result<List<Category>>> = categoryRepository.getCategoryList()
        .distinctUntilChanged()
        .map { list -> Result.Success(list) }
}