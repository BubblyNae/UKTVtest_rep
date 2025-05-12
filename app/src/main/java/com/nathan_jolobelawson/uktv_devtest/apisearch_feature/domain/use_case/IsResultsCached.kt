package com.nathan_jolobelawson.uktv_devtest.apisearch_feature.domain.use_case

import com.nathan_jolobelawson.uktv_devtest.apisearch_feature.domain.model.InvalidResultException
import com.nathan_jolobelawson.uktv_devtest.apisearch_feature.domain.repository.ResultRepository

class IsResultsCached(
    private val repository: ResultRepository
) {
    @Throws(InvalidResultException::class)
    operator fun invoke(): Boolean {
        return repository.isResultsCached()
    }
}