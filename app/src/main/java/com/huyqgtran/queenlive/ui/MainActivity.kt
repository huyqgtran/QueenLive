package com.huyqgtran.queenlive.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import androidx.appcompat.widget.Toolbar
import androidx.core.content.ContextCompat
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.huyqgtran.queenlive.R
import com.google.android.material.navigation.NavigationView
import com.huyqgtran.queenlive.NavGraphDirections
import com.huyqgtran.queenlive.utilities.HELP_GROUP_ID
import com.huyqgtran.queenlive.utilities.HOME_GROUP_ID
import com.huyqgtran.queenlive.utilities.TOUR_GROUP_ID

class MainActivity : AppCompatActivity() {

    private lateinit var drawerLayout: DrawerLayout
    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setNavigationDrawer()
        setSupportActionBar(findViewById(R.id.toolbar))

        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        navController = navHostFragment.navController
        appBarConfiguration = AppBarConfiguration(navController.graph, drawerLayout)
        setupActionBarWithNavController(navController, appBarConfiguration)
        findViewById<NavigationView>(R.id.nav_view)
            .run {
            setupWithNavController(navController)
            setNavigationItemSelectedListener {
                when(it.groupId) {
                    HOME_GROUP_ID -> navigateToTourFragment()
                    TOUR_GROUP_ID -> navigateToShowFragment(it.title.toString())
                    HELP_GROUP_ID -> navigateToHelpFragment()
                }

                true
            }
        }
    }

    fun setToolbarTitle(title: String) {
        findViewById<Toolbar>(R.id.toolbar).title = title
    }

    fun updateMenuDrawer(tourList: List<ViewTour>) {
        findViewById<NavigationView>(R.id.nav_view).menu.run {
            clear()
            add(HOME_GROUP_ID, Menu.NONE, Menu.NONE, getString(R.string.home)).icon =
                    ContextCompat.getDrawable(this@MainActivity, R.drawable.ic_home)
            for (tour in tourList) {
                add(TOUR_GROUP_ID, Menu.NONE, Menu.NONE, tour.name).icon =
                        ContextCompat.getDrawable(this@MainActivity, R.drawable.ic_music)
            }
            add(HELP_GROUP_ID, Menu.NONE, Menu.NONE, getString(R.string.help)).icon =
                    ContextCompat.getDrawable(this@MainActivity, R.drawable.ic_help)
        }
    }

    private fun navigateToTourFragment() {
        val action = NavGraphDirections.toTourFragment()
        navController.navigate(action)
        drawerLayout.closeDrawer(GravityCompat.START, false)
    }

    private fun navigateToHelpFragment() {
        val action = NavGraphDirections.toHelpFragment()
        navController.navigate(action)
        drawerLayout.closeDrawer(GravityCompat.START, false)
    }

    private fun navigateToShowFragment(tourName: String) {
        val action = NavGraphDirections.toShowFragment(tourName)
        navController.navigate(action)
        drawerLayout.closeDrawer(GravityCompat.START, false)
    }

    override fun onSupportNavigateUp(): Boolean {
        return findNavController(R.id.nav_host_fragment).navigateUp(appBarConfiguration)
                || super.onSupportNavigateUp()
    }

    private fun setNavigationDrawer() {
        drawerLayout = (findViewById<DrawerLayout>(R.id.drawer_layout))
            .apply {
                setStatusBarBackground(R.color.colorPrimaryDark)
            }
    }
}