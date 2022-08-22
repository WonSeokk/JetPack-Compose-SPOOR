package wwon.seokk.abandonedpets.ui.region

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.flowWithLifecycle
import kotlinx.coroutines.launch
import wwon.seokk.abandonedpets.data.remote.model.request.GetAbandonmentPublicRequest
import wwon.seokk.abandonedpets.domain.entity.region.RegionResultEntity
import wwon.seokk.abandonedpets.domain.entity.shelter.ShelterResultEntity
import wwon.seokk.abandonedpets.ui.base.ScreenState
import wwon.seokk.abandonedpets.ui.common.DropDownTextField
import wwon.seokk.abandonedpets.ui.common.NoticeTitle
import wwon.seokk.abandonedpets.ui.common.ScreenLoading
import wwon.seokk.abandonedpets.ui.home.HomeViewModel
import wwon.seokk.abandonedpets.ui.theme.AbandonedPetsTheme

/**
 * Created by WonSeok on 2022.08.18
 **/

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun PetRegionScreen(
    parentViewModel: HomeViewModel,
    petRegionViewModel: PetRegionViewModel = hiltViewModel(),
    navigateBack: () -> Unit
) {
    fun onSelectRegion(query: RegionResultEntity, field: SheetField) {
        petRegionViewModel.selectRegion(query, field)
    }

    fun onSelectShelter(query: ShelterResultEntity) {
        petRegionViewModel.selectShelter(query)
    }

    val lifecycleOwner = LocalLifecycleOwner.current
    val stateFlow = petRegionViewModel.uiState()
    val stateLifecycleAware = remember(lifecycleOwner, stateFlow) {
        stateFlow.flowWithLifecycle(lifecycleOwner.lifecycle, Lifecycle.State.STARTED)
    }
    val state by stateLifecycleAware.collectAsState(initial = petRegionViewModel.createInitialState())

    val scaffoldState = rememberScaffoldState()
    val bottomState = rememberModalBottomSheetState(ModalBottomSheetValue.Hidden, skipHalfExpanded = true)
    ModalBottomSheetLayout(
        sheetState = bottomState,
        sheetShape =  AbandonedPetsTheme.shapes.bottomSheetShape,
        sheetBackgroundColor = Color.White,
        sheetContent = {
            BottomContent(
                uiState = state,
                bottomState = bottomState,
                onSelectRegion = { query, field -> onSelectRegion(query, field) },
                onSelectShelter = { query -> onSelectShelter(query) }
             )
        }
    ) {
        Scaffold(
            scaffoldState = scaffoldState,
            content = {
                MainContent(
                    uiState = state,
                    bottomState = bottomState,
                    parentViewModel = parentViewModel,
                    petRegionViewModel = petRegionViewModel,
                    navigateBack = navigateBack
                )
            }
        )
    }


}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun MainContent(
    uiState: PetRegionState?,
    bottomState: ModalBottomSheetState?,
    parentViewModel: HomeViewModel?,
    petRegionViewModel: PetRegionViewModel?,
    navigateBack: () -> Unit
) {
    Column(
        modifier = Modifier
            .padding(15.dp)
            .fillMaxSize()
    ) {
        NoticeTitle(contentText = "찾고 있는 지역이\n어디인가요?")
        DropDownTextField(
            labelText = "시/도",
            value = uiState?.selectedUprRegion?.value?.orgNm ?: "전체",
            bottomState = bottomState,
            field = SheetField.UprRegion,
            state = uiState,
            viewModel = petRegionViewModel
        )
        DropDownTextField(labelText = "시/군/구",
            value = uiState?.selectedOrgRegion?.value?.orgNm ?: "전체",
            bottomState = bottomState,
            field = SheetField.OrgRegion,
            state = uiState,
            viewModel = petRegionViewModel
        )
        DropDownTextField(
            labelText = "보호소",
            value = uiState?.selectedShelter?.value?.careNm ?: "전체",
            bottomState = bottomState,
            field = SheetField.Shelter,
            state = uiState,
            viewModel = petRegionViewModel
        )
        TextButton(
            onClick = {
                parentViewModel?.requestPets(petRegionViewModel!!.requestQuery)
                navigateBack.invoke()
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp),
            colors = ButtonDefaults.textButtonColors(
                contentColor = Color.White,
                backgroundColor = AbandonedPetsTheme.colors.primaryColor
        )) {
            Text(text = "확인")
        }
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun BottomContent(
    uiState: PetRegionState,
    bottomState: ModalBottomSheetState,
    onSelectRegion: (RegionResultEntity, SheetField) -> Unit,
    onSelectShelter: (ShelterResultEntity) -> Unit
) {
    Surface(
        modifier = Modifier
            .padding(15.dp)
            .fillMaxWidth()
            .fillMaxHeight(0.5f)
    ) {
        Column {
            NoticeTitle(contentText = "지역을 선택하세요")
            SelectorListing(uiState, bottomState, onSelectRegion, onSelectShelter)
        }
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun SelectorListing(
    uiState: PetRegionState,
    bottomState: ModalBottomSheetState,
    onSelectRegion: (RegionResultEntity, SheetField) -> Unit,
    onSelectShelter: (ShelterResultEntity) -> Unit
) {

    when (uiState.screenState) {
        is ScreenState.Loading -> {
            ScreenLoading()
        }
        is ScreenState.Error -> {

        }
        is ScreenState.Success -> {
            val listState = rememberLazyListState()
            LazyColumn(
                state = listState,
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
            ) {
                uiState.uprRegions?.let { regions ->
                    items(regions.regionEntities.size) { index ->
                        SheetItem(
                            uiState = uiState,
                            listState = listState,
                            bottomState = bottomState,
                            index = index,
                            regions = regions,
                            onSelectRegion = onSelectRegion,
                            filed = SheetField.UprRegion
                        )
                    }
                }
                uiState.orgRegions?.let { regions ->
                    items(regions.regionEntities.size) { index ->
                        SheetItem(
                            uiState = uiState,
                            listState = listState,
                            bottomState = bottomState,
                            index = index,
                            regions = regions,
                            onSelectRegion = onSelectRegion,
                            filed = SheetField.OrgRegion
                        )
                    }
                }

                uiState.shelters?.let { shelters ->
                    items(shelters.shelterEntities.size) { index ->
                        SheetItem(
                            uiState = uiState,
                            listState = listState,
                            bottomState = bottomState,
                            index = index,
                            shelters = shelters,
                            onSelectShelter = onSelectShelter
                        )
                    }
                }
            }
        }
    }
}


@OptIn(ExperimentalMaterialApi::class)
@Preview(showBackground = true)
@Composable
private fun MainContentPreView() {
    AbandonedPetsTheme {
        MainContent(null, null, null,null) { }
    }
}