package com.estudos.kotlin

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController

@SpringBootApplication
@Configuration
@EnableWebSecurity
open class Application : WebSecurityConfigurerAdapter() {

    override fun configure(http: HttpSecurity) {
        http
            .authorizeRequests().anyRequest().hasRole("ADMIN")
        .and()
            .httpBasic()
    }

    override fun configure(auth: AuthenticationManagerBuilder) {
        auth
            .inMemoryAuthentication()
                .withUser("admin").password("pass").roles("ADMIN")
            .and()
                .withUser("user").password("pass").roles("USER")

    }
}

@RestController
class HomeController(val helloService: HelloService) {

    @GetMapping("/hello")
    fun hello() = helloService.hello()

    @GetMapping("/count")
    fun count() = helloService.count()

    @GetMapping("/restaurant/{id}")
    fun getById(@PathVariable id:Long) = helloService.getById(id)

}

fun main(args: Array<String>) {
    SpringApplication.run(Application::class.java, *args)
}

