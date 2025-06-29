package com.alwes.testkotlinapp.SuperHeroesApp

import android.os.Bundle
import android.util.Log
import android.util.TypedValue
import android.view.View
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.isVisible
import com.alwes.testkotlinapp.R
import com.alwes.testkotlinapp.databinding.ActivityDetailSuperHeroeBinding
import com.alwes.testkotlinapp.databinding.ActivitySuperHeroesListBinding
import com.squareup.picasso.Picasso
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import kotlin.math.roundToInt

class DetailSuperHeroeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailSuperHeroeBinding

    private lateinit var retrofit: Retrofit

    companion object {
        const val IDSH = "idSH"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailSuperHeroeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        enableEdgeToEdge()
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        retrofit = getRetroFit()
        val id = intent.getStringExtra(IDSH).orEmpty()
        getDataById(id)
    }
    private fun getDataById(id: String){
        binding.progressBarSH.isVisible = true
        CoroutineScope(Dispatchers.IO).launch {
            val superHeroDetail: Response<SuperHeroeDetailResponse> = retrofit.create(ApiService::class.java).getSuperHeroeById(id)
            if(superHeroDetail.body() != null){
                Log.i("status","todo bien")
                runOnUiThread {
                    createUI(superHeroDetail.body()!!)
                    binding.progressBarSH.isVisible = false
                }
            } else {
                Log.i("status", "todo mal")
            }
        }
    }

    private fun createUI(body: SuperHeroeDetailResponse) {
        Picasso.get().load(body.imageSHD.url).into(binding.imageSHD)
        binding.nameSH.text = body.name
        binding.realNameSHD.text = body.biographyResponse.fullNameSH
        binding.publisherSH.text = body.biographyResponse.publisherSH
        binding.alterEgoSH.text = "Alter Ego: ${body.biographyResponse.alterEgosSH}"
        binding.POBSH.text = "Lugar de Nacimiento: ${body.biographyResponse.POBSH}"
        binding.FASH.text = "Primera Aparción:  ${body.biographyResponse.FASH}"
        binding.alignSH.text = "Que lado le Juega: ${body.biographyResponse.alignmentSH}"

        prepareStats(body.powerStatsResponse)
    }
    private fun prepareStats(powerStatsResponse: SuperHeroePowerStatsResponse) {
        updateStatsValue(binding.inteGraph, binding.intelValue, powerStatsResponse.intelligenceSH)
        updateStatsValue(binding.strenGraph, binding.strenValue, powerStatsResponse.strengthSH)
        updateStatsValue(binding.speedGraph, binding.speedValue, powerStatsResponse.speedSH)
        updateStatsValue(binding.durabGraph, binding.durabValue, powerStatsResponse.durabilitySH)
        updateStatsValue(binding.powerGraph, binding.powerValue, powerStatsResponse.powerSH)
        updateStatsValue(binding.combatGraph, binding.combatValue, powerStatsResponse.combatSH)
    }
    private fun updateStatsValue(View: View,TextView: TextView, stat: String){
        val params = View.layoutParams
        params.height = pxtodp(stat.toFloat())
        View.layoutParams = params

        TextView.text = stat.toString()
    }
    private fun pxtodp(px:Float):Int{
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, px, resources.displayMetrics).roundToInt()
    }
    private fun getRetroFit(): Retrofit {
        return Retrofit
            .Builder()
            .baseUrl("https://www.superheroapi.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}