package com.example.fleetsavingtimer.presentation.components

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.fleetsavingtimer.ui.theme.accent
import com.example.fleetsavingtimer.ui.theme.primary

@Composable
fun ButtonComponent(buttonText: String, onClick: () -> Unit) {
    Button(
        shape = RoundedCornerShape(16.dp),
        colors = ButtonDefaults.buttonColors(
            contentColor = primary,
            containerColor = accent
        ),
        onClick = onClick

    ) {
        Text(
            text = buttonText,
            modifier = Modifier.padding(
                start = 32.dp,
                end = 32.dp
            )
        )
    }
}