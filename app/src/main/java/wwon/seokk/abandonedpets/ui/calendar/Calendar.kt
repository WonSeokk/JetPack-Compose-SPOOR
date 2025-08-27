package wwon.seokk.abandonedpets.ui.calendar

import androidx.compose.animation.core.*
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.windowInsetsBottomHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import wwon.seokk.abandonedpets.ui.theme.AbandonedPetsTheme
import java.time.DayOfWeek
import java.time.LocalDate
import java.time.format.TextStyle
import java.time.temporal.TemporalAdjusters
import java.time.temporal.WeekFields
import java.util.*

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun Calendar(
    calendarState: CalendarState,
    onDayClicked: (LocalDate) -> Unit,
    modifier: Modifier = Modifier,
    contentPadding: PaddingValues = PaddingValues()
) {
    val calendarUiState = calendarState.calendarUiState.value
    val numberSelectedDays = calendarUiState.numberSelectedDays.toInt()

    val selectedAnimationPercentage = remember(numberSelectedDays) {
        Animatable(0f)
    }

    LaunchedEffect(numberSelectedDays) {
        val animationSpec: TweenSpec<Float> = tween(
            durationMillis =
            (numberSelectedDays.coerceAtLeast(0) * DURATION_MILLIS_PER_DAY)
                .coerceAtMost(2000),
            easing = CubicBezierEasing(0.5f, 1f, 0.89f, 1f)
        )
        selectedAnimationPercentage.animateTo(
            targetValue = 1f,
            animationSpec = animationSpec
        )

    }

    LazyColumn(
        modifier = modifier.consumeWindowInsets(contentPadding),
        contentPadding = contentPadding
    ) {
        calendarState.listMonths.forEach { month ->
            itemsCalendarMonth(calendarUiState, onDayClicked, { selectedAnimationPercentage.value }, month)
        }
        item(key = "bottomSpacer") {
            Spacer(
                modifier = Modifier.windowInsetsBottomHeight(
                    WindowInsets.navigationBars
                )
            )
        }
    }
}

private fun LazyListScope.itemsCalendarMonth(
    calendarState: CalendarUiState,
    onDayClicked: (LocalDate) -> Unit,
    selectedPercentageProvider: () -> Float,
    month: Month
) {
    item(month.yearMonth.month.name + month.yearMonth.year + "header") {
        MonthHeader(
            modifier = Modifier.padding(start = 32.dp, end = 32.dp, top = 32.dp),
            month = month.yearMonth.month.getDisplayName(TextStyle.FULL, Locale.KOREA),
            year = month.yearMonth.year.toString()
        )
    }

    val contentModifier = Modifier
        .fillMaxWidth()
        .wrapContentWidth(Alignment.CenterHorizontally)
    item(month.yearMonth.month.name + month.yearMonth.year + "daysOfWeek") {
        DaysOfWeek(modifier = contentModifier)
    }

    itemsIndexed(month.weeks, key = { index, _ ->
        month.yearMonth.year.toString() + "/" + month.yearMonth.month.value + "/" + (index + 1).toString()
    }) { _, week ->
        val beginningWeek = week.yearMonth.atDay(1).plusWeeks(week.number.toLong())
        val currentDay = beginningWeek.with(TemporalAdjusters.previousOrSame(DayOfWeek.MONDAY))

        if (calendarState.hasSelectedPeriodOverlap(currentDay, currentDay.plusDays(6))) {
            WeekSelectionPill(
                state = calendarState,
                currentWeekStart = currentDay,
                widthPerDay = CELL_SIZE,
                week = week,
                selectedPercentageTotalProvider = selectedPercentageProvider
            )
        }
        Week(
            calendarState = calendarState,
            modifier = contentModifier,
            week = week,
            onDayClicked = onDayClicked
        )
        Spacer(Modifier.height(8.dp))
    }
}

internal val CALENDAR_STARTS_ON = WeekFields.ISO

@Preview(showBackground = true)
@Composable
fun DayPreview() {
    AbandonedPetsTheme {
        Calendar(CalendarState(), onDayClicked = { })
    }
}
