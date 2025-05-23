package com.pnet.aquadiz.operaciones

import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.Alignment
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.pnet.aquadiz.modules.Reserva
import com.pnet.aquadiz.modules.ReservaViewModel

@Composable
fun AñadirReservaScreen(navController: NavController,
                        viewModel: ReservaViewModel = viewModel()
) {
    val contexto = LocalContext.current

    var sala by remember { mutableStateOf("") }
    var nickname by remember { mutableStateOf("") }
    var nSocio by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var fecha by remember { mutableStateOf("") }
    var numPersonas by remember { mutableStateOf("") }
    var comentarios by remember { mutableStateOf("") }

    Column {
        OutlinedTextField(value = sala, onValueChange = { sala = it }, label = { Text("Sala") })
        OutlinedTextField(value = nickname, onValueChange = { nickname = it }, label = { Text("Nickname") })
        OutlinedTextField(value = nSocio, onValueChange = { nSocio = it }, label = { Text("Nº Socio") })
        OutlinedTextField(value = email, onValueChange = { email = it }, label = { Text("Email") })
        OutlinedTextField(value = fecha, onValueChange = { fecha = it }, label = { Text("Fecha") })
        OutlinedTextField(value = numPersonas, onValueChange = { numPersonas = it }, label = { Text("Nº de Personas") })
        OutlinedTextField(value = comentarios, onValueChange = { comentarios = it }, label = { Text("Comentarios") })

        Button(onClick = {
            viewModel.agregarReserva(
                Reserva(
                    id = 0, // Placeholder
                    sala = sala,
                    nickname = nickname,
                    nSocio = nSocio.toIntOrNull() ?: 0,
                    email = email,
                    fecha = fecha,
                    numPersonas = numPersonas.toIntOrNull() ?: 1,
                    comentarios = comentarios,
                ),
                onSuccess = {
                    Toast.makeText(contexto, "Reserva añadida", Toast.LENGTH_SHORT).show()
                    navController.popBackStack() // ← Esto te regresa a la pantalla anterior
                },
                onError = {
                    Toast.makeText(contexto, "Error al añadir", Toast.LENGTH_SHORT).show()
                }
            )
        }) {
            Text("Añadir Reserva")
        }
    }
}

