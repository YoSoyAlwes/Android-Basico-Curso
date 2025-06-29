package com.alwes.testkotlinapp.ToDoApp

import android.view.View
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.alwes.testkotlinapp.R

class CategoriesViewHolder(view: View): RecyclerView.ViewHolder(view) {

    private val titleName: TextView = view.findViewById(R.id.titleCategories)
    private val decoVar: View = view.findViewById(R.id.decoVar)
    private val cvCategories: CardView = view.findViewById(R.id.cvCategories)

    fun render(TaskCategory: TaskCategories, onItemSelected: (Int) -> Unit){

        val cvColor = if(TaskCategory.isSelected){
            R.color.green_pastel
        } else {
            R.color.red_pastel
        }
        cvCategories.setCardBackgroundColor(ContextCompat.getColor(cvCategories.context, cvColor))
        itemView.setOnClickListener { onItemSelected(layoutPosition) }

        when(TaskCategory){
            TaskCategories.Personal -> {
                titleName.text = "Personal"
                decoVar.setBackgroundColor(ContextCompat.getColor(decoVar.context, R.color.cat3))
            }
            TaskCategories.Business -> {
                titleName.text = "Negocios"
                decoVar.setBackgroundColor(ContextCompat.getColor(decoVar.context, R.color.cat2))
            }
            TaskCategories.Other -> {
                titleName.text = "Otros"
                decoVar.setBackgroundColor(ContextCompat.getColor(decoVar.context, R.color.cat1))
            }
        }
    }
}