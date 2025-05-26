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
import com.pnet.aquadiz.operaciones.EditarReservaScreen


@RunWith(AndroidJUnit4::class)
@MediumTest
class EditarReservaTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun muestraMensajeErrorSiCamposEstanVacios_editar() {
        composeTestRule.setContent {
            EditarReservaScreen(navController = rememberNavController(), reservaId = 1)
        }

        // Click en actualizar
        composeTestRule.onNodeWithText("Actualizar Reserva").performClick()

        // Comprueba que se muestra mensaje de error
        composeTestRule.onNodeWithTag("ErrorMessage")
            .assertIsDisplayed()
            .assertTextContains("Debe llenar los campos obligatorios")
    }
}
