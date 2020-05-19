package com.mayurdhaka.androidapps.androidtimes

import android.os.Bundle
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.Composable
import androidx.ui.core.ContextAmbient
import androidx.ui.core.Modifier
import androidx.ui.core.setContent
import androidx.ui.foundation.Text
import androidx.ui.layout.Column
import androidx.ui.layout.Row
import androidx.ui.layout.padding
import androidx.ui.material.MaterialTheme
import androidx.ui.tooling.preview.Preview
import androidx.ui.unit.dp
import androidx.ui.viewinterop.AndroidView
import com.mayurdhaka.androidapps.androidtimes.views.EntryView
import com.mayurdhaka.androidapps.androidtimes.views.FeedView
import com.mayurdhaka.androidapps.androidtimes.views.samplePost
import com.mayurdhaka.androidapps.net.Entry

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme {
                FeedView(getListOfEntries())
            }
        }
    }
}

private fun getListOfEntries(): List<Entry> {
	return listOf(
		samplePost,
		samplePost,
		samplePost,
		samplePost,
		samplePost,
		samplePost,
		samplePost,
		samplePost,
		samplePost
	)
}

