import com.nazardunets.news_api_interview.configuration.Versions
import com.nazardunets.news_api_interview.configuration.collections

plugins {
    id("com.android.application")
    kotlin("android")
    kotlin("kapt")
    kotlin("plugin.serialization")
}

android {
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = Versions.Compose.composeVersion
    }
}

dependencies {
    collections(
        Versions.Compose,
        Versions.Ktx,
        Versions.OrbitMVI,
        Versions.Ktor,
        Versions.Koin,
        Versions.Room,
        Versions.Serialization,
        Versions.Testing,
        Versions.UiTesting
    )
}