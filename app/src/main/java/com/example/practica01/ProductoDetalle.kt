package com.example.practica01

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class ProductoDetalle : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_producto_detalle)

        // Obtener el Intent y los datos extra
        val title = intent.getStringExtra("PRODUCT_TITLE")
        val description = intent.getStringExtra("PRODUCT_DESCRIPTION")
        val imageResId = intent.getIntExtra("PRODUCT_IMAGE", 0)

        // Configurar los Texx|tViews con los datos
        val titleTextView: TextView = findViewById(R.id.productDetailTitle)
        val descriptionTextView: TextView = findViewById(R.id.productDetailDescription)
        val imageView: ImageView = findViewById(R.id.productDetailImage)

        titleTextView.text = title
        descriptionTextView.text = description

        // Configurar la imagen con el recurso recibido
        if (imageResId != 0) {
            imageView.setImageResource(imageResId)
        }
    }
}
