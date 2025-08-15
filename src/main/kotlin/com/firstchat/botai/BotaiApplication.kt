package com.firstchat.botai

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.scheduling.annotation.EnableAsync

@SpringBootApplication
@EnableAsync
class BotaiApplication

fun main(args: Array<String>) {
	runApplication<BotaiApplication>(*args)
}
