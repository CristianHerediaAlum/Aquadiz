package com.pnet.aquadiz.operaciones

import android.content.Context
import android.graphics.Paint
import android.graphics.pdf.PdfDocument
import android.widget.Toast
import com.pnet.aquadiz.modules.Sala
import java.io.File
import java.io.FileOutputStream
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Typeface
import androidx.compose.runtime.Composable

fun generarPdfSalas(context: Context, sala: Sala) {
    val document = PdfDocument()
    val pageInfo = PdfDocument.PageInfo.Builder(595, 842, 1).create() // A4
    val page = document.startPage(pageInfo)
    val canvas = page.canvas
    val paint = Paint().apply {
        textSize = 14f
        typeface = Typeface.create(Typeface.DEFAULT, Typeface.BOLD)
    }

    var y = 50
//    salas.forEachIndexed { index, sala ->
        // Título de la sala
        paint.textSize = 16f
        canvas.drawText("Sala : ${sala.nombre}", 50f, y.toFloat(), paint)
        y += 25

        paint.textSize = 14f
        paint.typeface = Typeface.create(Typeface.DEFAULT, Typeface.NORMAL)

        // Información básica
        canvas.drawText("Precio: ${sala.precio}", 50f, y.toFloat(), paint)
        y += 20
        sala.caracteristicas.forEach {
            canvas.drawText(it, 50f, y.toFloat(), paint)
            y += 20
        }
//        canvas.drawText("Característica 1: ${sala.caracteristica1}", 50f, y.toFloat(), paint)
//        y += 20
//        canvas.drawText("Característica 2: ${sala.caracteristica2}", 50f, y.toFloat(), paint)
//        y += 20

        // Imagen si existe
        sala.imagenPath?.let {
            val bitmap = BitmapFactory.decodeFile(it)
            bitmap?.let {
                val scaled = Bitmap.createScaledBitmap(bitmap, 200, 120, true)
                canvas.drawBitmap(scaled, 50f, y.toFloat(), null)
                y += 130
            }
        }

        // Tabla simulada de instalaciones
        canvas.drawText("Instalaciones:", 50f, y.toFloat(), paint)
        y += 20
        sala.instalaciones.forEach {
            canvas.drawText("- $it", 70f, y.toFloat(), paint)
            y += 20
        }
//        y += 20
//        canvas.drawText("- ${sala.instalacion1}", 70f, y.toFloat(), paint)
//        y += 20
//        canvas.drawText("- ${sala.instalacion2}", 70f, y.toFloat(), paint)
        y += 40
//    }

    document.finishPage(page)

    val file = File(context.getExternalFilesDir(null), "salas_info.pdf")
    document.writeTo(FileOutputStream(file))
    document.close()

    Toast.makeText(context, "PDF guardado en: ${file.absolutePath}", Toast.LENGTH_LONG).show()
}
