package com.example.rickandmortycorrect.ui.activities

import android.content.res.Configuration
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.navigateUp
import com.example.rickandmortycorrect.R
import com.example.rickandmortycorrect.data.preference.LanguageSharedPreferences
import com.example.rickandmortycorrect.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint
import java.util.*
import javax.inject.Inject


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var navController: NavController

    @Inject
    lateinit var languageSharedPreferences: LanguageSharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupThems()
        setupNavigation()
        setupListener()
    }

    private fun setupThems() {
        if (languageSharedPreferences.getLanguage()) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
            binding.switchMaterial.isChecked = true
            if (getString(R.string.rick_and_morty_characters) == "Персонажи") {
                setupLanguage("en")
                updateActivity()
            }
        } else {
            setupLanguage("ru")
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        }
    }

    private fun setupNavigation() {
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        navController = navHostFragment.navController

        appBarConfiguration = AppBarConfiguration.Builder(
            R.id.characterFragment,
            R.id.episodeFragment,
            R.id.locationFragment
        ).build()
        NavigationUI.setupWithNavController(binding.bottomNavigation, navController)
        NavigationUI.setupWithNavController(binding.toolbarMain, navController, appBarConfiguration)
    }


    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp(appBarConfiguration)
                || super.onSupportNavigateUp()
    }

    private fun setupListener() {
        setupSwitchThemsAndLanguage()
    }

    private fun setupSwitchThemsAndLanguage() {
        binding.switchMaterial.setOnCheckedChangeListener { buttonView, isChecked ->
            if (isChecked) {
                setupLanguage("en")
                languageSharedPreferences.setLanguage(true)
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                updateActivity()

            } else {
                setupLanguage("ru")
                languageSharedPreferences.setLanguage(false)
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
                updateActivity()
            }
        }
    }


    private fun updateActivity() {
        finish();
        startActivity(intent);
    }

    private fun setupLanguage(language: String) {
        val locale = Locale(language)
        Locale.setDefault(locale)
        val config: Configuration = baseContext.resources.configuration
        config.locale = locale
        baseContext.resources.updateConfiguration(
            config,
            baseContext.resources.displayMetrics
        )
    }
}