package com.example.devhub

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.devhub.ui.theme.DevhubTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DevhubTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    Usuario()
                }
            }
        }
    }
}

@Composable
fun Usuario(){
    val boxHeight = remember {
        150.dp
    }
    val imageHeight = remember {
        boxHeight
    }
    Column {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(
                    Color(R.color.dark_blue)
                )
                .height(boxHeight)
        ) {
            Image(
                painterResource(id = R.drawable._18298414),
                contentDescription = "foto de perfil",
                modifier = Modifier
                    .offset(y = imageHeight / 2)
                    .size(imageHeight)
                    .align(Alignment.BottomCenter)
                    .clip(CircleShape),
            )
        }
        Spacer(modifier = Modifier.height(imageHeight / 2))
        Column(
            Modifier
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                "jmarcoshss",
                fontSize = 30.sp,
                fontWeight = FontWeight.Bold
            )
            Text(
                "João Marcos Henrique dos Santos Santana",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
            )
            Text(
                "dev mobile em formação"
            )
        }
    }

}



@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    DevhubTheme {
        Usuario()
    }
}