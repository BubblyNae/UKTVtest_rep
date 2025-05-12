package com.nathan_jolobelawson.uktv_devtest.apisearch_feature.data.util

object SWAPI_CONSTANTS{
    const val STAR_WARS_API_BASE_URL = "https://swapi.info/api/"

    const val STAR_WARS_VEHICLE_RELATIVE_URL = "vehicles"
    const val STAR_WARS_FILMS_RELATIVE_URL = "films"
    const val STAR_WARS_STARSHIPS_RELATIVE_URL = "starships"

    const val INVALID_RESULT_EXCEPTION_BAD_SEARCH_MESSAGE =
        "No results found. Try \"vehicle\", \"starships\", or \"films\"."
    const val INVALID_RESULT_EXCEPTION_EMPTY_RESULT_MESSAGE =
        "Trying to store an empty result!"
    const val INVALID_RESULT_EXCEPTION_ENTER_TEXT_MESSAGE =
        "Please enter text! "

    const val CLEAR_RESULTS_BUTTON_CONTENT_DESC_TEXT =
        "Clear Results Button"
    const val RETURN_TO_SEARCH_BUTTON_CONTENT_DESC_TEXT =
        "Return to Search Screen"
    const val REVEAL_ORDERING_BUTTON_CONTENT_DESC_TEXT =
        "Reveal Ordering Section Button"
}