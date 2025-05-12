package com.nathan_jolobelawson.uktv_devtest.apisearch_feature.data.data_source

import com.nathan_jolobelawson.uktv_devtest.apisearch_feature.data.util.SWAPI_CONSTANTS
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {
    val api: SwApiService by lazy {
        Retrofit.Builder().
                baseUrl(SWAPI_CONSTANTS.STAR_WARS_API_BASE_URL).
                addConverterFactory(GsonConverterFactory.create()).
                build().
                create(SwApiService::class.java)
    }
}