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
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
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
import java.util.Calendar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material3.Icon
import androidx.compose.ui.platform.testTag


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AñadirReservaScreen(navController: NavController,
                        viewModel: ReservaViewModel = viewModel()
) {
    val contexto = LocalContext.current
    val calendario = Calendar.getInstance()
    var errorMessage by remember { mutableStateOf<String?>(null) }


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
            //Selector de salas
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
            //Selector de demas campos
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
            // Manejador del DatePickerDialog para mostrar un calendario selector de fecha
            val datePicker = android.app.DatePickerDialog(
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
                //Comprobamos que los campos no esten vacios
                if (sala.isBlank() || nickname.isBlank() || nSocio.isBlank() || email.isBlank() || fecha.isBlank()) {
                    errorMessage = "Por favor, completa todos los campos obligatorios."
                    return@Button
                }
                //Si no hay errores, añadimos la reserva
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
                        navController.popBackStack()
                    },
                    onError = {
                        errorMessage = "Error al añadir"
                    }
                )
            },
                modifier = Modifier.align(Alignment.CenterHorizontally)) {
                Text("Añadir Reserva")
            }
            //Si hay un error, lo mostramos
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

