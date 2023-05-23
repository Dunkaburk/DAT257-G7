package com.example.group7.common
import android.content.Context
import android.content.SharedPreferences
import org.json.JSONObject

object FileManager {

    private const val PREF_NAME = "health_goals"
    private const val KEY_GOALS = "goals"
    private const val STEPS_GOAL = "stepsGoal"
    private const val SLEEP_GOAL = "sleepGoal"
    private const val WATER_GOAL = "waterGoal"

    data class Goals(val stepsGoal: Int, val sleepGoal: Int, val waterGoal: Int)

    private fun getSharedPreferences(context: Context): SharedPreferences {
        return context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
    }

    fun saveGoals(context: Context, stepsGoal: Int, sleepGoal: Int, waterGoal: Int) {
        val goals = JSONObject()
        goals.put(STEPS_GOAL, stepsGoal)
        goals.put(SLEEP_GOAL, sleepGoal)
        goals.put(WATER_GOAL, waterGoal)
        val editor = getSharedPreferences(context).edit()
        editor.putString(KEY_GOALS, goals.toString())
        editor.apply()
    }

    fun retrieveGoals(context: Context): Goals? {
        val json = getSharedPreferences(context).getString(KEY_GOALS, null)
        if (json != null) {
            val jsonObject = JSONObject(json)
            val stepsGoal = jsonObject.getInt(STEPS_GOAL)
            val sleepGoal = jsonObject.getInt(SLEEP_GOAL)
            val waterGoal = jsonObject.getInt(WATER_GOAL)
            return Goals(stepsGoal, sleepGoal, waterGoal)
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
