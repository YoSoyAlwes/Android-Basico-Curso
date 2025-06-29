package com.alwes.testkotlinapp.SuperHeroesApp

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.alwes.testkotlinapp.databinding.ItemSuperHeroeBinding
import com.squareup.picasso.Picasso

class SuperHeoresViewHolder(view: View): RecyclerView.ViewHolder(view) {

    private val binding = ItemSuperHeroeBinding.bind(view)

    fun bind(SuperHeroesItemResponse: SuperHeroesItemResponse, onItemSelected: (String) -> Unit) {

        binding.titleName.text = SuperHeroesItemResponse.name
        Picasso.get().load(SuperHeroesItemResponse.superHeroeImage.url).into(binding.imageSH)
        binding.idSH.text = "ID: ${SuperHeroesItemResponse.id}"
        binding.realNameSH.text = "Nombre Real: ${SuperHeroesItemResponse.superHeroesBiography.realName}"
        binding.publisherSH.text = "Publicador: ${SuperHeroesItemResponse.superHeroesBiography.publisher}"
        binding.root.setOnClickListener { onItemSelected(SuperHeroesItemResponse.id) }
    }
}