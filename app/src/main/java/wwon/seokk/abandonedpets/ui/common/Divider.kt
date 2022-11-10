package wwon.seokk.abandonedpets.ui.common

import androidx.compose.foundation.layout.padding
import androidx.compose.material.Divider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import wwon.seokk.abandonedpets.ui.theme.AbandonedPetsTheme

/**
 * Created by WonSeok on 2022.09.15
 **/
@Composable
fun PetListDivider() {
    Divider(
        modifier = Modifier.padding(horizontal = 14.dp),
        color = AbandonedPetsTheme.colors.iconColor.copy(alpha = 0.2f)
    )
}

@Composable
fun BottomDivider() {
    Divider(
        color = AbandonedPetsTheme.colors.iconColor.copy(alpha = 0.2f),
        thickness =  1.dp,
        startIndent = 0.dp
    )
}