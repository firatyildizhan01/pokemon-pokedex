package game.play.pokemon_pokedex.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import game.play.pokemon_pokedex.databinding.CardPokemonBinding
import game.play.pokemon_pokedex.models.PokeResult


class PokeListAdapter(val pokemonClick: (Int) -> Unit) :
    RecyclerView.Adapter<PokeListAdapter.PokeViewHolder>() {

    var pokemonList: List<PokeResult> = emptyList<PokeResult>()

    fun setData(list: List<PokeResult>) {
        pokemonList = list
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): PokeViewHolder {
        val binding =
            CardPokemonBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PokeViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return pokemonList.size
    }

    override fun onBindViewHolder(holder: PokeViewHolder, position: Int) {
        val pokemon = pokemonList[position]

        holder.binding.pokemonText.text = pokemon.name

        holder.binding.root.setOnClickListener { pokemonClick(position + 1) }
    }


    class PokeViewHolder(val binding: CardPokemonBinding) : RecyclerView.ViewHolder(binding.root) {
    }
}