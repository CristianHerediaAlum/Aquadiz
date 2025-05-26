package com.pnet.aquadiz.operaciones

import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import android.app.DatePickerDialog
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material3.Icon
import java.util.Calendar
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.pnet.aquadiz.modules.Reserva
import com.pnet.aquadiz.modules.ReservaViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditarReservaScreen(
    navController: NavController,
    reservaId: Int,
    viewModel: ReservaViewModel = viewModel()
) {
    val contexto = LocalContext.current
    val calendario = Calendar.getInstance()
    var errorMessage by remember { mutableStateOf<String?>(null) }

    // Buscamos la reserva por su ID
    LaunchedEffect(reservaId) {
        viewModel.buscarReservaPorId(reservaId)
    }

    // Observa los datos de la reserva
    val reserva = viewModel.ReservaDetail

    var id by remember { mutableStateOf("") }
    var sala by remember { mutableStateOf("") }
    var expanded by remember { mutableStateOf(false) }
    val opcionesSala = listOf("Sala de Baño Turco", "Sala de Hidromasaje")
    var nickname by remember { mutableStateOf("") }
    var nSocio by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var fecha by remember { mutableStateOf("") }
    var numPersonas by remember { mutableStateOf("") }
    var comentarios by remember { mutableStateOf("") }

    // Actualiza los campos con los datos de la reserva
    LaunchedEffect(reserva) {
        reserva?.let {
            id = it.id.toString()
            sala = it.sala
            nickname = it.nickname
            nSocio = it.nSocio.toString()
            email = it.email
            fecha = it.fecha
            numPersonas = it.numPersonas.toString()
            comentarios = it.comentarios
        }
    }
    // Le damos color al fondo
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = Color.LightGray
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp)
                .verticalScroll(rememberScrollState())
        ) {
            // Selector de salas
            Text(
                text = "Sala",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black,
                modifier = Modifier.padding(8.dp)
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

            // Inputs
            Text("Nickname", fontSize = 18.sp, fontWeight = FontWeight.Bold, color = Color.Black, modifier = Modifier.padding(8.dp))
            OutlinedTextField(
                value = nickname,
                onValueChange = { nickname = it },
                label = { Text("Nickname") },
                colors = OutlinedTextFieldDefaults.colors(unfocusedContainerColor = Color.White)
            )

            Text("Numero de socio", fontSize = 18.sp, fontWeight = FontWeight.Bold, color = Color.Black, modifier = Modifier.padding(8.dp))
            OutlinedTextField(
                value = nSocio,
                onValueChange = { nSocio = it },
                label = { Text("Nº Socio") },
                colors = OutlinedTextFieldDefaults.colors(unfocusedContainerColor = Color.White)
            )

            Text("Email", fontSize = 18.sp, fontWeight = FontWeight.Bold, color = Color.Black, modifier = Modifier.padding(8.dp))
            OutlinedTextField(
                value = email,
                onValueChange = { email = it },
                label = { Text("Email") },
                colors = OutlinedTextFieldDefaults.colors(unfocusedContainerColor = Color.White)
            )
            // Manejador del DatePickerDialog
            val datePicker = DatePickerDialog(
                contexto,
                { _, year, month, dayOfMonth ->
                    // Formatea la fecha seleccionada (ej: 25/05/2025)
                    fecha = "%02d/%02d/%04d".format(dayOfMonth, month + 1, year)
                },
                calendario.get(Calendar.YEAR),
                calendario.get(Calendar.MONTH),
                calendario.get(Calendar.DAY_OF_MONTH)
            )
            Text("Fecha de Reserva", fontSize = 18.sp, fontWeight = FontWeight.Bold, color = Color.Black, modifier = Modifier.padding(8.dp))
            OutlinedTextField(
                value = fecha,
                onValueChange = {}, // No permitir edición manual
                label = { Text("Seleccionar Fecha") },
                readOnly = true,
                modifier = Modifier.fillMaxWidth(),
                colors = OutlinedTextFieldDefaults.colors(unfocusedContainerColor = Color.White),
                trailingIcon = {
                    Icon(
                        imageVector = Icons.Filled.DateRange,
                        contentDescription = "Seleccionar fecha",
                        modifier = Modifier.clickable {
                            datePicker.show()
                        }
                    )
                }
            )

            Text("Nº de Personas", fontSize = 18.sp, fontWeight = FontWeight.Bold, color = Color.Black, modifier = Modifier.padding(8.dp))
            OutlinedTextField(
                value = numPersonas,
                onValueChange = { numPersonas = it },
                label = { Text("Nº de Personas") },
                colors = OutlinedTextFieldDefaults.colors(unfocusedContainerColor = Color.White)
            )

            Text("Comentarios", fontSize = 18.sp, fontWeight = FontWeight.Bold, color = Color.Black, modifier = Modifier.padding(8.dp))
            OutlinedTextField(
                value = comentarios,
                onValueChange = { comentarios = it },
                label = { Text("Comentarios") },
                colors = OutlinedTextFieldDefaults.colors(unfocusedContainerColor = Color.White)
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Botón
            Button(
                onClick = {
                    //Comprobamos que los campos no esten vacios
                    if (sala.isBlank() || nickname.isBlank() || nSocio.isBlank() || email.isBlank() || fecha.isBlank()) {
                        errorMessage = "Por favor, completa todos los campos obligatorios."
                        return@Button
                    }
                    // Si no hay errores, actualizamos la reserva
                    val nuevaReserva = Reserva(
                        id = id.toIntOrNull() ?: 0,
                        sala = sala,
                        nickname = nickname,
                        nSocio = nSocio.toIntOrNull() ?: 0,
                        email = email,
                        fecha = fecha,
                        numPersonas = numPersonas.toIntOrNull() ?: 1,
                        comentarios = comentarios
                    )
                    viewModel.editarReserva(
                        id.toIntOrNull() ?: 0, nuevaReserva,
                        onSuccess = {
                            Toast.makeText(contexto, "Reserva añadida", Toast.LENGTH_SHORT).show()
                            navController.popBackStack()
                        },
                        onError = {
                            errorMessage = "Error al añadir"
                        }
                    )
                },
                modifier = Modifier.align(Alignment.CenterHorizontally) // Centrado
            ) {
                Text("Actualizar Reserva")
            }
            // Si hay un error, lo mostramos
            errorMessage?.let {
                Text(
                    text = it,
                    color = Color.Red,
                    modifier = Modifier
                        .padding(top = 8.dp)
                        .testTag("ErrorMessage")
                )
            }
        }
    }
}
