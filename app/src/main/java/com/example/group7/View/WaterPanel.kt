package com.example.group7.View

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.group7.R

@Preview
@Composable
fun WaterView(/*navController: NavController*/) {
    WaterContent(/*navController*/)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WaterContent(/*navController: NavController*/){
    val logo: Painter = painterResource(R.drawable.eologo)
    var waterGoal by remember { mutableStateOf(2000.toFloat()) } // recommended 2000 ml
    var waterCount by remember { mutableStateOf(400.toFloat()) } // TODO get from counter
    var hasIndividualGoal by remember { mutableStateOf(false) }
    var goalProgress by remember { mutableStateOf((waterCount/waterGoal)) }

    val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior()
    Scaffold(
        modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            SmallTopAppBar(
                title = {
                    Text(
                        "Water Intake",
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                        letterSpacing = 2.sp
                    )
                },
                navigationIcon = {
                    IconButton(onClick = { /* Back to Dashboard */ }) {
                        Icon(Icons.Filled.ArrowBack, contentDescription = "Back")
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
            if (hasIndividualGoal) item { WaterGoalCard(
                goalProgress = goalProgress)}
            else {item { ChooseWaterGoalPanel() }}
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ChooseWaterGoalPanel() {
    Card( modifier = Modifier
        .fillMaxWidth()
        .padding(top = 2.dp, bottom = 2.dp, start = 15.dp, end = 15.dp),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 10.dp)){
        Text(
            text = "Choose the type of goal",
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.headlineSmall,
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )
        var tabIndex by remember { mutableStateOf(0) }

        val tabs = listOf("Imaginative", "Custom")

        Column(modifier = Modifier.fillMaxWidth()) {
            TabRow(selectedTabIndex = tabIndex) {
                tabs.forEachIndexed { index, title ->
                    Tab(text = { Text(title) },
                        selected = tabIndex == index,
                        onClick = { tabIndex = index }
                    )
                }
            }
            when (tabIndex) {
                0 -> ImaginativeWaterGoalTab()
                1 -> CustomWaterGoalTab()
            }
        }
        OutlinedButton(
            onClick = {/* TODO save current goal */},
            modifier = Modifier
                .padding(10.dp)
                .align(Alignment.CenterHorizontally))
        {
            Text("Save goal", fontSize = 18.sp)
            //hasIndividualGoal = true
        }
    }
}

@Composable
fun CustomWaterGoalTab() {
    var text by remember { mutableStateOf(TextFieldValue("")) }
    Row(
        modifier = Modifier.fillMaxWidth()){
        TextField(
            modifier = Modifier.padding(start= 32.dp),
            value = text,
            onValueChange = {
                text = it
            },
            label = { Text(text = "Number of glasses") },
            placeholder = { Text(text = "E.g. 6") },
        )
    }
}

@Composable
fun ImaginativeWaterGoalTab() {
    DropDownMenuImaginativeWaterGoals()
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WaterGoalCard(goalProgress: Float) {
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
                        text = "Name of Goal",
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
                        IconButton(onClick = { /* Open settings */ }) {
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
                    progress = goalProgress,
                    strokeWidth = 24.dp,
                    color = MaterialTheme.colorScheme.primary,
                    modifier = Modifier
                        .align(Alignment.Center)
                        .size(270.dp)
                )

                Box(modifier = Modifier.align(Alignment.Center)) {
                    var goalPercentage = (goalProgress) * 100
                    Text(text = "$goalPercentage% to Completion", modifier = Modifier.align(Alignment.Center), style = MaterialTheme.typography.bodyLarge)
                }
            }

        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DropDownMenuImaginativeWaterGoals() {
    val context = LocalContext.current
    val cityDestinations = arrayOf("Drink a bathtub", "Around the world once", "Climb Mount Everest horizontally")
    var expanded by remember { mutableStateOf(false) }
    var selectedText by remember { mutableStateOf("") }

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(32.dp)
    ) {
        ExposedDropdownMenuBox(
            expanded = expanded,
            onExpandedChange = {
                expanded = !expanded
            }
        ) {
            TextField(
                value = selectedText,
                onValueChange = { selectedText = it },
                trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded) },
            )

            val filteredOptions =
                cityDestinations.filter { it.contains(selectedText, ignoreCase = true) }
            if (filteredOptions.isNotEmpty()) {
                ExposedDropdownMenu(
                    expanded = expanded,
                    onDismissRequest = {
                        // We shouldn't hide the menu when the user enters/removes any character
                    }
                ) {
                    filteredOptions.forEach { item ->
                        DropdownMenuItem(
                            text = { Text(text = item) },
                            onClick = {
                                selectedText = item
                                expanded = false
                            }
                        )
                    }
                }
            }
        }
    }
}