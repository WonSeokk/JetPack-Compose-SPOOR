package wwon.seokk.abandonedpets.ui.calendar

import androidx.lifecycle.SavedStateHandle
import dagger.hilt.android.lifecycle.HiltViewModel
import wwon.seokk.abandonedpets.data.remote.model.request.GetAbandonmentPublicRequest
import wwon.seokk.abandonedpets.ui.PetRequestArgs
import wwon.seokk.abandonedpets.ui.PetRequestArgs.PetRequest
import wwon.seokk.abandonedpets.ui.base.BaseViewModel
import wwon.seokk.abandonedpets.ui.home.HomeViewModel
import wwon.seokk.abandonedpets.util.toFormat
import java.time.LocalDate
import javax.inject.Inject

/**
 * Created by WonSeok on 2022.08.25
 **/
@HiltViewModel
class CalendarViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle
) : BaseViewModel<CalendarState, CalendarSideEffect>(savedStateHandle) {
    var requestQuery = GetAbandonmentPublicRequest.EMPTY
    override fun createInitialState(): CalendarState = CalendarState()

    override fun initData() {
        val currentState = uiState().value.calendarUiState.value
        requestQuery = savedStateHandle[PetRequest]?: GetAbandonmentPublicRequest.EMPTY
        requestQuery.run {
            uiState().value.calendarUiState.value = currentState.setDates(startDate.toFormat(), endDate.toFormat())
        }
    }

    fun setSelectedDay(homeViewModel: HomeViewModel, newDate: LocalDate) {
        val currentState = uiState().value.calendarUiState.value
        currentState.updateSelectedDay(newDate).let {
            uiState().value.calendarUiState.value = it
            requestQuery.run {
                startDate = it.selectedStartDate.toFormat()
                endDate = it.selectedEndDate.toFormat()
                homeViewModel.requestPets(this)
            }
        }
    }

    private fun CalendarUiState.updateSelectedDay(newDate: LocalDate): CalendarUiState {
        return if(selectedStartDate != selectedEndDate)
            resetSelectedDay(newDate)
        else {
            if(newDate.isBefore(selectedStartDate))
                resetSelectedDay(newDate)
            else
                endSelectedDay(newDate)
        }
    }

    private fun CalendarUiState.resetSelectedDay(newDate: LocalDate): CalendarUiState =
        copy(animateDirection = AnimationDirection.BACKWARDS)
            .setDates(newDate, null)

    private fun CalendarUiState.endSelectedDay(newDate: LocalDate): CalendarUiState =
        copy(animateDirection = AnimationDirection.FORWARDS)
            .setDates(selectedStartDate, newDate)

}