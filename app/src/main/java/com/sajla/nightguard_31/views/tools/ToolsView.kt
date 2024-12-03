package com.sajla.nightguard_31.views.tools

import android.content.Context
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.sajla.nightguard_31.R
import com.sajla.nightguard_31.components.hour.CustomTimePicker
import com.sajla.nightguard_31.components.lists.CustomDropdown
import com.sajla.nightguard_31.components.switchs.CustomSwitch
import com.sajla.nightguard_31.viewmodel.switch.SwitchViewModel
import com.sajla.nightguard_31.viewmodel.time.TimeViewModel
import com.sajla.nightguard_31.viewmodel.list.ListViewModel


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ToolsView(navController: NavController, context: Context) {
    val timeViewModel: TimeViewModel = viewModel()
    val switchViewModel: SwitchViewModel = viewModel()
    val listViewModel: ListViewModel = viewModel()

    val timeState = timeViewModel.state
    val switchState = switchViewModel.state.value
    val listState = listViewModel.state

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text("Herramientas", style = MaterialTheme.typography.titleLarge) }
            )
        },
        content = { innerPadding ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
                    .padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(24.dp)
            ) {
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    elevation = CardDefaults.cardElevation(8.dp)
                ) {
                    Column(
                        modifier = Modifier.padding(16.dp),
                        verticalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        Text(
                            text = "A partir de qué hora se activará:",
                            style = MaterialTheme.typography.bodyLarge,
                            fontWeight = FontWeight.Bold
                        )
                        CustomTimePicker(
                            context = context,
                            initialHour = timeState.hour,
                            initialMinute = timeState.minute,
                            onTimeSelected = { formattedTime ->
                                val (hour, minute) = formattedTime.split(":").map { it.toInt() }
                                timeViewModel.onTimeSelected(hour, minute)
                            }
                        )
                    }
                }

                Card(
                    modifier = Modifier.fillMaxWidth(),
                    elevation = CardDefaults.cardElevation(8.dp)
                ) {
                    Column(
                        modifier = Modifier.padding(16.dp),
                        verticalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        Text(
                            text = "Notificaciones:",
                            style = MaterialTheme.typography.bodyLarge,
                            fontWeight = FontWeight.Bold
                        )
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                text = if (switchState.isOn) "Activadas" else "Desactivadas",
                                style = MaterialTheme.typography.bodyMedium
                            )
                            CustomSwitch(
                                context = context,
                                isOn = switchState.isOn,
                                onToggle = { switchViewModel.toggleSwitch() }
                            )
                        }
                    }
                }

                Card(
                    modifier = Modifier.fillMaxWidth(),
                    elevation = CardDefaults.cardElevation(8.dp)
                ) {
                    Column(
                        modifier = Modifier.padding(16.dp),
                        verticalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        Text(
                            text = "Intensidad:",
                            style = MaterialTheme.typography.bodyLarge,
                            fontWeight = FontWeight.Bold
                        )
                        CustomDropdown(
                            options = listOf("Relajado", "Regular", "Exigente"),
                            label = "Selecciona intensidad",
                            onOptionSelected = { listViewModel.onOptionSelected(it) }
                        )
                        Text(
                            text = "Seleccionado: ${listState.selectedOption}",
                            style = MaterialTheme.typography.bodyMedium
                        )
                    }
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
                    IconButton(onClick = { navController.navigate("ToolsView") }) {
                        Icon(
                            painter = painterResource(id = R.drawable.settings),
                            contentDescription = "Tools",
                            tint = MaterialTheme.colorScheme.primary
                        )
                    }
                    IconButton(onClick = { navController.navigate("InfoView") }) {
                        Icon(
                            painter = painterResource(id = R.drawable.info),
                            contentDescription = "Info",
                            tint = MaterialTheme.colorScheme.primary
                        )
                    }
                }
            }
        }
    )
}