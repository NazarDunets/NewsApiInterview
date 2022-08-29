package com.nazardunets.news_api_interview.configuration

interface DependenciesCollection {
    val dependencies: List<Pair<String, String>>
}

object Versions {
    object General {
        const val gradleVersion = "7.2.0"
        const val kotlinVersion = "1.6.21"
    }

    object Compose : DependenciesCollection {
        const val composeVersion = "1.2.0-beta03"

        override val dependencies = listOf(
            IMPLEMENTATION to "androidx.compose.ui:ui:$composeVersion",
            IMPLEMENTATION to "androidx.compose.material:material:$composeVersion",
            IMPLEMENTATION to "androidx.compose.ui:ui-tooling-preview:$composeVersion",
            IMPLEMENTATION to "androidx.activity:activity-compose:1.4.0",
            IMPLEMENTATION to "androidx.constraintlayout:constraintlayout-compose:1.1.0-alpha01",
            IMPLEMENTATION to "androidx.navigation:navigation-compose:2.4.2",
            IMPLEMENTATION to "io.coil-kt:coil-compose:2.1.0",

            ANDROID_TEST_IMPLEMENTATION to "androidx.compose.ui:ui-test-junit4:$composeVersion",

            DEBUG_IMPLEMENTATION to "androidx.compose.ui:ui-tooling:$composeVersion",
            DEBUG_IMPLEMENTATION to "androidx.compose.ui:ui-test-manifest:$composeVersion",
            DEBUG_IMPLEMENTATION to "androidx.customview:customview:1.2.0-alpha01",
            DEBUG_IMPLEMENTATION to "androidx.customview:customview-poolingcontainer:1.0.0-alpha01"
        )
    }

    object Ktx : DependenciesCollection {
        override val dependencies = listOf(
            IMPLEMENTATION to "androidx.core:core-ktx:1.8.0",
            IMPLEMENTATION to "androidx.lifecycle:lifecycle-runtime-ktx:2.4.1"
        )
    }

    object Material : DependenciesCollection {
        override val dependencies = listOf(
            IMPLEMENTATION to "com.google.android.material:material:1.6.1"
        )
    }

    object Koin : DependenciesCollection {
        val koinVersion = "3.2.0"

        override val dependencies = listOf(
            IMPLEMENTATION to "io.insert-koin:koin-androidx-compose:$koinVersion",
            IMPLEMENTATION to "io.insert-koin:koin-ktor:$koinVersion",
            IMPLEMENTATION to "io.insert-koin:koin-android:$koinVersion",
            IMPLEMENTATION to "io.insert-koin:koin-androidx-navigation:$koinVersion"
        )
    }

    object Ktor : DependenciesCollection {
        const val ktorVersion = "2.0.2"
        override val dependencies = listOf(
            IMPLEMENTATION to "io.ktor:ktor-client-core:$ktorVersion",
            IMPLEMENTATION to "io.ktor:ktor-client-cio:$ktorVersion",
            IMPLEMENTATION to "io.ktor:ktor-client-okhttp:$ktorVersion",
            IMPLEMENTATION to "io.ktor:ktor-client-content-negotiation:$ktorVersion",
            IMPLEMENTATION to "io.ktor:ktor-serialization-kotlinx-json:$ktorVersion",
            IMPLEMENTATION to "io.ktor:ktor-client-logging:$ktorVersion"
        )
    }

    object Serialization : DependenciesCollection {
        override val dependencies = listOf(
            IMPLEMENTATION to "org.jetbrains.kotlinx:kotlinx-serialization-json:1.3.3"
        )
    }

    object OrbitMVI : DependenciesCollection {
        val orbitVersion = "4.3.2"

        override val dependencies = listOf(
            IMPLEMENTATION to "org.orbit-mvi:orbit-viewmodel:$orbitVersion",
            IMPLEMENTATION to "org.orbit-mvi:orbit-test:$orbitVersion",
            IMPLEMENTATION to "org.orbit-mvi:orbit-compose:$orbitVersion"

        )
    }

    object Testing : DependenciesCollection {
        override val dependencies = listOf(
            TEST_IMPLEMENTATION to "junit:junit:4.13.2",
            TEST_IMPLEMENTATION to "io.mockk:mockk:1.12.4"
        )
    }

    object UiTesting : DependenciesCollection {
        override val dependencies = listOf(
            ANDROID_TEST_IMPLEMENTATION to "androidx.compose.ui:ui-test-junit4:${Compose.composeVersion}",
            ANDROID_TEST_IMPLEMENTATION to "androidx.test.ext:junit:1.1.3",
            ANDROID_TEST_IMPLEMENTATION to "androidx.test.espresso:espresso-core:3.4.0"
        )
    }

    object Room : DependenciesCollection {
        val roomVersion = "2.4.2"

        override val dependencies = listOf(
            IMPLEMENTATION to "androidx.room:room-runtime:$roomVersion",
            IMPLEMENTATION to "androidx.room:room-ktx:$roomVersion",
            KAPT to "androidx.room:room-compiler:$roomVersion"
        )
    }
}

internal const val IMPLEMENTATION = "implementation"
internal const val TEST_IMPLEMENTATION = "testImplementation"
internal const val ANDROID_TEST_IMPLEMENTATION = "androidTestImplementation"
internal const val DEBUG_IMPLEMENTATION = "debugImplementation"
internal const val KAPT = "kapt"
