package com.steccos.marsroverapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.steccos.marsroverapp.ui.theme.MarsRoverAppTheme
import com.steccos.marsroverapp.ui.view.RoverList
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NAvCompose()
        }
    }
}
