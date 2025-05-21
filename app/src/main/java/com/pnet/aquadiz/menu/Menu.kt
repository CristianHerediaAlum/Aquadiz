package com.pnet.aquadiz.menu

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.pnet.aquadiz.ui.theme.azulOscuro

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppMenu(navController: NavController) {
    var expanded by remember { mutableStateOf(false) }

    CenterAlignedTopAppBar(
//        title = { Text("Aquadiz") },
        title = {
//            Box(
//                modifier = Modifier.fillMaxWidth(),
//                contentAlignment = Alignment.Center
//            ) {
                Text(
                    text = "Aquadiz",
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black,
                    modifier = Modifier.padding(top = 20.dp)
                )
//            }
        },
        actions = {
            IconButton(onClick = { expanded = !expanded }) {
                Icon(
                    imageVector = Icons.Filled.Menu,
                    contentDescription = "Menu",
                    tint = Color.Black
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
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = azulOscuro,
        ),
        modifier = Modifier.height(100.dp)

    )
}
