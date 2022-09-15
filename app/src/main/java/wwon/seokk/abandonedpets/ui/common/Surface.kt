package wwon.seokk.abandonedpets.ui.common

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import wwon.seokk.abandonedpets.R
import wwon.seokk.abandonedpets.domain.entity.abandonmentpublic.AbandonmentPublicResultEntity
import wwon.seokk.abandonedpets.ui.theme.AbandonedPetsTheme
import wwon.seokk.abandonedpets.util.calculateNoticeDate
import wwon.seokk.abandonedpets.util.endStateText

@Composable
fun PetNoticeSurface(pet: AbandonmentPublicResultEntity) {
    Surface(
        shape = AbandonedPetsTheme.shapes.circleRoundCornerShape,
        color = if(pet.processState == "보호중")
            AbandonedPetsTheme.colors.primaryColor
        else
            AbandonedPetsTheme.colors.redColor
    ) {
        Row{
            Text(
                text = if(pet.processState == "보호중")
                    stringResource(id = R.string.end_notice_format, calculateNoticeDate(pet.noticeEdt))
                else
                    stringResource(id = R.string.end_state_format, endStateText(pet.processState)),
                style = AbandonedPetsTheme.typography.body2.copy(color = Color.White),
                modifier = Modifier.padding(10.dp, 5.dp, 10.dp, 5.dp)
            )
        }
    }
}