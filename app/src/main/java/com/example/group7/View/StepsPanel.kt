 package com.example.group7.View

import android.util.Log
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.group7.R
import com.example.group7.ViewModel.Screen
import com.example.group7.ViewModel.StepGoalViewModel
import com.example.group7.common.FileManager

 @Preview
 @Composable
 fun StepsView() {
     StepsContent(navController = rememberNavController())
 }

 @OptIn(ExperimentalMaterial3Api::class)
 @Composable
 fun StepsContent(navController: NavController) {
     val context = LocalContext.current
     val logo: Painter = painterResource(R.drawable.eologo)
     var stepsGoal by remember { mutableStateOf(10000) }
     var stepCount by remember { mutableStateOf(1000) } // TODO get from pedometer
     var progress by remember { mutableStateOf(0.2f) }
     var showDialog by remember { mutableStateOf(false) }
     var hasIndividualGoal by remember { mutableStateOf(false) } // TODO make getter and setter for this variable and set to false first time a user opens app and when goal is completed
     var goalProgress by remember { mutableStateOf(stepCount.toFloat()/stepsGoal.toFloat()) }
     val stepGoalViewModel = StepGoalViewModel(context = context)

     fun updateProgress(NewProgress: Float){
         goalProgress = NewProgress/stepsGoal.toFloat()
     }

     val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior()
     Scaffold(
         modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
         topBar = {
             SmallTopAppBar(
                 title = {
                     Text(
                         "Steps",
                         maxLines = 1,
                         overflow = TextOverflow.Ellipsis,
                         letterSpacing = 2.sp
                     )
                 },
                 navigationIcon = {
                     IconButton(onClick = {navController.navigate(Screen.Dashboard.route)}) {
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
//             item { GoalProgressCard() } @Tantan snälla kolla på det här vi pajar allt om vi tar bort det :(((


             //if (hasIndividualGoal) item { IndividualGoalsCard( { showDialog = true }, progress )}
             if (hasIndividualGoal) item {Card(
                 border = BorderStroke(1.dp, Color(0xFF000000)),
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
                                 text = "Goal",
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
                                 IconButton(onClick = { showDialog = true }) {
                                     Icon(
                                         painter = painterResource(R.drawable.footprint_24px),
                                         contentDescription = "Set steps",
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
                             Text(text = "$goalPercentage% of goal reached", modifier = Modifier.align(Alignment.Center), style = MaterialTheme.typography.bodyLarge)
                         }
                     }

                 }
             }}

             if (showDialog) item {
                 CustomGoalPopup(
                     onDismissRequest = { showDialog = false },
                     onSaveButtonClick = { inputNumber ->
                         println("Input number: $inputNumber")
                         goalProgress = inputNumber.toFloat()
                         showDialog = false
                     }
                 )
             }
             item { ChooseGoalPanel (stepGoalViewModel, onSaveClicked = { steps ->
                 stepsGoal = steps
                 println("Steps goal updated: $stepsGoal")
                 hasIndividualGoal = true
                 goalProgress = stepCount.toFloat()/stepsGoal.toFloat()
             }, context)
             }
         }
     }
 }

 @OptIn(ExperimentalMaterial3Api::class)
 @Composable
 fun IndividualGoalsCard(optionsAction: () -> Unit, goalProgress: Float) {
     Card(
         border = BorderStroke(1.dp, Color(0xFF000000)),
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
                         text = "Goal",
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
                     progress = goalProgress,
                     strokeWidth = 24.dp,
                     color = MaterialTheme.colorScheme.primary,
                     modifier = Modifier
                         .align(Alignment.Center)
                         .size(270.dp)
                 )

                 Box(modifier = Modifier.align(Alignment.Center)) {
                     var goalPercentage = (goalProgress) * 100
                     Text(text = "$goalPercentage% of goal reached", modifier = Modifier.align(Alignment.Center), style = MaterialTheme.typography.bodyLarge)
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
             Text("Set your step progress")
         },
         text = {
             Column {
                 Text("Set progress")
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
fun ChooseGoalPanel(vm: StepGoalViewModel, onSaveClicked: (Int) -> Unit, context: android.content.Context) {
    var steps by remember { mutableStateOf(0) }

    Card(
        border = BorderStroke(1.dp, Color(0xFF000000)),
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 2.dp, bottom = 2.dp, start = 15.dp, end = 15.dp),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 10.dp
        )
    ) {
        Text(
             text = "Choose the type of goal",
             textAlign = TextAlign.Center,
             style = MaterialTheme.typography.headlineSmall,
             modifier = Modifier.align(Alignment.CenterHorizontally)
         )
         var tabIndex by remember { mutableStateOf(0) }

         val tabs = listOf("City to city", "Imaginative", "Custom")

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
                 0 -> CityToCityTab(vm, onTabValueChanged = {
                         value -> steps = value
                     FileManager.saveGoals(context, value, 0, 0 )})
                 1 -> ImaginativeGoalsTab(onTabValueChanged = {
                         value -> steps = value
                     FileManager.saveGoals(context, value, 0, 0 )})
                 2 -> CustomStepsTab(onTabValueChanged = { value ->
                     steps = value
                     FileManager.saveGoals(context, value, 0, 0 )
                 })
             }
         }
        OutlinedButton(
            onClick = {
                onSaveClicked(steps)
            },
            modifier = Modifier
                .padding(10.dp)
                .align(Alignment.CenterHorizontally)
        ) {
            Text("Save goal", fontSize = 18.sp)
            //hasIndividualGoal = true
        }
    }
}

 @Composable
 fun CustomStepsTab(onTabValueChanged: (Int) -> Unit) {
     var text by remember { mutableStateOf(TextFieldValue("")) }
     Row(modifier = Modifier.fillMaxWidth()) {
         TextField(
             modifier = Modifier.padding(start = 32.dp),
             value = text,
             onValueChange = {
                 text = it
                 onTabValueChanged(it.text.toIntOrNull() ?: 0)
             },
             label = { Text(text = "Number of steps") },
             placeholder = { Text(text = "E.g. 10000") },
         )
     }
 }



 @Composable
 fun ImaginativeGoalsTab(onTabValueChanged: (Int) -> Unit) {
     DropDownMenuImaginativeGoals(onTabValueChanged)
 }

 @Composable
 fun CityToCityTab(vm: StepGoalViewModel, onTabValueChanged: (Int) -> Unit) {
     var start by remember { mutableStateOf("") }
     var end by remember { mutableStateOf("") }
     DropDownMenuCityStart(onTabValueChanged = { value ->
         start = value
         vm.setCityStart(value)
         println("Start updated: '$start'")
         if (end != "") {
             vm.getStepGoal(onTabValueChanged)
         }
     } )
     DropDownMenuCityDestination(onTabValueChanged = { value ->
         end = value
         vm.setCityEnd(value)
         println("End updated: '$end'")
         if (start != "") {
             vm.getStepGoal(onTabValueChanged)
         }
     } )

 }

 @OptIn(ExperimentalMaterial3Api::class)
 @Composable
 fun DropDownMenuCityStart(onTabValueChanged: (String) -> Unit) {
     val context = LocalContext.current
     val cityDestinations = arrayOf("Stockholm", "Copenhagen", "Helsinki", "London", "Paris")
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
                 label = { Text(text = "From:") },
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
                                 onTabValueChanged(item)
                             }
                         )
                     }
                 }
             }
         }
     }
 }

 @OptIn(ExperimentalMaterial3Api::class)
 @Composable
 fun DropDownMenuCityDestination(onTabValueChanged: (String) -> Unit) {
     val context = LocalContext.current
     val cityDestinations = arrayOf("Stockholm", "Copenhagen", "Helsinki", "London", "Paris")
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
                 label = { Text(text = "To:") },
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
                                 onTabValueChanged(item)
                             }
                         )
                     }
                 }
             }
         }
     }
 }

 @OptIn(ExperimentalMaterial3Api::class)
 @Composable
 fun StepsInputCard(onStepsChange: (Int) -> Unit){
     var (steps) = remember { mutableStateOf("") }

     Card(shape = MaterialTheme.shapes.medium, modifier = Modifier.padding(16.dp)) {
         Column(modifier = Modifier.padding(16.dp)) {
             Text(text = "Input step progress:")
             TextField(value = steps, onValueChange = { newValue ->
                 steps = newValue
                 val newSteps = newValue.toIntOrNull() ?: 0
                 onStepsChange(newSteps)
             }, modifier = Modifier.padding(top = 8.dp), keyboardOptions = KeyboardOptions(
                 keyboardType = KeyboardType.Number,
                 imeAction = ImeAction.Done
             ), keyboardActions = KeyboardActions(onDone = { onStepsChange(steps.toIntOrNull() ?: 0) }))
         }
     }
 }

 @OptIn(ExperimentalMaterial3Api::class)
 @Composable
 fun DropDownMenuImaginativeGoals(onTabValueChanged: (Int) -> Unit) {
     val context = LocalContext.current
     val cityDestinations = arrayOf(Pair("The Shire To Mordor", 56789), Pair("Around the world once", 80052472), Pair("Climb Mount Everest horizontally", 8564))
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
                 label = { Text(text = "To:") },
                 trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded) },
             )

             val filteredOptions =
                 cityDestinations.filter { it.first.contains(selectedText, ignoreCase = true) }
             if (filteredOptions.isNotEmpty()) {
                 ExposedDropdownMenu(
                     expanded = expanded,
                     onDismissRequest = {
                         // We shouldn't hide the menu when the user enters/removes any character
                     }
                 ) {
                     filteredOptions.forEach { item ->
                         DropdownMenuItem(
                             text = { Text(text = item.first) },
                             onClick = {
                                 selectedText = item.first
                                 expanded = false
                                 onTabValueChanged(item.second)
                             }
                         )
                     }
                 }
             }
         }
     }
 }