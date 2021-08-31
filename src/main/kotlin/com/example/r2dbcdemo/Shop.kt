package com.example.r2dbcdemo

import org.springframework.data.annotation.Id
import java.time.LocalDateTime

data class Shop(
    @Id val id: Long? = null,
    var name: String,
    var open: LocalDateTime? = null,
    var close: LocalDateTime? = null,
)