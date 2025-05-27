package com.pnet.aquadiz.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.compose.ui.text.font.FontWeight
import com.pnet.aquadiz.R
import com.pnet.aquadiz.ui.theme.azulClaro
import com.pnet.aquadiz.ui.theme.azulOscuro

@Composable
fun HomeScreen(navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(azulClaro)
            .padding(16.dp), // Opcional: Agrega padding para separar los elementos
        // Centramos horizontalmente y verticalmente
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceEvenly
    ) {

        Text(
            text = "Login",
            fontSize = 22.sp,
            fontWeight = FontWeight.Medium,
            color = Color.Black
        )

        Spacer(modifier = Modifier.height(24.dp))

        // Logo
        Image(
            painter = painterResource(id = R.drawable.logo),
            contentDescription = "Logo",
            modifier = Modifier.size(150.dp),
            contentScale = ContentScale.Fit
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Nombres
        Text(text = "Cristian Heredia Bravo")
        Text(text = "Rafael Pérez García")

        Spacer(modifier = Modifier.height(32.dp))

        // Botón
        Button(
            onClick = { navController.navigate("salas") },
            shape = RoundedCornerShape(8.dp),
            colors = ButtonDefaults.buttonColors(containerColor = azulOscuro)
        ) {
            Text("Entrar", color = Color.Black)
        }
    }
}
