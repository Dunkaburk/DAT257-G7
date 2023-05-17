package com.example.group7.ViewModel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.group7.Model.apitest
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class StepGoalViewModel(context: Context) : ViewModel() {

    var city1 = "Göteborg"
    var city2 = "Copenhagen"

    fun setCityStart(value: String) {
        city1 = value
    }

    fun setCityEnd(value: String) {
        city1 = value
    }

    private val coroutineScope = CoroutineScope(Dispatchers.Main)

    fun getStepGoal(onResult: (Int) -> Unit) {
        viewModelScope.launch {
            val apiResponse = apitest(city1, city2)
            val distanceMeter = apiResponse.rows[0].elements[0].distance.value
            val stepLength = 0.7
            val stepGoal = (distanceMeter / stepLength).toInt()
            onResult(stepGoal)
        }
    }



}
