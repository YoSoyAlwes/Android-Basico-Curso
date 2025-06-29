package com.alwes.testkotlinapp.ToDoApp

sealed class TaskCategories(var isSelected:Boolean = true) {
    object Personal : TaskCategories()
    object Business : TaskCategories()
    object Other : TaskCategories()
}