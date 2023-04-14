package com.example.group7.Model



class BodyInfo {
    private var height: String = ""
    private var weight: Int = 0
    private var age: Int = 0
    private var gender: String = ""

    fun setHeight(height: String) {
        this.height = height
    }
    fun setWeight(weight: Int) {
        this.weight = weight
    }
    fun setAge(age: Int) {
        this.age = age
    }
    fun setGender(gender: String) {
        this.gender = gender
    }

    fun getHeight(): String {
        return height
    }
    fun getWeight(): Int {
        return weight
    }
    fun getAge(): Int {
        return age
    }
    fun getGender(): String {
        return gender
    }
}
