package com.example.devhub

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.devhub.ui.theme.DevhubTheme
import com.example.devhub.webclient.GitHubWebClient

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
                    TelaDeUsuario("jmarcoshss")
                }
            }
        }
    }
}

@Composable
fun TelaDeUsuario(
    user:String,
    webClient: GitHubWebClient = GitHubWebClient()
){
    val foundUser by webClient.findProfileBy(user)
        .collectAsState(initial = null)
    foundUser?.let{userProfile ->
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
                AsyncImage(
                    userProfile.avatar,
                    contentDescription = "userProfile pic",
                    placeholder = painterResource(R.drawable.user_placeholder),
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
                    userProfile.login,
                    fontSize = 30.sp,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    userProfile.name,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    userProfile.bio
                )
            }
        }
    }



}



@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    DevhubTheme {
        TelaDeUsuario("jmarcoshss")
    }
}