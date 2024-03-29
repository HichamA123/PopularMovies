package com.example.popularmovies.api

import com.google.gson.JsonObject
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface MoviesApiService {

    // The GET method needed to retrieve movies
    @GET("/3/discover/movie?" +
            "api_key=384748872a5adcc5f2ceb5e3e282579a" +
            "&language=en-US" +
            "&sort_by=popularity.desc" +
            "&include_adult=false" +
            "&include_video=false" +
            "&page=1")
    fun getMovies(@Query("year") year: String): Call<JsonObject>
}