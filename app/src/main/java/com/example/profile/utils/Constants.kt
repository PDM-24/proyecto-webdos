package com.example.profile.utils

object Constants {

    // api sevice
    const val BASE_URL = ""
    const val API_PATH = "/api"

    // login
    const val POST_LOGIN_PATH = "/logIn"

    // whoAmI
    const val GET_WHO_AM_I_PATH = "/whoAmI"

    // comments

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

    // create a new user
    const val POST_NEW_USER_PATH = "/newUser"

    // get a user by id
    const val GET_USER_PATH = "/getUser/:id"

}