package com.juhyeon.calendar.shared.ui.system.theme

import androidx.compose.material3.Typography
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.PlatformTextStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import com.juhyeon.calendar.shared.ui.common.extension.textDp
import com.juhyeon.calendar.shared.ui.system.R

val font = FontFamily(
    Font(R.font.pretendard_regular, weight = FontWeight.Normal),
    Font(R.font.pretendard_bold, weight = FontWeight.Bold),
    Font(R.font.pretendard_light, weight = FontWeight.Light),
    Font(R.font.pretendard_semibold, weight = FontWeight.SemiBold),
    Font(R.font.pretendard_medium, weight = FontWeight.Medium)
)

val Typography.Normal8: TextStyle
    @Composable
    get() = TextStyle(
        fontSize = 8.textDp,
        fontWeight = FontWeight.Normal,
        fontFamily = font,
        platformStyle = PlatformTextStyle(includeFontPadding = false)
    )

val Typography.Medium8: TextStyle
    @Composable
    get() = TextStyle(
        fontSize = 8.textDp,
        fontWeight = FontWeight.Medium,
        fontFamily = font,
        platformStyle = PlatformTextStyle(includeFontPadding = false)
    )

val Typography.SemiBold8: TextStyle
    @Composable
    get() = TextStyle(
        fontSize = 8.textDp,
        fontWeight = FontWeight.SemiBold,
        fontFamily = font,
        platformStyle = PlatformTextStyle(includeFontPadding = false)
    )

val Typography.Bold8: TextStyle
    @Composable
    get() = TextStyle(
        fontSize = 8.textDp,
        fontWeight = FontWeight.Bold,
        fontFamily = font,
        platformStyle = PlatformTextStyle(includeFontPadding = false)
    )

val Typography.Normal10: TextStyle
    @Composable
    get() = TextStyle(
        fontSize = 10.textDp,
        fontWeight = FontWeight.Normal,
        fontFamily = font,
        platformStyle = PlatformTextStyle(includeFontPadding = false)
    )

val Typography.Medium10: TextStyle
    @Composable
    get() = TextStyle(
        fontSize = 10.textDp,
        fontWeight = FontWeight.Medium,
        fontFamily = font,
        platformStyle = PlatformTextStyle(includeFontPadding = false)
    )

val Typography.SemiBold10: TextStyle
    @Composable
    get() = TextStyle(
        fontSize = 10.textDp,
        fontWeight = FontWeight.SemiBold,
        fontFamily = font,
        platformStyle = PlatformTextStyle(includeFontPadding = false)
    )

val Typography.Bold10: TextStyle
    @Composable
    get() = TextStyle(
        fontSize = 10.textDp,
        fontWeight = FontWeight.Bold,
        fontFamily = font,
        platformStyle = PlatformTextStyle(includeFontPadding = false)
    )

val Typography.Normal11: TextStyle
    @Composable
    get() = TextStyle(
        fontSize = 11.textDp,
        fontWeight = FontWeight.Normal,
        fontFamily = font,
        platformStyle = PlatformTextStyle(includeFontPadding = false)
    )

val Typography.Medium11: TextStyle
    @Composable
    get() = TextStyle(
        fontSize = 11.textDp,
        fontWeight = FontWeight.Medium,
        fontFamily = font,
        platformStyle = PlatformTextStyle(includeFontPadding = false)
    )

val Typography.SemiBold11: TextStyle
    @Composable
    get() = TextStyle(
        fontSize = 11.textDp,
        fontWeight = FontWeight.SemiBold,
        fontFamily = font,
        platformStyle = PlatformTextStyle(includeFontPadding = false)
    )

val Typography.Bold11: TextStyle
    @Composable
    get() = TextStyle(
        fontSize = 11.textDp,
        fontWeight = FontWeight.Bold,
        fontFamily = font,
        platformStyle = PlatformTextStyle(includeFontPadding = false)
    )
