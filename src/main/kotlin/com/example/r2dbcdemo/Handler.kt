package com.example.r2dbcdemo

import org.springframework.web.bind.annotation.*
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import java.time.Clock
import java.time.LocalDateTime

@RestController
@RequestMapping(value = ["/**"])
class Handler( private val repo: ShopRepository ) {

    @GetMapping(value = ["/shop/{id}"], produces = ["application/json"])
    fun get(@PathVariable(name = "id") id: Long): Mono<Shop> {
        if (id > 0) return repo.findById(id)
        else throw IllegalArgumentException("Illegal Shop Id value.")
    }

    @PostMapping(value = ["/shop"], consumes = ["application/json"], produces = ["application/json"])
    fun save(@RequestBody name: String): Mono<Shop> {
        return repo.save(Shop(name = name, open = LocalDateTime.now(Clock.systemDefaultZone())))
    }

    @PutMapping(value = ["/shop/{id}"], produces = ["application/json"])
    fun update(@PathVariable(name = "id") id: Long): Mono<Shop> {
        return repo.findById(id)
            .map {
                it.apply {
                    close = LocalDateTime.now().plusDays(1)
                }
            }.onErrorMap { IllegalArgumentException("Illegal Shop Id Value.") }
            .log()
    }

    @GetMapping(value = ["/shops"], produces = ["application/json"])
    fun retrieve(@RequestParam(name = "page", required = false, defaultValue = "0") page: Int,
                 @RequestParam(name = "renderItem", required = false, defaultValue = "1") renderItem: Int): Flux<Shop> {
        return repo.findAll().skip(page * renderItem.toLong()).take(renderItem.toLong())
    }
}