package wwon.seokk.abandonedpets.ui.common

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ModalBottomSheetState
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch
import wwon.seokk.abandonedpets.domain.entity.kind.KindEntity
import wwon.seokk.abandonedpets.domain.entity.kind.KindResultEntity
import wwon.seokk.abandonedpets.domain.entity.region.RegionEntity
import wwon.seokk.abandonedpets.domain.entity.region.RegionResultEntity
import wwon.seokk.abandonedpets.domain.entity.shelter.ShelterEntity
import wwon.seokk.abandonedpets.domain.entity.shelter.ShelterResultEntity
import wwon.seokk.abandonedpets.ui.kind.PetKindState
import wwon.seokk.abandonedpets.ui.region.PetRegionState
import wwon.seokk.abandonedpets.ui.theme.AbandonedPetsTheme


enum class SheetField {
    UprRegion, OrgRegion, Shelter, UpKind, Kind
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun SheetItem(
    uiState: PetRegionState,
    listState: LazyListState,
    bottomState: ModalBottomSheetState,
    index: Int,
    regions: RegionEntity,
    onSelectRegion: (RegionResultEntity, SheetField) -> Unit,
    filed: SheetField
) {
    val scope = rememberCoroutineScope()
    fun hideBottomSheet() {
        scope.launch {
            bottomState.hide()
        }
    }
    fun scrollToItem() {
        scope.launch {
            listState.animateScrollToItem(index)
        }
    }
    val region = regions.regionEntities[index]
    val orgCd =  if(filed == SheetField.UprRegion) uiState.selectedUprRegion.value.orgCd else uiState.selectedOrgRegion.value.orgCd
    val textColor = if(region.orgCd == orgCd) AbandonedPetsTheme.colors.primaryColor else Color.LightGray
    if(region.orgCd == orgCd) scrollToItem()

    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .height(48.dp)
            .clip(AbandonedPetsTheme.shapes.smallRoundCornerShape)
            .clickable(
                enabled = true,
                onClick = {
                    if(filed == SheetField.UprRegion)
                        uiState.selectedUprRegion.value = region
                    else if (filed == SheetField.OrgRegion)
                        uiState.selectedOrgRegion.value = region
                    onSelectRegion.invoke(region, filed)
                    hideBottomSheet()
                }),
    ) {
        Text(
            text = region.orgNm,
            style = AbandonedPetsTheme.typography.body3.copy(color = textColor)
        )
    }
}


@OptIn(ExperimentalMaterialApi::class)
@Composable
fun SheetItem(
    uiState: PetRegionState,
    listState: LazyListState,
    bottomState: ModalBottomSheetState,
    index: Int,
    shelters: ShelterEntity,
    onSelectShelter: (ShelterResultEntity) -> Unit
) {
    val scope = rememberCoroutineScope()
    fun hideBottomSheet() {
        scope.launch {
            bottomState.hide()
        }
    }
    fun scrollToItem() {
        scope.launch {
            listState.animateScrollToItem(index)
        }
    }
    val shelter = shelters.shelterEntities[index]
    val careNo = uiState.selectedShelter.value.careRegNo
    val textColor = if(shelter.careRegNo == careNo) AbandonedPetsTheme.colors.primaryColor else Color.LightGray
    if(shelter.careRegNo == careNo) scrollToItem()
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .height(48.dp)
            .clip(AbandonedPetsTheme.shapes.smallRoundCornerShape)
            .clickable(
                enabled = true,
                onClick = {
                    onSelectShelter.invoke(shelter)
                    uiState.selectedShelter.value = shelter
                    hideBottomSheet()
                }),
    ) {
        Text(
            text = shelter.careNm,
            style = AbandonedPetsTheme.typography.body3.copy(color = textColor)
        )
    }
}


@OptIn(ExperimentalMaterialApi::class)
@Composable
fun SheetItem(
    uiState: PetKindState,
    listState: LazyListState,
    bottomState: ModalBottomSheetState,
    index: Int,
    kinds: KindEntity,
    onSelectKind: (KindResultEntity, SheetField) -> Unit,
    filed: SheetField
) {
    val scope = rememberCoroutineScope()
    fun hideBottomSheet() {
        scope.launch {
            bottomState.hide()
        }
    }
    fun scrollToItem() {
        scope.launch {
            listState.animateScrollToItem(index)
        }
    }
    val kind = kinds.kindEntities[index]
    val kindCd =  if(filed == SheetField.UpKind) uiState.selectedUpKind.value.kindCd else uiState.selectedKind.value.kindCd
    val textColor = if(kind.kindCd == kindCd) AbandonedPetsTheme.colors.primaryColor else Color.LightGray
    if(kind.kindCd == kindCd) scrollToItem()

    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .height(48.dp)
            .clip(AbandonedPetsTheme.shapes.smallRoundCornerShape)
            .clickable(
                enabled = true,
                onClick = {
                    if(filed == SheetField.UpKind)
                        uiState.selectedUpKind.value = kind
                    else if (filed == SheetField.Kind)
                        uiState.selectedKind.value = kind
                    onSelectKind.invoke(kind, filed)
                    hideBottomSheet()
                }),
    ) {
        Text(
            text = kind.knm,
            style = AbandonedPetsTheme.typography.body3.copy(color = textColor)
        )
    }
}