val Typography.Normal12: TextStyle
    @Composable
    get() = TextStyle(
        fontSize = 12.textDp,
        fontWeight = FontWeight.Normal,
        fontFamily = font,
        platformStyle = PlatformTextStyle(includeFontPadding = false)
    )

val Typography.Medium12: TextStyle
    @Composable
    get() = TextStyle(
        fontSize = 12.textDp,
        fontWeight = FontWeight.Medium,
        fontFamily = font,
        platformStyle = PlatformTextStyle(includeFontPadding = false)
    )

val Typography.SemiBold12: TextStyle
    @Composable
    get() = TextStyle(
        fontSize = 12.textDp,
        fontWeight = FontWeight.SemiBold,
        fontFamily = font,
        platformStyle = PlatformTextStyle(includeFontPadding = false)
    )

val Typography.Bold12: TextStyle
    @Composable
    get() = TextStyle(
        fontSize = 12.textDp,
        fontWeight = FontWeight.Bold,
        fontFamily = font,
        platformStyle = PlatformTextStyle(includeFontPadding = false)
    )

val Typography.Normal13: TextStyle
    @Composable
    get() = TextStyle(
        fontSize = 13.textDp,
        fontWeight = FontWeight.Normal,
        fontFamily = font,
        platformStyle = PlatformTextStyle(includeFontPadding = false)
    )

val Typography.Medium13: TextStyle
    @Composable
    get() = TextStyle(
        fontSize = 13.textDp,
        fontWeight = FontWeight.Medium,
        fontFamily = font,
        platformStyle = PlatformTextStyle(includeFontPadding = false)
    )

val Typography.SemiBold13: TextStyle
    @Composable
    get() = TextStyle(
        fontSize = 13.textDp,
        fontWeight = FontWeight.SemiBold,
        fontFamily = font,
        platformStyle = PlatformTextStyle(includeFontPadding = false)
    )

val Typography.Bold13: TextStyle
    @Composable
    get() = TextStyle(
        fontSize = 13.textDp,
        fontWeight = FontWeight.Bold,
        fontFamily = font,
        platformStyle = PlatformTextStyle(includeFontPadding = false)
    )

val Typography.Normal14: TextStyle
    @Composable
    get() = TextStyle(
        fontSize = 14.textDp,
        fontWeight = FontWeight.Normal,
        fontFamily = font,
        platformStyle = PlatformTextStyle(includeFontPadding = false)
    )

val Typography.Medium14: TextStyle
    @Composable
    get() = TextStyle(
        fontSize = 14.textDp,
        fontWeight = FontWeight.Medium,
        fontFamily = font,
        platformStyle = PlatformTextStyle(includeFontPadding = false)
    )

val Typography.SemiBold14: TextStyle
    @Composable
    get() = TextStyle(
        fontSize = 14.textDp,
        fontWeight = FontWeight.SemiBold,
        fontFamily = font,
        platformStyle = PlatformTextStyle(includeFontPadding = false)
    )

val Typography.Bold14: TextStyle
    @Composable
    get() = TextStyle(
        fontSize = 14.textDp,
        fontWeight = FontWeight.Bold,
        fontFamily = font,
        platformStyle = PlatformTextStyle(includeFontPadding = false)
    )
val Typography.Normal15: TextStyle
    @Composable
    get() = TextStyle(
        fontSize = 15.textDp,
        fontWeight = FontWeight.Normal,
        fontFamily = font,
        platformStyle = PlatformTextStyle(includeFontPadding = false)
    )

val Typography.Medium15: TextStyle
    @Composable
    get() = TextStyle(
        fontSize = 15.textDp,
        fontWeight = FontWeight.Medium,
        fontFamily = font,
        platformStyle = PlatformTextStyle(includeFontPadding = false)
    )

val Typography.SemiBold15: TextStyle
    @Composable
    get() = TextStyle(
        fontSize = 15.textDp,
        fontWeight = FontWeight.SemiBold,
        fontFamily = font,
        platformStyle = PlatformTextStyle(includeFontPadding = false)
    )

