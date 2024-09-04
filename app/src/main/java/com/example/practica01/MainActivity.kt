package com.example.practica01

import android.Manifest
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.core.content.ContextCompat.startActivity


class MainActivity : AppCompatActivity() {

    private val CHANNEL_ID = "my_channel_id"
    private lateinit var handler: Handler
    private var isTimerRunning = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Verifica y solicita permisos si es necesario
        checkNotificationPermission()

        // Crear el canal de notificación
        createNotificationChannel()

        handler = Handler(Looper.getMainLooper())

        val btnIniciarCronometro: Button = findViewById(R.id.btnIniciarCronometro)
        val btnDetenerCronometro: Button = findViewById(R.id.btnDetenerCronometro)

        btnIniciarCronometro.setOnClickListener {
            if (!isTimerRunning) {
                startTimer()
                isTimerRunning = true
            }
        }

        btnDetenerCronometro.setOnClickListener {
            stopTimer()
            isTimerRunning = false
        }



        // Configura los botones de compra
        val btnComprar1: Button = findViewById(R.id.btncomprar1)
        val btnComprar2: Button = findViewById(R.id.btncomprar2)
        val btnComprar3: Button = findViewById(R.id.btncomprar3)
        val btnComprar4: Button = findViewById(R.id.btncomprar4)

        // Configura los botones de detalle
        val btnDetalle1: Button = findViewById(R.id.btnDetalle1)
        val btnDetalle2: Button = findViewById(R.id.btnDetalle2)
        val btnDetalle3: Button = findViewById(R.id.btnDetalle3)
        val btnDetalle4: Button = findViewById(R.id.btnDetalle4)

        btnComprar1.setOnClickListener {
            sendNotification("Compra realizada", "Has comprado YEEZY100")
        }
        btnComprar2.setOnClickListener {
            sendNotification("Compra realizada", "Has comprado YEEZY250")
        }
        btnComprar3.setOnClickListener {
            sendNotification("Compra realizada", "Has comprado YEEZY300")
        }
        btnComprar4.setOnClickListener {
            sendNotification("Compra realizada", "Has comprado YEEZY470")
        }

