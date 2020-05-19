package com.mayurdhaka.androidapps.androidtimes.views

import android.webkit.WebView
import androidx.compose.Composable
import androidx.ui.core.ContextAmbient
import androidx.ui.core.Modifier
import androidx.ui.foundation.Text
import androidx.ui.layout.Column
import androidx.ui.layout.Row
import androidx.ui.layout.padding
import androidx.ui.material.MaterialTheme
import androidx.ui.tooling.preview.Preview
import androidx.ui.unit.dp
import androidx.ui.viewinterop.AndroidView
import com.mayurdhaka.androidapps.net.Entry

@Composable
fun EntryView(entry: Entry) {
	Row(modifier = Modifier.padding(8.dp)) {
		Column(modifier = Modifier.padding(8.dp)) {
			Text(text = entry.title)
			WebComponent(entry.content)
		}
	}
}

@Composable
fun WebComponent(
	url: String
) {
	// Thanks: https://stackoverflow.com/a/60608161/4400607
	val webView = WebView(ContextAmbient.current)
	webView.loadData(url, null, null)
	AndroidView(view = webView)
}

@Preview
@Composable
fun EntryViewPreview() {
	MaterialTheme {
		EntryView(samplePost)
	}
}

val samplePost = Entry(
	"The Quick Brown Fox Jumped Over the Bridge",
	"<p>Lorem ipsum dorem foo</p><p>Lorem ipsum dorem foo</p>"
)


