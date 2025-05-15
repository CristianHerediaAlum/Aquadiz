package com.pnet.aquadiz.menu

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.navigation.NavController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppMenu(navController: NavController) {
    var expanded by remember { mutableStateOf(false) }

    TopAppBar(
        title = { Text("Aquadiz") },
        actions = {
            IconButton(onClick = { expanded = !expanded }) {
                Icon(
                    imageVector = Icons.Filled.MoreVert,
                    contentDescription = "Menu"
                )
            }
            DropdownMenu(expanded = expanded, onDismissRequest = { expanded = false }) {
                DropdownMenuItem(
                    text = { Text("Salas") },
                    onClick = {
                        navController.navigate("salas")
                        expanded = false
                    }
                )
                DropdownMenuItem(
                    text = { Text("Reservas") },
                    onClick = {
                        navController.navigate("reservas")
                        expanded = false
                    }
                )
                DropdownMenuItem(
                    text = { Text("Inicio") },
                    onClick = {
                        navController.navigate("home")
                        expanded = false
                    }
                )
            }
        }
    )
}
