package com.example.lab13

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.clickable
import androidx.compose.material.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlin.random.Random

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            GameScreen()
        }
    }
}

@Composable
fun GameScreen() {
    var playerScore by remember { mutableStateOf(0) }
    var aiScore by remember { mutableStateOf(0) }
    var resultText by remember { mutableStateOf("Сделайте ход") }
    var playerChoice by remember { mutableStateOf(R.drawable.rock) }
    var aiChoice by remember { mutableStateOf(R.drawable.rock) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Игра Камень-Ножницы-Бумага",
            style = MaterialTheme.typography.headlineSmall.copy(fontSize = 24.sp)
        )

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Text(text = "Счёт:", fontSize = 18.sp)
                Text(text = "Игрок: $playerScore", fontSize = 18.sp)
                Text(text = "ИИ: $aiScore", fontSize = 18.sp)
            }
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Text(
                    text = resultText,
                    fontSize = 20.sp,
                    modifier = Modifier.padding(bottom = 16.dp)
                )
                Row {
                    Image(
                        painter = painterResource(id = playerChoice),
                        contentDescription = "Игрок",
                        modifier = Modifier
                            .size(100.dp)
                            .padding(8.dp)
                    )
                    Image(
                        painter = painterResource(id = aiChoice),
                        contentDescription = "ИИ",
                        modifier = Modifier
                            .size(100.dp)
                            .padding(8.dp)
                    )
                }
            }
        }

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            val choices = mapOf(
                "rock" to R.drawable.rock,
                "paper" to R.drawable.paper,
                "scissors" to R.drawable.scissors
            )

            choices.forEach { (choice, drawable) ->
                Image(
                    painter = painterResource(id = drawable),
                    contentDescription = choice,
                    modifier = Modifier
                        .size(100.dp)
                        .clickable {
                            val aiMove = choices.keys.random()
                            playerChoice = drawable
                            aiChoice = choices[aiMove] ?: R.drawable.rock
                            resultText = getResult(choice, aiMove)

                            if (resultText == "Ты победил!") {
                                playerScore++
                            } else if (resultText == "Ты проиграл((") {
                                aiScore++
                            }
                        }
                )
            }
        }
    }
}

fun getResult(player: String, ai: String): String {
    return when {
        player == ai -> "Ничья"
        (player == "rock" && ai == "scissors") ||
                (player == "paper" && ai == "rock") ||
                (player == "scissors" && ai == "paper") -> "Ты победил!"

        else -> "Ты проиграл(("
    }
}
