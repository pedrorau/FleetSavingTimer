package com.example.fleetsavingtimer.presentation.main

import android.Manifest
import android.content.Context
import android.os.Build
import android.widget.Toast
import androidx.activity.compose.BackHandler
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
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
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.fleetsavingtimer.presentation.components.AppBarComponent
import com.example.fleetsavingtimer.presentation.components.ButtonComponent
import com.example.fleetsavingtimer.presentation.components.InfoDialogComponent
import com.example.fleetsavingtimer.presentation.components.TextFieldComponent
import com.example.fleetsavingtimer.presentation.utils.BUTTON_TEXT
import com.example.fleetsavingtimer.presentation.utils.EMPTY_ALARM_TITLE_MESSAGE
import com.example.fleetsavingtimer.presentation.utils.FLEET_SAVE_TIMER
import com.example.fleetsavingtimer.presentation.utils.HOURS_TEXT
import com.example.fleetsavingtimer.presentation.utils.MINUTES_TEXT
import com.example.fleetsavingtimer.presentation.utils.NOT_OK_HOURS
import com.example.fleetsavingtimer.presentation.utils.NOT_OK_MINUTES
import com.example.fleetsavingtimer.ui.theme.FleetSavingTimerTheme
import kotlinx.coroutines.delay
import com.example.fleetsavingtimer.presentation.utils.checkAlarmPermission
import com.example.fleetsavingtimer.presentation.utils.getCurrentTime
import com.example.fleetsavingtimer.presentation.utils.saveDefaultValues
import com.example.fleetsavingtimer.presentation.utils.setAlarm
import com.example.fleetsavingtimer.presentation.utils.verifyIfAlarmTitleExist
import com.example.fleetsavingtimer.ui.theme.accent

@Composable
internal fun MainScreen(
    onClose: () -> Unit = { },
    goToSettings: () -> Unit = { },
) {

    BackHandler {
        onClose.invoke()
    }

    var time by remember { mutableStateOf(getCurrentTime()) }
    val context = LocalContext.current
    var hasPermission by remember { mutableStateOf(checkAlarmPermission(context)) }
    val requestPermissionLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestPermission(),
        onResult = { isGranted ->
            hasPermission = isGranted
        }
    )
    var showDialog by remember { mutableStateOf(false) }

    LaunchedEffect(Unit) {
        while (true) {
            time = getCurrentTime()
            delay(1000L)
        }
    }

    LaunchedEffect(Unit) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S && !hasPermission) {
            requestPermissionLauncher.launch(Manifest.permission.SCHEDULE_EXACT_ALARM)
        }

        if (!verifyIfAlarmTitleExist(context)) {
            saveDefaultValues(context)
        }
    }

    AppBarComponent(
        FLEET_SAVE_TIMER,
        onLeftAction = {
            showDialog = true
        },
        onRightAction = {
            goToSettings.invoke()
        }
    )

    MainScreenLayout(
        time = time,
        context = context
    )

    InfoDialogComponent(
        showDialog = showDialog
    ) {
        showDialog = false
    }
}

@Composable
internal fun MainScreenLayout(
    time: String,
    context: Context,
) {
    var hours by remember { mutableStateOf("") }
    var minutes by remember { mutableStateOf("") }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .padding(
                top = 16.dp
            )
    ) {
        Column(
            modifier = Modifier.align(Alignment.Center),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Text(
                text = time,
                fontSize = 36.sp,
                fontWeight = FontWeight.Bold,
                color = accent
            )

            Spacer(modifier = Modifier.height(20.dp))

            TextFieldComponent(
                value = hours,
                label = HOURS_TEXT,
                onValueChange = { input ->
                    if (input.all { it.isDigit() }) {
                        hours = input
                    }

                    if (hours.isEmpty()) return@TextFieldComponent

                    if (hours.toInt() !in 0..24) {
                        Toast.makeText(context, NOT_OK_HOURS, Toast.LENGTH_SHORT).show()
                    }
                }
            )

            Spacer(modifier = Modifier.height(10.dp))

            TextFieldComponent(
                value = minutes,
                label = MINUTES_TEXT,
                onValueChange = { input ->
                    if (input.all { it.isDigit() }) {
                        minutes = input
                    }

                    if (minutes.isEmpty()) return@TextFieldComponent

                    if (minutes.toInt() !in 0..59) {
                        Toast.makeText(context, NOT_OK_MINUTES, Toast.LENGTH_SHORT).show()
                    }
                }
            )

            Spacer(modifier = Modifier.height(30.dp))

            ButtonComponent(
                buttonText = BUTTON_TEXT,
            ) {
                setAlarm(context, hours.toInt(), minutes.toInt(), "Fleet Saving")
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MainPreview() {
    FleetSavingTimerTheme {
        MainScreenLayout(
            getCurrentTime(),
            LocalContext.current
        )
    }
}