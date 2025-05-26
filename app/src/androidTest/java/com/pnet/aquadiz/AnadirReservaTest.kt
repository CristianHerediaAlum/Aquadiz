package com.pnet.aquadiz

import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.navigation.compose.rememberNavController
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.MediumTest
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import com.pnet.aquadiz.operaciones.AñadirReservaScreen


@RunWith(AndroidJUnit4::class)
@MediumTest
class AñadirReservaTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun muestraMensajeErrorSiCamposEstanVacios() {
        composeTestRule.setContent {
            AñadirReservaScreen(navController = rememberNavController())
        }

        // Clic sin llenar campos
        composeTestRule.onNodeWithText("Añadir Reserva").performClick()

        // Verifica que aparece el mensaje de error en la UI
        composeTestRule.onNodeWithTag("ErrorMessage")
            .assertIsDisplayed()
    }


}
