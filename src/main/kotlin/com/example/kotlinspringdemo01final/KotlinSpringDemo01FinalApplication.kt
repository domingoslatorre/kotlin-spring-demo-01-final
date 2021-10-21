package com.example.kotlinspringdemo01final

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.web.bind.annotation.*
import java.time.LocalDate

@SpringBootApplication
class KotlinSpringDemo01FinalApplication

fun main(args: Array<String>) {
	runApplication<KotlinSpringDemo01FinalApplication>(*args)
}

@RestController
@RequestMapping("hello-world")
class HelloWorldController {
	@GetMapping
	fun index(): String = "Hello World"
}

enum class Especie {
	Cachorro,
	Gato,
	Passaro
}

class Animal(
	val nome: String,
	val especie: Especie,
	val peso: Double,
	val dataNascimento: LocalDate
)

val animais = mutableListOf<Animal>()

@RestController
@RequestMapping("animais")
class AnimalController {
	@GetMapping
	fun index(@RequestParam(required = false) especie: Especie?) =
		if(especie != null) animais.filter { it.especie == especie }
		else animais

	@PostMapping
	fun create(@RequestBody animal: Animal): Animal {
		animais.add(animal)
		return animal
	}
}
