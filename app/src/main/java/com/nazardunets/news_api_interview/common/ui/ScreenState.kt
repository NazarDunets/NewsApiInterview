package com.nazardunets.news_api_interview.common.ui

sealed class ScreenState<out SUCCESS, out ERROR> {
    object Loading : ScreenState<Nothing, Nothing>()

    data class Success<SUCCESS>(val data: SUCCESS) : ScreenState<SUCCESS, Nothing>()
    data class Error<ERROR>(val error: ERROR) : ScreenState<Nothing, ERROR>()
}
