package com.nathan_jolobelawson.uktv_devtest.apisearch_feature.domain.use_case

import com.nathan_jolobelawson.uktv_devtest.apisearch_feature.domain.model.InvalidResultException
import com.nathan_jolobelawson.uktv_devtest.apisearch_feature.domain.repository.ResultRepository

class ClearLocalResults(private val repository: ResultRepository) {

    @Throws(InvalidResultException::class)
    operator fun invoke(){ return repository.clearLocalResults() }
}