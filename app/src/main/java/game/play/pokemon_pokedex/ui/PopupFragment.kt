package game.play.pokemon_pokedex.ui

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.provider.Settings
import android.view.View
import androidx.fragment.app.Fragment
import game.play.pokemon_pokedex.R
import game.play.pokemon_pokedex.service.ForegroundService

class PopupFragment : Fragment(R.layout.fragment_popup) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        checkOverlayPermission()
        startService()
    }

//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        checkOverlayPermission()
//        startService()
//    }


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

    // check for permission again when user grants it from
    // the device settings, and start the service
    override fun onResume() {
        super.onResume()
        startService()
    }
}