package com.example.fleetsavingtimer.presentation.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.fleetsavingtimer.ui.theme.accent

@Composable
internal fun AppBarComponent(
    title: String? = null,
    onLeftAction: (() -> Unit)? = null,
    onRightAction: (() -> Unit)? = null,
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .statusBarsPadding()
            .padding(
                top = 16.dp,
                start = 16.dp,
                end = 16.dp
            )
    ) {
        onLeftAction?.also {
            IconButton(
                onClick = onLeftAction
            ) {
                Icon(
                    imageVector = Icons.Default.Info,
                    contentDescription = "",
                    tint = accent,
                    modifier = Modifier.size(48.dp)
                )
            }
        }

        Spacer(modifier = Modifier.weight(1f))

        title?.also {
            Text(
                text = title,
            )
        }

        Spacer(modifier = Modifier.weight(1f))

        onRightAction?.also {
            IconButton(
                onClick = onRightAction
            ) {
                Icon(
                    imageVector = Icons.Default.Settings,
                    contentDescription = "",
                    tint = accent,
                    modifier = Modifier.size(48.dp)
                )
            }
        }
    }
}