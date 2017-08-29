package com.estudos.kotlin

import org.springframework.stereotype.Service

@Service
class HelloService(val restaurantRepository: RestaurantRepository) {

    fun hello() = "Hello!"

    fun count() = restaurantRepository.count()

    fun getById(id:Long): Restaurant? = restaurantRepository.findOne(id)

}