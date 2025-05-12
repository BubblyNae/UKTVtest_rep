package com.nathan_jolobelawson.uktv_devtest.apisearch_feature.domain.use_case

import com.nathan_jolobelawson.uktv_devtest.apisearch_feature.data.util.SWAPI_CONSTANTS.INVALID_RESULT_EXCEPTION_EMPTY_RESULT_MESSAGE
import com.nathan_jolobelawson.uktv_devtest.apisearch_feature.domain.model.IResultData
import com.nathan_jolobelawson.uktv_devtest.apisearch_feature.domain.model.InvalidResultException
import com.nathan_jolobelawson.uktv_devtest.apisearch_feature.domain.repository.ResultRepository

class AddToLocalResults(
    private val repository: ResultRepository
) {
    @Throws(InvalidResultException::class)
    operator fun invoke(resultsToAdd: List<IResultData>) {
        if(!resultsToAdd.isEmpty()) return repository.addToLocalResults(resultsToAdd)
        else throw InvalidResultException(INVALID_RESULT_EXCEPTION_EMPTY_RESULT_MESSAGE)
    }
}