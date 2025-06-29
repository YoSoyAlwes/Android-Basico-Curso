package com.alwes.testkotlinapp

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.alwes.testkotlinapp.CalculatorIMC.ImcCalculatorActivity
import com.alwes.testkotlinapp.FirstApp.FirstAppActivity
import com.alwes.testkotlinapp.Settings.SettingsActivity
import com.alwes.testkotlinapp.SuperHeroesApp.SuperHeroesListActivity
import com.alwes.testkotlinapp.ToDoApp.ToDoAppActivity

class MenuActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_menu)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val btnAppSaludo = findViewById<AppCompatButton>(R.id.BtnAppSaludo);
        btnAppSaludo.setOnClickListener { navigetAppSaludo() }
        val btnIMCApp = findViewById<AppCompatButton>(R.id.btnIMCApp);
        btnIMCApp.setOnClickListener { navigateIMCApp() }
        val btnAppToDo = findViewById<AppCompatButton>(R.id.btnToDoAPP)
        btnAppToDo.setOnClickListener { navigateAppToDo() }
        val btnSuperHeroesApp: AppCompatButton = findViewById(R.id.btnSuperHeroesApp)
        btnSuperHeroesApp.setOnClickListener { navigateSuperHeroesApp() }
        val btnSettings: AppCompatButton = findViewById(R.id.btnSettings)
        btnSettings.setOnClickListener {navigateToConfiguration() }
    }

    private fun navigateIMCApp() {
        val intentIMCCaluclator = Intent(this, ImcCalculatorActivity::class.java)
        startActivity(intentIMCCaluclator)
    }

    private fun navigetAppSaludo (){
        val intentAppSaludo = Intent(this, FirstAppActivity::class.java )
        startActivity(intentAppSaludo)
    }
    private fun navigateAppToDo() {
        val intentToDoApp = Intent(this, ToDoAppActivity::class.java)
        startActivity(intentToDoApp)
    }
    private fun navigateSuperHeroesApp() {
        val intentSuperHeroes = Intent(this, SuperHeroesListActivity::class.java)
        startActivity(intentSuperHeroes)
    }
    private fun navigateToConfiguration(){
        val intentSetting = Intent(this, SettingsActivity::class.java)
        startActivity(intentSetting)
    }
}