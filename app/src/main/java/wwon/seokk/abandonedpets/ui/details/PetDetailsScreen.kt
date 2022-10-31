package wwon.seokk.abandonedpets.ui.details


import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.*
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.flowWithLifecycle
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import me.onebone.toolbar.*
import wwon.seokk.abandonedpets.R
import wwon.seokk.abandonedpets.domain.entity.abandonmentpublic.AbandonmentPublicResultEntity
import wwon.seokk.abandonedpets.ui.common.*
import wwon.seokk.abandonedpets.ui.theme.AbandonedPetsTheme
import wwon.seokk.abandonedpets.util.calculateAge
import wwon.seokk.abandonedpets.util.noticeDateFormatter
import wwon.seokk.abandonedpets.util.setStatusBar
import wwon.seokk.abandonedpets.util.toFormat
import java.time.format.DateTimeFormatter

/**
 * Created by WonSeok on 2022.08.31
 **/
@Composable
fun PetDetailsScreen(
    petDetailsViewModel: PetDetailsViewModel = hiltViewModel(),
    openImage: (String) -> Unit,
    navigateBack: () -> Unit
) {
    val systemUiController = rememberSystemUiController()
    val state = rememberCollapsingToolbarScaffoldState()
    val isScroll = state.toolbarState.minHeight + 250 >= state.toolbarState.height

    val lifecycleOwner = LocalLifecycleOwner.current
    val scaffoldState = rememberScaffoldState()
    val stateFlow = petDetailsViewModel.uiState()
    val stateLifecycleAware = remember(lifecycleOwner, stateFlow) {
        stateFlow.flowWithLifecycle(lifecycleOwner.lifecycle, Lifecycle.State.STARTED)
    }
    val uiState by stateLifecycleAware.collectAsState(initial = petDetailsViewModel.createInitialState())
    val pet = uiState.petDetail.value

    val featurePrepareMsg = stringResource(id = R.string.common_prepare_message)
    val action = stringResource(id = R.string.common_confirm)

    LaunchedEffect(petDetailsViewModel.uiSideEffect()) {
        val messageHost = SnackBarView(this)
        petDetailsViewModel.uiSideEffect().collect { uiSideEffect ->
            when (uiSideEffect) {
                is PetDetailSideEffect.ShowSnackBar -> {
                    messageHost.showSnackBar(
                        snackBarHostState = scaffoldState.snackbarHostState,
                        message = uiSideEffect.message
                    )
                }
            }
        }
    }

    Scaffold(scaffoldState = scaffoldState) {
        Box {
            systemUiController.setStatusBar(false)
            CollapsingToolbarScaffold(
                modifier = Modifier.fillMaxSize(),
                state = state,
                toolbar = {
                    AsyncImage(model = ImageRequest.Builder(LocalContext.current)
                        .data(pet.popfile)
                        .crossfade(true)
                        .build(),
                        contentScale = ContentScale.Crop,
                        placeholder = painterResource(R.drawable.ic_spoor),
                        contentDescription = stringResource(id = R.string.pet_image_description),
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(400.dp)
                            .road(Alignment.BottomCenter, Alignment.TopCenter)
                            .clickable { openImage.invoke(pet.popfile) }
                    )
                    val test = if(isScroll) 80.dp else 0.dp
                    Spacer(
                        modifier = Modifier
                            .height(test)
                            .fillMaxWidth()
                            .background(AbandonedPetsTheme.colors.primaryColor)
                    )
                    Row(modifier = Modifier.statusBarsPadding()){
                        BackButton { navigateBack() }
                        Spacer(modifier = Modifier.weight(1f))
                        ShareButton {
                            petDetailsViewModel.handleSnackBar(featurePrepareMsg, action)
                        }
                    }
                },
                scrollStrategy = ScrollStrategy.ExitUntilCollapsed
            ) {
                Body(pet)
            }
            BottomBar(modifier = Modifier
                .align(Alignment.BottomCenter)
                .background(AbandonedPetsTheme.colors.surfaceColor),
                pet = pet
            ) {
                petDetailsViewModel.handleSnackBar(featurePrepareMsg, action)
            }
        }
    }
}

@Composable
private fun Body(pet: AbandonmentPublicResultEntity) {
    val scroll = rememberScrollState(0)
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp)
            .verticalScroll(scroll),
    ){
        PetDetailTitle(pet)
        BottomDivider()
        PetDetailContent(pet)
    }
}

