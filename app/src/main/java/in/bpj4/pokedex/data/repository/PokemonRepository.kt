package `in`.bpj4.pokedex.data.repository

import dagger.hilt.android.scopes.ActivityScoped
import `in`.bpj4.pokedex.data.remote.PokeApi
import `in`.bpj4.pokedex.data.remote.responses.Pokemon
import `in`.bpj4.pokedex.data.remote.responses.PokemonList
import `in`.bpj4.pokedex.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

@ActivityScoped
class PokemonRepository @Inject constructor(
    private val api: PokeApi
) : Repository {

    override suspend fun getPokemonList(
        limit: Int,
        offset: Int
    ): Flow<Resource<PokemonList>> = flow {
        emit(Resource.Loading())
        emit(
            try {
                Resource.Success(data = api.getPokemonList(limit, offset))
            } catch (e: Exception) {
                e.printStackTrace()
                Resource.Error("idk man fuck you")
            }
        )
    }

    override suspend fun getPokemonInfo(
        pokemonName: String
    ): Resource<Pokemon> {
        return try {
            Resource.Success(api.getPokemon(pokemonName))
        } catch (e: Exception) {
            e.printStackTrace()
            Resource.Error("idk man fuck you")
        }
    }
}