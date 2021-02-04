package com.example.pspapi1

import org.slf4j.LoggerFactory
import org.springframework.boot.CommandLineRunner
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration


@Configuration
class LoadDatabase {
    companion object{
        val logger = LoggerFactory.getLogger(LoadDatabase.javaClass)
    }

    @Bean
    fun initDatabase(cocktailRepository : CocktailRepository) : CommandLineRunner{
        return CommandLineRunner { args: Array<String?>? ->
            logger.info("Preloading " + cocktailRepository.save(Cocktail(11007,"Margarita","IBA,ContemporaryClassic","Alcoholic")))
            logger.info("Preloading " + cocktailRepository.save(Cocktail(11118, "Blue Margarita","AERc Peore","Alcoholic")))
            logger.info("Preloading " + cocktailRepository.save(Cocktail(17216,"Tommy's Margarita","IBA, New Era","Alcoholic")))
            logger.info("Preloading " + cocktailRepository.save(Cocktail(11007,"Whitecap Margarita","IBA,Perod","Alcoholic")))
        }
    }

}