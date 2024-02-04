package `in`.bpj4.pokedex.pokemondetail

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import `in`.bpj4.pokedex.data.remote.responses.Pokemon
import `in`.bpj4.pokedex.data.repository.Repository
import `in`.bpj4.pokedex.util.Resource
import javax.inject.Inject

@HiltViewModel
class PokemonDetailViewmodel @Inject constructor(
    private val repository: Repository
) : ViewModel() {

    suspend fun getPokemonDetails(pokemonName: String): Resource<Pokemon>{
        return repository.getPokemonInfo(pokemonName)
    }

}