package com.mayurdhaka.androidapps.net

import androidx.test.ext.junit.runners.AndroidJUnit4
import kotlinx.coroutines.runBlocking

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class ParserTests {
	private val feedFetcher: FeedFetcher = FeedFetcher()
	@Test
	fun assertAtomAndRSSFeedsAreFetchedAndParsed() {
		runBlocking {
			// These tests can break at any time if the source breaks.
			//
			// RSS 2.0 Feed:
			val bbcFeed = feedFetcher
				.getFeedAtURL("http://feeds.bbci.co.uk/news/technology/rss.xml")
			// ATOM 1.0 Feed:
			val vergeFeed = feedFetcher
				.getFeedAtURL("https://www.theverge.com/rss/index.xml")
			assertFalse(bbcFeed.isEmpty())
			assertFalse(vergeFeed.isEmpty())
		}
	}
}
