package wwon.seokk.abandonedpets.ui.common

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
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
                .padding(0.dp, 20.dp, 0.dp, 30.dp)
        )
        Row(modifier = Modifier.weight(0.5f)){}
    }
}

@Composable
fun DropDownTextField(
    labelText: String
) {
    TextField(
        value = "",
        onValueChange = { },
        label = { Text(text = labelText) },
        readOnly = true,
        colors = TextFieldDefaults.textFieldColors(
            backgroundColor = Color.Transparent,
            focusedIndicatorColor = Color.Red
        ),
        trailingIcon = { Icon(imageVector = Icons.Filled.ArrowDropDown, contentDescription = "") },
        modifier = Modifier
            .padding(0.dp, 0.dp, 0.dp, 20.dp)
            .fillMaxWidth()
    )
}