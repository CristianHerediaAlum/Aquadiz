package com.pnet.aquadiz.operaciones

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import android.widget.Toast
import androidx.compose.ui.platform.LocalContext

import com.pnet.aquadiz.modules.ReservaViewModel
import com.pnet.aquadiz.modules.RetrofitClient

@Composable
fun EliminarReservaScreen(viewModel: ReservaViewModel) {
    var id by remember { mutableStateOf("") }
    val context = LocalContext.current

    Column(modifier = Modifier.padding(16.dp)) {
        OutlinedTextField(
            value = id,
            onValueChange = { id = it },
            label = { Text("ID de la reserva a eliminar") }
        )

        Button(onClick = {
            viewModel.eliminarReserva(
                id.toInt(),
                onSuccess = {
                    Toast.makeText(context, "Reserva eliminada correctamente", Toast.LENGTH_SHORT).show()
                },
                onError = {
                    Toast.makeText(context, "Error al eliminar la reserva", Toast.LENGTH_SHORT).show()
                }
            )
        }) {
            Text("Eliminar Reserva")
        }
    }
}