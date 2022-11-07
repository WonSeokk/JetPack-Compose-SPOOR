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

/**
 * Created by WonSeok on 2022.09.16
 **/
@Composable
fun PetNoticeSurface(pet: AbandonmentPublicResultEntity) {
    val remainDay = calculateNoticeDate(pet.noticeEdt).toInt()
    Surface(
        shape = AbandonedPetsTheme.shapes.circleRoundCornerShape,
        color = if(pet.processState == stringResource(id = R.string.common_caring))
            when {
                remainDay <= 7 -> AbandonedPetsTheme.colors.orangeColor
                else -> AbandonedPetsTheme.colors.primaryColor
            }
        else
            AbandonedPetsTheme.colors.redColor
    ) {
        Row{
            Text(
                text = if(pet.processState == stringResource(id = R.string.common_caring)) {
                    when (remainDay) {
                        0 -> stringResource(id = R.string.format_end_today, remainDay)
                        else -> stringResource(id = R.string.format_end_notice, remainDay)
                    }
                }
                else
                    stringResource(id = R.string.format_end_state, endStateText(pet.processState)),
                style = AbandonedPetsTheme.typography.body2.copy(color = Color.White),
                modifier = Modifier.padding(10.dp, 5.dp, 10.dp, 5.dp)
            )
        }
    }
}