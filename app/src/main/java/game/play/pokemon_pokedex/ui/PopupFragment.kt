package game.play.pokemon_pokedex.ui

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.provider.Settings
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import game.play.pokemon_pokedex.R
import game.play.pokemon_pokedex.databinding.FragmentPopupBinding
import game.play.pokemon_pokedex.databinding.FragmentPopupWindowBinding
import game.play.pokemon_pokedex.service.ForegroundService
import game.play.pokemon_pokedex.viewmodels.PokeInfoViewModel

class PopupFragment : Fragment(R.layout.fragment_popup_window) {

    val args: PopupFragmentArgs by navArgs()

    private lateinit var viewModel: PokeInfoViewModel
    private lateinit var binding: FragmentPopupWindowBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentPopupWindowBinding.bind(view)

        viewModel = ViewModelProvider(this).get(PokeInfoViewModel::class.java)

        initUI()
        checkOverlayPermission()
        startService()

        val intent = Intent(Intent.ACTION_MAIN)
        intent.addCategory(Intent.CATEGORY_HOME)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
        startActivity(intent)
    }

    fun startService() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            // check if the user has already granted
            // the Draw over other apps permission
            if (Settings.canDrawOverlays(context)) {
                // start the service based on the android version
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    activity!!.startForegroundService(
                        Intent(
                            activity,
                            ForegroundService::class.java
                        )
                    )
                } else {
                    activity?.startService(Intent(activity, ForegroundService::class.java))

                }
            }
        } else {
            activity?.startService(Intent(activity, ForegroundService::class.java))
        }
    }

    // method to ask user to grant the Overlay permission
    fun checkOverlayPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (!Settings.canDrawOverlays(context)) {
                // send user to the device settings
                val myIntent = Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION)
                startActivity(myIntent)
            }
        }
    }

    private fun initUI() {
        val id = args.id

        viewModel.getPokemonInfo(id)

        viewModel.pokemonInfo.observe(this, { pokemon ->
            binding.pokemonNameText.text = "Pokemon Name : ${pokemon.name}"

            Glide.with(this).load(pokemon.sprites.frontDefault).into(binding.imageFront)
            Glide.with(this).load(pokemon.sprites.backDefault).into(binding.imageBack)

        })
    }


    // check for permission again when user grants it from
    // the device settings, and start the service
    override fun onResume() {
        super.onResume()
        startService()
    }
}

