package com.majorbriggs.wearsample.presentation.components

import android.content.res.Configuration
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Android
import androidx.compose.material.icons.rounded.Message
import androidx.compose.material.icons.rounded.PhoneAndroid
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.wear.compose.material.AppCard
import androidx.wear.compose.material.Card
import androidx.wear.compose.material.Icon
import androidx.wear.compose.material.MaterialTheme
import androidx.wear.compose.material.Text
import androidx.wear.compose.material.TitleCard
import androidx.wear.tooling.preview.devices.WearDevices


@Composable
fun CardExample(
    modifier: Modifier = Modifier,
) {
    Card(
        modifier = modifier.height(80.dp),
        onClick = { /* ... */ }
    ) {
        Text("GDG3City")
    }
}


@Composable
fun TitleCardExample(
    modifier: Modifier = Modifier,
) {
    TitleCard(
        modifier = modifier,
        time = { Text("12m") },
        title = { Text("GDG3City") },
        onClick = { /* ... */ }
    ) {
        Text("Join us!")
    }
}


@Composable
fun AppCardExample(
    modifier: Modifier = Modifier,
    iconModifier: Modifier = Modifier,
) {
    AppCard(
        modifier = modifier,
        appImage = {
            Icon(
                imageVector = Icons.Rounded.Android,
                contentDescription = "triggers open message action",
                modifier = iconModifier
            )
        },
        appName = { Text("Messages") },
        time = { Text("12m") },
        title = { Text("GDG3City") },
        onClick = { /* ... */ }
    ) {
        Text("Join us!")
    }
}


@Preview(
    uiMode = Configuration.UI_MODE_TYPE_WATCH,
    device = WearDevices.LARGE_ROUND,
)
@Composable
fun CardExamplePreview() {
    MaterialTheme {
        CardExample(
            modifier = Modifier
                .fillMaxWidth()
        )
    }
}

@Preview(
    uiMode = Configuration.UI_MODE_TYPE_WATCH,
    device = WearDevices.LARGE_ROUND,
)
@Composable
fun AppCardExamplePreview() {
    MaterialTheme {
        AppCardExample(
            modifier = Modifier
                .fillMaxWidth(),
            iconModifier = Modifier
                .size(24.dp)
                .wrapContentSize(align = Alignment.Center)
        )
    }
}

@Preview(
    uiMode = Configuration.UI_MODE_TYPE_WATCH,
    device = WearDevices.LARGE_ROUND,
)
@Composable
fun TitleCardExamplePreview() {
    MaterialTheme {
        TitleCardExample(
            modifier = Modifier
                .fillMaxWidth()
        )
    }
}
