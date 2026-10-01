package com.juhyeon.calendar.domain.category.delete

import com.juhyeon.calendar.domain.FlowNoParamUseCase
import com.juhyeon.calendar.domain.Result
import com.juhyeon.calendar.domain.annotaion.DefaultDispatcher
import com.juhyeon.calendar.domain.category.CategoryRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.take
import javax.inject.Inject

class DeleteAllCategoryUseCase @Inject constructor(
    private val categoryRepository: CategoryRepository,
    @param:DefaultDispatcher private val dispatcher: CoroutineDispatcher
) : FlowNoParamUseCase<Unit>(dispatcher) {

    override fun execute(): Flow<Result<Unit>> = categoryRepository.getCategoryList()
        .onEach { categoryRepository.deleteAllCategory() }
        .map { Result.Success(Unit) }
        .take(1)
}