package com.example.pspapi1

import org.springframework.data.jpa.domain.AbstractPersistable_.id
import org.springframework.web.bind.annotation.*
import java.security.MessageDigest
import java.util.*
import javax.crypto.Cipher
import javax.crypto.spec.SecretKeySpec

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
        // sin cifrado --> return cocktailRepository.findById(idDrink).get()
        //con cifrado:
        val cifrado = idDrink.toString()
        val llave = "ASDFGHJKL"
        val descifrado = descifrar(cifrar(cifrado, llave), llave)
        return cocktailRepository.findById(descifrado.toInt()).get()
    }

    @DeleteMapping("/cocktail/{idDrink}")
    fun deleteCocktail(@PathVariable idDrink : Int){
        cocktailRepository.deleteById(idDrink)
    }

}

private fun cifrar (textoString: String, llaveString: String) : String {
    println("Cifrando $textoString ...")
    val cipher = Cipher.getInstance("AES/ECB/PKCS5Padding")
    cipher.init(Cipher.ENCRYPT_MODE, getKey(llaveString))
    val textoCifrado = Base64.getEncoder().encodeToString(cipher.doFinal(textoString.toByteArray(Charsets.UTF_8)))
    println("He obtenido $textoCifrado")
    return textoCifrado
}

private fun descifrar (textoCifrado: String, llaveString: String) : String {
    println("Descifrando $textoCifrado ...")
    val cipher = Cipher.getInstance("AES/ECB/PKCS5Padding")
    cipher.init(Cipher.DECRYPT_MODE, getKey(llaveString))
    val textoDescifrado = String(cipher.doFinal(Base64.getDecoder().decode(textoCifrado)))
    println("He obtenido $textoDescifrado")
    return textoDescifrado
}

private fun getKey (llaveString: String) : SecretKeySpec {
    var llaveUTF8 = llaveString.toByteArray(Charsets.UTF_8)
    val sha = MessageDigest.getInstance("SHA-1")
    llaveUTF8 = sha.digest(llaveUTF8)
    llaveUTF8 = llaveUTF8.copyOf(16)
    return SecretKeySpec(llaveUTF8, "AES")
}