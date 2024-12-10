package com.juhyeon.calendar.shared.ui.system.theme.navigation.top

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.juhyeon.calendar.shared.ui.common.extension.clickableSingle
import com.juhyeon.calendar.shared.ui.system.theme.Bold16
import com.juhyeon.calendar.shared.ui.system.theme.White100
import com.juhyeon.calendar.shared.ui.system.theme.icon.CommonArrowBack
import com.juhyeon.calendar.shared.ui.system.theme.icon.CommonBack
import com.juhyeon.calendar.shared.ui.system.theme.icon.CommonClose

@Composable
fun BasicTopNavigation(
    arrow: Arrow = Arrow.On,
    close: Close = Close.Off,
    title: Title = Title.Off,
    onLeftIconClick: () -> Unit = { }
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(48.dp)
            .background(White100)
            .padding(horizontal = 16.dp)
    ) {
        when (title) {
            is Title.On -> {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(horizontal = 50.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = title.title,
                        overflow = TextOverflow.Ellipsis,
                        maxLines = 1,
                        style = MaterialTheme.typography.Bold16
                    )
                }
            }
            is Title.Off -> { }
        }
        Row(
            modifier = Modifier.fillMaxHeight(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            when (arrow) {
                Arrow.On ->
                    Box(
                        modifier = Modifier
                            .size(34.dp)
                            .clickableSingle { onLeftIconClick() }
                    ) {
                        Icon(
                            modifier = Modifier
                                .align(Alignment.CenterStart),
                            imageVector = CommonBack,
                            contentDescription = ""
                        )
                    }
                Arrow.Off -> when (close) {
                    Close.On ->
                        Box(
                            modifier = Modifier
                                .size(34.dp)
                                .clickableSingle { onLeftIconClick() }
                        ) {
                            Icon(
                                modifier = Modifier
                                    .align(Alignment.CenterStart),
                                imageVector = CommonClose,
                                contentDescription = ""
                            )
                        }
                    Close.Off -> { }
                }
            }
        }
    }
}

@Composable
fun TopNavigationTitleBack(
    title: String,
    onBackClick: () -> Unit
) {
    BasicTopNavigation(
        arrow = Arrow.On,
        title = Title.On(title),
        onLeftIconClick = { onBackClick() }
    )
}

@Composable
fun TopNavigationTitleClose(
    title: String,
    onCloseClick: () -> Unit
) {
    BasicTopNavigation(
        arrow = Arrow.Off,
        close = Close.On,
        title = Title.On(title),
        onLeftIconClick = { onCloseClick() }
    )
}

@Preview(showBackground = true)
@Composable
private fun TopNavigationTitleBackPreview() {
    TopNavigationTitleBack(
        title = "테스트",
        onBackClick = { }
    )
}

@Preview(showBackground = true)
@Composable
private fun TopNavigationTitleClosePreview() {
    TopNavigationTitleClose(
        title = "테스트",
        onCloseClick = { }
    )
}