@Composable
private fun PetDetailTitle(pet: AbandonmentPublicResultEntity) {
    PetNoticeSurface(pet)
    Spacer(Modifier.height(5.dp))
    DetailTitleText(pet.noticeNo)
    DetailBodyText(stringResource(id = R.string.detail_screen_notice_date) ,noticeDateFormatter(pet.noticeSdt, pet.noticeEdt))
}

@Composable
private fun PetDetailContent(pet: AbandonmentPublicResultEntity) {
    Spacer(Modifier.height(15.dp))
    DetailTitleText(stringResource(id = R.string.detail_screen_pet_feature))
    Spacer(Modifier.height(10.dp))
    DetailBodyText(stringResource(id = R.string.common_kinds), pet.kindCd)
    DetailBodyText(stringResource(id = R.string.detail_screen_color) ,pet.colorCd)
    DetailBodyText(stringResource(id = R.string.detail_screen_sex),
        when(pet.sexCd) {
            "M" -> stringResource(id = R.string.common_male)
            "F" -> stringResource(id = R.string.common_female)
            else -> stringResource(id = R.string.common_unknown)
        })
    DetailBodyText(stringResource(id = R.string.detail_screen_age), calculateAge(pet.age.replace(stringResource(id = R.string.common_age_year), "")))
    DetailBodyText(stringResource(id = R.string.detail_screen_weight), pet.weight)
    DetailBodyText(stringResource(id = R.string.detail_screen_neuter), when(pet.neuterYn) {
        "N" -> "X"
        "Y" -> "O"
        else -> stringResource(id = R.string.common_unknown)
    })
    DetailBodyText(stringResource(id = R.string.detail_screen_feature), pet.specialMark)
    DetailBodyText(stringResource(id = R.string.detail_screen_happen_date), pet.happenDt.toFormat().toFormat(DateTimeFormatter.ISO_LOCAL_DATE))
    DetailBodyText(stringResource(id = R.string.detail_screen_happen_place), pet.happenPlace)
    Spacer(Modifier.height(15.dp))
    DetailTitleText(stringResource(id = R.string.detail_screen_shelter))
    Spacer(Modifier.height(10.dp))
    DetailBodyText(stringResource(id = R.string.detail_screen_shelter_name), pet.careNm)
    DetailBodyText(stringResource(id = R.string.detail_screen_tel), pet.careTel)
    DetailBodyText(stringResource(id = R.string.detail_screen_address), pet.careAddr)
}

@Composable
private fun DetailTitleText(text: String) {
    Text(
        text = text,
        style = AbandonedPetsTheme.typography.title1.copy(
            fontWeight = FontWeight.Bold,
            fontSize = 16.sp
        )
    )
    Spacer(Modifier.height(5.dp))
}

@Composable
private fun DetailBodyText(title: String, content: String) {
    Row {
        Text(
            modifier = Modifier.weight(0.25f),
            text = title,
            style = AbandonedPetsTheme.typography.body1.copy(
                fontSize = 13.sp
            )
        )
        Text(
            modifier = Modifier.weight(1f),
            text = content,
            style = AbandonedPetsTheme.typography.body1.copy(
                fontSize = 14.sp
            )
        )
    }
    Spacer(Modifier.height(5.dp))
}

@Composable
private fun BottomBar(modifier: Modifier = Modifier, pet: AbandonmentPublicResultEntity, favoriteClick: () -> Unit) {
    val context = LocalContext.current
    Box(modifier = modifier) {
        BottomDivider()
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .padding(vertical = 10.dp)
                .height(56.dp)
        ) {
            FavoriteButton(isLiked = false, modifier = Modifier.padding(5.dp)) {
                favoriteClick.invoke()
            }
            TextButton(
                onClick = {
                    context.startActivity(Intent(Intent.ACTION_DIAL, Uri.parse("tel:${pet.careTel.replace("","-")}")))
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp)
                    .weight(1f),
                colors = ButtonDefaults.textButtonColors(
                    contentColor = Color.White,
                    backgroundColor = AbandonedPetsTheme.colors.primaryColor
                )) {
                Text(text = stringResource(id = R.string.detail_screen_tel_shelter))
            }
            Spacer(Modifier.width(30.dp))
        }
    }
}


@Preview(showBackground = true)
@Composable
private fun PetDetailsPreView() {
    AbandonedPetsTheme {
//        PetDetailsScreen {}
        Body(AbandonmentPublicResultEntity.EMPTY)
    }
}
