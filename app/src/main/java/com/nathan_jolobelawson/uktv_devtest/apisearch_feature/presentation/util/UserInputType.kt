package com.nathan_jolobelawson.uktv_devtest.apisearch_feature.presentation.util

enum class UserInputType {
    ANY,
    VEHICLE,
    STARSHIPS,
    FILMS;

    companion object {
        fun checkUserInput(userInput: String): UserInputType {
            return when (userInput) {
                "vehicle" -> VEHICLE
                "vehicles" -> VEHICLE
                "starships" -> STARSHIPS
                "ships" -> STARSHIPS
                "ship" -> STARSHIPS
                "starship" -> STARSHIPS
                "film" -> FILMS
                "films" -> FILMS
                "movies" -> FILMS
                "movie" -> FILMS
                "episode" -> FILMS
                else -> ANY
            }
        }
    }
}