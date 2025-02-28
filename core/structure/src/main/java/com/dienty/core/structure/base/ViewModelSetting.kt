package com.dienty.core.structure.base

import kotlinx.coroutines.channels.Channel

data class ViewModelSetting(
    val intentChannelSize: Int = Channel.UNLIMITED,
    val effectChannelSize: Int = Channel.BUFFERED,
    val errorChannelSize: Int = Channel.BUFFERED
)