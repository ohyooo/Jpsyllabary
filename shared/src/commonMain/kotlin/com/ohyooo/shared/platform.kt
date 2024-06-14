package com.ohyooo.shared

import org.jetbrains.compose.resources.FontResource

const val GITHUB_LINK = "https://github.com/ohyooo/JPSyllabary"

expect fun getPlatformName(): String

expect fun openGitHub()

expect fun getFont(): FontResource?