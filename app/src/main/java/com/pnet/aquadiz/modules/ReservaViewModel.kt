package com.pnet.aquadiz.modules

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch


class ReservaViewModel : ViewModel(){
    //Mantiene el estado de la lista de Reservas

    var message by mutableStateOf("")
    private val api = RetrofitClient.api

    private val _Reservas = MutableStateFlow<List<Reserva>>(emptyList())
    var ReservaDetail by mutableStateOf<Reserva?>(null)
        private set


    val Reservas: StateFlow<List<Reserva>> = _Reservas


    fun obtenerReservas() {
        viewModelScope.launch {
            try {
                _Reservas.value = api.getReservas()
            } catch (e: Exception) {
                Log.e("ReservaViewModel", "Error al OBTENER Reservas", e)
            }
        }
    }

    fun buscarReservaPorId(id: Int) {
        viewModelScope.launch {
            try {
                ReservaDetail = api.getReservaById(id)
            } catch (e: Exception) {
                Log.e("ReservaViewModel", "Error al OBTENER Reserva por ID", e)
                ReservaDetail = null
            }
        }
    }
    //POST
    fun agregarReserva(
        reserva: Reserva,
        onSuccess: () -> Unit,
        onError: () -> Unit
    ) {
        viewModelScope.launch {
            try {
                val created = api.crearReserva(reserva)
                message = "Reserva creada"
                obtenerReservas()
                onSuccess() // Llamas al callback si todo va bien
            } catch (e: Exception) {
                Log.e("ReservaViewModel", "Error al AGREGAR Reserva", e)
                onError() // Llamas al callback si hay un error
            }
        }
    }


    // PUT
    fun editarReserva(id: Int, Reserva: Reserva, onSuccess: () -> Unit, onError: () -> Unit) {
        viewModelScope.launch {
            try {
                val mod = api.updateReserva(id, Reserva)
                message = "Editado completamente: ${mod.id}"
                obtenerReservas()
                onSuccess()
            } catch (e: Exception) {
                Log.e("ReservaViewModel", "Error al EDITAR (PUT) Reserva", e)
                onError()
            }
        }
    }
    // PATCH
    fun patchReserva(id: Int, fields: Map<String, Any>) {
        viewModelScope.launch {
            try {
                val patched = api.patchReserva(id, fields)
                message = "Editado parcialmente: ${patched.id}"
                obtenerReservas()
            } catch (e: Exception) {
                Log.e("ReservaViewModel", "Error al EDITAR PARCIALMENTE (PATCH) Reserva", e)
            }

        }
    }
    // DELETE
    fun eliminarReserva(
        id: Int,
        onSuccess: () -> Unit,
        onError: () -> Unit
    ) {
        viewModelScope.launch {
            try {
                api.deleteReserva(id)
                message = "Eliminado ID: $id"
                obtenerReservas()
                onSuccess()
            } catch (e: Exception) {
                Log.e("ReservaViewModel", "Error al ELIMINAR Reserva", e)
                onError()
            }
        }
    }

}