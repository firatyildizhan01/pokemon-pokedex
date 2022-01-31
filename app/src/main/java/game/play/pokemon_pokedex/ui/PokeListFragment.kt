package game.play.pokemon_pokedex.ui

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import game.play.pokemon_pokedex.R
import game.play.pokemon_pokedex.adapters.PokeListAdapter
import game.play.pokemon_pokedex.databinding.FragmentPokeListBinding
import game.play.pokemon_pokedex.viewmodels.PokeListViewModel


class PokeListFragment : Fragment(R.layout.fragment_poke_list) {

    private lateinit var viewModel: PokeListViewModel
    private lateinit var binding: FragmentPokeListBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentPokeListBinding.bind(view)

        viewModel = ViewModelProvider(this).get(PokeListViewModel::class.java)

        initUI()

    }

    private fun initUI() {
        binding.pokelistRecyclerView.layoutManager = LinearLayoutManager(context)
        binding.pokelistRecyclerView.adapter = PokeListAdapter {
            val action = PokeListFragmentDirections.actionPokeListFragmentToPokeInfoFragment(it)
            findNavController().navigate(action)

        }

        viewModel.getPokemonList()

        viewModel.pokemonList.observe(this, { list ->
            (binding.pokelistRecyclerView.adapter as PokeListAdapter).setData(list)
        })
    }
}

