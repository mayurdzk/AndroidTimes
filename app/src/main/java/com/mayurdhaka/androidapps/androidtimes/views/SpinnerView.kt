package com.mayurdhaka.androidapps.androidtimes.views

import androidx.compose.Composable
import androidx.ui.core.Alignment
import androidx.ui.core.Modifier
import androidx.ui.foundation.Box
import androidx.ui.layout.fillMaxSize
import androidx.ui.layout.wrapContentSize
import androidx.ui.material.CircularProgressIndicator

@Composable
fun CenteredSpinnerView() {
	Box(modifier = Modifier.fillMaxSize().wrapContentSize(Alignment.Center)) {
		CircularProgressIndicator()
	}
}
