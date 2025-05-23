package com.pnet.aquadiz.menu

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.pnet.aquadiz.modules.ReservaViewModel
import com.pnet.aquadiz.screens.HomeScreen
import com.pnet.aquadiz.screens.ReservasScreen
import com.pnet.aquadiz.screens.SalasScreen
import com.pnet.aquadiz.operaciones.AñadirReservaScreen

@Composable
fun Navigation(navController: NavHostController, modifier: Modifier) {
    NavHost(navController, startDestination = "home", modifier = modifier) {
        composable("home") { HomeScreen(navController) }
        composable("reservas") { ReservasScreen(navController) }
        composable("salas") { SalasScreen() }
        composable("añadirReserva") { AñadirReservaScreen(navController) }
    }
}
