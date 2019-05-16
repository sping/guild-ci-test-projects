package nl.sping.gitview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.annotation.ColorInt
import androidx.core.content.ContextCompat
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.findNavController
import androidx.navigation.ui.setupActionBarWithNavController
import nl.sping.gitview.data.TokenWarehouse

class MainActivity : AppCompatActivity(), NavController.OnDestinationChangedListener {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        TokenWarehouse.clearTokens()
        setupActionBar()
        setupNavigation()
        setStatusBarColor(ContextCompat.getColor(this, R.color.colorPrimary))
    }

    override fun onSupportNavigateUp(): Boolean  = findNavController(R.id.mainNavigationFragment).navigateUp()

    private fun setStatusBarColor(@ColorInt color: Int) {
        window.statusBarColor = color
    }

    private fun setupActionBar() {
        supportActionBar?.setDisplayShowTitleEnabled(true)
    }

    private fun setupNavigation() {
        val navController = findNavController(R.id.mainNavigationFragment)
        navController.addOnDestinationChangedListener(this)
        setupActionBarWithNavController(navController)
    }

    override fun onDestinationChanged(controller: NavController, destination: NavDestination, arguments: Bundle?) {
       when (destination.id) {
           R.id.loginFragment -> {
               val token = TokenWarehouse.getAuthToken() ?: ""
               if (token.isEmpty()) {
                   supportActionBar?.hide()
               } else {
                   findNavController(R.id.mainNavigationFragment).navigate(R.id.action_global_repoListFragment)
               }
           }
       }
    }
}
