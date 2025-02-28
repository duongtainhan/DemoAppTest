package com.dienty.core.network.logging

class Configuration @JvmOverloads constructor(
    val headerModifiers: List<HeaderModifier> = emptyList(),
    val components: List<CommandComponent> = CommandComponent.DEFAULT,
    val flags: CurlFlags = CurlFlags.EMPTY,
    val limit: Long = 1024L * 1024L,
    val delimiter: String = " "
)