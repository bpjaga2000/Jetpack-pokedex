package `in`.bpj4.pokedex.data.repository

import `in`.bpj4.pokedex.data.remote.responses.Pokemon
import `in`.bpj4.pokedex.data.remote.responses.PokemonList
import `in`.bpj4.pokedex.util.Resource
import kotlinx.coroutines.flow.Flow

interface Repository {
    suspend fun getPokemonList(
        limit: Int,
        offset: Int
    ): Flow<Resource<PokemonList>>

    suspend fun getPokemonInfo(pokemonName: String): Resource<Pokemon>
}