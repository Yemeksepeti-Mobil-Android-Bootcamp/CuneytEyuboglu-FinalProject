package com.example.yemeksiparisapp


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.*
import com.example.yemeksiparisapp.data.local.SharedPrefManager
import com.example.yemeksiparisapp.utils.onNavDestinationSelected
import com.fxn.BubbleTabBar
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val bottomNavView: BubbleTabBar = findViewById(R.id.bottom_nav_view)

        val navController = findNavController(R.id.nav_host_fragment_container)
        Navigation.setViewNavController(bottomNavView,navController)
        bottomNavView.addBubbleListener{id->
            bottomNavView.onNavDestinationSelected(id,navController)
        }
        navController.addOnDestinationChangedListener{_,destination,_ ->
            //I don't think its the right way to do that.
            if (destination.label == "fragment_basket" || destination.label == "ProfileFragment") {
                bottomNavView.setSelectedWithId(destination.id, false)
            }
        }
    }
}