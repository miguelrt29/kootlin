package com.example.myapplication.componentes

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun PurchaseSummary(
    totalProducts: Int,
    totalPrice: String
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = "Productos totales: $totalProducts", fontSize = 18.sp)
            Text(text = "Valor final: $totalPrice", fontSize = 20.sp)
        }
    }
}
