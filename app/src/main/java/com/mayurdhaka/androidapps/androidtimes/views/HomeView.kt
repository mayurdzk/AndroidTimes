package com.mayurdhaka.androidapps.androidtimes.views

import androidx.compose.Composable
import androidx.ui.animation.Crossfade
import com.mayurdhaka.androidapps.androidtimes.CurrentHomeState
import com.mayurdhaka.androidapps.androidtimes.HomeState


// Would be interesting to see how I could achieve this *without* the `Crossfade` animation.
// Perhaps functional composition using `map` ?
@Composable
fun HomeView(currentHomeState: CurrentHomeState) {
	Crossfade(current = currentHomeState.state) {
		when (it) {
			is HomeState.Loading -> CenteredSpinnerView()
			is HomeState.DisplayEntries -> FeedView(entries = it.entries)
		}
	}
}

