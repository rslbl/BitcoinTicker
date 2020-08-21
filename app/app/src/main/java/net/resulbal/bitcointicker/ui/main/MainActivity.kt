package net.resulbal.bitcointicker.ui.main

import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import kotlinx.android.synthetic.main.activity_main.bottomNavigationView
import kotlinx.android.synthetic.main.activity_main.toolbar
import net.resulbal.bitcointicker.R
import net.resulbal.bitcointicker.R.layout
import net.resulbal.bitcointicker.ui.base.BaseActivity

class MainActivity: BaseActivity() {

  private lateinit var navController: NavController
  private val fragmentList = setOf(
    R.id.coinListFragment,
    R.id.favoriteFragment,
    R.id.profileFragment
  )

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(layout.activity_main)
    setSupportActionBar(toolbar)

    navController = findNavController(R.id.fragmentContainer)
    setupActionBarWithNavController(navController, AppBarConfiguration(fragmentList))
    bottomNavigationView.setupWithNavController(navController)
  }
}