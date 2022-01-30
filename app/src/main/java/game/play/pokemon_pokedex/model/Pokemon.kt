package game.play.pokemon_pokedex.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Pokemon(
    @Expose @SerializedName("id") val id: Int,
    @Expose @SerializedName("name") val name: String,
    @Expose @SerializedName("weight") val weight: Int,
    @Expose @SerializedName("height") val height: Int,
    @Expose @SerializedName("sprites") val sprites: Int,
)

data class Sprites(
    @Expose @SerializedName("front_default") val frontDefault: String?,
    @Expose @SerializedName("front_default") val backDefault: String?
)