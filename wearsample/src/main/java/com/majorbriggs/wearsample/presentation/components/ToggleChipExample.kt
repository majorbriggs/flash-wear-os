package com.majorbriggs.wearsample.presentation.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.wear.compose.material.Switch
import androidx.wear.compose.material.Text
import androidx.wear.compose.material.ToggleChip
import androidx.wear.tooling.preview.devices.WearDevices

@Composable
fun ToggleChipExample(modifier: Modifier = Modifier) {
    var checked by remember { mutableStateOf(true) }
    ToggleChip(
        modifier = modifier,
        checked = checked,
        toggleControl = {
            Switch(
                checked = checked,
            )
        },
        onCheckedChange = {
            checked = it
        },
        label = {
            Text(
                text = "Toggle Chip",
                maxLines = 1,
            )
        }
    )
}

@Preview(
    device = WearDevices.LARGE_ROUND,
)
@Composable
fun ToggleChipExamplePreview() {
    ToggleChipExample(
        Modifier.fillMaxWidth()
    )
}
