package me.skrilltrax.myapplication

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.net.URL

class Data {

    @Expose
    @SerializedName("num")
    lateinit var num: Number

    @Expose
    @SerializedName("title")
    lateinit var title: String

    @Expose
    @SerializedName("img")
    lateinit var img: String

}