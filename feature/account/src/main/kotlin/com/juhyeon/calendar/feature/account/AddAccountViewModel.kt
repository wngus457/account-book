package com.juhyeon.calendar.feature.account

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.toRoute
import com.juhyeon.calendar.domain.category.Category
import com.juhyeon.calendar.domain.category.get.GetCategoryListUseCase
import com.juhyeon.calendar.domain.expense.Expense
import com.juhyeon.calendar.domain.expense.insert.InsertExpenseUseCase
import com.juhyeon.calendar.domain.onError
import com.juhyeon.calendar.domain.onSuccess
import com.juhyeon.calendar.shared.core.mvi.BaseViewModel
import com.juhyeon.calendar.shared.core.mvi.MviReducer
import com.juhyeon.calendar.shared.navigation.AddAccount
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import java.time.LocalTime
import java.time.format.DateTimeFormatter
import javax.inject.Inject

@HiltViewModel
class AddAccountViewModel @Inject constructor(
    private val state: SavedStateHandle,
    private val insertExpenseUseCase: InsertExpenseUseCase,
    getCategoryListUseCase: GetCategoryListUseCase
) : BaseViewModel<AddAccountContract.Event, AddAccountContract.State, AddAccountContract.Effect>() {

    private val route = state.toRoute<AddAccount>()
    private val year = route.year
    private val month = route.month
    private val date = route.date

    val isEdit = route.expenseKey.isNotEmpty()
    val isExpenditure = mutableStateOf(route.isExpenditure)
    val price = mutableStateOf(route.price.toString())
    val memo = mutableStateOf(route.memo)
    val category = mutableStateOf(route.categoryKey)
    val categories = mutableStateOf(emptyList<Category>())

    init {
        getCategoryListUseCase()
            .onSuccess {
                categories.value = it
                if (category.value == "0") category.value = it.firstOrNull()?.categoryKey ?: "0"
            }
            .launchIn(viewModelScope)
    }

    override fun initState() = AddAccountContract.State()

    override fun handleEvent(event: AddAccountContract.Event) {
        when (event) {
            is AddAccountContract.Event.OnBackClick -> reducer.setEffect(AddAccountContract.Effect.NavigateToBack)
            is AddAccountContract.Event.OnKeyClick -> changePrice(event.key)
            is AddAccountContract.Event.OnSaveClick -> saveAccount()
            is AddAccountContract.Event.OnMemoChange -> memo.value = event.memo
            is AddAccountContract.Event.OnCategorySelect -> category.value = event.category
            is AddAccountContract.Event.OnExpenditureChange -> isExpenditure.value = event.isExpenditure
        }
    }

    private fun changePrice(key: String) {
        when (key) {
            "<-" -> {
                if (price.value.length <= 1) {
                    price.value = "0"
                } else {
                    price.value = price.value.dropLast(1)
                }
            }
            else -> {
                if (price.value == "0") {
                    price.value = key
                } else {
                    price.value += key
                }
            }
        }
    }

    private fun saveAccount() {
        val priceValue = price.value.toLongOrNull() ?: 0L
        if (priceValue <= 0) return

        // 수정이면 원래 시간을 유지한다.
        val time = route.time.ifEmpty { LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm")) }

        val expense = Expense(
            key = "",
            year = year,
            month = month,
            date = date,
            expenseList = listOf(
                Expense.ExpenseItem(
                    key = route.expenseKey,
                    price = priceValue,
                    time = time,
                    category = category.value,
                    memo = memo.value,
                    isExpenditure = isExpenditure.value
                )
            ),
            totalExpense = if (isExpenditure.value) priceValue else 0L,
            totalEarning = if (!isExpenditure.value) priceValue else 0L
        )

        insertExpenseUseCase(expense)
            .onSuccess {
                reducer.setEffect(AddAccountContract.Effect.SaveSuccess)
                reducer.setEffect(AddAccountContract.Effect.NavigateToBack)
            }
            .onError { }
            .launchIn(viewModelScope)
    }
}