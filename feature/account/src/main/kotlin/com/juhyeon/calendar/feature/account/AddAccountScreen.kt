package com.juhyeon.calendar.feature.account

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.juhyeon.calendar.feature.account.component.NumberKeyComponent
import com.juhyeon.calendar.shared.ui.common.extension.clickableSingleIgnoreInteraction
import com.juhyeon.calendar.shared.ui.system.theme.navigation.top.TopNavigationTitleClose
import com.juhyeon.calendar.shared.ui.system.theme.theme.Gray800
import com.juhyeon.calendar.shared.ui.system.theme.theme.Radius8
import com.juhyeon.calendar.shared.ui.system.theme.theme.White100
import com.juhyeon.calendar.shared.ui.system.theme.theme.bold
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
            }
        }
    }
    AddAccountContents(
        state = state,
        isExpenditure = addAccountViewModel.isExpenditure.value,
        price = addAccountViewModel.price.value,
        onBackClick = { postEvent(AddAccountContract.Event.OnBackClick) },
        onChangeExpenditure = { addAccountViewModel.isExpenditure.value = it },
        onKeyClick = { postEvent(AddAccountContract.Event.OnKeyClick(it)) }
    )
}

@Composable
private fun AddAccountContents(
    state: AddAccountContract.State,
    isExpenditure: Boolean,
    price: String,
    onBackClick: () -> Unit,
    onChangeExpenditure: (Boolean) -> Unit,
    onKeyClick: (String) -> Unit
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
            verticalArrangement = Arrangement.spacedBy(20.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(10.dp, Alignment.CenterHorizontally)
            ) {
                Text(
                    modifier = Modifier
                        .clip(Radius8)
                        .border(1.dp, Gray800, Radius8)
                        .clickableSingleIgnoreInteraction { onChangeExpenditure(true) }
                        .padding(horizontal = 16.dp, vertical = 8.dp),
                    text = "지출",
                    style = MaterialTheme.typography.semiBold(16)
                )
                Text(
                    modifier = Modifier
                        .clip(Radius8)
                        .border(1.dp, Gray800, Radius8)
                        .clickableSingleIgnoreInteraction { onChangeExpenditure(false) }
                        .padding(horizontal = 16.dp, vertical = 8.dp),
                    text = "수입",
                    style = MaterialTheme.typography.semiBold(16)
                )
            }

            Text(
                modifier = Modifier.fillMaxWidth(),
                text = if (price.isEmpty()) "0" else price.toLong().applyCommaFormat(),
                textAlign = TextAlign.End,
                style = MaterialTheme.typography.bold(30)
            )

            NumberKeyComponent(
                onKeyClick = { key -> onKeyClick(key) }
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun AddAccountContentsPreview() {
    AddAccountContents(
        state = AddAccountContract.State(null),
        isExpenditure = true,
        price = "1000",
        onBackClick = { },
        onChangeExpenditure = { },
        onKeyClick = { }
    )
}