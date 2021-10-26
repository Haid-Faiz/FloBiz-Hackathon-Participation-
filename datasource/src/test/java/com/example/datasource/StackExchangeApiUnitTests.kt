package com.example.datasource

import com.example.datasource.models.ApiClient
import com.example.datasource.models.services.StackExchangeApi
import junit.framework.Assert.assertNotNull
import kotlinx.coroutines.runBlocking
import org.junit.Test

/**
 * Example local unit test, which will execute on the development machine (host).
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class StackExchangeApiUnitTests {

    private val api = ApiClient().retrofit.create(StackExchangeApi::class.java)

    @Test
    fun `GET- All Question`() = runBlocking {
        val response = api.fetchAllQuestions()
        assertNotNull(response.body()?.questions)
    }

    @Test
    fun `GET- Search Question`() = runBlocking {
        val response = api.searchQuestions(searchQuery = "Kotlin")
        assertNotNull(response.body()?.questions)
    }

    @Test
    fun `GET- Filter Questions By Tags`() = runBlocking {
        val response = api.filterQuestionsByTags(tags = "Android; Kotlin")
        assertNotNull(response.body()?.questions)
    }
}