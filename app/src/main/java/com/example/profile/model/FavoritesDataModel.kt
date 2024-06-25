package com.example.profile.model

import com.example.profile.R

object FavoritesList {
    val list = mutableListOf<FavoritesDataModel>()

    fun add(favorite: FavoritesDataModel) {
        list.add(favorite)
    }

    fun removeIf(predicate: (FavoritesDataModel) -> Boolean): Boolean {
        return list.removeIf(predicate)
    }

    fun any(predicate: (FavoritesDataModel) -> Boolean): Boolean {
        return list.any(predicate)
    }
}