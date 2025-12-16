package com.example.myapplication.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.myapplication.componentes.ProductCounter
import com.example.myapplication.componentes.PurchaseSummary

@Composable
fun MyShoppingScreen() {

    // ---------- ESTADOS ----------
    var productName by remember { mutableStateOf("") }
    var priceText by remember { mutableStateOf("") }
    var count by remember { mutableStateOf(0) }

    val budget = 50000

    // Total con seguridad numérica
    val unitPrice = priceText.filter { it.isDigit() }.toIntOrNull() ?: 0
    val totalPrice = unitPrice * count

    // Formato con separadores
    val formattedTotal = "%,d".format(totalPrice)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {

        // ---------- PRODUCT COUNTER ----------
        ProductCounter(
            name = productName,
            priceText = priceText,
            count = count,
            onCountChange = { count = it },
            onNameChange = { productName = it },
            onPriceChange = { priceText = it }
        )

        Spacer(modifier = Modifier.height(16.dp))

        // ---------- RESUMEN ----------
        PurchaseSummary(
            totalProducts = count,
            totalPrice = "$$formattedTotal"
        )

        Spacer(modifier = Modifier.height(16.dp))

        // ---------- ELIMINAR PRODUCTO ----------
        Button(
            onClick = {
                if (count > 0) {
                    count -= 1
                }
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Eliminar Producto")
        }

        Spacer(modifier = Modifier.height(24.dp))

        // ---------- BOTÓN GUARDAR ----------
        Button(
            onClick = {
                println("Producto: $productName")
                println("Cantidad: $count")
                println("Total: $formattedTotal")
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Guardar Compra")
        }
    }
}
