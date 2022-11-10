package wwon.seokk.abandonedpets.ui.calendar

import androidx.compose.foundation.layout.Row
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.clearAndSetSemantics
import wwon.seokk.abandonedpets.ui.theme.AbandonedPetsTheme
import java.time.YearMonth

@Composable
internal fun MonthHeader(modifier: Modifier = Modifier, month: String, year: String) {
    Row(modifier = modifier.clearAndSetSemantics { }) {
        Text(
            modifier = Modifier.weight(1f),
            text = month,
            style = MaterialTheme.typography.h6.copy(color = AbandonedPetsTheme.colors.surfaceOppositeColor)
        )
        Text(
            modifier = Modifier.align(Alignment.CenterVertically),
            text = year,
            style = AbandonedPetsTheme.typography.body1.copy(color = AbandonedPetsTheme.colors.surfaceOppositeColor)
        )
    }
}

data class Month(
    val yearMonth: YearMonth,
    val weeks: List<Week>
)

