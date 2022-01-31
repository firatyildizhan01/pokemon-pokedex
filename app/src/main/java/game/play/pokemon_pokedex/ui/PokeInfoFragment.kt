package game.play.pokemon_pokedex.ui

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import game.play.pokemon_pokedex.R
import game.play.pokemon_pokedex.databinding.FragmentPokeInfoBinding
import game.play.pokemon_pokedex.viewmodels.PokeInfoViewModel


class PokeInfoFragment : Fragment(R.layout.fragment_poke_info) {

    val args: PokeInfoFragmentArgs by navArgs()

    private lateinit var binding: FragmentPokeInfoBinding

    private lateinit var viewModel: PokeInfoViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentPokeInfoBinding.bind(view)

        viewModel = ViewModelProvider(this).get(PokeInfoViewModel::class.java)
    }

    private fun initUI() {

        val id = args.id

        viewModel.getPokemonInfo(id)

        viewModel.pokemonInfo.observe(this, { pokemon ->
            binding.nameTextView.text = pokemon.name
            binding.heightText.text = "${pokemon.height}m"
            binding.weightText.text = "${pokemon.weight}"

            Glide.with(this).load(pokemon.sprites.frontDefault).into(binding.pokemonImageVEw)
        })

    }
}
