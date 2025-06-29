package com.alwes.testkotlinapp.SuperHeroesApp

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.alwes.testkotlinapp.R

class SuperHeroesAdapater(
    var superHeroesList: List<SuperHeroesItemResponse> = emptyList(),
    private val onItemSelected: (String) -> Unit
) :
    RecyclerView.Adapter<SuperHeoresViewHolder>() {

    fun updateList(superHeroesList: List<SuperHeroesItemResponse>) {
        this.superHeroesList = superHeroesList
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SuperHeoresViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return SuperHeoresViewHolder(
            layoutInflater.inflate(
                R.layout.item_super_heroe,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: SuperHeoresViewHolder, position: Int) {
        holder.bind(superHeroesList[position], onItemSelected)
    }

    override fun getItemCount(): Int = superHeroesList.size
}