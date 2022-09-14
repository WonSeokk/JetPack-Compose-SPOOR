package wwon.seokk.abandonedpets.ui.details

import android.util.Log
import android.view.Window
import android.view.WindowManager
import androidx.annotation.FloatRange
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Share
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.Layout
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.*
import androidx.compose.ui.zIndex
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.flowWithLifecycle
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import kotlinx.coroutines.launch
import me.onebone.toolbar.*
import wwon.seokk.abandonedpets.R
import wwon.seokk.abandonedpets.ui.theme.AbandonedPetsTheme
import kotlin.math.max
import kotlin.math.min
import kotlin.math.roundToInt

/**
 * Created by WonSeok on 2022.08.31
 **/
@Composable
fun PetDetailsScreen(
    petDetailsViewModel: PetDetailsViewModel = hiltViewModel(),
    navigateBack: () -> Unit
) {
    val systemUiController = rememberSystemUiController()
    val state = rememberCollapsingToolbarScaffoldState()
    val isScroll = state.toolbarState.minHeight + 250 >= state.toolbarState.height

    val lifecycleOwner = LocalLifecycleOwner.current
    val stateFlow = petDetailsViewModel.uiState()
    val stateLifecycleAware = remember(lifecycleOwner, stateFlow) {
        stateFlow.flowWithLifecycle(lifecycleOwner.lifecycle, Lifecycle.State.STARTED)
    }
    val uiState by stateLifecycleAware.collectAsState(initial = petDetailsViewModel.createInitialState())
    val petDetail = uiState.petDetail.value

    Box {
        systemUiController.setStatusBarColor(
            darkIcons = false,
            color = Color.Transparent
        )
        CollapsingToolbarScaffold(
            modifier = Modifier.fillMaxSize(),
            state = state,
            toolbar = {
                AsyncImage(model = ImageRequest.Builder(LocalContext.current)
                    .data(petDetail.popfile)
                    .crossfade(true)
                    .build(),
                    contentScale = ContentScale.Crop,
                    placeholder = painterResource(R.drawable.ic_pets),
                    contentDescription = stringResource(id = R.string.pet_image_description),
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(350.dp)
                        .road(Alignment.BottomCenter, Alignment.TopCenter)
                )
                val test = if(isScroll) 80.dp else 0.dp
                Spacer(
                    modifier = Modifier
                        .height(test)
                        .fillMaxWidth()
                        .background(AbandonedPetsTheme.colors.primaryColor)
                )
                Row(modifier = Modifier.statusBarsPadding()){
                    Icon(
                        modifier = Modifier
                            .wrapContentSize()
//                        .clickable { onBack() }
                            .padding(16.dp),
                        imageVector = Icons.Filled.ArrowBack,
                        tint = MaterialTheme.colors.onPrimary,
                        contentDescription = ""
                    )
                    Spacer(modifier = Modifier.weight(1f))
                    Icon(
                        modifier = Modifier
                            .wrapContentSize()
                            .clickable { }
                            .padding(16.dp),
                        imageVector = Icons.Filled.Share,
                        tint = MaterialTheme.colors.onPrimary,
                        contentDescription = ""
                    )
                }

            },
            scrollStrategy = ScrollStrategy.ExitUntilCollapsed
        ) {
            Body(state.toolbarState)
        }
        BottomBar(modifier = Modifier.align(Alignment.BottomCenter))
    }
}

@Composable
private fun Body(
    state: CollapsingToolbarState
) {
    val maxHeight = remember { state.maxHeight }
    val scope = rememberCoroutineScope()
    val scroll = rememberScrollState(0)
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(scroll)
    ){
//        Text(text = scroll.isScrollInProgress.toString())
        Text(text = state.height.toString())
        Text(text = state.minHeight.toString())
        Text(text = state.maxHeight.toString())
    }
}

@Composable
private fun BottomBar(modifier: Modifier = Modifier) {
    Box(modifier = modifier) {
        BottomDivider()
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .navigationBarsPadding()
                .padding(horizontal = 24.dp)
                .height(56.dp)
        ) {
            TextButton(
                onClick = { },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp),
                colors = ButtonDefaults.textButtonColors(
                    contentColor = Color.White,
                    backgroundColor = AbandonedPetsTheme.colors.primaryColor
                )) {
                Text(text = "보호소 연락하기")
            }
        }
    }
}

@Composable
fun BottomDivider(
    modifier: Modifier = Modifier,
    color: Color = MaterialTheme.colors.onSurface.copy(alpha = 0.12f),
    thickness: Dp = 1.dp,
    startIndent: Dp = 0.dp
) {
    Divider(
        modifier = modifier,
        color = color,
        thickness = thickness,
        startIndent = startIndent
    )
}

@Preview(showBackground = true)
@Composable
private fun PetDetailsPreView() {
    AbandonedPetsTheme {
        PetDetailsScreen {}
    }
}
