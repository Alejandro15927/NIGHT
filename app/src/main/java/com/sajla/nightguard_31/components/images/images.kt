package com.sajla.nightguard_31.components.images

import androidx.compose.foundation.Image
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import com.sajla.nightguard_31.R

@Composable
fun CustomImage(image: Int, description: String = ""){
    Image(painter = painterResource(id = image), contentDescription = description)
}