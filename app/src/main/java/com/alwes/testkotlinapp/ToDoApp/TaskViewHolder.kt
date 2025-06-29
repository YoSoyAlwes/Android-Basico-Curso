package com.alwes.testkotlinapp.ToDoApp

import android.content.res.ColorStateList
import android.graphics.Paint
import android.view.View
import android.widget.CheckBox
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.alwes.testkotlinapp.R

class TaskViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    private val titleTask: TextView = view.findViewById(R.id.titleTask)
    private val cbTask: CheckBox = view.findViewById(R.id.cbTask)

    fun render(task: Task) {
        if (task.isSelected) {
            titleTask.paintFlags = titleTask.paintFlags or Paint.STRIKE_THRU_TEXT_FLAG
        } else {
            titleTask.paintFlags = titleTask.paintFlags and Paint.STRIKE_THRU_TEXT_FLAG.inv()
        }
        titleTask.text = task.name
        cbTask.isChecked = task.isSelected

        var color = when (task.category) {
            TaskCategories.Business -> R.color.cat2
            TaskCategories.Other -> R.color.cat1
            TaskCategories.Personal -> R.color.cat3
        }
        cbTask.buttonTintList = ColorStateList.valueOf(ContextCompat.getColor(cbTask.context, color))
    }
}