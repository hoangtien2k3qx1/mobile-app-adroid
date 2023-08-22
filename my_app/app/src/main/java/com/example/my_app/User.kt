package com.example.my_app

import com.google.gson.JsonObject
import org.json.JSONException
import org.json.JSONObject

class User {
    private lateinit var name: String
    private lateinit var username: String
    private lateinit var email: String
    private lateinit var password: String

    constructor(name: String, username: String, email: String, password: String) {
        this.name = name
        this.username = username
        this.email = email
        this.password = password
    }

    // convert JSONObject to User object ?
    fun createUserFromJsonObject(jsonObject: JSONObject): User? {
        try {
            val name: String = jsonObject.getString("name")
            val username: String = jsonObject.getString("username")
            val email: String = jsonObject.getString("email")
            val password: String = jsonObject.getString("password")

            return User(name, username, email, password)
        } catch (e: JSONException) {
            return null
        }
    }


}