package com.sajla.nightguard_31.views.info

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.compose.ui.res.painterResource
import com.sajla.nightguard_31.R

@Composable
fun InfoView(navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Información",
            style = MaterialTheme.typography.headlineMedium,
            modifier = Modifier.padding(bottom = 24.dp)
        )

        Button(
            onClick = {
                val intent = Intent(Intent.ACTION_VIEW).apply {
                    data = Uri.parse("https://www.tu-enlace-al-manual.com/manual.pdf")
                }
                navController.context.startActivity(intent)
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = "Manual de Usuario")
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceAround
        ) {
            IconButton(onClick = { navController.navigate("Home") }) {
                Icon(
                    painter = painterResource(id = R.drawable.home),
                    contentDescription = "Home",
                    tint = MaterialTheme.colorScheme.primary
                )
            }

            IconButton(onClick = {navController.navigate("InfoView") }) {
                Icon(
                    painter = painterResource(id = R.drawable.info),
                    contentDescription = "Info",
                    tint = MaterialTheme.colorScheme.primary
                )
            }
        }
    }
}