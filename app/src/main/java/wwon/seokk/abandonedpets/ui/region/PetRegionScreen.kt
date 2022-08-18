package wwon.seokk.abandonedpets.ui.region

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.flowWithLifecycle
import wwon.seokk.abandonedpets.ui.base.ScreenState
import wwon.seokk.abandonedpets.ui.common.DropDownTextField
import wwon.seokk.abandonedpets.ui.common.NoticeTitle
import wwon.seokk.abandonedpets.ui.theme.AbandonedPetsTheme

/**
 * Created by WonSeok on 2022.08.18
 **/

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun PetRegionScreen(
    petRegionViewModel: PetRegionViewModel = hiltViewModel(),
    navigateBack: () -> Unit
) {
    val scaffoldState = rememberScaffoldState()
    val bottomState = rememberModalBottomSheetState(ModalBottomSheetValue.HalfExpanded)
    ModalBottomSheetLayout(
        sheetState = bottomState,
        sheetContent = {
            BottomContent(petRegionViewModel = petRegionViewModel)
        }
    ) {
        Scaffold(
            scaffoldState = scaffoldState,
            content = {
                MainContent()
            }
        )
    }
}

@Composable
fun MainContent() {
    Column(
        modifier = Modifier
            .padding(15.dp)
            .fillMaxWidth()
            .fillMaxHeight()
    ) {
        NoticeTitle(contentText = "찾고 있는 지역이\n어디인가요?")
        DropDownTextField(labelText = "시/도")
        DropDownTextField(labelText = "시/군/구")
        DropDownTextField(labelText = "보호소")
        TextButton(onClick = { /*TODO*/ },
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

@Composable
fun BottomContent(petRegionViewModel: PetRegionViewModel) {
    Surface(modifier = Modifier.fillMaxSize(), color = Color.White, shape = AbandonedPetsTheme.shapes.bottomSheetShape) {
        Column {
            NoticeTitle(contentText = "지역을 선택하세요")
            SelectorListing(petRegionViewModel)
        }
    }
}

@Composable
fun SelectorListing(petRegionViewModel: PetRegionViewModel) {
    val lifecycleOwner = LocalLifecycleOwner.current
    val stateFlow = petRegionViewModel.uiState()
    val stateLifecycleAware = remember(lifecycleOwner, stateFlow) {
        stateFlow.flowWithLifecycle(lifecycleOwner.lifecycle, Lifecycle.State.STARTED)
    }
    val state by stateLifecycleAware.collectAsState(initial = petRegionViewModel.createInitialState())

    when (state.screenState) {
        is ScreenState.Loading -> {
            //do nothing
        }
        is ScreenState.Error -> {

        }
        is ScreenState.Success -> {
            state.regions?.let { regions ->
                LazyColumn {
                    items(regions.regionEntities.size) { index ->
                        Text(text = regions.regionEntities[index].orgNm)
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun MainContentPreView() {
    AbandonedPetsTheme {
        MainContent()
    }
}
