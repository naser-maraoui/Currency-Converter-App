package com.example.currencyconverterapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.Button
import android.widget.Toast
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.textfield.TextInputEditText

class MainActivity : AppCompatActivity() {
    val TAG = "test"
    private val tunisiandinar = "Tunisian Dinar"
    private val americandollar = "American Dollar"
    private val AED = "AED"
    private val GBP = "GBP"

    val values = mapOf(
        tunisiandinar to 1.0 ,
        americandollar to 0.32 ,
        AED to 7.5 ,
        GBP to 14
    )

    lateinit var fromDropDownMenu : AutoCompleteTextView
    lateinit var toDropDownMenu : AutoCompleteTextView
    lateinit var resultEt : TextInputEditText
    lateinit var amountEt : TextInputEditText
    lateinit var convertBtn : Button



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initializeViews()
        populateDropDownMenu()


        convertBtn.setOnClickListener{
            if (amountEt.text.toString().isNotEmpty()){
                val amount = amountEt.text.toString().toDouble()
                val toValue = values[toDropDownMenu.text.toString()].toString().toDouble()
                val fromValue = values[fromDropDownMenu.text.toString()].toString().toDouble()
                val result = amount.times(toValue.div(fromValue))
                resultEt.setText(result.toString())
            }else {
                Snackbar.make(fromDropDownMenu , "Amount field requiret" , Snackbar.LENGTH_SHORT).show()
            }

        }
    }

    private fun populateDropDownMenu(){
        val listeOfCountry = listOf(tunisiandinar , americandollar , AED , GBP)
        val adapter = ArrayAdapter (this , R.layout.drop_liste , listeOfCountry)
        toDropDownMenu.setAdapter(adapter)
        fromDropDownMenu.setAdapter(adapter)

    }

    private fun initializeViews(){
        convertBtn = findViewById(R.id.convert_button)
        amountEt = findViewById(R.id.amount_txt)
        resultEt = findViewById(R.id.result_cv)
        toDropDownMenu = findViewById(R.id.to_currency)
        fromDropDownMenu = findViewById(R.id.from_currency)
    }
}



