package com.example.pspapi1

import org.springframework.data.jpa.repository.JpaRepository

interface CocktailRepository : JpaRepository<Cocktail, Int>