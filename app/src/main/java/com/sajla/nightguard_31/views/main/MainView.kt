package com.sajla.nightguard_31.views.main

import android.annotation.SuppressLint
import androidx.compose.material3.Button
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import com.sajla.nightguard_31.R

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun MainView (navController: NavController) {

    Scaffold (
        topBar = {
            CenterAlignedTopAppBar(title = { Text(text = "NightGuard") },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = colorResource(id = R.color.purple_text)
                ))
        }
    ) {
        ContentMainView(navController)
    }
}

@Composable
fun ContentMainView(navController: NavController){
    Button(onClick = { navController.navigate("Login") }) {
        Text(text = "Cerrar Sesión")
    }


}