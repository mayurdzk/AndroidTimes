package com.mayurdhaka.androidapps.net

import kotlinx.coroutines.runBlocking
import org.junit.Test

import org.junit.Assert.*

class ExampleUnitTest {
	private val feedFetcher: FeedFetcher = FeedFetcher()
    @Test
    fun addition_isCorrect() {
        runBlocking {
			val dfFeeds = feedFetcher
				.getFeedAtURL("https://daringfireball.net/feeds/main")
			val vergeFeeds = feedFetcher
				.getFeedAtURL("https://www.theverge.com/rss/index.xml")
			assertFalse(dfFeeds.isEmpty())
			assertFalse(vergeFeeds.isEmpty())
		}
    }
}
