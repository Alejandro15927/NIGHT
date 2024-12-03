package com.sajla.nightguard_31.views.main

import android.app.Activity
import android.os.Handler
import android.os.Looper
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.sajla.nightguard_31.R
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import com.sajla.nightguard_31.components.hour.CustomTimePicker
import com.sajla.nightguard_31.components.images.CustomImage
import com.sajla.nightguard_31.components.textfields.CustomOutlinedTextField
import com.sajla.nightguard_31.viewmodel.time.TimeViewModel
import kotlinx.coroutines.delay

@Composable
fun StartView(navController: NavController) {
    val timeViewModel: TimeViewModel = viewModel()
    val timeState = timeViewModel.state
    var isFirstImage by remember { mutableStateOf(true) }
    val context = LocalContext.current
    var isAppLocked by remember { mutableStateOf(false) }
    var isCardLocked by remember { mutableStateOf(false) }
    var remainingTime by remember { mutableStateOf(0L) }
    var isButtonEnabled by remember { mutableStateOf(true) }

    LaunchedEffect(isAppLocked) {
        while (isAppLocked && remainingTime > 0) {
            delay(1000)
            remainingTime -= 1000
        }
        if (remainingTime <= 0) {
            isAppLocked = false
            isCardLocked = false
            isButtonEnabled = true
        }
    }

    Box(modifier = Modifier.fillMaxSize()) {

        if (isAppLocked) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.Black.copy(alpha = 0.5f))
                    .align(Alignment.Center)
                    .clickable(enabled = false) { }
            ) {
                CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
            }
        }

        if (isCardLocked) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.Black.copy(alpha = 0.5f))
                    .align(Alignment.Center)
                    .clickable(enabled = false) { }
            ) {
                CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
            }
        }


        IconButton(
            onClick = { navController.navigate("Home") },
            modifier = Modifier
                .align(Alignment.TopEnd)
                .padding(16.dp)
        ) {
            Icon(
                painter = painterResource(id = R.drawable.user_icon),
                contentDescription = "User Button",
                tint = Color.Gray
            )
        }

        IconButton(
            onClick = { navController.navigate("Login") },
            modifier = Modifier
                .align(Alignment.TopStart)
                .padding(16.dp)
        ) {
            Icon(
                painter = painterResource(id = R.drawable.flechaizquierda),
                contentDescription = "Back Button",
                tint = Color.Gray
            )
        }

        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            elevation = CardDefaults.cardElevation(8.dp)
        ) {
            Column(
                modifier = Modifier.padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Text(
                    text = "¿Cuánto tiempo va a estar apagado el dispositivo?",
                    style = MaterialTheme.typography.bodyLarge,
                    fontWeight = FontWeight.Bold
                )
                CustomTimePicker(
                    context = context,
                    initialHour = timeState.hour,
                    initialMinute = timeState.minute,
                    onTimeSelected = { formattedTime ->
                        if (!isAppLocked) {
                            val (hour, minute) = formattedTime.split(":").map { it.toInt() }
                            timeViewModel.onTimeSelected(hour, minute)
                            remainingTime = (hour * 60 + minute) * 60000L
                        }
                    }
                )
            }
        }

        val currentImage = if (isFirstImage) R.drawable.boton1 else R.drawable.boton2

        Column(
            modifier = Modifier.align(Alignment.Center),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Box(
                modifier = Modifier
                    .padding(8.dp)
                    .shadow(
                        elevation = 16.dp,
                        shape = CircleShape,
                        clip = false
                    )
                    .background(
                        color = if (isFirstImage) Color(0xAAFF0000) else Color(0xAA00FF00),
                        shape = CircleShape
                    )
                    .padding(16.dp)
            ) {
                CustomImage(image = currentImage, description = "App Logo")
            }

            Spacer(modifier = Modifier.height(20.dp))

            Button(
                onClick = {
                    if (isButtonEnabled) {
                        isButtonEnabled = false
                        isFirstImage = !isFirstImage

                        val lockDuration = (timeState.hour * 60 + timeState.minute) * 60 * 1000L

                        isAppLocked = true
                        isCardLocked = true
                        val activity = context as? Activity
                        activity?.startLockTask()

                        Handler(Looper.getMainLooper()).postDelayed({
                            activity?.stopLockTask()
                            isAppLocked = false
                            isCardLocked = false
                        }, lockDuration)
                    }
                },
                enabled = isButtonEnabled,
                colors = ButtonDefaults.buttonColors(
                    contentColor = Color.White,
                    containerColor = Color(0xFF008577)
                )
            ) {
                Text(text = "Start App", fontWeight = FontWeight.Bold)
            }
            Spacer(modifier = Modifier.height(16.dp))

            if (isAppLocked) {
                val hours = (remainingTime / 3600000).toInt()
                val minutes = ((remainingTime % 3600000) / 60000).toInt()
                val seconds = ((remainingTime % 60000) / 1000).toInt()
                val formattedTime = String.format("%02d:%02d:%02d", hours, minutes, seconds)
                Text(
                    text = "Tiempo restante: $formattedTime",
                    style = MaterialTheme.typography.bodyLarge,
                    modifier = Modifier.padding(top = 16.dp),
                    fontWeight = FontWeight.Bold
                )
            }
        }

        Row(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceAround
        ) {
            IconButton(
                onClick = { navController.navigate("Home") },
                enabled = !isAppLocked
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.home),
                    contentDescription = "Home",
                    tint = if (isAppLocked) Color.Gray else Color.Black
                )
            }

            IconButton(
                onClick = { navController.navigate("InfoView") },
                enabled = !isAppLocked
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.info),
                    contentDescription = "Contacts",
                    tint = if (isAppLocked) Color.Gray else Color.Black
                )
            }
        }
    }
}