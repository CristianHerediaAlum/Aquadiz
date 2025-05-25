package com.pnet.aquadiz.operaciones

import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.clickable
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.pnet.aquadiz.modules.Reserva
import com.pnet.aquadiz.modules.ReservaViewModel
import com.pnet.aquadiz.ui.theme.azulClaro
import com.pnet.aquadiz.ui.theme.azulOscuro

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AñadirReservaScreen(navController: NavController,
                        viewModel: ReservaViewModel = viewModel()
) {
    val contexto = LocalContext.current

    var sala by remember { mutableStateOf("") }
    var expanded by remember { mutableStateOf(false) }
    val opcionesSala = listOf("Sala de Baño Turco", "Sala de Hidromasaje")
    var nickname by remember { mutableStateOf("") }
    var nSocio by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var fecha by remember { mutableStateOf("") }
    var numPersonas by remember { mutableStateOf("") }
    var comentarios by remember { mutableStateOf("") }
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = Color.LightGray
    ) {
        Column(     modifier = Modifier
            .padding(16.dp)
            .verticalScroll(rememberScrollState())) {
            Text(
                text = "Sala",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black,
                modifier = Modifier
                    .padding(8.dp)
            )
            ExposedDropdownMenuBox(
                expanded = expanded,
                onExpandedChange = { expanded = !expanded }
            ) {
                TextField(
                    value = sala,
                    onValueChange = {},
                    readOnly = true,
                    label = { Text("Seleccionar Sala") },
                    colors = ExposedDropdownMenuDefaults.textFieldColors(
                        unfocusedContainerColor = Color.White
                    ),
                    trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded) },
                    modifier = Modifier
                        .menuAnchor()
                        .fillMaxWidth()
                )

                ExposedDropdownMenu(
                    expanded = expanded,
                    onDismissRequest = { expanded = false }
                ) {
                    opcionesSala.forEach { opcion ->
                        DropdownMenuItem(
                            text = { Text(opcion) },
                            onClick = {
                                sala = opcion
                                expanded = false
                            }
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "Nickname",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black,
                modifier = Modifier
                    .padding(8.dp)
            )
            OutlinedTextField(
                value = nickname,
                onValueChange = { nickname = it },
                label = { Text("Nickname") },
                colors = OutlinedTextFieldDefaults.colors( unfocusedContainerColor = Color.White)
            )

            Text(
                text = "Numero de socio",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black,
                modifier = Modifier
                    .padding(8.dp)
            )
            OutlinedTextField(
                value = nSocio,
                onValueChange = { nSocio = it },
                label = { Text("Nº Socio") },
                colors = OutlinedTextFieldDefaults.colors( unfocusedContainerColor = Color.White)
            )
            Text(
                text = "Email",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black,
                modifier = Modifier
                    .padding(8.dp)
            )
            OutlinedTextField(
                value = email,
                onValueChange = { email = it },
                label = { Text("Email") },
                colors = OutlinedTextFieldDefaults.colors( unfocusedContainerColor = Color.White)
            )

            Text(
                text = "Fecha de Reserva",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black,
                modifier = Modifier
                    .padding(8.dp)
            )
            OutlinedTextField(
                value = fecha,
                onValueChange = { fecha = it },
                label = { Text("Fecha") },
                colors = OutlinedTextFieldDefaults.colors( unfocusedContainerColor = Color.White)
            )
            Text(
                text = "Nº de Personas",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black,
                modifier = Modifier
                    .padding(8.dp)
            )
            OutlinedTextField(
                value = numPersonas,
                onValueChange = { numPersonas = it },
                label = { Text("Nº de Personas") },
                colors = OutlinedTextFieldDefaults.colors( unfocusedContainerColor = Color.White)
            )
            Text(
                text = "Comentarios",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black,
                modifier = Modifier
                    .padding(8.dp)
            )
            OutlinedTextField(
                value = comentarios,
                onValueChange = { comentarios = it },
                label = { Text("Comentarios") },
                colors = OutlinedTextFieldDefaults.colors( unfocusedContainerColor = Color.White)
            )

            Spacer(modifier = Modifier.height(16.dp))

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
            },
                modifier = Modifier.align(Alignment.CenterHorizontally)) {
                Text("Añadir Reserva")
            }
        }
    }
}

