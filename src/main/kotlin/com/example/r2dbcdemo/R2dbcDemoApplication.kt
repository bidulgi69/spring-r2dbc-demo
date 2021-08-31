package com.example.r2dbcdemo

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.data.r2dbc.repository.config.EnableR2dbcRepositories

@EnableR2dbcRepositories(basePackages = ["com.example.r2dbcdemo"])
@SpringBootApplication
class R2dbcDemoApplication

fun main(args: Array<String>) {
    runApplication<R2dbcDemoApplication>(*args)
}
