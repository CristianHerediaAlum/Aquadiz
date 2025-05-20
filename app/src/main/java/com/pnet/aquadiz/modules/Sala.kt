package com.pnet.aquadiz.modules

data class Sala(
    val nombre: String,
    val precio: String,
    val caracteristica1: String,
    val caracteristica2: String,
    val instalacion1: String,
    val instalacion2: String,
    val imagenPath: String? = null // Ruta a imagen en almacenamiento interno
)

