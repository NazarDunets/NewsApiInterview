package com.nazardunets.news_api_interview.details.ui

import android.content.Intent
import android.net.Uri
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalContext
import com.nazardunets.news_api_interview.news.ui.details.DetailsScreen
import com.nazardunets.news_api_interview.news.ui.details.DetailsViewModel
import org.koin.androidx.compose.koinViewModel
import org.koin.core.parameter.parametersOf
import org.orbitmvi.orbit.compose.collectAsState
import org.orbitmvi.orbit.compose.collectSideEffect

@Composable
fun DetailsDestination(
    id: Int,
) {
    val vm = koinViewModel<DetailsViewModel> { parametersOf(id) }
    val state by vm.collectAsState()

    val context = LocalContext.current

    fun openInBrowser(link: String) {
        Intent(
            Intent.ACTION_VIEW,
            Uri.parse(link)
        ).also {
            context.startActivity(it)
        }
    }

    vm.collectSideEffect {
        openInBrowser(link = it.link)
    }

    DetailsScreen(
        state = state,
        onFavoriteClick = vm::onFavoriteClick,
        onOpenLinkClick = vm::onOpenLinkClick,
    )
}
