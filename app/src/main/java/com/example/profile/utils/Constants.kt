package com.example.profile.utils

object Constants {

    // api sevice
    const val BASE_URL = "http://192.168.137.1:3000"
    const val API_PATH = "/api"

    //api prueba
    const val BASE_URL2 = "http://192.168.1.15:8080"

    // login
    const val LOGIN_PATH = "/auth"
    const val POST_LOGIN_PATH = "/logIn"

    // whoAmI
    const val GET_WHO_AM_I_PATH = "/whoAmI"

    // comments
    const val COMMENTS_PATH = "/comments"
    // get all comments
    const val GET_ALL_COMMENTS_PATH = "/"

    // post new comment
    const val POST_NEW_COMMENT_PATH = "/"

    // get comment by id
    const val GET_COMMENT_BY_ID_PATH = "/:id"

    // update comment by id
    const val PUT_COMMENT_BY_ID_PATH = "/:id"

    // delete comment by id
    const val DELETE_COMMENT_BY_ID_PATH = "/:id"

    // users
    const val USERS_PATH = "/users"
    // create a new user
    const val POST_NEW_USER_PATH = "/newUser"

    const val USER_ID = "_id"
    const val USER_FIRST_NAME = "firstName"
    const val USER_LAST_NAME = "lastName"
    const val USER_EMAIL = "email"
    const val USER_NAME = "username"
    const val USER_PASSWORD = "password"
    const val USER_CONFIRM_PASSWORD = "confirmPassword"
    const val USER_SALT = "salt"
    const val USER_TOKENS = "tokens"

    // apiResponse
    const val RESPONSE_SUCCESSFUL = "ok"
    const val RESPONSE_ERROR = "error"
    const val RESPONSE_SUCCESSFUL_LOGIN = "token"

    // get a user by id
    const val GET_USER_PATH = "/getUser/:id"

}