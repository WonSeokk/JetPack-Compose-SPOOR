package wwon.seokk.abandonedpets.ui.calendar

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import wwon.seokk.abandonedpets.util.getNumberWeeks
import java.time.LocalDate
import java.time.Period
import java.time.YearMonth

/**
 * Created by WonSeok on 2022.08.25
 **/
data class CalendarState (
    var calendarUiState: MutableState<CalendarUiState> = mutableStateOf(CalendarUiState()),
    var listMonths: List<Month> = emptyList(),
    private val calendarStartDate: LocalDate = LocalDate.now().minusYears(3).withDayOfMonth(1),
    private val calendarEndDate: LocalDate = LocalDate.now(),
    private val periodBetweenCalendarStartEnd: Period = Period.between(
        calendarStartDate,
        calendarEndDate
    )
) {
    init {
        val tempListMonths = mutableListOf<Month>()
        var startYearMonth = YearMonth.from(calendarStartDate)
        for (numberMonth in 0..periodBetweenCalendarStartEnd.toTotalMonths()) {
            val numberWeeks = startYearMonth.getNumberWeeks()
            val listWeekItems = mutableListOf<Week>()
            for (week in 0 until numberWeeks) {
                listWeekItems.add(
                    Week(
                        number = week,
                        yearMonth = startYearMonth
                    )
                )
            }
            val month = Month(startYearMonth, listWeekItems)
            tempListMonths.add(month)
            startYearMonth = startYearMonth.plusMonths(1)
        }
        listMonths = tempListMonths.toList().reversed()
    }

    companion object {
        const val DAYS_IN_WEEK = 7
    }
}

enum class AnimationDirection {
    FORWARDS,
    BACKWARDS;

    fun isBackwards() = this == BACKWARDS
    fun isForwards() = this == FORWARDS
}
