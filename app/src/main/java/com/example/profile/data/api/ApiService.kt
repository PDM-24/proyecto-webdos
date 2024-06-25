package com.example.profile.data.api

import com.example.profile.utils.Constants
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.Path

interface ApiService {
    /*
    post
    @POST("ruta de api + endpoint")
    @POST("/api/newUser")
     */
    @Headers(value = ["Content-Type: application/json"])
    @POST(Constants.API_PATH+Constants.USERS_PATH+Constants.POST_NEW_USER_PATH)
    suspend fun createNewUser(@Body user: UserApi): ApiResponseSuccessful

    @Headers(value = ["Content-Type: application/json"])
    @POST(Constants.API_PATH+Constants.LOGIN_PATH+Constants.POST_LOGIN_PATH)
    suspend fun logIn(@Body login: LoginApi): ApiResponseSuccessfulLogin

    @Headers(value = ["Content-Type: application/json"])
    @POST(Constants.API_PATH + Constants.COMMENTS_PATH + Constants.POST_NEW_COMMENT_PATH + Constants.POST_NEW_COMMENT_IDENTIFIER_PATH)
    suspend fun postComment(
        @Path("identifier") identifier: String,
        @Body comment: CommentApi): CommentApi

    @GET(Constants.API_PATH + Constants.COMMENTS_PATH + Constants.GET_ALL_COMMENTS_PATH)
    suspend fun getComments(@Path("identifier") identifier: String): List<CommentApi>

}