package com.example.cleanarch.compose

import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun makeText(text: String?, fontSize: Int = 12, fontWeight: FontWeight = FontWeight.Normal, fontStyle: FontStyle = FontStyle.Normal, paddingTop: Int = 12, maxLines: Int = 100){
    Text(text = text ?: "", fontSize = fontSize.sp, fontWeight = fontWeight, fontStyle = fontStyle, modifier = Modifier.padding(top = paddingTop.dp), maxLines = maxLines, overflow = TextOverflow.Ellipsis)
}