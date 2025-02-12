package com.example.fleetsavingtimer.presentation.utils

import android.Manifest
import android.content.Context
import android.content.Intent
import android.icu.util.Calendar
import android.os.Build
import android.provider.AlarmClock
import androidx.core.content.ContextCompat
import androidx.core.content.PermissionChecker
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

fun getCurrentTime(): String {
    return SimpleDateFormat("HH:mm:ss", Locale.getDefault()).format(Date())
}

fun setAlarm(context: Context, hour: Int, minutes: Int, message: String) {
    val intent = Intent(AlarmClock.ACTION_SET_ALARM).apply {
        putExtra(AlarmClock.EXTRA_HOUR, hour)
        putExtra(AlarmClock.EXTRA_MINUTES, minutes)
        putExtra(AlarmClock.EXTRA_MESSAGE, message)
        putExtra(AlarmClock.EXTRA_VIBRATE, true)
        putExtra(AlarmClock.EXTRA_SKIP_UI, true)

        //Aqui hay que hacer una logica para sumarle un dia
        putExtra(AlarmClock.EXTRA_DAYS, arrayListOf(obtainTomorrowDayOfWeek()))
    }

    if(intent.resolveActivity(context.packageManager) != null) {
        context.startActivity(intent)
    }
}

fun checkAlarmPermission(context: Context): Boolean {
    return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
        ContextCompat.checkSelfPermission(context, Manifest.permission.SCHEDULE_EXACT_ALARM) == PermissionChecker.PERMISSION_GRANTED
    } else {
        true
    }
}

fun obtainTomorrowDayOfWeek(): Int {
    val calendar = Calendar.getInstance()
    calendar.add(Calendar.DAY_OF_YEAR, 1)
    val tomorrowDayOfWeek = calendar.get(Calendar.DAY_OF_WEEK)
    return tomorrowDayOfWeek
}

suspend fun saveDefaultValues(context: Context) {
    DataStoreHelper.save(context, TITLE_ALARM_KEY, DEFAULT_TITLE_ALARM_VALUE)
    DataStoreHelper.save(context, MINUTES_KEY, DEFAULT_MINUTES_VALUE)
}

suspend fun saveAlarmTitle(context: Context, title: String) {
    DataStoreHelper.save(context, TITLE_ALARM_KEY, title)
}

suspend fun saveFreeMinutes(context: Context, minutes: String) {
    DataStoreHelper.save(context, MINUTES_KEY, minutes)
}

suspend fun verifyIfAlarmTitleExist(context: Context): Boolean {
    DataStoreHelper.get(context, TITLE_ALARM_KEY)?.let {
        return true
    } ?: run {
        return false
    }
}

suspend fun obtainAlarmTitle(context: Context): String {
    return DataStoreHelper.get(context, TITLE_ALARM_KEY).orEmpty()
}

suspend fun obtainFreeMinutes(context: Context): String {
    return DataStoreHelper.get(context, MINUTES_KEY).orEmpty()
}