package `in`.bpj4.pokedex.pokemonlist

import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.palette.graphics.Palette
import dagger.hilt.android.lifecycle.HiltViewModel
import `in`.bpj4.pokedex.data.models.PokedexListEntry
import `in`.bpj4.pokedex.data.repository.Repository
import `in`.bpj4.pokedex.util.Constants.PAGE_SIZE
import `in`.bpj4.pokedex.util.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PokemonListViewModel @Inject constructor(
    private val repository: Repository
) : ViewModel() {

    private var currentPage = 0

    var pokemonList = mutableStateOf<List<PokedexListEntry>>(listOf())
    var loadError = mutableStateOf("")
    var isLoading = mutableStateOf(false)
    var endReached = mutableStateOf(false)

    private var cachedPokemonList = listOf<PokedexListEntry>()
    private var isNewList = true
    var isSearching = mutableStateOf(false)

    fun searchPokemonList(query: String) {
        val list = if (isNewList) pokemonList.value else cachedPokemonList
        viewModelScope.launch(Dispatchers.Default) {
            if (query.isBlank()) {
                pokemonList.value = list
                isNewList = true
                isSearching.value = false
            } else {
                val res = list.filter {
                    it.pokemonName.contains(
                        query,
                        true
                    ) || it.number == query.toIntOrNull()
                }
                if (isNewList) {
                    cachedPokemonList = pokemonList.value
                    isNewList = false
                }
                pokemonList.value = res
                isSearching.value = false
            }
        }
    }

    fun loadPokemonPaginated() {
        viewModelScope.launch {
            repository.getPokemonList(PAGE_SIZE, currentPage * PAGE_SIZE).collect { result ->
                when (result) {
                    is Resource.Loading -> isLoading.value = true
                    is Resource.Error -> {
                        loadError.value = result.message ?: ""
                        isLoading.value = false
                    }

                    is Resource.Success -> {
                        endReached.value = (currentPage * PAGE_SIZE) >= result.data!!.count
                        val pokedexEntries = result.data.results.mapIndexed { index, item ->

                            val number = if (item.url.endsWith("/"))
                                item.url.dropLast(1).takeLastWhile { it.isDigit() }
                            else
                                item.url.takeLastWhile { it.isDigit() }

                            val url =
                                "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/$number.png"
                            PokedexListEntry(item.name.uppercase(), url, number.toInt())
                        }

                        currentPage++
                        loadError.value = ""
                        isLoading.value = false
                        pokemonList.value += pokedexEntries
                    }
                }
            }
        }
    }

    fun calcDominantColor(drawable: Drawable, onFinish: (Color) -> Unit) {
        val bmp = (drawable as BitmapDrawable).bitmap.copy(Bitmap.Config.ARGB_8888, true)

        Palette.from(bmp).generate { palette ->
            palette?.dominantSwatch?.rgb?.let {
                onFinish(Color(it))
            }
        }
    }
}