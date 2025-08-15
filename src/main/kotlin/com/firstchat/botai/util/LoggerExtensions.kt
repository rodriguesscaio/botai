package com.firstchat.botai.util

import org.slf4j.Logger
import org.slf4j.LoggerFactory

inline val <T : Any> T.logger: Logger
    get() = LoggerFactory.getLogger(this::class.java)