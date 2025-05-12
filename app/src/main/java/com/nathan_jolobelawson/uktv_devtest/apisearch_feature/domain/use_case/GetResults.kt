package com.nathan_jolobelawson.uktv_devtest.apisearch_feature.domain.use_case

import com.nathan_jolobelawson.uktv_devtest.apisearch_feature.domain.model.IResultData
import com.nathan_jolobelawson.uktv_devtest.apisearch_feature.domain.model.InvalidResultException
import com.nathan_jolobelawson.uktv_devtest.apisearch_feature.domain.repository.ResultRepository
import com.nathan_jolobelawson.uktv_devtest.apisearch_feature.presentation.util.UserInputType

class GetResults(
    private val repository: ResultRepository
) {
    @Throws(InvalidResultException::class)
    operator fun invoke(userInputType: UserInputType): List<IResultData> {
        return repository.getResults(userInputType)
    }
}