package com.nazardunets.news_api_interview.theme

import androidx.compose.runtime.Composable
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.ui.res.colorResource
import com.nazardunets.news_api_interview.R

object Colors {
    @Composable
    @ReadOnlyComposable
    fun primary() = colorResource(id = R.color.primary)

    @Composable
    @ReadOnlyComposable
    fun primaryVariant() = colorResource(id = R.color.primary_variant)

    @Composable
    @ReadOnlyComposable
    fun secondary() = colorResource(id = R.color.secondary)

    @Composable
    @ReadOnlyComposable
    fun secondaryVariant() = colorResource(id = R.color.secondary_variant)

    @Composable
    @ReadOnlyComposable
    fun textTitle() = colorResource(id = R.color.text_title)

    @Composable
    @ReadOnlyComposable
    fun textBody() = colorResource(id = R.color.text_body)

    @Composable
    @ReadOnlyComposable
    fun textSubtitle() = colorResource(id = R.color.text_subtitle)

    @Composable
    @ReadOnlyComposable
    fun backgroundMain() = colorResource(id = R.color.background_main)

    @Composable
    @ReadOnlyComposable
    fun backgroundSecondary() = colorResource(id = R.color.background_secondary)
}