        // Configura los botones de detalle con las imágenes correspondientes
        btnDetalle1.setOnClickListener {
            openProductDetailActivity("YEEZY100", "Los YEEZY100 son un par de tenis realizados por KanyeWest orginario de Chicago, fueron desarrollados\n en colaboracion con Adidas fueron unos de los primeros tenis que YE realizo dentro de su propia marca llamada: YEEZY\n los colores dentro de la zapatilla fueron inspirados de su otro reciente lanzamiento del artista llamado: Graduation, Kanye lanzó los tenis junto con su disco\n para ofrecer una experiencia completa a todos sus seguidores tanto de su musica como de los tenis", R.drawable.yeezy)
        }
        btnDetalle2.setOnClickListener {
            openProductDetailActivity("YEEZY250", "Los YEEZY250 son un par de tenis realizados por KanyeWest orginario de Chicago, fueron desarrollados\n" +
                    " en colaboracion con Adidas fueron unos de los primeros tenis que YE realizo dentro de su propia marca llamada: YEEZY\n" +
                    " los colores dentro de la zapatilla fueron inspirados de su otro reciente lanzamiento del artista llamado: Graduation, Kanye lanzó los tenis junto con su disco\n" +
                    " para ofrecer una experiencia completa a todos sus seguidores tanto de su musica como de los tenis", R.drawable.yeezy2)
        }
        btnDetalle3.setOnClickListener {
            openProductDetailActivity("YEEZY300", "Los YEEZY300 son un par de tenis realizados por KanyeWest orginario de Chicago, fueron desarrollados\n" +
                    " en colaboracion con Adidas fueron unos de los primeros tenis que YE realizo dentro de su propia marca llamada: YEEZY\n" +
                    " los colores dentro de la zapatilla fueron inspirados de su otro reciente lanzamiento del artista llamado: Graduation, Kanye lanzó los tenis junto con su disco\n" +
                    " para ofrecer una experiencia completa a todos sus seguidores tanto de su musica como de los tenis", R.drawable.yeezy3)
        }
        btnDetalle4.setOnClickListener {
            openProductDetailActivity("YEEZY470", "Los YEEZY470 son un par de tenis realizados por KanyeWest orginario de Chicago, fueron desarrollados\n" +
                    " en colaboracion con Adidas fueron unos de los primeros tenis que YE realizo dentro de su propia marca llamada: YEEZY\n" +
                    " los colores dentro de la zapatilla fueron inspirados de su otro reciente lanzamiento del artista llamado: Graduation, Kanye lanzó los tenis junto con su disco\n" +
                    " para ofrecer una experiencia completa a todos sus seguidores tanto de su musica como de los tenis", R.drawable.yeezy4)
        }
    }

    private fun startTimer() {
        handler.postDelayed({
            val intent = Intent(this, InfoActivity::class.java)
            startActivity(intent)
        }, 30000) // 30 segundos
    }

    private fun stopTimer() {
        handler.removeCallbacksAndMessages(null)
    }

    private fun createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val name = "Mi Canal de Notificaciones"
            val descriptionText = "Descripción del canal"
            val importance = NotificationManager.IMPORTANCE_DEFAULT
            val channel = NotificationChannel(CHANNEL_ID, name, importance).apply {
                description = descriptionText
            }
            val notificationManager: NotificationManager =
                getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)
        }
    }

    private fun sendNotification(title: String, text: String) {
        // Verifica si se tienen permisos para mostrar notificaciones
        if (ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.POST_NOTIFICATIONS
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            // Si no se tienen permisos, solicita permisos
            ActivityCompat.requestPermissions(
                this,
                arrayOf(Manifest.permission.POST_NOTIFICATIONS),
                1
            )
            return
        }

        // Crea un Intent para abrir la MainActivity cuando se toque la notificación
        val intent = Intent(this, MainActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        }

        val pendingIntent: PendingIntent = PendingIntent.getActivity(
            this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
        )

        // Construye la notificación
        val builder = NotificationCompat.Builder(this, CHANNEL_ID)
            .setSmallIcon(R.drawable.ic_launcher_foreground) // Verifica que este ícono exista
            .setContentTitle(title)
            .setContentText(text)
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .setContentIntent(pendingIntent)
            .setAutoCancel(true)

        // Obtén el NotificationManager
        val notificationManager = NotificationManagerCompat.from(this)

        // Intenta enviar la notificación
        try {
            notificationManager.notify(1, builder.build())
        } catch (e: Exception) {
            e.printStackTrace() // Imprime la traza de la pila en caso de error
        }
    }

    private fun checkNotificationPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            if (ActivityCompat.checkSelfPermission(
                    this,
                    Manifest.permission.POST_NOTIFICATIONS
                ) != PackageManager.PERMISSION_GRANTED
            ) {
                ActivityCompat.requestPermissions(
                    this,
                    arrayOf(Manifest.permission.POST_NOTIFICATIONS),
                    1
                )
            }
        }
    }

    // Método actualizado para abrir ProductoDetalleActivity y pasar la imagen
    private fun openProductDetailActivity(title: String, description: String, imageResId: Int) {
        val intent = Intent(this, ProductoDetalle::class.java).apply {
            putExtra("PRODUCT_TITLE", title)
            putExtra("PRODUCT_DESCRIPTION", description)
            putExtra("PRODUCT_IMAGE", imageResId) // Pasar la ID de la imagen
        }
        startActivity(intent)
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == 1) {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // Permiso concedido, puedes enviar notificaciones aquí si es necesario
            } else {
                // Permiso denegado, maneja la situación según sea necesario
            }
        }
    }
}










