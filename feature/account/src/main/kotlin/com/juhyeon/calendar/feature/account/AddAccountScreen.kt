package com.juhyeon.calendar.feature.account

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.juhyeon.calendar.feature.account.component.NumberKeyComponent
import com.juhyeon.calendar.shared.ui.common.extension.clickableSingleIgnoreInteraction
import com.juhyeon.calendar.shared.ui.system.theme.button.ButtonBasic
import com.juhyeon.calendar.shared.ui.system.theme.button.ButtonFlexible
import com.juhyeon.calendar.shared.ui.system.theme.navigation.top.TopNavigationTitleClose
import com.juhyeon.calendar.shared.ui.system.theme.theme.Blue600
import com.juhyeon.calendar.shared.ui.system.theme.theme.Gray300
import com.juhyeon.calendar.shared.ui.system.theme.theme.Gray800
import com.juhyeon.calendar.shared.ui.system.theme.theme.Radius8
import com.juhyeon.calendar.shared.ui.system.theme.theme.White100
import com.juhyeon.calendar.shared.ui.system.theme.theme.bold
import com.juhyeon.calendar.shared.ui.system.theme.theme.departureNormal
import com.juhyeon.calendar.shared.ui.system.theme.theme.semiBold
import com.juhyeon.calendar.shared.util.kotlin.extension.applyCommaFormat

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
        isExpenditure = addAccountViewModel.isExpenditure.value,
        price = addAccountViewModel.price.value,
        memo = addAccountViewModel.memo.value,
        selectedCategory = addAccountViewModel.category.value,
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
    isExpenditure: Boolean,
    price: String,
    memo: String,
    selectedCategory: String,
    onBackClick: () -> Unit,
    onChangeExpenditure: (Boolean) -> Unit,
    onKeyClick: (String) -> Unit,
    onMemoChange: (String) -> Unit,
    onCategorySelect: (String) -> Unit,
    onSaveClick: () -> Unit
) {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            TopNavigationTitleClose(
                title = "내역 추가",
                onCloseClick = { onBackClick() }
            )
        },
        containerColor = White100
    ) {
        Column(
            modifier = Modifier
                .padding(it)
                .padding(horizontal = 16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(10.dp, Alignment.CenterHorizontally)
            ) {
                Text(
                    modifier = Modifier
                        .clip(Radius8)
                        .border(1.dp, if (isExpenditure) Blue600 else Gray800, Radius8)
                        .background(if (isExpenditure) Blue600.copy(alpha = 0.1f) else White100, Radius8)
                        .clickableSingleIgnoreInteraction { onChangeExpenditure(true) }
                        .padding(horizontal = 16.dp, vertical = 8.dp),
                    text = "지출",
                    style = MaterialTheme.typography.semiBold(16),
                    color = if (isExpenditure) Blue600 else Gray800
                )
                Text(
                    modifier = Modifier
                        .clip(Radius8)
                        .border(1.dp, if (!isExpenditure) Blue600 else Gray800, Radius8)
                        .background(if (!isExpenditure) Blue600.copy(alpha = 0.1f) else White100, Radius8)
                        .clickableSingleIgnoreInteraction { onChangeExpenditure(false) }
                        .padding(horizontal = 16.dp, vertical = 8.dp),
                    text = "수입",
                    style = MaterialTheme.typography.semiBold(16),
                    color = if (!isExpenditure) Blue600 else Gray800
                )
            }

            Text(
                modifier = Modifier.fillMaxWidth(),
                text = if (price.isEmpty()) "0" else price.toLong().applyCommaFormat(),
                textAlign = TextAlign.End,
                style = MaterialTheme.typography.bold(30)
            )

            // 카테고리 선택
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                listOf("식비" to "1", "교통" to "2", "기타" to "0").forEach { (name, id) ->
                    Text(
                        modifier = Modifier
                            .clip(Radius8)
                            .border(1.dp, if (selectedCategory == id) Blue600 else Gray300, Radius8)
                            .background(if (selectedCategory == id) Blue600.copy(alpha = 0.1f) else White100, Radius8)
                            .clickableSingleIgnoreInteraction { onCategorySelect(id) }
                            .padding(horizontal = 12.dp, vertical = 6.dp),
                        text = name,
                        style = MaterialTheme.typography.departureNormal(14),
                        color = if (selectedCategory == id) Blue600 else Gray800
                    )
                }
            }

            // 메모 입력
            BasicTextField(
                value = memo,
                onValueChange = onMemoChange,
                modifier = Modifier
                    .fillMaxWidth()
                    .border(1.dp, Gray300, Radius8)
                    .padding(12.dp),
                textStyle = MaterialTheme.typography.departureNormal(14).copy(color = Gray800),
                cursorBrush = SolidColor(Blue600),
                decorationBox = { innerTextField ->
                    if (memo.isEmpty()) {
                        Text(
                            text = "메모를 입력하세요",
                            style = MaterialTheme.typography.departureNormal(14),
                            color = Gray300
                        )
                    }
                    innerTextField()
                }
            )

            NumberKeyComponent(
                onKeyClick = { key -> onKeyClick(key) }
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
        onBackClick = { },
        onChangeExpenditure = { },
        onKeyClick = { },
        onMemoChange = { },
        onCategorySelect = { },
        onSaveClick = { }
    )
}