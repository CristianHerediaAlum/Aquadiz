package com.pnet.aquadiz.modules

data class Reserva(
    val id: Int,
    val sala: String,
    val nickname: String,
    val nSocio: Int,
    val email: String,
    val fecha: String,
    val numPersonas: Int,
    val comentarios: String
)
