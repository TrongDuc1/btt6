package com.example.btt2screen.services


import com.example.btt2screen.model.MovieResp
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieApis {

    @GET("movie/now_playing")
    suspend fun listNowPlayMovies(
        @Query("language") language: String, @Query("page") page: Int
    ): MovieResp

    /////movie/top_rated
    @GET("movie/top_rated")
    suspend fun listTopRateMovies(
        @Query("language") language: String,
        @Query("page") page: Int,
    ): MovieResp
}