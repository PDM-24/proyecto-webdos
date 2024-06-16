package com.example.profile.model

data class SuggestionDataModel(
    val id: Int,
    val photo_id: Int,
    val ratings: String,
    val rating: Double,
    val likeCount: Int,
    val reviews: Int
)