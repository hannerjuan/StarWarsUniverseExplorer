package com.example.starwarsuniverseexplorer.utils

fun extractIdFromUrl(url: String): String {
    return url.split("/").filter { it.isNotEmpty() }.last()
}