val Typography.Bold15: TextStyle
    @Composable
    get() = TextStyle(
        fontSize = 15.textDp,
        fontWeight = FontWeight.Bold,
        fontFamily = font,
        platformStyle = PlatformTextStyle(includeFontPadding = false)
    )
val Typography.Normal16: TextStyle
    @Composable
    get() = TextStyle(
        fontSize = 16.textDp,
        fontWeight = FontWeight.Normal,
        fontFamily = font,
        platformStyle = PlatformTextStyle(includeFontPadding = false)
    )

val Typography.Medium16: TextStyle
    @Composable
    get() = TextStyle(
        fontSize = 16.textDp,
        fontWeight = FontWeight.Medium,
        fontFamily = font,
        platformStyle = PlatformTextStyle(includeFontPadding = false)
    )

val Typography.SemiBold16: TextStyle
    @Composable
    get() = TextStyle(
        fontSize = 16.textDp,
        fontWeight = FontWeight.SemiBold,
        fontFamily = font,
        platformStyle = PlatformTextStyle(includeFontPadding = false)
    )

val Typography.Bold16: TextStyle
    @Composable
    get() = TextStyle(
        fontSize = 16.textDp,
        fontWeight = FontWeight.Bold,
        fontFamily = font,
        platformStyle = PlatformTextStyle(includeFontPadding = false)
    )
val Typography.Normal17: TextStyle
    @Composable
    get() = TextStyle(
        fontSize = 17.textDp,
        fontWeight = FontWeight.Normal,
        fontFamily = font,
        platformStyle = PlatformTextStyle(includeFontPadding = false)
    )

val Typography.Medium17: TextStyle
    @Composable
    get() = TextStyle(
        fontSize = 17.textDp,
        fontWeight = FontWeight.Medium,
        fontFamily = font,
        platformStyle = PlatformTextStyle(includeFontPadding = false)
    )

val Typography.SemiBold17: TextStyle
    @Composable
    get() = TextStyle(
        fontSize = 17.textDp,
        fontWeight = FontWeight.SemiBold,
        fontFamily = font,
        platformStyle = PlatformTextStyle(includeFontPadding = false)
    )

val Typography.Bold17: TextStyle
    @Composable
    get() = TextStyle(
        fontSize = 17.textDp,
        fontWeight = FontWeight.Bold,
        fontFamily = font,
        platformStyle = PlatformTextStyle(includeFontPadding = false)
    )
val Typography.Normal18: TextStyle
    @Composable
    get() = TextStyle(
        fontSize = 18.textDp,
        fontWeight = FontWeight.Normal,
        fontFamily = font,
        platformStyle = PlatformTextStyle(includeFontPadding = false)
    )

val Typography.Medium18: TextStyle
    @Composable
    get() = TextStyle(
        fontSize = 18.textDp,
        fontWeight = FontWeight.Medium,
        fontFamily = font,
        platformStyle = PlatformTextStyle(includeFontPadding = false)
    )

val Typography.SemiBold18: TextStyle
    @Composable
    get() = TextStyle(
        fontSize = 18.textDp,
        fontWeight = FontWeight.SemiBold,
        fontFamily = font,
        platformStyle = PlatformTextStyle(includeFontPadding = false)
    )

val Typography.Bold18: TextStyle
    @Composable
    get() = TextStyle(
        fontSize = 18.textDp,
        fontWeight = FontWeight.Bold,
        fontFamily = font,
        platformStyle = PlatformTextStyle(includeFontPadding = false)
    )
val Typography.Normal19: TextStyle
    @Composable
    get() = TextStyle(
        fontSize = 19.textDp,
        fontWeight = FontWeight.Normal,
        fontFamily = font,
        platformStyle = PlatformTextStyle(includeFontPadding = false)
    )

