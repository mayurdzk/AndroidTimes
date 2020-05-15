package com.mayurdhaka.androidapps.net

import androidx.test.platform.app.InstrumentationRegistry
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
class ExampleInstrumentedTest {
	private val feedFetcher: FeedFetcher = FeedFetcher()
	@Test
	fun addition_isCorrect() {
		runBlocking {
			val dfFeeds = feedFetcher
				.getFeedAtURL("http://feeds.bbci.co.uk/news/technology/rss.xml")
			val vergeFeeds = feedFetcher
				.getFeedAtURL("http://feeds.bbci.co.uk/news/entertainment_and_arts/rss.xml")
			assertFalse(dfFeeds.isEmpty())
			assertFalse(vergeFeeds.isEmpty())
		}
	}
}
