package wwon.seokk.abandonedpets.ui.home

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import wwon.seokk.abandonedpets.R
import wwon.seokk.abandonedpets.domain.entity.abandonmentpublic.AbandonmentPublicResultEntity
import wwon.seokk.abandonedpets.ui.theme.AbandonedPetsTheme
import wwon.seokk.abandonedpets.util.calculateAge
import wwon.seokk.abandonedpets.util.calculateNoticeDate
import wwon.seokk.abandonedpets.util.endStateText
import wwon.seokk.abandonedpets.util.noticeDateFormatter

/**
 * Created by WonSeok on 2022.08.15
 **/
@Composable
fun PetCard(
    pet: AbandonmentPublicResultEntity,
    petClick: (AbandonmentPublicResultEntity) -> Unit
) {
    Card(
        elevation = 0.dp,
        modifier = Modifier
            .padding(10.dp)
            .fillMaxWidth()
            .height(145.dp)
            .clickable(
                enabled = true,
                onClick = {
                    petClick(pet)
                })
    ) {
        Row {
            PetImage(pet)
            Column(
                modifier = Modifier
                    .weight(1f)
                    .padding(20.dp, 5.dp, 5.dp, 5.dp)
            ) {
                PetNotice(pet)
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(top = 10.dp),
                    verticalArrangement = Arrangement.Top,
                    horizontalAlignment = Alignment.Start
                ) {
                    PetInfo(pet)
                    PetInfo2(pet)
                    PetShelter(pet)
                    PetPlace(pet)
                }
            }
        }
    }
}

@Composable
private fun PetNotice(pet: AbandonmentPublicResultEntity) {
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
                style = AbandonedPetsTheme.typography.body2,
                modifier = Modifier.padding(10.dp, 5.dp, 10.dp, 5.dp),
                color = Color.White
            )
        }
    }
}


@Composable
private fun PetInfo(pet: AbandonmentPublicResultEntity) {
    val petKind = if(pet.kindCd.isNotBlank()) "${pet.kindCd} · " else ""
    Text(text = "$petKind${pet.colorCd}",
        style = AbandonedPetsTheme.typography.body1
    )
}

@Composable
private fun PetInfo2(pet: AbandonmentPublicResultEntity) {
    val petSex = if(pet.sexCd.isNotBlank()) {
        when(pet.sexCd) {
            "M" -> "남"
            "F" -> "여"
            else -> "모름"
        }.plus(" · ")
    } else ""
    val birthYear = pet.age.replace("(년생)", "")
    val petAge = calculateAge(birthYear)
    Text(text = stringResource(id = R.string.sex_age_format, petSex, petAge, birthYear),
        style = AbandonedPetsTheme.typography.body1,
        modifier = Modifier.padding(bottom = 3.dp)
    )
}

@Composable
private fun PetShelter(pet: AbandonmentPublicResultEntity) {
    Text(text = pet.careNm,
        style = AbandonedPetsTheme.typography.body2,
        color = Color.Gray
    )
}

@Composable
private fun PetPlace(pet: AbandonmentPublicResultEntity) {
    Text(text = pet.happenPlace,
        fontWeight = FontWeight.Bold,
        style = AbandonedPetsTheme.typography.body1
    )
}

@Composable
private fun PetImage(pet: AbandonmentPublicResultEntity) {
    Card(
        modifier = Modifier
            .padding(5.dp)
            .width(135.dp)
            .height(135.dp)
            .clip(AbandonedPetsTheme.shapes.largeRoundCornerShape)
    ) {
        AsyncImage(model = ImageRequest.Builder(LocalContext.current)
            .data(pet.popfile)
            .crossfade(true)
            .build(),
            contentScale = ContentScale.FillBounds,
            placeholder = painterResource(R.drawable.ic_pets),
            contentDescription = stringResource(id = R.string.pet_image_description),
        )
    }
}


@Preview(showBackground = true)
@Composable
private fun PetCardPreview() {
    AbandonedPetsTheme{
        PetCard(
            AbandonmentPublicResultEntity("","","","",
                "","","","","","20220809","20220905","","보호중",
                "","","","","","","","",""),
            petClick ={ }
        )
    }
}