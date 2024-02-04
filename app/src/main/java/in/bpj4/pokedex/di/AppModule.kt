package `in`.bpj4.pokedex.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import `in`.bpj4.pokedex.data.remote.PokeApi
import `in`.bpj4.pokedex.data.repository.PokemonRepository
import `in`.bpj4.pokedex.data.repository.Repository
import `in`.bpj4.pokedex.util.Constants.BASE_URL
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun providesPokemonRepository(
        api: PokeApi
    ): Repository = PokemonRepository(api)

    @Singleton
    @Provides
    fun providesPokeApi(): PokeApi {
        val logging = HttpLoggingInterceptor()
        return Retrofit.Builder().addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_URL).client(
                OkHttpClient.Builder().addInterceptor(logging).build()
            ).build().create(PokeApi::class.java)
    }
}