val Typography.Medium19: TextStyle
    @Composable
    get() = TextStyle(
        fontSize = 19.textDp,
        fontWeight = FontWeight.Medium,
        fontFamily = font,
        platformStyle = PlatformTextStyle(includeFontPadding = false)
    )

val Typography.SemiBold19: TextStyle
    @Composable
    get() = TextStyle(
        fontSize = 19.textDp,
        fontWeight = FontWeight.SemiBold,
        fontFamily = font,
        platformStyle = PlatformTextStyle(includeFontPadding = false)
    )

val Typography.Bold19: TextStyle
    @Composable
    get() = TextStyle(
        fontSize = 19.textDp,
        fontWeight = FontWeight.Bold,
        fontFamily = font,
        platformStyle = PlatformTextStyle(includeFontPadding = false)
    )
val Typography.Normal20: TextStyle
    @Composable
    get() = TextStyle(
        fontSize = 20.textDp,
        fontWeight = FontWeight.Normal,
        fontFamily = font,
        platformStyle = PlatformTextStyle(includeFontPadding = false)
    )

val Typography.Medium20: TextStyle
    @Composable
    get() = TextStyle(
        fontSize = 20.textDp,
        fontWeight = FontWeight.Medium,
        fontFamily = font,
        platformStyle = PlatformTextStyle(includeFontPadding = false)
    )

val Typography.SemiBold20: TextStyle
    @Composable
    get() = TextStyle(
        fontSize = 20.textDp,
        fontWeight = FontWeight.SemiBold,
        fontFamily = font,
        platformStyle = PlatformTextStyle(includeFontPadding = false)
    )

val Typography.Bold20: TextStyle
    @Composable
    get() = TextStyle(
        fontSize = 20.textDp,
        fontWeight = FontWeight.Bold,
        fontFamily = font,
        platformStyle = PlatformTextStyle(includeFontPadding = false)
    )
val Typography.Normal21: TextStyle
    @Composable
    get() = TextStyle(
        fontSize = 21.textDp,
        fontWeight = FontWeight.Normal,
        fontFamily = font,
        platformStyle = PlatformTextStyle(includeFontPadding = false)
    )

val Typography.Medium21: TextStyle
    @Composable
    get() = TextStyle(
        fontSize = 21.textDp,
        fontWeight = FontWeight.Medium,
        fontFamily = font,
        platformStyle = PlatformTextStyle(includeFontPadding = false)
    )

val Typography.SemiBold21: TextStyle
    @Composable
    get() = TextStyle(
        fontSize = 21.textDp,
        fontWeight = FontWeight.SemiBold,
        fontFamily = font,
        platformStyle = PlatformTextStyle(includeFontPadding = false)
    )

val Typography.Bold21: TextStyle
    @Composable
    get() = TextStyle(
        fontSize = 21.textDp,
        fontWeight = FontWeight.Bold,
        fontFamily = font,
        platformStyle = PlatformTextStyle(includeFontPadding = false)
    )
val Typography.Normal22: TextStyle
    @Composable
    get() = TextStyle(
        fontSize = 22.textDp,
        fontWeight = FontWeight.Normal,
        fontFamily = font,
        platformStyle = PlatformTextStyle(includeFontPadding = false)
    )

val Typography.Medium22: TextStyle
    @Composable
    get() = TextStyle(
        fontSize = 22.textDp,
        fontWeight = FontWeight.Medium,
        fontFamily = font,
        platformStyle = PlatformTextStyle(includeFontPadding = false)
    )

val Typography.SemiBold22: TextStyle
    @Composable
    get() = TextStyle(
        fontSize = 22.textDp,
        fontWeight = FontWeight.SemiBold,
        fontFamily = font,
        platformStyle = PlatformTextStyle(includeFontPadding = false)
    )

val Typography.Bold22: TextStyle
    @Composable
    get() = TextStyle(
        fontSize = 22.textDp,
        fontWeight = FontWeight.Bold,
        fontFamily = font,
        platformStyle = PlatformTextStyle(includeFontPadding = false)
    )