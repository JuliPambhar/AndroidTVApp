package com.app.androidtvapp.ui.main

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.KeyEvent
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import androidx.activity.viewModels
import androidx.fragment.app.FragmentActivity
import androidx.navigation.Navigation
import com.app.androidtvapp.R
import com.app.androidtvapp.databinding.ActivityMainBinding
import com.app.androidtvapp.ui.detail.DetailActivity
import com.app.androidtvapp.ui.home.ListFragmentViewModel
import com.app.androidtvapp.util.Constants
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : FragmentActivity(), View.OnKeyListener {

    private val viewModel: ListFragmentViewModel by viewModels()
    private lateinit var binding: ActivityMainBinding

    private var sideMenu = false
    private var selectedMenu = Constants.MENU_HOME

    private lateinit var lastSelectedMenu: View

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnSearch.setOnKeyListener(this)
        binding.btnHome.setOnKeyListener(this)
        binding.btnMovies.setOnKeyListener(this)
        binding.btnSettings.setOnKeyListener(this)
        binding.btnTv.setOnKeyListener(this)
        binding.btnGenre.setOnKeyListener(this)
        binding.btnLanguage.setOnKeyListener(this)
        binding.btnSports.setOnKeyListener(this)

        viewModel.clickedMovie.observe(this) { movie ->

            val intent = Intent(this, DetailActivity::class.java)
            intent.putExtra("id", movie.id)
            startActivity(intent)
        }

        lastSelectedMenu = binding.btnHome
        lastSelectedMenu.isActivated = true
    }

    override fun onKey(v: View?, keyCode: Int, event: KeyEvent?): Boolean {
        val navController = Navigation.findNavController(this, R.id.nav_host_fragment)

        when (keyCode) {
            KeyEvent.KEYCODE_DPAD_CENTER -> {
                lastSelectedMenu.isActivated = false
                v?.isActivated = true
                lastSelectedMenu = v!!
                when (v.id) {
                    R.id.btn_search -> {
                        selectedMenu = Constants.MENU_SEARCH
                        navController.navigate(R.id.fragment_search)
                        closeMenu()
                    }

                    R.id.btn_home -> {
                        selectedMenu = Constants.MENU_HOME
                        navController.navigate(R.id.fragment_home)
                        closeMenu()
                    }

                    R.id.btn_movies -> {}
                    R.id.btn_tv -> {}
                    R.id.btn_sports -> {}
                    R.id.btn_settings -> {}
                    R.id.btn_language -> {}
                    R.id.btn_genre -> {}
                }
            }

            KeyEvent.KEYCODE_DPAD_LEFT -> {
                if (!sideMenu) {
                    switchToLastSelectedMenu()

                    openMenu()
                    sideMenu = true
                }
            }

        }
        return false
    }

    private fun switchToLastSelectedMenu() {
        when (selectedMenu) {
            Constants.MENU_SEARCH -> {
                binding.btnSearch.requestFocus()
            }

            Constants.MENU_HOME -> {
                binding.btnHome.requestFocus()
            }

            Constants.MENU_TV -> {
                binding.btnTv.requestFocus()
            }

            Constants.MENU_MOVIE -> {
                binding.btnMovies.requestFocus()
            }

            Constants.MENU_SPORTS -> {
                binding.btnSports.requestFocus()
            }

            Constants.MENU_LANGUAGE -> {
                binding.btnLanguage.requestFocus()
            }

            Constants.MENU_GENRES -> {
                binding.btnGenre.requestFocus()
            }

            Constants.MENU_SETTINGS -> {
                binding.btnSettings.requestFocus()
            }
        }
    }

    private fun openMenu() {
        val animSlide: Animation = AnimationUtils.loadAnimation(this, R.anim.slide_in_left)
        binding.blfNavBar.startAnimation(animSlide)

        binding.blfNavBar.requestLayout()
        binding.blfNavBar.layoutParams.width = getWidthInPercent(this, 16)
    }

    private fun closeMenu() {
        binding.blfNavBar.requestLayout()
        binding.blfNavBar.layoutParams.width = getWidthInPercent(this, 5)

        sideMenu = false
    }

    private fun getWidthInPercent(context: Context, percent: Int): Int {
        val width = context.resources.displayMetrics.widthPixels ?: 0
        return (width * percent) / 100
    }

    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
        if (keyCode == KeyEvent.KEYCODE_DPAD_RIGHT && sideMenu) {
            sideMenu = false
            closeMenu()
        }

        return super.onKeyDown(keyCode, event)
    }

    override fun onBackPressed() {
        if (sideMenu) {
            sideMenu = false
            closeMenu()
        } else {
            super.onBackPressed()
        }
    }
}