package game.play.pokemon_pokedex.ui

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.provider.Settings
import android.view.View
import android.view.WindowInsets
import androidx.annotation.RequiresApi
import androidx.appcompat.app.ActionBar
import androidx.fragment.app.Fragment
import game.play.pokemon_pokedex.R
import game.play.pokemon_pokedex.databinding.FragmentOverlayBinding
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.findNavController

class OverlayFragment : Fragment(R.layout.fragment_overlay) {

    private lateinit var binding: FragmentOverlayBinding

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        hideSystemBars()

        binding = FragmentOverlayBinding.bind(view)

        if (Settings.canDrawOverlays(context)) {
            findNavController().navigate(
                R.id.action_overlayFragment_to_pokeListFragment
            )
        }

        binding.overlayButton.setOnClickListener {

            if (!Settings.canDrawOverlays(context)) {
                val intent = Intent(
                    Settings.ACTION_MANAGE_OVERLAY_PERMISSION,

                    )
                startActivityForResult(intent, 0)
            }
        }
    }

    private fun hideSystemBars() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            activity?.window?.insetsController?.hide(WindowInsets.Type.systemBars())
        } else {
            var uiVisibility = activity?.window?.decorView!!.systemUiVisibility
            uiVisibility = uiVisibility or View.SYSTEM_UI_FLAG_LOW_PROFILE
            uiVisibility = uiVisibility or View.SYSTEM_UI_FLAG_FULLSCREEN
            uiVisibility = uiVisibility or View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
            uiVisibility = uiVisibility or View.SYSTEM_UI_FLAG_IMMERSIVE
            uiVisibility = uiVisibility or View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
            activity?.window?.decorView!!.systemUiVisibility = uiVisibility
        }
    }

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (Settings.canDrawOverlays(context)) {
            findNavController().navigate(
                R.id.action_overlayFragment_to_pokeListFragment
            )
        }
    }

    override fun onResume() {
        super.onResume()
        (activity as AppCompatActivity?)!!.supportActionBar!!.hide()
    }

    override fun onStop() {
        super.onStop()
        (activity as AppCompatActivity?)!!.supportActionBar!!.show()
    }
}