package com.nazardunets.news_api_interview.common.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Warning
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.nazardunets.news_api_interview.R
import com.nazardunets.news_api_interview.theme.Colors
import com.nazardunets.news_api_interview.theme.Dimens
import com.nazardunets.news_api_interview.theme.Typography

@Composable
fun ErrorContent(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Icon(
            modifier = Modifier.size(48.dp),
            imageVector = Icons.Default.Warning,
            contentDescription = null,
            tint = Colors.textBody()
        )

        Spacer(modifier = Modifier.height(Dimens.indent8))

        Text(
            text = stringResource(R.string.something_went_wrong),
            style = Typography.body1,
            color = Colors.textBody()
        )
    }
}
