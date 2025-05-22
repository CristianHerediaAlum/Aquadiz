package com.pnet.aquadiz.screens

import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.pnet.aquadiz.modules.ReservaViewModel
import com.pnet.aquadiz.modules.Reserva
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun ReservasScreen(
    navController: NavController,
    viewModel: ReservaViewModel = viewModel()
) {
    val reservas by viewModel.Reservas.collectAsState()
    val contexto = LocalContext.current

    LaunchedEffect(Unit) {
        viewModel.obtenerReservas()
    }

    Column(modifier = Modifier.fillMaxSize()) {
        LazyColumn(modifier = Modifier.weight(1f)) {
            items(reservas) { reserva ->
                Card(
                    colors = CardDefaults.cardColors(containerColor = Color(0xFFFAFAFA)),
                    modifier = Modifier
                        .padding(8.dp)
                        .fillMaxWidth()
                ) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Text("Sala: ${reserva.sala}")
                        Text("Nickname: ${reserva.nickname}")
                        Text("Fecha: ${reserva.fecha}")
                        Text("Nº Personas: ${reserva.numPersonas}")
                        Text("Comentario: ${reserva.comentarios}")

                        Spacer(modifier = Modifier.height(8.dp))

                        Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
//                            Button(onClick = {
//                                navController.navigate("verReserva/${reserva.id}")
//                            }) {
//                                Text("Ver")
//                            }

                            Button(onClick = {
                                viewModel.eliminarReserva(reserva.id,
                                    onSuccess = {
                                        Toast.makeText(contexto, "Reserva eliminada", Toast.LENGTH_SHORT).show()
                                        viewModel.obtenerReservas()
                                    },
                                    onError = {
                                        Toast.makeText(contexto, "Error al eliminar", Toast.LENGTH_SHORT).show()
                                    })
                            }) {
                                Text("Eliminar")
                            }

                            Button(onClick = {
                                navController.navigate("editarReserva/${reserva.id}")
                            }) {
                                Text("Editar")
                            }
                        }
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = { navController.navigate("añadirReserva") },
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth()
        ) {
            Text("Añadir nueva reserva")
        }
    }
}

