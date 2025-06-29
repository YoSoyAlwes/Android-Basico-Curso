package com.alwes.testkotlinapp.ToDoApp

import android.app.Dialog
import android.os.Bundle
import android.widget.Button
import android.widget.RadioButton
import android.widget.RadioGroup
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton
import androidx.appcompat.widget.AppCompatEditText
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.alwes.testkotlinapp.R
import com.alwes.testkotlinapp.ToDoApp.TaskCategories.*
import com.google.android.material.floatingactionbutton.FloatingActionButton

class ToDoAppActivity : AppCompatActivity() {
    private var listCategories = listOf(
        Personal,
        Business,
        Other
    )

    private var listTask = mutableListOf(
        Task("Terminar Curso Android", Personal),
        Task("Alguna Novia", Personal)
    )

    private lateinit var rvCategories: RecyclerView
    private lateinit var categoriesAdapter: CategoriesAdapter

    private lateinit var rvTask: RecyclerView
    private lateinit var TaskAdapter: TaskAdapter;

    private lateinit var addTask: FloatingActionButton;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_to_do_app)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        initComponents()
        initListeners()
        initUI()
    }
    private fun initComponents(){
        rvCategories = findViewById(R.id.rvCategories)
        rvTask = findViewById(R.id.rvTasks)
        addTask = findViewById(R.id.addTask)
    }
    fun initListeners(){
        addTask.setOnClickListener { showDialogAddTask() }
    }

    private fun showDialogAddTask() {
        val dialogTask = Dialog(this)
        dialogTask.setContentView(R.layout.dialog_add_task)

        val btnAddTask: AppCompatButton = dialogTask.findViewById(R.id.finalButton)
        val nameAddTask: AppCompatEditText = dialogTask.findViewById(R.id.nameAddTask)
        val catRadioButton: RadioGroup = dialogTask.findViewById(R.id.catRadioButton)
        val taskName = nameAddTask.text.toString();
        //if(taskName.isNotEmpty()){
            btnAddTask.setOnClickListener {
                val radioButtonSelectId = catRadioButton.checkedRadioButtonId
                val selectedRadioButton: RadioButton = catRadioButton.findViewById(radioButtonSelectId)
                val taskAddCategorie = when(selectedRadioButton.text){
                    getString(R.string.cat1) -> Personal
                    getString(R.string.cat2) -> Business
                    getString(R.string.cat3) -> Other
                    else -> Other
                }
                listTask.add(Task(nameAddTask.text.toString(), taskAddCategorie))
                dialogTask.hide()
                updateTask()
            }
        //}
        dialogTask.show()
    }
    private fun initUI(){
        categoriesAdapter = CategoriesAdapter(listCategories) { updateCategories(it) }
        rvCategories.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL,   false)
        rvCategories.adapter = categoriesAdapter

        TaskAdapter = TaskAdapter(listTask) { onItemSelected(it) }
        rvTask.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        rvTask.adapter = TaskAdapter
    }
    private fun onItemSelected(pos: Int){
        listTask[pos].isSelected = !listTask[pos].isSelected
        updateTask()
    }
    private fun updateCategories(pos: Int){
        listCategories[pos].isSelected = !listCategories[pos].isSelected
        categoriesAdapter.notifyItemChanged(pos)
        updateTask()
    }
    private fun updateTask(){
        val selectedCategories: List<TaskCategories> = listCategories.filter { it.isSelected }
        val newTasks = listTask.filter { selectedCategories.contains(it.category)}
        TaskAdapter.tasks = newTasks
        TaskAdapter.notifyDataSetChanged()
        TaskAdapter.notifyDataSetChanged()
    }
}