package com.juhyeon.calendar.domain.setting.baseday

import java.time.LocalDate

const val DEFAULT_BASE_DAY = 1
const val MIN_BASE_DAY = 1
const val MAX_BASE_DAY = 31

/**
 * 이 날짜가 속한 기준일 윈도우의 시작일.
 * 기준일이 10이면 10월 10일 ~ 11월 9일이 한 윈도우이므로 10월 15일 -> 10월 10일.
 */
fun LocalDate.baseDayWindowStart(baseDay: Int): LocalDate {
    val thisMonthStart = atBaseDay(baseDay)
    return if (!isBefore(thisMonthStart)) thisMonthStart else minusMonths(1).atBaseDay(baseDay)
}

/** 윈도우 종료일 = 다음 윈도우 시작일 - 1일. */
fun LocalDate.baseDayWindowEnd(baseDay: Int): LocalDate =
    baseDayWindowStart(baseDay).plusMonths(1).atBaseDay(baseDay).minusDays(1)

/**
 * 이 날짜가 속한 달의 기준일.
 * 기준일 31 + 2월처럼 그 달에 없는 날은 말일로 당긴다. (2월 31일은 존재하지 않아 그대로 쓰면 예외)
 */
private fun LocalDate.atBaseDay(baseDay: Int): LocalDate =
    withDayOfMonth(minOf(baseDay.coerceIn(MIN_BASE_DAY, MAX_BASE_DAY), lengthOfMonth()))