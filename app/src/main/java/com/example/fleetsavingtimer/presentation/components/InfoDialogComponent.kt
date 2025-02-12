package com.example.fleetsavingtimer.presentation.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.BasicAlertDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.material3.Text
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.fleetsavingtimer.presentation.utils.DIALOG_BUTTON_TEXT
import com.example.fleetsavingtimer.presentation.utils.DIALOG_CONTENT
import com.example.fleetsavingtimer.presentation.utils.DIALOG_TITLE
import com.example.fleetsavingtimer.ui.theme.FleetSavingTimerTheme
import com.example.fleetsavingtimer.ui.theme.accent
import com.example.fleetsavingtimer.ui.theme.primary

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun InfoDialogComponent(
    showDialog: Boolean,
    onDismiss: () -> Unit
) {
    if (showDialog) {
        BasicAlertDialog(
            onDismissRequest = onDismiss,
        ) {
            Surface(
                shape = RoundedCornerShape(16.dp),
                border = BorderStroke(2.dp, accent),
                color = primary,
                tonalElevation = 4.dp,
                modifier = Modifier.padding(16.dp)
            ) {
                Column(
                    modifier = Modifier.padding(32.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = DIALOG_TITLE,
                        fontWeight = FontWeight.Bold,
                        fontSize = 20.sp
                    )

                    Spacer(modifier = Modifier.height(32.dp))

                    Text(
                        text = DIALOG_CONTENT,
                        style = TextStyle(
                            textAlign = TextAlign.Justify
                        ),
                        modifier = Modifier.fillMaxWidth(),
                        fontSize = 18.sp
                    )

                    Spacer(modifier = Modifier.height(32.dp))

                    ButtonComponent(
                        buttonText = DIALOG_BUTTON_TEXT
                    ) {
                        onDismiss.invoke()
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DialogPreview() {
    FleetSavingTimerTheme {
        InfoDialogComponent(
            true,
        ) {
            //
        }
    }
}