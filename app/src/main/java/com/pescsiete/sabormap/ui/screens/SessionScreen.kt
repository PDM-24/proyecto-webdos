package com.pescsiete.sabormap.ui.screens

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.pescsiete.sabormap.ui.theme.SaborMapTheme
import com.pescsiete.sabormap.R

@Composable
fun SessionScreen() {
    SaborMapTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.primary
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.padding(16.dp)
                        .fillMaxWidth()
                ) {
                    Image(
                        painter = painterResource(id = R.mipmap.ic_launcher_foreground),
                        contentDescription = "Logo de SaborMap",
                        modifier = Modifier.size(150.dp) // Aumenta el tamaño del logo
                    )
                    Text(
                        text = "SaborMap",
                        color = Color.White,
                        style = MaterialTheme.typography.headlineMedium.copy(
                            fontSize = 35.sp, // Aumenta el tamaño del texto
                            fontWeight = FontWeight.Bold // Hace el texto más grueso
                        )
                    )
                }
                Spacer(modifier = Modifier.height(32.dp))
                Button(
                    onClick = {  },
                    modifier = Modifier
                        .height(48.dp)
                        .fillMaxWidth(0.8f),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.White,
                        contentColor = MaterialTheme.colorScheme.primary
                    )
                ) {
                    Text(text = "Login",
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold)
                }
                Spacer(modifier = Modifier.height(16.dp))
                Button(
                    onClick = {  },
                    modifier = Modifier
                        .height(48.dp)
                        .fillMaxWidth(0.8f),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.White,
                        contentColor = MaterialTheme.colorScheme.primary
                    )
                ) {
                    Text(text = "Register",
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold)
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun SessionScreenPreview() {
    SessionScreen()
}
