package com.example.group7.common
import android.content.Context
import android.content.SharedPreferences
import android.util.Log
import org.json.JSONObject

object FileManager {

    private const val PREF_NAME = "health_goals"
    private const val KEY_GOALS = "goals"
    private const val KEY_STEP_COUNT = "key_step_count"
    private const val KEY_STEP_GOAL = "key_step_goal"
    private const val STEPS_GOAL = "stepsGoal"
    private const val STEPS_COUNT = "stepCount"

    data class Goals(val stepsGoal: Int, val sleepGoal: Int, val waterGoal: Int, val stepCount: Int)

    private fun getSharedPreferences(context: Context): SharedPreferences {
        return context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
    }

    fun saveGoals(context: Context, stepsGoal: Int, sleepGoal: Int, waterGoal: Int, stepCount: Int) {
        val goals = JSONObject()
        goals.put(STEPS_GOAL, stepsGoal)
        goals.put(STEPS_COUNT, stepCount)
        val editor = getSharedPreferences(context).edit()
        editor.putString(KEY_GOALS, goals.toString())
        editor.apply()
    }

    fun saveSteps(context: Context, stepsGoal: Int) {
        val goals = JSONObject()
        goals.put(STEPS_GOAL, stepsGoal)
        val editor = getSharedPreferences(context).edit()
        editor.putString(KEY_GOALS, goals.toString())
        editor.putString(KEY_STEP_GOAL, goals.toString())
        editor.apply()
        Log.d("FileManager", "Step Goal saved: $stepsGoal")

    }

    fun saveStepCount(context: Context, stepCount: Int) {
        val goals = JSONObject()
        goals.put(STEPS_COUNT, stepCount)
        val editor = getSharedPreferences(context).edit()
        editor.putString(KEY_GOALS, goals.toString())
        editor.putString(KEY_STEP_COUNT, goals.toString())
        editor.apply()
        Log.d("FileManager", "Step count saved: $stepCount")
    }



    fun retrieveGoals(context: Context): Int? {
        val json = getSharedPreferences(context).getString(KEY_GOALS, null)
        if (json != null) {
            val jsonObject = JSONObject(json)
            val stepsGoal = jsonObject.getInt(STEPS_GOAL)
            return stepsGoal
        }
        return null
    }

    fun retrieveStepCount(context: Context): Int? {
        val json = getSharedPreferences(context).getString(KEY_STEP_COUNT, null)
        if (json != null) {
            val jsonObject = JSONObject(json)
            val stepCount = jsonObject.getInt(STEPS_COUNT)
            return stepCount
        }
        return null
    }

    fun retrieveSteps(context: Context): Int? {
        val json = getSharedPreferences(context).getString(KEY_STEP_GOAL, null)
        if (json != null) {
            val jsonObject = JSONObject(json)
            val stepsGoal = jsonObject.getInt(STEPS_GOAL)
            Log.d("FileManager", "Step goal_ $stepsGoal")
            return stepsGoal
        }
        return null
    }
}

/*

// How to save goals
FileManager.saveGoals(context, 10000, 8, 8)

// How to retrieve goals
val goals = FileManager.retrieveGoals(context)
if (goals != null) {
    val stepsGoal = goals.stepsGoal
    val sleepGoal = goals.sleepGoal
    val waterGoal = goals.waterGoal
}

 */
