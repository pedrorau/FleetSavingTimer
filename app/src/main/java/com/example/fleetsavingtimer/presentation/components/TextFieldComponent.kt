package com.example.fleetsavingtimer.presentation.components

import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import com.example.fleetsavingtimer.ui.theme.accent
import com.example.fleetsavingtimer.ui.theme.secondary

@Composable
internal fun TextFieldComponent(
    value: String,
    label: String,
    onValueChange: (String) -> Unit
) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        label = { Text(label) },
        textStyle = TextStyle(textAlign = TextAlign.Center),
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
        //keyboardActions = KeyboardActions(onPaste = {}),
        modifier = Modifier.pointerInput(Unit) {
            detectTapGestures(
                onLongPress = { /* No actions on paste */ }
            )
        },
        singleLine = true,
        colors = OutlinedTextFieldDefaults.colors(
            focusedContainerColor = secondary,
            unfocusedContainerColor = secondary,
            focusedTextColor = accent,
            disabledTextColor = accent,
            focusedBorderColor = accent,
            unfocusedBorderColor = accent
        ),
    )
}