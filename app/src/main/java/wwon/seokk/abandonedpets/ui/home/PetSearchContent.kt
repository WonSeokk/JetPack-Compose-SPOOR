package wwon.seokk.abandonedpets.ui.home

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyGridScope
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material.*
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import wwon.seokk.abandonedpets.R
import wwon.seokk.abandonedpets.data.remote.model.request.GetAbandonmentPublicRequest
import wwon.seokk.abandonedpets.ui.base.ScreenState
import wwon.seokk.abandonedpets.ui.common.ArrowIcon
import wwon.seokk.abandonedpets.ui.theme.AbandonedPetsTheme
import wwon.seokk.abandonedpets.util.noticeDateFormatter

/**
 * Created by WonSeok on 2022.08.15
 **/
@Composable
fun PetSearchContent(
    widthSize: WindowWidthSizeClass,
    uiState: HomeState,
    openPetRegionSearch: (GetAbandonmentPublicRequest) -> Unit,
    openPetKindSearch: (GetAbandonmentPublicRequest) -> Unit,
    openCalendar: (GetAbandonmentPublicRequest) -> Unit
) {
    val columns = when (widthSize) {
        WindowWidthSizeClass.Compact -> 1
        WindowWidthSizeClass.Medium -> 2
        WindowWidthSizeClass.Expanded -> 4
        else -> 1
    }

    PetSearch(
        columns = columns,
        content = {
            item {
                SearchInput(R.string.common_region, uiState, openPetRegionSearch)
            }
            item {
                SearchInput(R.string.common_kinds, uiState, openPetKindSearch)
            }
            item {
                SearchInput(R.string.common_date, uiState, openCalendar)
            }
        }
    )
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
private fun SearchInput(
    @StringRes titleRes: Int,
    uiState: HomeState?,
    openScreen: (GetAbandonmentPublicRequest) -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(50.dp)
            .clip(AbandonedPetsTheme.shapes.mediumRoundCornerShape),
        backgroundColor = AbandonedPetsTheme.colors.surfaceColor,
        onClick = {
            openScreen.invoke(uiState!!.requestQuery.value)
        }
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ){
            Text(
                modifier = Modifier
                    .padding(24.dp, 0.dp, 0.dp, 0.dp)
                    .weight(0.5f),
                text = stringResource(id = titleRes),
                style = AbandonedPetsTheme.typography.title1.copy(
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp,
                    textAlign = TextAlign.Left
                )
            )
            Row(
                modifier = Modifier
                    .padding(0.dp, 0.dp, 5.dp, 0.dp)
                    .weight(0.5f),
                horizontalArrangement = Arrangement.End
            ){
                SearchTextFieldText(titleRes, uiState!!.requestQuery.value)
            }
            ArrowIcon()
        }
    }
}

@Composable
private fun SearchTextFieldText(@StringRes title: Int, request: GetAbandonmentPublicRequest) {
    Text(
        text = when(title) {
            R.string.common_region -> "${request.upr.orgNm} · ${request.org.orgNm} · ${request.shelter.careNm}"
            R.string.common_kinds -> "${request.upKind.knm} · ${request.kind.knm}"
            R.string.common_date -> noticeDateFormatter(request.startDate, request.endDate)
            else -> "" },
        style = AbandonedPetsTheme.typography.body1
    )
}

@Composable
private fun PetSearch(
    columns: Int,
    content: LazyGridScope.() -> Unit
) {
    LazyVerticalGrid(
        modifier = Modifier.padding(start = 24.dp, top = 0.dp, end = 24.dp, bottom = 12.dp),
        columns = GridCells.Fixed(columns),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        content = content
    )
}

@Preview(showBackground = true)
@Composable
private fun SearchInputPreview() {
    val testUiState = HomeState(ScreenState.Loading, mutableStateOf(GetAbandonmentPublicRequest.EMPTY), null, emptyList(),
        null)
    SearchInput(R.string.common_region, testUiState) { }
}