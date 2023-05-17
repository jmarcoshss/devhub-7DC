package com.example.devhub.ui.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
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
import com.example.devhub.R
import com.example.devhub.model.toProfileUiState
import com.example.devhub.ui.theme.DevhubTheme
import com.example.devhub.webclient.GitHubWebClient


@Composable
fun TelaDePerfil(
    user:String,
    webClient: GitHubWebClient = GitHubWebClient()
){
    val foundUser by webClient.findProfileBy(user)
        .collectAsState(initial = null)
    foundUser?.let{userProfile ->
        val state = userProfile.toProfileUiState()
        Profile(state)

    }
}
@Composable
private fun Profile(state: ProfileUiState){
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
                state.image,
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
                state.name,
                fontSize = 30.sp,
                fontWeight = FontWeight.Bold
            )
            Text(
                state.user,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
            )
            Text(
                state.bio
            )
        }
    }
}



@Preview(showBackground = true)
@Composable
fun ProfilePreview() {
    Profile(
        state = ProfileUiState(
            user = "jmarcoshss",
            image = "https://avatars.githubusercontent.com/u/118298414?v=4",
            name = "João Marcos Henrique dos Santos Santana",
            bio = "dev mobile em construção"
        )
    )
}
data class ProfileUiState(
    val user: String = "",
    val image: String = "",
    val name: String = "",
    val bio: String = ""
)