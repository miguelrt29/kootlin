package com.example.myapplication.model

data class Product(
    val id: Int,
    val name: String,
    val price: Int,
    val imageUrl: String
)

val productsDummy = listOf(
    Product(1, "Manzanas", 2000, "https://static.vecteezy.com/system/resources/previews/009/887/176/non_2x/red-apples-free-png.png"),
    Product(2, "Pan integral", 3500, "https://static.vecteezy.com/system/resources/previews/010/175/168/non_2x/sliced-whole-wheat-bread-cutout-file-png.png"),
    Product(3, "Huevos x12", 12000, "https://static.vecteezy.com/system/resources/previews/019/908/317/non_2x/fresh-chicken-eggs-in-woven-bamboo-basket-isolated-with-clipping-path-in-file-format-close-up-with-full-focus-png.png"),
    Product(4, "Leche", 4500, "https://static.vecteezy.com/system/resources/thumbnails/048/094/360/small/a-milk-glass-with-splash-on-transparent-backdrop-free-png.png"),
    Product(5, "Aguacate", 3000, "https://static.vecteezy.com/system/resources/previews/019/782/664/non_2x/avocado-cartoon-character-free-png.png"),
)
