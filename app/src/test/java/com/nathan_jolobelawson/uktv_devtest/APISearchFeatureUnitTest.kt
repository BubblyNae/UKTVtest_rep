package com.nathan_jolobelawson.uktv_devtest

import org.junit.Test

import org.junit.Assert.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class APISearchFeatureUnitTest {

    // This test should be a success if an "InvalidResultException" is thrown when the
    // search button is pressed with no data in the TextField
    @Test
    fun searchScreen_SearchButtonErrorWithNoText() {
        TODO()
    }

    // This test should be a success if the ResultsScreen receives a list of IResultData
    // containing results relevant to the search query
    @Test
    fun searchScreen_SearchButtonReturnsLocalCachedResults() {
        TODO()
    }

    // This test should be a success if the ResultsScreen receives a single IResultData entry
    // based on the userText in the TextField
    @Test
    fun searchScreen_SearchButtonReturnsLocalCachedResult() {
        TODO()
    }

    // This test should be a success if an "InvalidResultException" is thrown when
    // the search button is pressed with irrelevant text in the TextField
    // (neither a result category or matching result text)
    @Test
    fun searchScreen_SearchButtonErrorWithWrongText() {
        TODO()
    }
}