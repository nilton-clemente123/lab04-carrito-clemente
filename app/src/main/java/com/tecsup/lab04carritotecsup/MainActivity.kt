package com.tecsup.lab04carritotecsup

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SegmentedButtonDefaults.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.tecsup.lab04carritotecsup.ui.theme.Lab04CarritoTecsupTheme


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Lab04CarritoTecsupTheme {
                PantallaRegistro()
            }
        }
    }
}

@Preview
@Composable
fun PantallaRegistro(modifier: Modifier = Modifier) {
    var nombre by remember { mutableStateOf("") }
    var precio by remember { mutableStateOf("") }
    var cantidad by remember { mutableStateOf("") }
    val productos = remember {
        mutableStateListOf<Producto>()
    }

    Scaffold(
        topBar = {
            Surface(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(80.dp),
                color = Color(0xFF52638F)
            ) {
                Text(
                    text = "Registro de Producto",
                    modifier = Modifier
                        .padding(16.dp)
                        .padding(top = 20.dp),
                    color = Color.White,
                    style = MaterialTheme.typography.titleLarge
                )
            }
        }
    ) { innerPadding ->

        Column(
            modifier = modifier
                .fillMaxSize()
                .padding(16.dp)
                .padding(innerPadding)
        ) {

            Text(
                text = "Nuevo producto",
                style = MaterialTheme.typography.headlineSmall
            )
            Text(
                text = "Completa los datos y presiona Agregar",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.outline
            )
            Spacer(modifier = Modifier.height(24.dp))
            OutlinedTextField(
                value = nombre,
                onValueChange = { nombre = it },
                label = {
                    Text("Nombre del producto")
                },
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(16.dp))

            Row(
                modifier = Modifier.fillMaxWidth()
            ) {

                OutlinedTextField(
                    value = precio,
                    onValueChange = { precio = it },
                    label = {
                        Text("Precio (S/)")
                    },
                    modifier = Modifier.weight(1f)
                )
                Spacer(modifier = Modifier.width(16.dp))
                OutlinedTextField(
                    value = cantidad,
                    onValueChange = { cantidad = it },
                    label = {
                        Text("Cantidad")
                    },
                    modifier = Modifier.weight(1f)
                )
            }
            Spacer(modifier = Modifier.height(24.dp))
            Button(
                onClick = {
                    val precioNum = precio.toDoubleOrNull() ?: 0.0
                    val cantidadNum = cantidad.toIntOrNull() ?: 0
                    if (nombre.isNotBlank() && precioNum > 0 && cantidadNum > 0) {
                        productos.add(Producto(nombre, precioNum, cantidadNum))
                        nombre = ""
                        precio = ""
                        cantidad = ""
                    }
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("AGREGAR")
            }

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = "Productos: ${productos.size}"
            )

            Spacer(modifier = Modifier.height(8.dp))

            // Lista de productos
            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                items(productos) { producto ->
                    TarjetaProducto(
                        producto = producto,

                        // Evento de eliminación
                        onEliminar = {
                            productos.remove(producto)
                        }
                    )
                }
            }
            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = "Desarrollado por Clemente",
                modifier = Modifier.fillMaxWidth(),
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                textAlign = TextAlign.Center
            )
        }
    }
}


@Composable
fun TarjetaProducto(
    producto: Producto,
    onEliminar: () -> Unit
) {

    Card(
        modifier = Modifier.fillMaxWidth()
    ) {

        Row(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {

            Column(
                modifier = Modifier.weight(1f)
            ) {

                // Nombre del producto
                Text(
                    text = producto.nombre,
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold
                )

                // Precio x cantidad
                Text(
                    text = "S/ ${producto.precio} x ${producto.cantidad}",
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }

            // Importe total
            Text(
                text = "Importe: S/ ${
                    "%.2f".format(producto.precio * producto.cantidad)
                }"
            )

            // Botón eliminar
            IconButton(
                onClick = onEliminar
            ) {

                Icon(
                    imageVector = Icons.Default.Delete,
                    contentDescription = "Eliminar",
                    tint = MaterialTheme.colorScheme.error
                )
            }
        }
    }
}