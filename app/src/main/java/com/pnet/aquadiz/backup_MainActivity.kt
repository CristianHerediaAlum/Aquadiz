//// MainActivity.kt
//package com.pnet.aquadiz
//
//import android.os.Bundle
//import androidx.activity.ComponentActivity
//import androidx.activity.compose.setContent
//import androidx.compose.foundation.layout.*
//import androidx.compose.material.icons.Icons
//import androidx.compose.material.icons.filled.MoreVert
//import androidx.compose.material3.*
//import androidx.compose.runtime.*
//import androidx.compose.ui.Alignment
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.tooling.preview.Preview
//import androidx.compose.ui.unit.dp
//import androidx.navigation.NavHostController
//import androidx.navigation.compose.NavHost
//import androidx.navigation.compose.composable
//import androidx.navigation.compose.rememberNavController
//import com.pnet.aquadiz.ui.theme.AquadizTheme
//
//class MainActivity : ComponentActivity() {
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContent {
//            AquadizTheme {
//                MainApp()
//            }
//        }
//    }
//}
//
//@OptIn(ExperimentalMaterial3Api::class)
//@Composable
//fun MainApp() {
//    val navController = rememberNavController()
//
//    Scaffold(
//        topBar = {
//            TopAppBar(
//                title = { Text("Aquadiz") },
//                actions = {
//                    OverflowMenu(navController)
//                }
//            )
//        },
//        content = { padding ->
//            NavigationHost(navController, Modifier.padding(padding))
//        }
//    )
//}
//
//@Composable
//fun OverflowMenu(navController: NavHostController) {
//    var expanded by remember { mutableStateOf(false) }
//
//    IconButton(onClick = { expanded = true }) {
//        Icon(Icons.Filled.MoreVert, contentDescription = "Menu")
//    }
//
//    DropdownMenu(
//        expanded = expanded,
//        onDismissRequest = { expanded = false }
//    ) {
//        DropdownMenuItem(
//            text = { Text("Salas") },
//            onClick = {
//                navController.navigate("salas") {
//                    // Evita duplicados en la pila de navegación
//                    popUpTo("salas") { inclusive = true }
//                }
//                expanded = false
//            }
//        )
//        DropdownMenuItem(
//            text = { Text("Reservas") },
//            onClick = {
//                navController.navigate("reservas") {
//                    popUpTo("reservas") { inclusive = true }
//                }
//                expanded = false
//            }
//        )
//    }
//}
//
//@Composable
//fun NavigationHost(navController: NavHostController, modifier: Modifier = Modifier) {
//    NavHost(navController, startDestination = "salas", modifier = modifier) {
//        composable("salas") { SalasView() }
//        composable("reservas") { ReservasView() }
//    }
//}
//
//@Composable
//fun SalasView() {
//    Box(
//        modifier = Modifier.fillMaxSize(),
//        contentAlignment = Alignment.Center
//    ) {
//        Text("Vista de Salas", style = MaterialTheme.typography.headlineMedium)
//    }
//}
//
//@Composable
//fun ReservasView() {
//    Box(
//        modifier = Modifier.fillMaxSize(),
//        contentAlignment = Alignment.Center
//    ) {
//        Text("Vista de Reservas", style = MaterialTheme.typography.headlineMedium)
//    }
//}
//
//@Preview(showBackground = true)
//@Composable
//fun PreviewMainApp() {
//    AquadizTheme {
//        MainApp()
//    }
//}
