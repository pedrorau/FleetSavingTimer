package com.example.fleetsavingtimer.presentation.settings

import android.widget.Toast
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.fleetsavingtimer.presentation.components.ButtonComponent
import com.example.fleetsavingtimer.presentation.components.TextFieldComponent
import com.example.fleetsavingtimer.presentation.utils.BUTTON_TEXT
import com.example.fleetsavingtimer.presentation.utils.DEFAULT_TITLE_ALARM
import com.example.fleetsavingtimer.presentation.utils.DEFAULT_TITLE_MINUTES
import com.example.fleetsavingtimer.presentation.utils.EMPTY_ALARM_TITLE_MESSAGE
import com.example.fleetsavingtimer.presentation.utils.SETTINGS_TEXT
import com.example.fleetsavingtimer.presentation.utils.obtainAlarmTitle
import com.example.fleetsavingtimer.presentation.utils.obtainFreeMinutes
import com.example.fleetsavingtimer.presentation.utils.saveAlarmTitle
import com.example.fleetsavingtimer.presentation.utils.saveFreeMinutes
import com.example.fleetsavingtimer.ui.theme.FleetSavingTimerTheme
import com.example.fleetsavingtimer.ui.theme.accent
import kotlinx.coroutines.launch

@Composable
internal fun SettingsScreen(
    onBack: () -> Unit = { },
) {
    BackHandler {
        onBack.invoke()
    }

    SettingsScreenLayout {
        onBack.invoke()
    }
}

@Composable
internal fun SettingsScreenLayout(
    onBack: () -> Unit = { },
) {
    var alarmTitle by remember { mutableStateOf("") }
    var freeMinutes by remember { mutableStateOf("") }
    val context = LocalContext.current
    val scope = rememberCoroutineScope()

    LaunchedEffect(Unit) {
        alarmTitle = obtainAlarmTitle(context)
        freeMinutes = obtainFreeMinutes(context)
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Column(
            modifier = Modifier.align(Alignment.Center),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Text(
                text = SETTINGS_TEXT,
                fontSize = 36.sp,
                fontWeight = FontWeight.Bold,
                color = accent
            )

            Spacer(modifier = Modifier.height(30.dp))

            TextFieldComponent(
                value = alarmTitle,
                label = DEFAULT_TITLE_ALARM,
                onValueChange = { input ->
                    alarmTitle = input
                }
            )

            Spacer(modifier = Modifier.height(30.dp))

            TextFieldComponent(
                value = freeMinutes,
                label = DEFAULT_TITLE_MINUTES,
                onValueChange = { input ->
                    freeMinutes = input
                }
            )

            Spacer(modifier = Modifier.height(30.dp))

            ButtonComponent(
                buttonText = BUTTON_TEXT,
            ) {
                if (alarmTitle.isNotEmpty() && freeMinutes.isNotEmpty()) {
                    scope.launch {
                        saveAlarmTitle(context, alarmTitle)
                        saveFreeMinutes(context, freeMinutes)
                    }
                    onBack.invoke()
                } else  {
                    Toast.makeText(context, EMPTY_ALARM_TITLE_MESSAGE, Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MainPreview() {
    FleetSavingTimerTheme {
        SettingsScreenLayout()
    }
}