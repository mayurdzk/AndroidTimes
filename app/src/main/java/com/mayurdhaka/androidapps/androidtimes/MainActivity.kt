package com.mayurdhaka.androidapps.androidtimes

import android.content.Context
import android.os.Bundle
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.Composable
import androidx.compose.Model
import androidx.ui.core.Alignment
import androidx.ui.core.ContextAmbient
import androidx.ui.core.Modifier
import androidx.ui.core.setContent
import androidx.ui.foundation.Box
import androidx.ui.foundation.Text
import androidx.ui.layout.*
import androidx.ui.material.CircularProgressIndicator
import androidx.ui.material.MaterialTheme
import androidx.ui.tooling.preview.Preview
import androidx.ui.unit.dp
import androidx.ui.viewinterop.AndroidView
import com.mayurdhaka.androidapps.androidtimes.views.EntryView
import com.mayurdhaka.androidapps.androidtimes.views.FeedView
import com.mayurdhaka.androidapps.androidtimes.views.HomeView
import com.mayurdhaka.androidapps.androidtimes.views.samplePost
import com.mayurdhaka.androidapps.net.Entry
import com.mayurdhaka.androidapps.net.FeedFetcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlin.coroutines.CoroutineContext

class MainActivity : AppCompatActivity() {
	private val feedFetcher = FeedFetcher()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

		setContent {
			MaterialTheme {
				HomeView(currentHomeState = CurrentHomeState)
			}
		}

		GlobalScope.launch {
			val dfFeed = feedFetcher.getFeedAtURL("https://daringfireball.net/feeds/main")
			withContext(Dispatchers.Main) {
				CurrentHomeState.state = HomeState.DisplayEntries(dfFeed)
			}
		}
    }
}

sealed class HomeState {
	object Loading : HomeState()
	data class DisplayEntries(val entries: List<Entry>): HomeState()
}

@Model
object CurrentHomeState {
	var state: HomeState = HomeState.Loading
}


