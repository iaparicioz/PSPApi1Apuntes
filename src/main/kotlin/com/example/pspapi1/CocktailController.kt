package com.example.pspapi1

import org.springframework.web.bind.annotation.*

class CocktailController (private val cocktailRepository: CocktailRepository){

    @GetMapping("/cocktails")
    fun getAllCocktails(): MutableList<Cocktail> {
        return cocktailRepository.findAll()
    }

    @PostMapping("/cocktail")
    fun insertCocktail(@RequestBody cocktail: Cocktail){
        cocktailRepository.save(cocktail)
    }

    @GetMapping("/cocktail/{idDrink}")
    fun getCocktail(@PathVariable idDrink : Int) : Cocktail {
        return cocktailRepository.findById(idDrink).get()
    }

    @DeleteMapping("/cocktail/{idDrink}")
    fun deleteCocktail(@PathVariable idDrink : Int){
        cocktailRepository.deleteById(idDrink)
    }

}