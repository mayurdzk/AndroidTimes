package com.mayurdhaka.androidapps.androidtimes.views

import androidx.compose.Composable
import androidx.ui.foundation.VerticalScroller
import androidx.ui.layout.Column
import com.mayurdhaka.androidapps.net.Entry

@Composable
fun FeedView(entries: List<Entry>) {
	VerticalScroller {
		Column {
			entries.forEach {
				EntryView(entry = it)
			}
		}
	}
}
