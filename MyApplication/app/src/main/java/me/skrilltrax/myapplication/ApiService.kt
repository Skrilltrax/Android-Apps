package me.skrilltrax.myapplication

import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {

    @GET("{id}/info.0.json")
    fun getData(@Path("id") id: Number) : Single<Data>
}