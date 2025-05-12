package com.nathan_jolobelawson.uktv_devtest.apisearch_feature.domain.use_case

import com.nathan_jolobelawson.uktv_devtest.apisearch_feature.data.util.SWAPI_CONSTANTS.INVALID_RESULT_EXCEPTION_ENTER_TEXT_MESSAGE
import com.nathan_jolobelawson.uktv_devtest.apisearch_feature.domain.model.IResultData
import com.nathan_jolobelawson.uktv_devtest.apisearch_feature.domain.model.InvalidResultException
import com.nathan_jolobelawson.uktv_devtest.apisearch_feature.domain.repository.ResultRepository

class GetResult(
    private val repository: ResultRepository
) {
    @Throws(InvalidResultException::class)
    suspend operator fun invoke(userInput: String): IResultData {
        if(userInput.isBlank()){
            throw InvalidResultException(INVALID_RESULT_EXCEPTION_ENTER_TEXT_MESSAGE)
        }

        return repository.getResult(userInput)
    }
}