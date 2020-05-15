package com.mayurdhaka.androidapps.net

import okhttp3.OkHttpClient
import okhttp3.Request
import org.xmlpull.v1.XmlPullParser
import org.xmlpull.v1.XmlPullParserFactory
import java.io.Reader

class FeedFetcher {
	private val okHttpClient: OkHttpClient = OkHttpClient()
	private val parser: XmlPullParser
	init {
		val factory = XmlPullParserFactory.newInstance()
		factory.isNamespaceAware = true
		parser = factory.newPullParser()
	}

	suspend fun getFeedAtURL(url: String): List<Entry> {
		val request = Request.Builder()
			.url(url)
			.build()
		val response = okHttpClient.newCall(request).execute()
		// TODO: Remove crasher
		val responseBodyString = response.body!!.charStream()
		return parseEntries(responseBodyString)
	}

	private fun parseEntries(stream: Reader): List<Entry> {
		val entries: MutableList<Entry> = mutableListOf()
		parser.setInput(stream)
		var eventType: Int = parser.getEventType()
		while (eventType != XmlPullParser.END_DOCUMENT) {
			if (eventType == XmlPullParser.START_DOCUMENT) {
				println("Start document")
			} else if (eventType == XmlPullParser.START_TAG) {
				print(parser.name)
				if (parser.name == ENTRY_TAG || parser.name == ITEM_TAG) {
					parseSingleEntry(parser)?.let {
						entries.add(it)
					}
					// One may be tempted to bail parsing here but we may have one dud entry
					// so we don't want the parsing to stop.
				}
			} else if (eventType == XmlPullParser.END_TAG) {
				System.out.println("End tag " + parser.getName())
			} else if (eventType == XmlPullParser.TEXT) {
				System.out.println("Text " + parser.getText())
			}
			eventType = parser.next()
		}
		// Clear internal resources and reset.
		parser.setInput(null)
		return entries
	}

	private fun parseSingleEntry(xpp: XmlPullParser): Entry? {
		var title: String? = null
		var content: String? = null
		var assignNextTextToTitle = false
		var assignNextTextToContent = false

		var eventType: Int = xpp.getEventType()
		while (eventType != XmlPullParser.END_DOCUMENT) {
			if (title != null && content != null) {
				return Entry(title, content)
			}
			if (eventType == XmlPullParser.START_TAG) {
				if (xpp.name == TITLE_TAG) {
					assignNextTextToTitle = true
				} else if (xpp.name == CONTENT_TAG || xpp.name == DESCRIPTION_TAG) {
					assignNextTextToContent = true
				}
			} else if (eventType == XmlPullParser.TEXT) {
				if (assignNextTextToContent) {
					assignNextTextToContent = false
					content = xpp.text
				} else if (assignNextTextToTitle) {
					assignNextTextToTitle = false
					title = xpp.text
				}
			}
			eventType = xpp.next()
		}
		// If we've made it this far, we don't have an entry.
		return null
	}
}

data class Entry(val title: String, val content: String)

private const val ENTRY_TAG = "entry"
private const val ITEM_TAG = "item"
private const val TITLE_TAG = "title"
private const val CONTENT_TAG = "content"
private const val DESCRIPTION_TAG = "description"
