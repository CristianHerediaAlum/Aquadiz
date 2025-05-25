package com.pnet.aquadiz.modules

import androidx.compose.ui.graphics.painter.Painter

data class Sala(
    val nombre : String,
    val precio : String,
    val caracteristicas : List<String>,
    val instalaciones : List<String>,
    val imagenResId : Int,
//    val imagenPath : String? = null
)

