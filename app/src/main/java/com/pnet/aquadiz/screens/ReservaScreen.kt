package com.pnet.aquadiz.screens

import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.pnet.aquadiz.modules.ReservaViewModel
import com.pnet.aquadiz.modules.Reserva
import androidx.lifecycle.viewmodel.compose.viewModel
import com.pnet.aquadiz.ui.theme.azulClaro
import com.pnet.aquadiz.ui.theme.azulOscuro

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

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = azulClaro
    ) {
        Column(modifier = Modifier.fillMaxSize()) {
            Text(
                text = "Reservas",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black,
                modifier = Modifier
                    .padding(16.dp)
                    .align(Alignment.CenterHorizontally)
            )
            LazyColumn(                modifier = Modifier
                .weight(1f) // ← SOLUCIÓN AQUÍ
                .padding(horizontal = 64.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)) {
                items(reservas) { reserva ->
                    Card(
                        modifier = Modifier
                            .fillMaxWidth(),
                        shape = RoundedCornerShape(8.dp),
                        colors = CardDefaults.cardColors(containerColor = Color.LightGray),
                        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
                    ) {
                        Column(modifier = Modifier.padding(16.dp)) {
                            Text("• Sala seleccionada: ${reserva.sala}")
                            Text("• Nickname: ${reserva.nickname}")
                            Text("• Número carnet socio: ${reserva.nSocio}")
                            Text("• Email: ${reserva.email}")
                            Text("• Fecha: ${reserva.fecha}")
                            Text("• Número personas: ${reserva.numPersonas}")
                            Text("• Comentario: ${reserva.comentarios}")

                            Spacer(modifier = Modifier.height(8.dp))

                            Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
//                                Button(onClick = {
//                                    navController.navigate("verReserva/${reserva.id}")
//                                }) {
//                                    Text("Ver")
//                                }

                                Button(
                                    onClick = {
                                        navController.navigate("editarReserva/${reserva.id}")
                                    },
                                    shape = RoundedCornerShape(8.dp),
                                    colors = ButtonDefaults.buttonColors(
                                        containerColor = azulOscuro
                                    )
                                ) {
                                    Text("Editar", color = Color.Black)
                                }

                                Button(
                                    onClick = {
                                        viewModel.eliminarReserva(
                                            reserva.id,
                                            onSuccess = {
                                                Toast.makeText(
                                                    contexto,
                                                    "Reserva eliminada",
                                                    Toast.LENGTH_SHORT
                                                ).show()
                                                viewModel.obtenerReservas()
                                            },
                                            onError = {
                                                Toast.makeText(
                                                    contexto,
                                                    "Error al eliminar",
                                                    Toast.LENGTH_SHORT
                                                ).show()
                                            })
                                    },
                                    shape = RoundedCornerShape(8.dp),
                                    colors = ButtonDefaults.buttonColors(containerColor = Color.Red)
                                ) {
                                    Text("Eliminar", color = Color.White)
                                }
                                Spacer(modifier = Modifier.height(8.dp))
                            }
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            Button(
                onClick = { navController.navigate("añadirReserva") },
                shape = RoundedCornerShape(8.dp),
                colors = ButtonDefaults.buttonColors(containerColor = azulOscuro),
                modifier = Modifier
                    .padding(16.dp)
                    .align(Alignment.CenterHorizontally)
            ) {
                Text("Añadir nueva reserva", color = Color.Black)
            }
        }
    }
}
