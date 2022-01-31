package game.play.pokemon_pokedex.models

import com.google.gson.annotations.SerializedName

data class Pokemon(
    @SerializedName("id") val id: Int,
    @SerializedName("name") val name: String,
    @SerializedName("weight") val weight: Int,
    @SerializedName("height") val height: Int,
    @SerializedName("sprites") val sprites: Sprites,
)

data class Sprites(
    @SerializedName("front_default") val frontDefault: String?,
    @SerializedName("back_default") val backDefault: String?
)