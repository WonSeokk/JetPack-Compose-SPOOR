package wwon.seokk.abandonedpets.ui.calendar

import androidx.compose.foundation.layout.*
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.flowWithLifecycle
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import wwon.seokk.abandonedpets.ui.common.NavigateUpAppBar
import wwon.seokk.abandonedpets.ui.home.HomeViewModel
import wwon.seokk.abandonedpets.ui.theme.AbandonedPetsTheme
import java.time.LocalDate

/**
 * Created by WonSeok on 2022.08.25
 **/
@Composable
fun CalendarScreen(
    parentViewModel: HomeViewModel,
    calendarViewModel: CalendarViewModel = hiltViewModel(),
    navigateBack: () -> Unit
) {
    val systemUiController = rememberSystemUiController()
    systemUiController.setSystemBarsColor(AbandonedPetsTheme.colors.surfaceColor)
    
    val lifecycleOwner = LocalLifecycleOwner.current
    val stateFlow = calendarViewModel.uiState()
    val stateLifecycleAware = remember(lifecycleOwner, stateFlow) {
        stateFlow.flowWithLifecycle(lifecycleOwner.lifecycle, Lifecycle.State.STARTED)
    }
    val state by stateLifecycleAware.collectAsState(initial = calendarViewModel.createInitialState())

    CalendarContent(
        calendarState = state,
        onDayClicked = { dateClicked ->
            calendarViewModel.setSelectedDay(parentViewModel, dateClicked)
        },
        navigateBack = navigateBack
    )
}


@Composable
private fun CalendarContent(
    calendarState: CalendarState,
    onDayClicked: (LocalDate) -> Unit,
    navigateBack: () -> Unit
) {
    Scaffold(
        modifier = Modifier
            .statusBarsPadding()
            .windowInsetsPadding(WindowInsets.navigationBars.only(WindowInsetsSides.Start + WindowInsetsSides.End)),
        backgroundColor = AbandonedPetsTheme.colors.surfaceColor,
        topBar = {
            NavigateUpAppBar(navigateBack = navigateBack)
        },
    ) { contentPadding ->
        Calendar(
            calendarState = calendarState,
            onDayClicked = onDayClicked,
            contentPadding = contentPadding
        )
    }
}