package com.example.group7.ViewModel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.group7.Model.apitest
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class StepGoalViewModel(context: Context) : ViewModel() {

    private val coroutineScope = CoroutineScope(Dispatchers.Main)

    fun getStepGoal(onResult: (Int) -> Unit) {
         viewModelScope.launch {
             //Comment out this block if you need to for testing other things
             /*   val apiResponse = apitest()
            val distanceMeter = apiResponse.rows[0].elements[0].distance.value
            val stepLength = 0.7
            val stepGoal = (distanceMeter/stepLength).toInt()
            onResult(stepGoal)
            }  */
         }
    }

}