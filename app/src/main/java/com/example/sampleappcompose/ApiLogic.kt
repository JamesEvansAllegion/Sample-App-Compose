package com.example.sampleappcompose

import androidx.compose.runtime.MutableState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

data class ApiResponse(val Title: String, val Year: String, val imdbRating: String, val Response: String)

interface ApiService {
    @GET("/")
    suspend fun makeApiCall(@Query("apikey") apikey: String, @Query("t") title: String): ApiResponse
}

suspend fun makeApiCall(movieTitle: String): ApiResponse {
    val retrofit = Retrofit.Builder()
        .baseUrl("http://www.omdbapi.com/") // OMDb API
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val apiService = retrofit.create(ApiService::class.java)

    return withContext(Dispatchers.IO) {
        apiService.makeApiCall("6b938676", movieTitle)
    }
}

suspend fun launchApiCall(movieTitle: String, apiResponse: MutableState<String>, logs: MutableList<String>) {
    try {
        val response = makeApiCall(movieTitle)
        if(response.Response == "True") {
            apiResponse.value = "Title: ${response.Title} \n Year: ${response.Year} \n IMDb Rating: ${response.imdbRating}"
            logs.add("API call made successfully.")
        }
        else{
            apiResponse.value = "Movie \"$movieTitle\" not found."
        }
    } catch (e: Exception) {
        logs.add("API call failed due to error: ${e.localizedMessage}")
    }
}