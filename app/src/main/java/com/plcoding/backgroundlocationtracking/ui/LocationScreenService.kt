package com.plcoding.backgroundlocationtracking.ui

import android.content.Context
import android.content.Intent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.intent.foregroundService.LocationService

@Composable
fun LocationScreenService (
    context: Context,
    userEmail: String?
) {
    val startServiceIntent = Intent(context, LocationService::class.java).apply {
        action = LocationService.ACTION_START
        putExtra("user_email", "${userEmail}")

    }
    val stopServiceIntent = Intent(context, LocationService::class.java).apply {
        action = LocationService.ACTION_STOP
    }
    Column(
    modifier = Modifier.fillMaxSize(),
    verticalArrangement = Arrangement.Center,
    horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Button(onClick = { context.startService(startServiceIntent) }
        ) {
            Text(text = "Start")
        }
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = { context.startService(stopServiceIntent) }
        ) {
            Text(text = "Stop")
        }
    }
}