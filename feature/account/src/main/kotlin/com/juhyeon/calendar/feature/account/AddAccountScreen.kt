package com.juhyeon.calendar.feature.account

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.juhyeon.calendar.feature.account.component.AccountButtonComponent
import com.juhyeon.calendar.domain.category.Category
import com.juhyeon.calendar.feature.account.component.CategoryComponent
import com.juhyeon.calendar.feature.account.component.InputAmountComponent
import com.juhyeon.calendar.feature.account.component.MemoComponent
import com.juhyeon.calendar.feature.account.component.NumberKeyComponent
import com.juhyeon.calendar.shared.ui.system.theme.button.ButtonBasic
import com.juhyeon.calendar.shared.ui.system.theme.button.ButtonFlexible
import com.juhyeon.calendar.shared.ui.system.theme.navigation.top.TopNavigationTitleClose
import com.juhyeon.calendar.shared.ui.system.theme.theme.White100
import com.juhyeon.calendar.shared.ui.system.theme.theme.bold

@Composable
fun AddAccountScreen(
    navController: NavHostController,
    addAccountViewModel: AddAccountViewModel = hiltViewModel()
) {
    val state = addAccountViewModel.stateFlow.collectAsState().value
    val postEvent = addAccountViewModel.eventHandler

    LaunchedEffect(true) {
        addAccountViewModel.effectFlow.collect { effect ->
            when (effect) {
                is AddAccountContract.Effect.NavigateToBack -> navController.popBackStack()
                is AddAccountContract.Effect.SaveSuccess -> { }
            }
        }
    }
    AddAccountContents(
        state = state,
        isEdit = addAccountViewModel.isEdit,
        isExpenditure = addAccountViewModel.isExpenditure.value,
        price = addAccountViewModel.price.value,
        memo = addAccountViewModel.memo.value,
        selectedCategory = addAccountViewModel.category.value,
        categories = addAccountViewModel.categories.value,
        onBackClick = { postEvent(AddAccountContract.Event.OnBackClick) },
        onChangeExpenditure = { postEvent(AddAccountContract.Event.OnExpenditureChange(it)) },
        onKeyClick = { postEvent(AddAccountContract.Event.OnKeyClick(it)) },
        onMemoChange = { postEvent(AddAccountContract.Event.OnMemoChange(it)) },
        onCategorySelect = { postEvent(AddAccountContract.Event.OnCategorySelect(it)) },
        onSaveClick = { postEvent(AddAccountContract.Event.OnSaveClick) }
    )
}

@Composable
private fun AddAccountContents(
    state: AddAccountContract.State,
    isEdit: Boolean = false,
    isExpenditure: Boolean,
    price: String,
    memo: String,
    selectedCategory: String,
    categories: List<Category>,
    onBackClick: () -> Unit = { },
    onChangeExpenditure: (Boolean) -> Unit = { },
    onKeyClick: (String) -> Unit = { },
    onMemoChange: (String) -> Unit=  { },
    onCategorySelect: (String) -> Unit = { },
    onSaveClick: () -> Unit = { }
) {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            TopNavigationTitleClose(
                title = if (isEdit) "내역 수정" else "내역 추가",
                onCloseClick = { onBackClick() }
            )
        },
        containerColor = White100
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(it)
                .padding(horizontal = 20.dp)
                .verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.spacedBy(28.dp)
        ) {
            AccountButtonComponent(
                isExpenditure = isExpenditure,
                onChangeExpenditure = onChangeExpenditure
            )

            InputAmountComponent(
                price = price
            )

            NumberKeyComponent(
                onKeyClick = { key -> onKeyClick(key) }
            )

            CategoryComponent(
                categories = categories,
                selectedCategory = selectedCategory,
                onCategorySelect = onCategorySelect
            )
            MemoComponent(
                text = memo,
                onValueChange = onMemoChange
            )

            ButtonBasic(
                text = "입력",
                textStyle = MaterialTheme.typography.bold(30),
                flexible = ButtonFlexible.False,
                onClick = { onSaveClick() }
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun AddAccountContentsPreview() {
    AddAccountContents(
        state = AddAccountContract.State(),
        isExpenditure = true,
        price = "1000",
        memo = "",
        selectedCategory = "0",
        categories = emptyList()
    )
}