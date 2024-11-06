package com.example.darkmode

import android.os.Bundle
import androidx.activity.ComponentActivity
import android.content.Context
import android.content.SharedPreferences
import android.view.ViewGroup
import android.widget.Button
import androidx.appcompat.app.AppCompatDelegate
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.constraintlayout.widget.ConstraintLayout.LayoutParams

class MainActivity : ComponentActivity() {

    private val PREFS_NAME = "theme_prefs"
    private val KEY_IS_DARK_MODE = "is_dark_mode"
    private lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        sharedPreferences = getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)


        val isDarkMode = sharedPreferences.getBoolean(KEY_IS_DARK_MODE, false)
        setTheme(isDarkMode)

        // Crear el layout programáticamente
        val layout = ConstraintLayout(this)
        layout.layoutParams = ViewGroup.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.MATCH_PARENT
        )


        val themeButton = Button(this)
        themeButton.text = "Cambiar Tema"
        themeButton.layoutParams = LayoutParams(
            LayoutParams.WRAP_CONTENT,
            LayoutParams.WRAP_CONTENT
        ).apply {
            // Centrar el botón en el layout
            startToStart = ConstraintLayout.LayoutParams.PARENT_ID
            endToEnd = ConstraintLayout.LayoutParams.PARENT_ID
            topToTop = ConstraintLayout.LayoutParams.PARENT_ID
            bottomToBottom = ConstraintLayout.LayoutParams.PARENT_ID
        }


        layout.addView(themeButton)
        setContentView(layout)


        themeButton.setOnClickListener {
            // Cambia el modo de tema
            val newIsDarkMode = !isDarkMode
            setTheme(newIsDarkMode)
            saveThemePreference(newIsDarkMode)
        }
    }


    private fun setTheme(isDarkMode: Boolean) {
        if (isDarkMode) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
        } else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        }
    }


    private fun saveThemePreference(isDarkMode: Boolean) {
        val editor = sharedPreferences.edit()
        editor.putBoolean(KEY_IS_DARK_MODE, isDarkMode)
        editor.apply()
    }
}

