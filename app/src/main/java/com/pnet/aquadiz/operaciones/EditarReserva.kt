package com.pnet.aquadiz.operaciones

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.pnet.aquadiz.modules.Reserva
import com.pnet.aquadiz.modules.ReservaViewModel

@Composable
fun EditarReservaScreen(viewModel: ReservaViewModel) {
    var id by remember { mutableStateOf("") }
    var sala by remember { mutableStateOf("") }
    var nickname by remember { mutableStateOf("") }
    var nSocio by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var fecha by remember { mutableStateOf("") }
    var numPersonas by remember { mutableStateOf("") }
    var comentarios by remember { mutableStateOf("") }

    Column(modifier = Modifier.padding(16.dp)) {
        OutlinedTextField(value = id, onValueChange = { id = it }, label = { Text("ID a actualizar") })
        OutlinedTextField(value = sala, onValueChange = { sala = it }, label = { Text("Sala") })
        OutlinedTextField(value = nickname, onValueChange = { nickname = it }, label = { Text("Nickname") })
        OutlinedTextField(value = nSocio, onValueChange = { nSocio = it }, label = { Text("Nº Socio") })
        OutlinedTextField(value = email, onValueChange = { email = it }, label = { Text("Email") })
        OutlinedTextField(value = fecha, onValueChange = { fecha = it }, label = { Text("Fecha") })
        OutlinedTextField(value = numPersonas, onValueChange = { numPersonas = it }, label = { Text("Nº de Personas") })
        OutlinedTextField(value = comentarios, onValueChange = { comentarios = it }, label = { Text("Comentarios") })

        Button(onClick = {
            val reserva = Reserva(
                id = id.toIntOrNull() ?: 0,
                sala = sala,
                nickname = nickname,
                nSocio = nSocio.toIntOrNull() ?: 0,
                email = email,
                fecha = fecha,
                numPersonas = numPersonas.toIntOrNull() ?: 1,
                comentarios = comentarios
            )
            viewModel.editarReserva(id.toIntOrNull() ?: 0, reserva)
        }) {
            Text("Actualizar Reserva")
        }
    }
}
