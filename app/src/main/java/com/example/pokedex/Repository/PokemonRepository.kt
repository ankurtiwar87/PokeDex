package com.example.pokedex.Repository

import com.example.pokedex.data.remote.PokeApi
import com.example.pokedex.data.remote.response.Pokemon
import com.example.pokedex.data.remote.response.PokemonList
import com.example.pokedex.util.Resource
import dagger.hilt.android.scopes.ActivityScoped
import javax.inject.Inject


@ActivityScoped
class PokemonRepository @Inject constructor(
    private val api: PokeApi
){
    suspend fun getPokemonList(limit:Int,offset:Int):Resource<PokemonList>{
        val response =try {
            api.getPokemonList(limit,offset)
        }
        catch(e:Exception){
            return Resource.Failure("Ann Unknown error occur")
        }

        return Resource.Success(response)
    }


    suspend fun getPokemonInfo(name:String):Resource<Pokemon>{
        val response =try {
            api.getPokemonInfo(name)
        }
        catch(e:Exception){
            return Resource.Failure("Ann Unknown error occur")
        }

        return Resource.Success(response)
    }
}