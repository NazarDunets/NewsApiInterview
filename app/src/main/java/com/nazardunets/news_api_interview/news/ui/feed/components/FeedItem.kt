package com.nazardunets.news_api_interview.news.ui.feed.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.outlined.Favorite
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.nazardunets.news_api_interview.theme.Colors
import com.nazardunets.news_api_interview.theme.Dimens
import com.nazardunets.news_api_interview.theme.Shapes
import com.nazardunets.news_api_interview.theme.Typography

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun FeedItem(
    modifier: Modifier = Modifier,
    model: FeedItemUiModel,
    onFavoriteClick: (Int) -> Unit,
    onItemClick: (Int) -> Unit
) {
    Card(
        modifier = modifier
            .height(FeedItemHeight)
            .fillMaxWidth(),
        backgroundColor = Colors.backgroundMain(),
        shape = Shapes.medium,
        onClick = { onItemClick(model.id) }
    ) {
        Row(Modifier.fillMaxSize()) {
            AsyncImage(
                modifier = Modifier
                    .height(FeedItemHeight)
                    .aspectRatio(IMAGE_ASPECT_RATIO),
                model = model.imageUrl,
                contentDescription = null,
                contentScale = ContentScale.Crop,
                alignment = Alignment.Center
            )

            Spacer(Modifier.width(Dimens.indent8))

            Column(
                Modifier
                    .fillMaxHeight()
                    .weight(1f)
                    .padding(Dimens.indent12)
            ) {
                val icon = if (model.isFavorite) Icons.Default.Favorite else Icons.Outlined.FavoriteBorder

                Icon(
                    modifier = Modifier
                        .align(Alignment.End)
                        .clickable {
                            onFavoriteClick(model.id)
                        },
                    imageVector = icon,
                    contentDescription = null,
                    tint = Colors.textTitle()
                )

                Spacer(Modifier.height(Dimens.indent4))

                Text(
                    modifier = Modifier
                        .weight(1f),
                    text = model.title,
                    style = Typography.h3,
                    color = Colors.textTitle(),
                    overflow = TextOverflow.Ellipsis,
                )

                Spacer(Modifier.height(Dimens.indent4))

                Text(
                    modifier = Modifier
                        .align(Alignment.End),
                    text = model.sourceName,
                    style = Typography.subtitle1,
                    color = Colors.textTitle(),
                    overflow = TextOverflow.Ellipsis,
                    maxLines = 1
                )
            }
        }
    }
}

private val FeedItemHeight = 150.dp
private const val IMAGE_ASPECT_RATIO = 3f / 4

data class FeedItemUiModel(
    val id: Int,
    val title: String,
    val sourceName: String,
    val imageUrl: String,
    val isFavorite: Boolean
)

@Preview
@Composable
private fun FeedItemPreview() {
    val model = FeedItemUiModel(
        id = 12,
        title = "Tear gas used to disperse protestors Arizona Capitol building, officials say - CNN",
        sourceName = "CNN",
        imageUrl = "",
        isFavorite = true
    )

    FeedItem(
        model = model,
        onFavoriteClick = {},
        onItemClick = {}
    )
}
