package com.nazardunets.news_api_interview.navigation

import androidx.navigation.NamedNavArgument

// Copy-pasted from another pet project
abstract class Graph {
    protected abstract val localRoute: String

    // route to use, when constructing the graph contains arguments declaration
    abstract val graphRoute: String

    // route to use, when trying to navigate to the current destination
    abstract val navRoute: String

    open val args: List<NamedNavArgument> = emptyList()

    protected val argsPostfix: String
        get() {
            if (args.isEmpty()) return ""

            return buildString {
                append(QUESTION_MARK)
                args.forEachIndexed { index, it ->
                    append(it.name, EQUALS, PARAM_NAME_PREFIX, it.name, PARAM_NAME_POSTFIX)
                    if (index != args.size - 1) append(AMPERSAND)
                }
            }
        }

    fun getNavRouteWithArgs(args: Array<out Pair<String, Any>>): String {
        return buildString {
            append(navRoute)
            if (args.isNotEmpty()) {
                append(QUESTION_MARK)
                args.forEachIndexed { index, it ->
                    append(it.first, EQUALS, it.second)
                    if (index != args.size - 1) append(AMPERSAND)
                }
            }
        }
    }

    override fun toString(): String = graphRoute

    abstract inner class Destination(
        final override val localRoute: String,
    ) : Graph() {
        override val navRoute: String
            get() = buildString {
                append(this@Graph.graphRoute)
                append(ROUTE_SEPARATOR)
                append(localRoute)
            }

        override val graphRoute: String
            get() = buildString {
                append(navRoute)
                append(argsPostfix)
            }

    }
}

typealias SubGraph = Graph.Destination

abstract class RootGraph(final override val localRoute: String) : Graph() {
    override val navRoute: String = localRoute
    override val graphRoute: String = localRoute
}

internal const val ROUTE_SEPARATOR = '/'
internal const val QUESTION_MARK = '?'
internal const val EQUALS = '='
internal const val AMPERSAND = '&'
internal const val PARAM_NAME_PREFIX = '{'
internal const val PARAM_NAME_POSTFIX = '}'
