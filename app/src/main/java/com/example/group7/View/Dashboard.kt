package com.example.group7.View


//import androidx.navigation.NavController
import androidx.compose.foundation.BorderStroke
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.clickable
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.SmallTopAppBar
import androidx.compose.ui.draw.clip
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.platform.LocalContext

import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.sp
import com.example.group7.R
import com.example.group7.ViewModel.StepGoalViewModel
import com.example.group7.ui.theme.AmbundiTheme


@Composable
@Preview
fun Dashboard(){
    DashboardContent()
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DashboardContent(/*navController: NavController*/ ) {
    //val logo: Painter = painterResource(R.drawable.ambundi_logo)
        val context = LocalContext.current
        var stepsGoal by remember { mutableStateOf(10000) }
        var sleepGoal by remember { mutableStateOf((8 * 60)) }
        val stepgoalviewModel: StepGoalViewModel = StepGoalViewModel(context = context)
        stepgoalviewModel.getStepGoal { stepGoal ->
            stepsGoal = stepGoal }
        var sleepCount by remember { mutableStateOf((2 * 60)) }
        var stepCount by remember { mutableStateOf(0) }
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
                    IconButton(onClick = { /* Send me to Dashboard */ }) {
                        Image(
                            painter = painterResource(id = R.drawable.eologo),
                            contentDescription = null // decorative element
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
                verticalArrangement = Arrangement.spacedBy(8.dp),
                modifier = Modifier
            ) {

                //item { DailyGoalsCard() }
                item { ProgressCard("Steps",progress, { StepsProgress(stepsProgress = stepCount, stepsGoal = stepsGoal) }) }
                item { ProgressCard("Sleep",progress, { SleepProgress(sleepProgress = stepCount, sleepGoal = stepsGoal) }) }
                item { WaterIntakePanel()}
                //item { ChooseGoalTypePanel()}
            }
        }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WaterIntakePanel() {
    val paddingModifier = Modifier.padding(12.dp)
    var waterCount by remember { mutableStateOf(0) }
    Card(
        border = BorderStroke(0.5.dp, MaterialTheme.colorScheme.secondary),
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 2.dp, bottom = 2.dp, start = 15.dp, end = 15.dp)
            .clickable { /* TODO Navigate to chosen screen */ },
        elevation = CardDefaults.cardElevation(
                defaultElevation = 10.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxSize()
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth(0.4f)
                    .padding(10.dp),
                horizontalAlignment = Alignment.Start
            ) {
                Text(
                    text = "Water Intake",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.W900,
                    maxLines = 1
                )
                Text(
                    text = "$waterCount glasses",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Normal
                )
            }
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                OutlinedButton(
                    modifier = Modifier.padding(8.dp),
                    onClick = { if (waterCount > 0) waterCount--},
                ) {
                    Text(text = "-", fontSize = 28.sp, fontWeight = FontWeight.Bold)
                }
                OutlinedButton(
                    modifier = Modifier.padding(8.dp),
                    onClick = { waterCount++ },
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF313131))
                ) {
                    Text(text = "+", fontSize = 28.sp)
                }
                Icon(
                    modifier = Modifier
                        .size(24.dp)
                        .padding(start = 4.dp),
                    imageVector = Icons.Filled.Delete, // TODO INSERT WATER ICON
                    contentDescription = "Glass of water"
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProgressCard(title : String, progressPercentage : Float, Progress: @Composable () -> Unit ) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 2.dp, bottom = 2.dp, start = 15.dp, end = 15.dp)
            .clickable { /* TODO Navigate to chosen screen */ },
        elevation = CardDefaults.cardElevation(
            defaultElevation = 10.dp),
        border = BorderStroke(0.5.dp, Color(0xFF000000)),
    ) {
        Row(modifier = Modifier.height(100.dp), verticalAlignment = Alignment.CenterVertically) {
            Row(
                modifier = Modifier
                    .fillMaxWidth(0.4f)
                    .fillMaxHeight()
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(bottom = 10.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = title,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 12.dp, vertical = 8.dp),
                        textAlign = TextAlign.Left,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.W900
                    )
                    Progress()
                }
                }
            Row(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(end = 36.dp),
                horizontalArrangement = Arrangement.End,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxHeight()
                        .fillMaxWidth(0.82f)
                        .padding(end = 20.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center)
                {
                    Text(text = (progressPercentage*100).toInt().toString()+"%", style = MaterialTheme.typography.bodyLarge, modifier = Modifier.padding(5.dp))
                    CustomLinearProgressBar(InitProgress = progressPercentage)
                }
                Icon(imageVector = Icons.Filled.Favorite, contentDescription = "Say what", modifier = Modifier
                    .size(24.dp)
                    .padding(start = 5.dp))

                // Progressbar() TODO make progressbar
            }

            }

        }

    }

