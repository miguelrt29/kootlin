package com.example.myapplication.componentes

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.myapplication.model.Product

@Composable
fun ProductItem(
    product: Product,
    onAddClick: () -> Unit,
    onRemoveClick: () -> Unit   
) {

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        elevation = CardDefaults.cardElevation(4.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFFF8F8F8))
    ) {

        Row(
            modifier = Modifier.padding(12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {

            AsyncImage(
                model = product.imageUrl,
                contentDescription = product.name,
                modifier = Modifier
                    .size(70.dp)
                    .clip(RoundedCornerShape(14.dp))
            )

            Spacer(modifier = Modifier.width(12.dp))

            Column(modifier = Modifier.weight(1f)) {
                Text(product.name, fontSize = 20.sp)
                Text(
                    text = "Precio: ${product.price}",
                    fontSize = 15.sp,
                    color = Color.Gray
                )
            }

            // -------------------------
            // BOTÓN AGREGAR
            // -------------------------
            Box(
                modifier = Modifier
                    .background(Color(0xFF4CAF50), RoundedCornerShape(10.dp))
                    .clickable { onAddClick() }
                    .padding(vertical = 8.dp, horizontal = 10.dp)
            ) {
                Text("Agregar", color = Color.White, fontSize = 14.sp)
            }

            Spacer(modifier = Modifier.width(8.dp))

            // -------------------------
            // BOTÓN ELIMINAR
            // -------------------------
            Box(
                modifier = Modifier
                    .background(Color(0xFFE53935), RoundedCornerShape(10.dp))
                    .clickable { onRemoveClick() }
                    .padding(vertical = 8.dp, horizontal = 10.dp)
            ) {
                Text("Eliminar", color = Color.White, fontSize = 14.sp)
            }
        }
    }
}
