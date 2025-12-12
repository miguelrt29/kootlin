package com.example.myapplication.componentes

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun ProductCounter(
    name: String,
    priceText: String,   // Ahora viene como String correctamente
    count: Int,
    onCountChange: (Int) -> Unit,
    onNameChange: (String) -> Unit,
    onPriceChange: (String) -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(modifier = Modifier.weight(1f).padding(end = 16.dp)) {

            // Campo de texto para el nombre del producto
            OutlinedTextField(
                value = name,
                onValueChange = onNameChange,
                label = { Text("Nombre del Producto") },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true,
                textStyle = androidx.compose.ui.text.TextStyle(fontSize = 16.sp)
            )

            Spacer(modifier = Modifier.height(8.dp))

            // Campo de texto para el precio unitario
            OutlinedTextField(
                value = priceText,
                onValueChange = { newValue ->
                    // Filtramos solo los dígitos
                    onPriceChange(newValue.filter { it.isDigit() })
                },
                label = { Text("Precio Unitario") },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true,
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                textStyle = androidx.compose.ui.text.TextStyle(fontSize = 16.sp)
            )
        }

        // Columna para los botones de contador
        Column(horizontalAlignment = Alignment.CenterHorizontally) {

            // Botón para aumentar el contador
            Button(
                onClick = { onCountChange(count + 1) },
                modifier = Modifier.size(35.dp),
                contentPadding = PaddingValues(0.dp)
            ) {
                Text("+", fontSize = 20.sp)
            }

            Spacer(modifier = Modifier.height(4.dp))

            // Mostrar la cantidad
            Text(text = "$count", fontSize = 18.sp)

            Spacer(modifier = Modifier.height(4.dp))

            // Botón para disminuir el contador
            Button(
                onClick = { if (count > 0) onCountChange(count - 1) },
                modifier = Modifier.size(35.dp),
                contentPadding = PaddingValues(0.dp)
            ) {
                Text("-", fontSize = 20.sp)
            }
        }
    }
}
