package com.nazardunets.news_api_interview.news.ui.details

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Icon
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.nazardunets.news_api_interview.common.ui.ErrorContent
import com.nazardunets.news_api_interview.common.ui.LoadingOverlay
import com.nazardunets.news_api_interview.common.ui.ScreenState
import com.nazardunets.news_api_interview.theme.Colors
import com.nazardunets.news_api_interview.theme.Dimens
import com.nazardunets.news_api_interview.theme.Typography

@Composable
fun DetailsScreen(
    state: ScreenState<DetailsScreenUiModel, Exception>,
    onFavoriteClick: (Int) -> Unit,
    onOpenLinkClick: (String) -> Unit
) {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = Colors.backgroundMain()
    ) {
        when (state) {
            is ScreenState.Success -> {
                DetailsContent(state.data, onFavoriteClick, onOpenLinkClick)
            }
            is ScreenState.Error -> {
                ErrorContent(Modifier.fillMaxSize())
            }
            ScreenState.Loading -> {
                LoadingOverlay(Modifier.fillMaxSize())
            }
        }
    }
}

@Composable
fun DetailsContent(
    model: DetailsScreenUiModel,
    onFavoriteClick: (Int) -> Unit,
    onOpenLinkClick: (String) -> Unit
) {
    LazyColumn(
        modifier = Modifier.fillMaxSize()
    ) {
        item {
            AsyncImage(
                modifier = Modifier
                    .height(HeaderImageHeight)
                    .fillMaxWidth(),
                model = model.imageUrl,
                contentDescription = null,
                alignment = Alignment.Center,
                contentScale = ContentScale.Crop
            )
        }

        item {
            Spacer(modifier = Modifier.height(Dimens.indent12))
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(Dimens.indent12),
                horizontalArrangement = Arrangement.End
            ) {
                Icon(
                    modifier = Modifier
                        .size(IconButtonSize)
                        .clickable {
                            onOpenLinkClick(model.goToUrl)
                        },
                    imageVector = Icons.Default.ExitToApp,
                    contentDescription = null,
                    tint = Colors.textTitle()
                )

                Spacer(modifier = Modifier.width(Dimens.indent12))

                val icon = if (model.isFavorite) Icons.Default.Favorite else Icons.Outlined.FavoriteBorder

                Icon(
                    modifier = Modifier
                        .size(IconButtonSize)
                        .clickable {
                            onFavoriteClick(model.id)
                        },
                    imageVector = icon,
                    contentDescription = null,
                    tint = Colors.textTitle()
                )
            }
        }

        item {
            Spacer(modifier = Modifier.height(Dimens.indent12))
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = Dimens.indent8),
                text = model.title,
                textAlign = TextAlign.Center,
                style = Typography.h3,
                color = Colors.textTitle()
            )
        }

        item {
            Spacer(modifier = Modifier.height(Dimens.indent12))
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = Dimens.indent8),
                text = model.body,
                textAlign = TextAlign.Start,
                style = Typography.body1,
                color = Colors.textBody()
            )
        }
    }
}

private val HeaderImageHeight = 200.dp
private val IconButtonSize = 36.dp

@Preview
@Composable
private fun DetailsScreenPreview() {
    val model = DetailsScreenUiModel(
        id = 0,
        title = "Tear gas used to disperse protestors Arizona Capitol building, officials say - CNN",
        body = "Tear gas used to disperse protestors Arizona Capitol building, officials say - CNNTear gas used to disperse protestors Arizona Capitol building, officials say - CNNTear gas used to disperse protestors Arizona Capitol building, officials say - CNNTear gas used to disperse protestors Arizona Capitol building, officials say - CNN",
        sourceName = "CNN",
        imageUrl = "",
        goToUrl = "",
        isFavorite = false
    )

    DetailsScreen(
        state = ScreenState.Success(model),
        onFavoriteClick = {},
        onOpenLinkClick = {}
    )
}

data class DetailsScreenUiModel(
    val id: Int,
    val title: String,
    val body: String,
    val sourceName: String,
    val imageUrl: String,
    val goToUrl: String,
    val isFavorite: Boolean
)
