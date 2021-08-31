package com.example.r2dbcdemo

import org.springframework.data.domain.Pageable
import org.springframework.data.r2dbc.repository.R2dbcRepository
import org.springframework.stereotype.Repository
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

@Repository
interface ShopRepository: R2dbcRepository<Shop, Long> {
    override fun findById(id: Long): Mono<Shop>
    fun findByNameLike(name: String): Flux<Shop>
    override fun findAll(): Flux<Shop>
    fun findAllByNameLike(name: String, pageable: Pageable): Flux<Shop>
}