package game.play.pokemon_pokedex.models

import com.google.gson.annotations.SerializedName

data class PokeApiResponse(
    @SerializedName("count") val count: Int,
    @SerializedName("next") val next: String,
    @SerializedName("previous") val previous: String,
    @SerializedName("results") val results: List<PokeResult>
)

data class PokeResult(
    @SerializedName("name") val name: String,
    @SerializedName("url") val url: String,
)