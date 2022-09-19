package wwon.seokk.abandonedpets.ui.common

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch
import wwon.seokk.abandonedpets.ui.kind.PetKindState
import wwon.seokk.abandonedpets.ui.kind.PetKindViewModel
import wwon.seokk.abandonedpets.ui.region.PetRegionState
import wwon.seokk.abandonedpets.ui.region.PetRegionViewModel
import wwon.seokk.abandonedpets.ui.theme.AbandonedPetsTheme

/**
 * Created by WonSeok on 2022.08.18
 **/
@Composable
fun NoticeTitle(contentText: String) {
    Row{
        Text(text = contentText,
            style = AbandonedPetsTheme.typography.title1,
            modifier = Modifier
                .weight(0.5f)
                .padding(0.dp, 0.dp, 0.dp, 30.dp)
        )
        Row(modifier = Modifier.weight(0.5f)){}
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun DropDownTextField(
    labelText: String,
    value: String,
    bottomState: ModalBottomSheetState?,
    field: SheetField,
    state: Any?,
    viewModel: Any?
) {
    val scope = rememberCoroutineScope()
    fun openBottomSheet() {
        scope.launch { bottomState?.show() }
    }

    TextField(
        value = value,
        onValueChange = { },
        label = { Text(text = labelText) },
        readOnly = true,
        colors = TextFieldDefaults.textFieldColors(
            backgroundColor = Color.Transparent,
            disabledLabelColor = AbandonedPetsTheme.colors.primaryColor,
            disabledIndicatorColor = AbandonedPetsTheme.colors.primaryColor,
            disabledTextColor = Color.Black
        ),
        trailingIcon = { Icon(imageVector = Icons.Filled.ArrowDropDown, contentDescription = "") },
        modifier = Modifier
            .padding(0.dp, 0.dp, 0.dp, 20.dp)
            .fillMaxWidth()
            .clickable(
                onClick = {
                    when(field) {
                        SheetField.UprRegion -> {
                            (viewModel as PetRegionViewModel).getSido()
                            openBottomSheet()
                        }
                        SheetField.OrgRegion -> {
                            (viewModel as PetRegionViewModel).getSigungu()
                            if((state as PetRegionState).selectedUprRegion.value.orgCd.isNotBlank())
                                openBottomSheet()
                        }
                        SheetField.Shelter -> {
                            (viewModel as PetRegionViewModel).getShelter()
                            if((state as PetRegionState).selectedOrgRegion.value.orgCd.isNotBlank())
                                openBottomSheet()
                        }
                        SheetField.UpKind -> {
                            (viewModel as PetKindViewModel).getUpKind()
                            openBottomSheet()
                        }
                        SheetField.Kind -> {
                            (viewModel as PetKindViewModel).getKind()
                            if((state as PetKindState).selectedUpKind.value.kindCd.isNotBlank())
                                openBottomSheet()
                        }
                    }
                }),
        enabled = false
    )
}