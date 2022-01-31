package game.play.pokemon_pokedex.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import game.play.pokemon_pokedex.api.PokeAPI
import game.play.pokemon_pokedex.api.RetrofitClient
import game.play.pokemon_pokedex.models.Pokemon
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.create

class PokeInfoViewModel : ViewModel() {

    private val service: PokeAPI = RetrofitClient.getClient().create(PokeAPI::class.java)

    val pokemonInfo = MutableLiveData<Pokemon>()

    fun getPokemonInfo(id: Int) {
        val call = service.getPokemonInfo(id)

        call.enqueue(object : Callback<Pokemon> {
            override fun onResponse(call: Call<Pokemon>, response: Response<Pokemon>) {
                response.body()?.let { pokemon ->
                    pokemonInfo.postValue(pokemon)
                }
            }

            override fun onFailure(call: Call<Pokemon>, t: Throwable) {
                call.cancel()
            }

        })

    }

}