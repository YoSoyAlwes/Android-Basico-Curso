package com.alwes.testkotlinapp.FirstApp

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton
import androidx.appcompat.widget.AppCompatEditText
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.alwes.testkotlinapp.R

class FirstAppActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_first_app)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val btnHola = findViewById<AppCompatButton>(R.id.btnHola)
        val txtName = findViewById<AppCompatEditText>(R.id.txtName)
        btnHola.setOnClickListener {
            val name = txtName.text.toString()
            if(name.isNotEmpty()){
                val intent = Intent(this, ShowNameActivity::class.java)
                intent.putExtra("nameToShow", name)
                startActivity(intent);
                Log.i("Gracias por el curso", "Boton Pulsado por ${name}")
            }
        }
    }
}