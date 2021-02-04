package com.example.pspapi1

import java.util.*
import javax.persistence.Entity
import javax.persistence.Id

@Entity
class Cocktail (@Id var idDrink : Int, var strDrink : String, var strTags : String, var strAlcoholic : String){

    override fun equals(other: Any?): Boolean {
        if (other is Cocktail){
            return other.idDrink == idDrink && idDrink != null
        }else{
            return false
        }
    }

    override fun hashCode(): Int {
        return Objects.hash(idDrink,strDrink,strTags,strAlcoholic)
    }


    override fun toString(): String {
        return "$strDrink es un alcohol con id $idDrink, es $strAlcoholic y su empresa es $strTags"
    }



}