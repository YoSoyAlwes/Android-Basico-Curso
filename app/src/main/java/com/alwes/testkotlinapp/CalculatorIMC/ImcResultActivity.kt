package com.alwes.testkotlinapp.CalculatorIMC

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.alwes.testkotlinapp.CalculatorIMC.ImcCalculatorActivity.Companion.resultImc
import com.alwes.testkotlinapp.R

class ImcResultActivity : AppCompatActivity() {

    private var result: Double = 0.0;

    private lateinit var statusText: TextView
    private lateinit var resultText: TextView
    private lateinit var tipsText: TextView
    private lateinit var reCalculateButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_imc_result)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        initComponents();
        initListeners();
        initUI();
    }
    private fun initComponents(){
        result = intent.extras?.getDouble(resultImc) ?: -1.0;
        statusText = findViewById(R.id.statusText)
        resultText = findViewById(R.id.resultText)
        tipsText = findViewById(R.id.tipsText)
        reCalculateButton = findViewById(R.id.buttonreCal)
    }
    private fun initListeners(){
        reCalculateButton.setOnClickListener { onBackPressed() }
    }
    private fun initUI(){
        resultText.setText(result.toString())
        when(result){
            in 0.00..18.85 -> {
                statusText.setText(R.string.group1)
                statusText.setTextColor(ContextCompat.getColor(this, R.color.red))
                tipsText.setText(R.string.tip1)
            }
            in 18.86 .. 24.99 -> {
                statusText.setText(R.string.group2)
                statusText.setTextColor(ContextCompat.getColor(this, R.color.green))
                tipsText.setText(R.string.tip2)
            }
            in 25.00 .. 29.99 -> {
                statusText.setText(R.string.group3)
                statusText.setTextColor(ContextCompat.getColor(this, R.color.yellow))
                tipsText.setText(R.string.tip3)
            }
            in 30.00..Double.MAX_VALUE -> {
                statusText.setText(R.string.group4)
                statusText.setTextColor(ContextCompat.getColor(this, R.color.red))
                tipsText.setText(R.string.tip4)
            }
            else -> statusText.setText(R.string.Error)
        }
    }
}