/*@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DailyGoalsCard(){
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 2.dp, bottom = 2.dp, start = 15.dp, end = 15.dp)
            .clickable { *//* TODO Navigate to chosen screen *//* },
        elevation = CardDefaults.cardElevation(
            defaultElevation = 10.dp),
        border = BorderStroke(1.dp, Color(0xFF000000)),
    )
    {
        Row(modifier = Modifier.height(40.dp), verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.Center)
        {
            Text(text = "Daily remaining Goals", style = MaterialTheme.typography.bodyLarge, modifier = Modifier.padding(5.dp).align(Alignment.CenterVertically))
        }
        Divider(color = Color(0xFF000000), thickness = 2.dp, modifier = Modifier.fillMaxWidth(0.8f))
        *//*Row(modifier = Modifier.height(40.dp), verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.Center)
        {
            Text(
                buildAnnotatedString {
                    withStyle(
                        style = SpanStyle(
                            color = Color(0xFF3B7CD9),
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Bold
                        )
                    ) {
                        append("DRINK")
                    }
                }
            )
            Box(
                modifier = Modifier
                    .background(shape = RoundedCornerShape(16.dp), color = Color(0xFF000000))
                    .padding(16.dp)
            ) {
                Text(
                    "2",
                    fontSize = 16.sp
                )
            }
            //val logo: Painter = painterResource(R.drawable.ambundi_logo)
            Text(text = "GLASSES", style = MaterialTheme.typography.bodyLarge, modifier = Modifier.padding(5.dp))
            Icon(painter = painterResource(R.drawable.ic_launcher_background), contentDescription = "Botuh of watuh", modifier = Modifier.size(28.dp).align(Alignment.CenterVertically))
        }*//*



    }
}*/



@Composable
private fun CustomLinearProgressBar(InitProgress: Float){
    var progress by remember { mutableStateOf(InitProgress) }
    val animatedProgress = animateFloatAsState(
        targetValue = progress,
        animationSpec = ProgressIndicatorDefaults.ProgressAnimationSpec
    ).value

    Column(modifier = Modifier.fillMaxWidth()) {
        LinearProgressIndicator(
            progress = progress,
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 10.dp)
                .clip(RoundedCornerShape(50.dp))
                .height(8.dp),
            color = Color(0xFF3B7CD9),
            trackColor = Color(0xFF1B3861)
        )
    }
}

@Composable
fun LinearProgressIndicatorTestfunnyhaha() {
    var progress by remember { mutableStateOf(0.1f) }
    val animatedProgress = animateFloatAsState(
        targetValue = progress,
        animationSpec = ProgressIndicatorDefaults.ProgressAnimationSpec
    ).value

    Column(horizontalAlignment = Alignment.CenterHorizontally) {

        Spacer(Modifier.height(30.dp))
        Text("LinearProgressIndicator with progress set by buttons")
        LinearProgressIndicator(progress = animatedProgress)
        Spacer(Modifier.height(30.dp))
        OutlinedButton(
            onClick = {
                if (progress < 1f) progress += 0.1f
            }
        ) {
            Text("+")
        }

        OutlinedButton(
            onClick = {
                if (progress > 0f) progress -= 0.1f
            }
        ) {
            Text("-")
        }
    }
}


@Composable
fun StepsProgress(stepsProgress : Int, stepsGoal : Int) {
    var steps by remember { mutableStateOf(stepsProgress) }
    Row(modifier = Modifier.fillMaxSize(), verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.Center)
    {
        Text(
            buildAnnotatedString {
                withStyle(style = SpanStyle(fontWeight = FontWeight.W400, fontSize = 24.sp)
                ) {
                    append(steps.toString())
                }
                withStyle(style = SpanStyle(fontWeight = FontWeight.W900, fontSize = 28.sp)
                ) {
                    append(" / ")
                }
                withStyle(style = SpanStyle(fontWeight = FontWeight.W300, fontSize = 18.sp)
                ) {
                    append(stepsGoal.toString())
                }
            }
        )

    }
}

@Composable
fun SleepProgress(sleepProgress : Int, sleepGoal : Int) {
    var sleep by remember { mutableStateOf(sleepProgress) }
    var remainingSleep by remember { mutableStateOf(sleepGoal - sleep) }
    var remainingHours by remember { mutableStateOf(remainingSleep / 60) }
    var remainingMinutes by remember { mutableStateOf(remainingSleep % 60) }

    Row(modifier = Modifier.fillMaxSize(), verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.Center)
    {
        Text(
            buildAnnotatedString {
                withStyle(style = SpanStyle(fontWeight = FontWeight.W400, fontSize = 18.sp)
                ) {
                    append(remainingHours.toString())
                }
                withStyle(style = SpanStyle(fontWeight = FontWeight.W900, fontSize = 20.sp)
                ) {
                    append(" hrs ")
                }
                withStyle(style = SpanStyle(fontWeight = FontWeight.W300, fontSize = 18.sp)
                ) {
                    append(remainingMinutes.toString())
                }
                withStyle(style = SpanStyle(fontWeight = FontWeight.W300, fontSize = 18.sp)
                ) {
                    append(" mins left")
                }
            }
        )

    }
}




//------------------------------------------------------------
