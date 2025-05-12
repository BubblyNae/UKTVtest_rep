package com.nathan_jolobelawson.uktv_devtest.di

import com.nathan_jolobelawson.uktv_devtest.apisearch_feature.data.repository.ResultRepositoryImpl
import com.nathan_jolobelawson.uktv_devtest.apisearch_feature.domain.repository.ResultRepository
import com.nathan_jolobelawson.uktv_devtest.apisearch_feature.domain.use_case.AddToLocalResults
import com.nathan_jolobelawson.uktv_devtest.apisearch_feature.domain.use_case.ClearLocalResults
import com.nathan_jolobelawson.uktv_devtest.apisearch_feature.domain.use_case.GetLocalResults
import com.nathan_jolobelawson.uktv_devtest.apisearch_feature.domain.use_case.GetResult
import com.nathan_jolobelawson.uktv_devtest.apisearch_feature.domain.use_case.GetResults
import com.nathan_jolobelawson.uktv_devtest.apisearch_feature.domain.use_case.IsResultsCached
import com.nathan_jolobelawson.uktv_devtest.apisearch_feature.domain.use_case.LocallyCacheResults
import com.nathan_jolobelawson.uktv_devtest.apisearch_feature.domain.use_case.ResultsUseCases
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideResultRepository(): ResultRepository {
        return ResultRepositoryImpl()
    }

    @Provides
    @Singleton
    fun provideResultsUseCases(repository: ResultRepository): ResultsUseCases {
        return ResultsUseCases(
            locallyCacheResults = LocallyCacheResults(repository),
            getResult = GetResult(repository),
            getResults = GetResults(repository),
            clearLocalResults = ClearLocalResults(repository),
            addToLocalResults = AddToLocalResults(repository),
            isResultsCached = IsResultsCached(repository),
            getLocalResults = GetLocalResults(repository)
        )
    }
}