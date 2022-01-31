package game.play.pokemon_pokedex.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import game.play.pokemon_pokedex.api.PokeAPI
import game.play.pokemon_pokedex.api.RetrofitClient
import game.play.pokemon_pokedex.models.PokeApiResponse
import game.play.pokemon_pokedex.models.PokeResult
import retrofit2.Call
import retrofit2.Response

class PokeListViewModel() : ViewModel() {

    private val service: PokeAPI = RetrofitClient.getClient().create(PokeAPI::class.java)

    val pokemonList = MutableLiveData<List<PokeResult>>()

    fun getPokemonList() {
        val call = service.getPokemonList(100, 0)

        call.enqueue(object : retrofit2.Callback<PokeApiResponse> {
            override fun onResponse(
                call: Call<PokeApiResponse>,
                response: Response<PokeApiResponse>
            ) {
                response.body()?.results?.let { list ->
                    pokemonList.postValue(list)
                }
            }

            override fun onFailure(call: Call<PokeApiResponse>, t: Throwable) {
                call.cancel()
            }
        })
    }
}