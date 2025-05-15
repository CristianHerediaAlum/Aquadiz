package com.pnet.aquadiz

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.pnet.aquadiz.menu.AppMenu
import com.pnet.aquadiz.menu.Navigation
import com.pnet.aquadiz.ui.theme.AquadizTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AquadizTheme {
                MainApp()
            }
        }
    }
}

@Composable
fun MainApp() {
    val navController = rememberNavController()
    Scaffold(
        topBar = { AppMenu(navController) },
        content = { padding ->
            Navigation(navController, Modifier.padding(padding))
        }
    )
}
