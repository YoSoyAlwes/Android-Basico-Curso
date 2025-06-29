package com.alwes.testkotlinapp.SuperHeroesApp

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import com.alwes.testkotlinapp.R
import com.alwes.testkotlinapp.SuperHeroesApp.DetailSuperHeroeActivity.Companion.IDSH
import com.alwes.testkotlinapp.databinding.ActivitySuperHeroesListBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

class SuperHeroesListActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySuperHeroesListBinding
    private lateinit var retrofit: Retrofit

    private lateinit var adapter: SuperHeroesAdapater

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivitySuperHeroesListBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        retrofit = getRetroFit()
        initUI()
    }
    private fun initUI() {
        binding.searchBarHeroes.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                searchByName(query.orEmpty())
                return false
            }
            override fun onQueryTextChange(newText: String?): Boolean = false

        })
        adapter = SuperHeroesAdapater() {navigateToDetail(it)}
        binding.superHeoresRV.setHasFixedSize(true)
        binding.superHeoresRV.layoutManager = LinearLayoutManager(this)
        binding.superHeoresRV.adapter = adapter
    }
    private fun searchByName(query: String){
        binding.progressBarSH.isVisible = true
        CoroutineScope(Dispatchers.IO).launch {
            val myResponse = retrofit.create(ApiService::class.java).getSuperHeroe(query)
            if(myResponse.isSuccessful){
                Log.i("i", "Funciona")
                val response = myResponse.body();
                if(response != null){
                    Log.i("i", response.toString())
                    runOnUiThread {
                        adapter.updateList(response.superHeroes)
                        binding.progressBarSH.isVisible = false
                    }
                }
            } else {
                Log.i("i", "No Funciona")
            }
        }
    }
    private fun getRetroFit(): Retrofit{
        return Retrofit
            .Builder()
            .baseUrl("https://www.superheroapi.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
    private fun navigateToDetail(id: String) {
        val intent = Intent(this, DetailSuperHeroeActivity::class.java)
        intent.putExtra(IDSH,id)
        startActivity(intent)
    }
}