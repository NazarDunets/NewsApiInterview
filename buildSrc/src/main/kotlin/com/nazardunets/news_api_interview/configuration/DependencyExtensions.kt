package com.nazardunets.news_api_interview.configuration

import org.gradle.kotlin.dsl.DependencyHandlerScope
import org.gradle.kotlin.dsl.project

fun DependencyHandlerScope.collections(vararg collections: DependenciesCollection) {
    collections.forEach { collection ->
        collection.dependencies.forEach {
            add(it.first, it.second)
        }
    }
}

fun DependencyHandlerScope.modules(vararg modules: String) {
    modules.forEach {
        IMPLEMENTATION(project(it))
    }
}

fun DependencyHandlerScope.localModulor() {
    IMPLEMENTATION(name = "ui-kit-release", ext = "aar", group = "com.nazardunets.modulor")
    IMPLEMENTATION(name = "resources-release", ext = "aar", group = "com.nazardunets.resources")
}

fun DependencyHandlerScope.localNavigation() {
    IMPLEMENTATION(name = "navigation-release", ext = "aar", group = "com.nazardunets.navigation")
}
