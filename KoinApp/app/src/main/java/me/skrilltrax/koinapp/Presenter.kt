package me.skrilltrax.koinapp

import android.content.SharedPreferences

interface View {
    fun onSaveDetails()
    fun onGetDetails(email: String, name: String)
}

class Presenter(private var mainView: View, private var sharedPreferences: SharedPreferences) {

    private lateinit var email: String
    private lateinit var name: String

    fun saveDetails(email: String, name: String) {
        this.email = email
        this.name = name
        sharedPreferences.edit().putString("email", this.email).apply()
        sharedPreferences.edit().putString("name", this.name).apply()
        mainView.onSaveDetails()
    }

    fun getDetails(email: String) {
        this.email = email
        this.name = requireNotNull(sharedPreferences.getString("name", ""))
        mainView.onGetDetails(email, name)
    }
}