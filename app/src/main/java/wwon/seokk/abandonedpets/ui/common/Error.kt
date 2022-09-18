package wwon.seokk.abandonedpets.ui.common

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import wwon.seokk.abandonedpets.R
import wwon.seokk.abandonedpets.ui.theme.AbandonedPetsTheme

/**
 * Created by WonSeok on 2022.09.16
 **/
@Composable
fun GetPetsError(buttonClick: () -> Unit) {
    Column(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            modifier = Modifier.padding(8.dp),
            text = stringResource(id = R.string.home_screen_error_message),
            style = AbandonedPetsTheme.typography.body1.copy(
                color = AbandonedPetsTheme.colors.surfaceOppositeColor,
                textAlign = TextAlign.Center
            )
        )

        TextButton(
            colors = ButtonDefaults.textButtonColors(
                contentColor = AbandonedPetsTheme.colors.surfaceColor,
                backgroundColor = AbandonedPetsTheme.colors.primaryColor,
            ), onClick = buttonClick
        ){
            Text(text = stringResource(id = R.string.home_screen_retry))
        }

    }
}

@Preview(showBackground = true)
@Composable
fun ErrorItemPreview() {
    GetPetsError { }
}