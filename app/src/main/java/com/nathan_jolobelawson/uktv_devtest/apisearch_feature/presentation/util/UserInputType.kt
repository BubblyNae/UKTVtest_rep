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
                "starships" -> STARSHIPS
                "ships" -> STARSHIPS
                "film" -> FILMS
                "films" -> FILMS
                "movies" -> FILMS
                "episode" -> FILMS
                else -> ANY
            }
        }
    }
}