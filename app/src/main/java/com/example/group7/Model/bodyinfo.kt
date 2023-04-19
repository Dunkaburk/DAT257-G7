package com.example.group7.Model

import org.json.JSONObject
import java.io.File

// testing for co-author
class BodyInfo {
    private var height: String = ""
    private var weight: Int = 0
    private var age: Int = 0
    private var gender: String = ""

    fun storeInfo(height: String, weight: Int, age: Int, gender: String) {
        this.height = height
        this.weight = weight
        this.age = age
        this.gender = gender

        val json = JSONObject()
        json.put("height", height)
        json.put("weight", weight)
        json.put("age", age)
        json.put("gender", gender)

        val file = File("body_info.json")
        file.writeText(json.toString())
    }
    fun readInfo(): JSONObject {
        val file = File("body_info.json")
        val json = JSONObject(file.readText())
        return json
    }
}
