package com.example.group7.View

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.group7.ui.theme.*

@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
fun StartScreen() {

    Row(verticalAlignment = Alignment.CenterVertically){
        Image(
            painterResource(id = R.drawable.eo_logo_launcher),
            "content description"
        )
        Text("Loading...")
    }

}