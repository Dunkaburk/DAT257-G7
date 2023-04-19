package com.example.group7.View

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.material3.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.tooling.preview.Preview

@Preview
@Composable
fun BodyView() {
    Column {
        Row {
            Text(text = "Height")
            simpleTextField()
        }
        Row {
            Text(text = "Weight")
            simpleTextField()
        }
        Row {
            Text(text = "Age")
            simpleTextField()
        }

        updatebodyinfobutton()
    }
}

@Composable

fun updatebodyinfobutton() {
    Button(onClick = { /*TODO*/ }) {
        Text(text = "Update Body Info")
    }
}
@Composable
fun simpleTextField() {
    var previousValue by remember { mutableStateOf(TextFieldValue("")) }
    TextField(
        value = previousValue,
        onValueChange = {
            previousValue = it
        },
        placeholder = { Text(text = "Your Placeholder") },
    )
}