package com.example.group7.View

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
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
    var stepCount by remember { mutableStateOf(1000) } // TODO get from pedometer
    var sleepGoal by remember { mutableStateOf((8*60)) }
    var sleepCount by remember { mutableStateOf((2*60)) }
    var progress by remember { mutableStateOf(0.2f) }
    var showDialog by remember { mutableStateOf(false) }
    var hasIndividualGoal by remember { mutableStateOf(true) }
    var goalProgress by remember { mutableStateOf(((stepCount/stepsGoal).toFloat())) }

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
        }
    ) { innerPadding ->
        LazyColumn(
            contentPadding = innerPadding,
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            item { GoalProgressCard() }
            if (hasIndividualGoal) item { IndividualGoalsCard( { showDialog = true }, goalProgress )}
            if (showDialog) item {
                CustomGoalPopup(
                    onDismissRequest = { showDialog = false },
                    onSaveButtonClick = { inputNumber ->
                        println("Input number: $inputNumber")
                        stepsGoal = inputNumber.toInt()
                        showDialog = false
                    })
            }
            item { ChooseGoalPanel({ showDialog = true }) }
        }
    }
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
                    .fillMaxHeight()
                    .width(2.dp)
            )
            Text(text = "1483", modifier = Modifier.padding(10.dp))
            Icon(painter = painterResource(R.drawable.rectangle_23), contentDescription = "Steps foot",
                modifier = Modifier
                    .size(28.dp)
                    .defaultMinSize(minWidth = 24.dp, minHeight = 24.dp)
                    .align(Alignment.CenterVertically)
                    .padding(end = 8.dp)
            )
            Text(text = "10000 Steps", modifier = Modifier.padding(10.dp))
            Spacer(modifier = Modifier.weight(1f))
            Icon(painter = painterResource(R.drawable.footprint_24px), contentDescription = "Steps foot",
                modifier = Modifier
                    .size(28.dp)
                    .defaultMinSize(minWidth = 24.dp, minHeight = 24.dp)
                    .align(Alignment.CenterVertically)
                    .padding(end = 8.dp)
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun IndividualGoalsCard(optionsAction: () -> Unit, goalProgress: Float) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        elevation = CardDefaults.cardElevation(8.dp)
    ) {
        Column(
            modifier = Modifier
                .padding(8.dp)
                .fillMaxWidth()
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                )
                {
                    Text(
                        text = "Individual goals",
                        textAlign = TextAlign.Center,
                        style = MaterialTheme.typography.headlineSmall,
                        modifier = Modifier.align(Alignment.Center)
                    )
                    Box(
                        modifier = Modifier
                            .align(Alignment.CenterEnd)
                            .padding(end = 8.dp)
                    )
                    {
                        IconButton(onClick = { optionsAction() }) {
                            Icon(
                                imageVector = Icons.Filled.Settings,
                                contentDescription = "Settings icon",
                                modifier = Modifier.size(32.dp)
                            )
                        }
                    }
                }


            }
            Spacer(modifier = Modifier.height(16.dp))
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(270.dp)
                    .align(Alignment.CenterHorizontally)
            )
            {
                CircularProgressIndicator(
                    progress = 0.6f,
                    strokeWidth = 24.dp,
                    color = MaterialTheme.colorScheme.primary,
                    modifier = Modifier
                        .align(Alignment.Center)
                        .size(270.dp)
                )

                Box(modifier = Modifier.align(Alignment.Center)) {
                    var goalPercentage = (goalProgress) * 100
                    Text(text = "60% to Mordor", modifier = Modifier.align(Alignment.Center), style = MaterialTheme.typography.bodyLarge)
                }
            }

        }
    }
}

@Composable
fun CustomGoalPopup(onDismissRequest: () -> Unit, onSaveButtonClick: (String) -> Unit) {
    val inputNumber = remember { mutableStateOf("") }

    AlertDialog(
        onDismissRequest = onDismissRequest,
        title = {
            Text("Customized goal")
        },
        text = {
            Column {
                Text("Choose step goals")
                TextField(
                    value = inputNumber.value,
                    onValueChange = { value ->
                        if (value.all { it.isDigit() }) {
                            inputNumber.value = value
                        }
                    },
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Number,
                        imeAction = ImeAction.Done
                    ),
                    keyboardActions = KeyboardActions(
                        onDone = { onSaveButtonClick(inputNumber.value) }
                    ),
                    modifier = Modifier.padding(top = 8.dp)
                )
                Button(
                    onClick = { onSaveButtonClick(inputNumber.value) },
                    modifier = Modifier.padding(top = 16.dp)
                ) {
                    Text("SAVE")
                }
            }
        },
        confirmButton = {},
    )
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ChooseGoalPanel(OnClickAction: () -> Unit){
    Card( modifier = Modifier
        .fillMaxWidth()
        .padding(top = 2.dp, bottom = 2.dp, start = 15.dp, end = 15.dp),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 10.dp)){
        OutlinedButton(
            onClick = {OnClickAction()},
            modifier = Modifier
                .padding(10.dp)
                .align(Alignment.CenterHorizontally))
        {
            Text("Choose goal", fontSize = 18.sp)
        }
    }
}
