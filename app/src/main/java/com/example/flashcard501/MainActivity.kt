package com.example.flashcard501

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Snackbar
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.flashcard501.ui.theme.Flashcard501Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Flashcard501Theme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Flashcards()
                }
            }
        }
    }
}

@Composable
fun Flashcards() {
    val questionList = listOf(
        "What is 5 multiplied by 7?" to "35",
        "How many continents are there on Earth?" to "7",
        "What is the square root of 81?" to "9",
        "How many days are there in a non-leap year?" to "365",
        "How many degrees are in a circle?" to "360",
        "What is 12 divided by 4?" to "3",
        "How many sides does a pentagon have?" to "5",
        "What is the atomic number of oxygen?" to "8",
        "How many minutes are there in an hour?" to "60",
        "How many hours are there in a day?" to "24",
        "What is 9 squared (9²)?" to "81",
        "How many weeks are there in a year?" to "52",
        "How many teeth does an adult human have?" to "32",
        "What is the freezing point of water in degrees Celsius?" to "0",
        "How many legs does a spider have?" to "8",
        "What is the value of Pi (π) rounded to two decimal places?" to "3.14",
        "How many millimeters are there in a centimeter?" to "10",
        "How many letters are there in the English alphabet?" to "26",
        "Quiz Complete" to "13491241042194012491240914" //unguessable answer to last 'question' so the app doesn't break
    )
    var snackbarVisibleState by remember { mutableStateOf(false) }
    var text by remember { mutableStateOf("") }
    var currentQuestion by remember { mutableIntStateOf(0) }
    var answerCorrect by remember { mutableStateOf(false) }
    Column {
        Spacer(modifier = Modifier.height(40.dp))
        Card { Text(text = questionList[currentQuestion].first) }//displays question part of question,answer tuple
        Spacer(modifier = Modifier.height(15.dp))
        TextField(
            value = text,
            onValueChange = { text = it },
            label = { Text("Answer") })
        Button(onClick =
        {
            if (text.isNotEmpty() && questionList[currentQuestion].second == text) { //answer is correct, go to next question and reset field
                text = ""
                currentQuestion += 1
                answerCorrect = true //makes snackbar "correct"
                snackbarVisibleState = true //show snackbar

            } else {
                text = ""
                answerCorrect = false //makes snackbar "wrong"
                snackbarVisibleState = true //show snackbar
            }
        }) { Text("Check Answer") }
        if (snackbarVisibleState) {
            Snackbar(
                action = {
                    Button(onClick = {
                        snackbarVisibleState = false //dismiss on click
                    }) {
                        Text("Dismiss")
                    }
                },
                modifier = Modifier.padding(8.dp)
            ) {
                if (answerCorrect) {
                    Text(text = "Correct")
                } else {
                    Text("Wrong")
                }
            }
        }

        if (currentQuestion == 18) {
            Snackbar(
                action = {
                    Button(onClick = {
                        currentQuestion = 0
                    }) {
                        Text("Reset Quiz")
                    }
                },
                modifier = Modifier.padding(8.dp)
            ) { Text(text = "Quiz Complete") }
        }
    }
}