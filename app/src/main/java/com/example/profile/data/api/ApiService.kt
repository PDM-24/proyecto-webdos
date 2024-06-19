package com.example.profile.data.api

import com.example.profile.utils.Constants
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

interface ApiService {

    /*
    post
    @POST("ruta de api + endpoint")
    @POST("/api/newUser")
     */
    @Headers(value = ["Content-Type: application/json"])
    @POST(Constants.API_PATH+Constants.POST_NEW_USER_PATH)
    suspend fun createNewUser(@Body user: UserApi): ApiResponseSuccesful

}