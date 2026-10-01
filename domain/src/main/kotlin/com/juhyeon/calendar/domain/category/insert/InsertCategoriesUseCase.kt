package com.juhyeon.calendar.domain.category.insert

import com.juhyeon.calendar.domain.FlowUseCase
import com.juhyeon.calendar.domain.Result
import com.juhyeon.calendar.domain.annotaion.DefaultDispatcher
import com.juhyeon.calendar.domain.category.Category
import com.juhyeon.calendar.domain.category.CategoryRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class InsertCategoriesUseCase @Inject constructor(
    private val categoryRepository: CategoryRepository,
    @param:DefaultDispatcher private val dispatcher: CoroutineDispatcher
) : FlowUseCase<List<Category>, Unit>(dispatcher) {

    override fun execute(parameters: List<Category>): Flow<Result<Unit>> = flow {
        emit(Result.Success(categoryRepository.insertCategories(parameters)))
    }
}