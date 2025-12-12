package com.example.myapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myapplication.model.Product
import com.example.myapplication.componentes.ProductItem
import com.example.myapplication.componentes.PurchaseSummary
import com.example.myapplication.ui.theme.MyApplicationTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApplicationTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    MyShoppingScreen()
                }
            }
        }
    }
}

@Composable
fun MyShoppingScreen() {

    // ---------- ESTADO DEL PRESUPUESTO ----------
    var budgetText by remember { mutableStateOf("") }
    val budget = budgetText.toIntOrNull() ?: 0

    // ---------- PRODUCTOS DUMMY ----------
    val productsDummy = remember {
        listOf(
            Product(1, "Manzanas", 2000, "https://static.vecteezy.com/system/resources/previews/009/887/176/non_2x/red-apples-free-png.png"),
            Product(2, "Pan integral", 3500, "https://static.vecteezy.com/system/resources/previews/010/175/168/non_2x/sliced-whole-wheat-bread-cutout-file-png.png"),
            Product(3, "Huevos x12", 12000, "https://static.vecteezy.com/system/resources/previews/019/908/317/non_2x/fresh-chicken-eggs-in-woven-bamboo-basket-isolated-with-clipping-path-in-file-format-close-up-with-full-focus-png.png"),
            Product(4, "Leche", 4500, "https://static.vecteezy.com/system/resources/thumbnails/048/094/360/small/a-milk-glass-with-splash-on-transparent-backdrop-free-png.png"),
            Product(5, "Aguacate", 3000, "https://static.vecteezy.com/system/resources/previews/019/782/664/non_2x/avocado-cartoon-character-free-png.png"),
        )
    }

    // ---------- ESTADO DE CANTIDADES POR PRODUCTO ----------
    val counts = remember { mutableStateMapOf<Int, Int>() }

    // ---------- CÁLCULO DEL TOTAL DE LA COMPRA ----------
    val totalCompra = productsDummy.sumOf { product ->
        val qty = counts[product.id] ?: 0
        qty * product.price
    }

    val formattedTotal = "%,d".format(totalCompra)

    val budgetColor =
        if (totalCompra <= budget || budget == 0) Color(0xFF4CAF50)
        else Color(0xFFFF5252)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {

        // ---------- CAMPO DE PRESUPUESTO ----------
        OutlinedTextField(
            value = budgetText,
            onValueChange = { budgetText = it.filter { it.isDigit() } },
            label = { Text("Presupuesto") },
            modifier = Modifier.fillMaxWidth(),
            singleLine = true
        )

        Spacer(modifier = Modifier.height(12.dp))

        // ---------- TOTAL COMPRA ----------
        Text(
            text = "Total: $formattedTotal",
            fontSize = 24.sp,
            color = budgetColor
        )

        Spacer(modifier = Modifier.height(12.dp))

        // ---------- LISTA DE PRODUCTOS ----------
        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
        ) {
            items(productsDummy) { product ->
                ProductItem(
                    product = product,
                    onAddClick = {
                        // Incrementamos la cantidad del producto en el mapa 'counts'
                        val currentCount = counts[product.id] ?: 0
                        counts[product.id] = currentCount + 1
                    },
                    onRemoveClick = {
                        // Decrementamos la cantidad del producto en el mapa 'counts'
                        val currentCount = counts[product.id] ?: 0
                        if (currentCount > 0) {
                            counts[product.id] = currentCount - 1
                        }
                    }
                )
            }
        }

        // ---------- RESUMEN FINAL ----------
        PurchaseSummary(
            totalProducts = counts.values.sum(),
            totalPrice = formattedTotal  // ✔ SE ENVÍA COMO STRING
        )
    }
}
