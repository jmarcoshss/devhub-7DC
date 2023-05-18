package com.example.devhub.ui.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
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
import com.example.devhub.model.GitHubRepository
import com.example.devhub.webclient.GitHubWebClient


@Composable
fun TelaDePerfil(
    user:String,
    webClient: GitHubWebClient = GitHubWebClient()
){
    val uiState = webClient.uiState
    LaunchedEffect(null) {
        webClient.findProfileBy(user)
    }
    Profile(uiState)
}

@Composable
fun Profile(uiState: ProfileUiState) {
    LazyColumn {
        item {
            ProfileHeader(uiState)
        }
        item {
            if (uiState.repositories.isNotEmpty()) {
                Text(
                    text = "Repositórios", Modifier.padding(8.dp),
                    fontSize = 24.sp
                )
            }
        }
        items(uiState.repositories) { repo ->
            RepositoryItem(repo = repo)
        }
    }
}

@Composable
private fun ProfileHeader(state: ProfileUiState){
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
                    Color(R.color.dark_blue),
                    shape = RoundedCornerShape(16.dp)
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

@Composable
fun RepositoryItem(repo: GitHubRepository) {
    Card(
        modifier = Modifier.padding(8.dp),
        elevation = 4.dp
    ) {
        Column {
            Text(
                repo.name,
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color(0xFF2d333b))
                    .padding(8.dp),
                fontSize = 24.sp,
                color = Color.White
            )
            if (repo.description.isNotBlank()) {
                Text(
                    repo.description,
                    modifier = Modifier
                        .padding(8.dp)
                        .fillMaxWidth()
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun RepositoryItemPreview() {
    RepositoryItem(
        repo = GitHubRepository(
            name = "jmarcoshss",
            description = "my personal information"
        )
    )
}


@Preview(showBackground = true)
@Composable
fun ProfilePreview() {
    Profile(
        uiState = ProfileUiState(
            user = "jmarcoshss",
            image = "https://avatars.githubusercontent.com/u/118298414?v=4",
            name = "João Marcos Henrique dos Santos Santana",
            bio = "dev mobile em construção"
        )
    )
}

@Preview(showBackground = true)
@Composable
fun ProfileWithRepositoriesPreview() {
    Profile(
        uiState = ProfileUiState(
            user = "jmarcoshss",
            image = "https://avatars.githubusercontent.com/u/118298414?v=4",
            name = "João Marcos Henrique dos Santos Santana",
            bio = "dev mobile em construção",
            repositories = listOf(
                GitHubRepository(
                    name = "",
                    description = ""
                ),
                GitHubRepository(
                    name = "",
                    description = ""
                ),
                GitHubRepository(
                    name = "",
                    description = ""
                )
            )
        )
    )
}
data class ProfileUiState(
    val user: String = "",
    val image: String = "",
    val name: String = "",
    val bio: String = "",
    val repositories: List<GitHubRepository> = emptyList()
)