package com.juhyeon.calendar.feature.account

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.juhyeon.calendar.shared.ui.common.extension.clickableSingleIgnoreInteraction
import com.juhyeon.calendar.shared.ui.system.theme.Gray800
import com.juhyeon.calendar.shared.ui.system.theme.SemiBold16
import com.juhyeon.calendar.shared.ui.system.theme.White100
import com.juhyeon.calendar.shared.ui.system.theme.navigation.top.TopNavigationTitleClose

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
        onBackClick = { postEvent(AddAccountContract.Event.OnBackClick) },
        onChangeExpenditure = { addAccountViewModel.isExpenditure.value = it }
    )
}

@Composable
private fun AddAccountContents(
    state: AddAccountContract.State,
    isExpenditure: Boolean,
    onBackClick: () -> Unit,
    onChangeExpenditure: (Boolean) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(White100)
    ) {
        TopNavigationTitleClose(
            title = "내역 추가",
            onCloseClick = { onBackClick() }
        )

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(10.dp, Alignment.CenterHorizontally)
        ) {
            Text(
                modifier = Modifier
                    .clip(RoundedCornerShape(8.dp))
                    .border(1.dp, Gray800, RoundedCornerShape(8.dp))
                    .clickableSingleIgnoreInteraction { onChangeExpenditure(true) }
                    .padding(horizontal = 16.dp, vertical = 8.dp),
                text = "지출",
                style = MaterialTheme.typography.SemiBold16
            )
            Text(
                modifier = Modifier
                    .clip(RoundedCornerShape(8.dp))
                    .border(1.dp, Gray800, RoundedCornerShape(8.dp))
                    .clickableSingleIgnoreInteraction { onChangeExpenditure(false) }
                    .padding(horizontal = 16.dp, vertical = 8.dp),
                text = "수입",
                style = MaterialTheme.typography.SemiBold16
            )
        }

        Text(
            text = "1,000"
        )

    }
}

@Preview(showBackground = true)
@Composable
private fun AddAccountContentsPreview() {
    AddAccountContents(
        state = AddAccountContract.State(null),
        isExpenditure = true,
        onBackClick = { },
        onChangeExpenditure = { }
    )
}