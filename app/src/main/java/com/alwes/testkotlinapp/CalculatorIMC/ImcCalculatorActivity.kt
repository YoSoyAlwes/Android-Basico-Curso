package com.alwes.testkotlinapp.CalculatorIMC

import android.content.Intent
import android.icu.text.DecimalFormat
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.alwes.testkotlinapp.R
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.slider.RangeSlider

class ImcCalculatorActivity : AppCompatActivity() {

    private var maleSelected = true
    private var femaleSelected = false
    private var heightNumber = 30
    private var weightNumber = 50
    private var ageNumber = 50
    //private var IMC:Int = 0;

    private lateinit var Masc: CardView
    private lateinit var Fem: CardView
    private lateinit var rsHeight: RangeSlider
    private lateinit var txtHeight: TextView
    private lateinit var txtWeight: TextView
    private lateinit var txtAge: TextView
    private lateinit var btnPlusWeight: FloatingActionButton
    private lateinit var btnMinusWeight: FloatingActionButton
    private lateinit var btnPlusAge: FloatingActionButton
    private lateinit var btnMinusAge: FloatingActionButton
    private lateinit var btnIMC: AppCompatButton

    companion object {
        val resultImc = "resIMC"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_imc_calculator)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        initComponents()
        initListeners()
        initUI()
    }
    private fun initComponents() {
        //inicio sus Componentes
        //Generos
        Masc = findViewById(R.id.Masc)
        Fem = findViewById(R.id.Fem)
        //Altura
        rsHeight = findViewById(R.id.rsHeight)
        txtHeight = findViewById(R.id.txtHeight)
        //Peso y Edad Textos
        txtWeight = findViewById(R.id.weightNumber)
        txtAge = findViewById(R.id.ageNumber)
        //Peso y Edad Botones
        btnMinusAge = findViewById(R.id.btnMinusAge)
        btnPlusAge = findViewById(R.id.btnPlusAge)
        btnPlusWeight = findViewById(R.id.btnPlusWeight)
        btnMinusWeight = findViewById(R.id.btnMinusWeight)
        //Calculo
        btnIMC = findViewById(R.id.btnCalc)
    }
    private fun initListeners() {
        //Genero
        Masc.setOnClickListener {
            //Ajustamos si es hombre
            maleSelected = true
            femaleSelected = false
            genderSelected() }
        Fem.setOnClickListener {
            //Ajustamos si es mujer
            femaleSelected = true
            maleSelected = false
            genderSelected() }
        rsHeight.addOnChangeListener { _, value, _ ->
            val df = DecimalFormat("#.##")
            var result = df.format(value)
            heightNumber = result.toInt();
            txtHeight.setText(result + " CM")
        }
        btnMinusAge.setOnClickListener{
            ageNumber -= 1
            setAge()
        }
        btnPlusAge.setOnClickListener{
            ageNumber +=1
            setAge()
        }
        btnPlusWeight.setOnClickListener{
            weightNumber += 1
            setWeight()
        }
        btnMinusWeight.setOnClickListener{
            weightNumber -= 1
            setWeight()
        }
        btnIMC.setOnClickListener {
            navigateToResult(weightNumber, heightNumber)
        }
    }
    private fun setAge() {
        txtAge.setText("${ageNumber.toString()} Años")
    }
    private fun setWeight() {
        txtWeight.setText("${weightNumber.toString()} KG")
    }
    private fun genderSelected(){
        //Pone El color de acuerdo a cual es falso y cual es Verdadero
        Masc.setCardBackgroundColor(genderBackgroudColor(maleSelected))
        Fem.setCardBackgroundColor(genderBackgroudColor(femaleSelected))
    }
    private fun genderBackgroudColor(isSelectedComponent: Boolean): Int {
        //le da su color correspondiente
        val codeColor = if(isSelectedComponent) {
            R.color.green_pastel
        } else {
            R.color.red_pastel
        }
        return ContextCompat.getColor(this, codeColor)
    }
    //Iniciamos la UI?
    private fun initUI() {
        genderSelected()
        setWeight()
        setAge()
    }
    private fun navigateToResult(w: Int,h: Int) {
        var value = (w/(h.toDouble()/100 * h.toDouble()/100))
        val df = DecimalFormat("#.##")
        var result = df.format(value).toDouble()
        val resIntent = Intent(this, ImcResultActivity::class.java)
        resIntent.putExtra(resultImc, result)
        startActivity(resIntent)
    }

}