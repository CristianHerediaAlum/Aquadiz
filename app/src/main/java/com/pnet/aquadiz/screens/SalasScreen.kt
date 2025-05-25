package com.pnet.aquadiz.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.pnet.aquadiz.R
import com.pnet.aquadiz.ui.theme.azulClaro
import com.pnet.aquadiz.ui.theme.azulOscuro
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import com.pnet.aquadiz.modules.Sala
import com.pnet.aquadiz.operaciones.generarPdfSalas

@Composable
fun SalasScreen() {

    val salas = listOf<Sala>(
        Sala(
            nombre = "Sala de hidromasajes",
            precio = "10€",
            caracteristicas = listOf("Capacidad de hasta 10 personas", "Burbujas controlables para cada sección"),
            instalaciones = listOf("Agua a 30ºC", "Bar de bebidas"),
            imagenResId = R.drawable.sala_hidromasaje,
//            imagenPath = "/res/drawable/sala_hidromasaje.jpg"
        ),
        Sala(
            nombre = "Sala de baño turco",
            precio = "20€",
            caracteristicas = listOf("Disfruta de la sauna en solitario o acompañado", "Temperatura de 50ºC para una relajación total"),
            instalaciones = listOf("Asientos de madera de roble", "Dividido en salas comunes e individuales"),
            imagenResId =  R.drawable.bano_turco,
//            imagenPath = "/res/drawable/bano_turco.jpg"
        )
    )

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = azulClaro
    ) {
        Column (modifier = Modifier.fillMaxSize()) {
            Text(
                text = "Salas",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black,
                modifier = Modifier
                    .padding(16.dp)
                    .align(Alignment.CenterHorizontally)
            )
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 64.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                items(2) { index ->
                    SalaCard(sala = salas[index])
                }
            }
        }
    }
}

@Composable
fun SalaCard (sala : Sala) {
    val context = LocalContext.current
//    var expandido by remember { mutableStateOf(false) }
    Card(
        modifier = Modifier
            .fillMaxWidth(),
        shape = RoundedCornerShape(8.dp),
        colors = CardDefaults.cardColors(containerColor = Color.LightGray),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(id = sala.imagenResId),
                contentDescription = "Imagen de sala",
                modifier = Modifier.size(200.dp)
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(text = sala.nombre, fontWeight = FontWeight.Bold)
            Text(text = sala.precio)

            sala.caracteristicas.forEach {
                Text(text = it, fontSize = 12.sp, color = Color.Black)
            }

            Spacer(modifier = Modifier.height(8.dp))

//            Button(
//                onClick = { expandido = !expandido },
//                shape = RoundedCornerShape(8.dp),
//                colors = ButtonDefaults.buttonColors(containerColor = azulOscuro)
//            ) {
//                Text("Instalaciones", color = Color.Black)
//            }
//            if(expandido) {
                Column(modifier = Modifier.fillMaxWidth()) {
                    Text(text = "Instalaciones",
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 4.dp)
                            .background(azulOscuro)
                            .padding(8.dp),
                        color = Color.Black,
                        textAlign = TextAlign.Center
                    )
                    sala.instalaciones.forEach {
                        Text(
                            text = it,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 4.dp)
                                .background(Color.Gray)
                                .padding(8.dp),
                            color = Color.Black,
                            textAlign = TextAlign.Center
                        )
                    }
//                }
            }
            Spacer(modifier = Modifier.height(8.dp))

            Button(
                onClick = { generarPdfSalas(context = context, sala = sala) },
                shape = RoundedCornerShape(8.dp),
                colors = ButtonDefaults.buttonColors(containerColor = azulOscuro)
            ) {
                Icon(Icons.Default.KeyboardArrowDown, contentDescription = null, tint = Color.Black)
                Spacer(Modifier.width(8.dp))
                Text("Descargar", color = Color.Black)
            }
        }
    }
}