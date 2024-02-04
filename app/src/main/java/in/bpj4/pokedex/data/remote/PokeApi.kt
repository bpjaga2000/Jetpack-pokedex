package `in`.bpj4.pokedex.data.remote

import `in`.bpj4.pokedex.data.remote.responses.Pokemon
import `in`.bpj4.pokedex.data.remote.responses.PokemonList
import `in`.bpj4.pokedex.util.Resource
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface PokeApi {

    @GET("pokemon")
    suspend fun getPokemonList(
        @Query("limit") limit: Int,
        @Query("offset") offset: Int
    ): PokemonList

    @GET("pokemon/{pokemonName}")
    suspend fun getPokemon(
        @Path("pokemonName") pokemonName: String,
    ): Pokemon
}