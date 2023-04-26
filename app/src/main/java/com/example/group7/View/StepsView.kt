package com.example.group7.View

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.outlined.Menu
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.group7.R

@Composable
fun StepsView(/*navController: NavController*/) {
    StepsContent(/*navController*/)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun StepsContent(/*navController: NavController*/) {
    //val logo: Painter = painterResource(R.drawable.ambundi_logo)
    var stepsGoal by remember { mutableStateOf(10000) }
    var stepCount by remember { mutableStateOf(0) }
    var sleepGoal by remember { mutableStateOf((8*60)) }
    var sleepCount by remember { mutableStateOf((2*60)) }
    var progress by remember { mutableStateOf(0.2f) }
    val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior()
    Scaffold(
        modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            SmallTopAppBar(
                title = {
                    Text(
                        "Ambundi",
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                        letterSpacing = 2.sp
                    )
                },
                navigationIcon = {
                    IconButton(onClick = { /* doSomething() */ }) {
                        Icon(
                            imageVector = Icons.Filled.Delete, // TODO INSERT APP ICON HERE
                            contentDescription = "Localized description"
                        )
                    }
                },
                actions = {
                    IconButton(
                        onClick = { /* TODO Ta upp meny*/ },
                        modifier = Modifier
                            .padding(end = 8.dp)
                            .align(Alignment.CenterVertically)
                            .weight(1f, fill = false)
                    ) {
                        Icon(Icons.Filled.Menu, contentDescription = "Menu")
                    }
                },
                scrollBehavior = scrollBehavior
            )
        },
        content = { innerPadding ->
            LazyColumn(
                contentPadding = innerPadding,
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                item { GoalProgressCard() }
            }
        }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GoalProgressCard() {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 2.dp, bottom = 2.dp, start = 15.dp, end = 15.dp)
            .clickable { /* TODO Navigate to chosen screen */ },
        elevation = CardDefaults.cardElevation(
            defaultElevation = 10.dp),
        border = BorderStroke(1.dp, Color(0xFF000000)),
    )
    {
        Row(modifier = Modifier.fillMaxSize())
        {
            Text(text = "Daily", modifier = Modifier.padding(10.dp))
            Divider(
                color = Color(0xFFFFFFFF),
                modifier = Modifier
                    .fillMaxHeight()  //fill the max height
                    .width(2.dp)
            )
            Text(text = "1483", modifier = Modifier.padding(10.dp))
            Icon(painter = painterResource(R.drawable.rectangle_23), contentDescription = "Steps foot",
                modifier = Modifier.size(28.dp)
                    .defaultMinSize(minWidth = 24.dp, minHeight = 24.dp)
                    .align(Alignment.CenterVertically)
                    .padding(end = 8.dp)
            )
            Text(text = "10000 Steps", modifier = Modifier.padding(10.dp))
            Spacer(modifier = Modifier.weight(1f)) // Add this line to push the Icon to the end
            Icon(painter = painterResource(R.drawable.footprint_24px), contentDescription = "Steps foot",
                modifier = Modifier.size(28.dp)
                    .defaultMinSize(minWidth = 24.dp, minHeight = 24.dp)
                    .align(Alignment.CenterVertically)
                    .padding(end = 8.dp)
            )
        }
    }
}
