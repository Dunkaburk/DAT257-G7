package com.example.group7.View

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import com.example.group7.ui.theme.*
import com.example.group7.R
import androidx.compose.animation.core.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import kotlinx.coroutines.delay


@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
fun StartScreen() {

    Column(
        modifier = Modifier
            .background(Color.Black)
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally

    ){
        Image(
            painter = painterResource(id = R.drawable.eologo),
            "content description"
        )
        LoadingAnimation()
    }

}

