package com.example.calculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import kotlinx.android.synthetic.main.activity_main.*
import net.objecthunter.exp4j.ExpressionBuilder


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//        var resultView : String
//        var expressionHolder : StringBuilder

        // buttons clicks for numbers
        zeroBtn.setOnClickListener { getBtnSel("0", false) }
        oneBtn.setOnClickListener { getBtnSel("1", false) }
        twoBtn.setOnClickListener { getBtnSel("2", false) }
        threeBtn.setOnClickListener { getBtnSel("3", false) }
        fourBtn.setOnClickListener { getBtnSel("4", false) }
        fiveBtn.setOnClickListener { getBtnSel("5", false) }
        sixBtn.setOnClickListener { getBtnSel("6", false) }
        sevenBtn.setOnClickListener { getBtnSel("7", false) }
        eightBtn.setOnClickListener { getBtnSel("8", false) }
        nineBtn.setOnClickListener { getBtnSel("9", false) }
        pointBtn.setOnClickListener { getBtnSel(".", false) }

        //buttons for basic operations
        addBtn.setOnClickListener { getBtnSel("+", true) }
        minusBtn.setOnClickListener { getBtnSel("-", true) }
        multipleBtn.setOnClickListener { getBtnSel("*", true) }
        divideBtn.setOnClickListener { getBtnSel("/", true) }
        leftbracketBtn.setOnClickListener { getBtnSel("(", false) }
        rightbracketBtn.setOnClickListener { getBtnSel(")", false) }

        //other operations
        btnMinusAdd.setOnClickListener { getBtnSel("", false) }
        percentBtn.setOnClickListener { getBtnSel("0", false) }

        // click functions for bases
        hexTextView.setOnClickListener {resultTextView.text = hexTextView.text.substring(3)}
        decTextView.setOnClickListener {resultTextView.text = decTextView.text.substring(3)}
        octTextView.setOnClickListener {resultTextView.text = octTextView.text.substring(3)}
        binTextView.setOnClickListener {resultTextView.text = binTextView.text.substring(3)}

        deleteBtn.setOnClickListener {
            var resultStr : String = resultTextView.text.toString()
            resultTextView.text = resultStr.substring(0, resultStr.length-1)
        }

        // clear all operations
        cancelBtn.setOnClickListener {
            resultHolderTextView.text = ""
            resultTextView.text = ""
            hexTextView.text = "HEX"
            decTextView.text = "DEC"
            octTextView.text = "OCT"
            binTextView.text = "BIN"
            bitwiseOutputTextView.text = ""
        }


        equalBtn.setOnClickListener {
            resultHolderTextView.append(resultTextView.text)
//            BasesTextView.text = resultHolderTextView.text

            val expression = ExpressionBuilder(resultHolderTextView.text.toString()).build()
            val result = expression.evaluate()
            val longResult = result.toLong()

            if(result == longResult.toDouble()) {
                resultTextView.text = ""
                resultTextView.text = longResult.toString()
                resultHolderTextView.text = ""
//                resultHolderTextView.text = longResult.toString()
              convertResultToBase(longResult.toString())
            }
            else {
                resultTextView.text = ""
                resultTextView.text = result.toString()
//                resultTextView.text = ""
                resultHolderTextView.text = ""
//                resultHolderTextView.text = result.toString()
              convertResultToBase(result.toString())

            }

        }
        BitwiseSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {

            }

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                when(position){
                    1 -> bitwiseOutputTextView.text = getBitwiseSel("AND", bitwiseInputOne.text.toString().toInt(), bitwiseInputTwo.text.toString().toInt()).toString()
                    2 ->  bitwiseOutputTextView.text = getBitwiseSel("OR", bitwiseInputOne.text.toString().toInt(), bitwiseInputTwo.text.toString().toInt()).toString()
                    3 -> bitwiseOutputTextView.text = getBitwiseSel("XOR", bitwiseInputOne.text.toString().toInt(), bitwiseInputTwo.text.toString().toInt()).toString()
                }

            }

        }
    }

    fun getBtnSel(selectStr : String, isOperation : Boolean){
//        if(resultStr.compareTo("0") == 0){
//            resultTextView.text = ""
//        }

        // when you select a number
        if(!isOperation){
            resultTextView.append(selectStr)

        }
        // when you select an operation
        else{
            resultTextView.append(selectStr)
            resultHolderTextView.append(resultTextView.text)
            resultTextView.text = ""
        }

    }

    // convert to bases
    fun convertResultToBase(result : String) {
        val listBasesView = StringBuilder()

        val hexString = java.lang.Integer.toHexString(result.toInt())
        val octString = java.lang.Integer.toOctalString(result.toInt())
        val binString = java.lang.Integer.toBinaryString(result.toInt())

        hexTextView.text = ("HEX    $hexString")
        decTextView.text = ("DEC    $result")
        octTextView.text = ("OCT    $octString")
        binTextView.text = ("BIN    $binString")
    }

    fun getBitwiseSel(selItem : String, numOne : Int, numTwo : Int): Comparable<*> {

        val bitwiseResult = when(selItem){
            "AND" -> numOne and numTwo
            "OR" -> numOne or numTwo
            "XOR" -> numOne xor numTwo
            else -> ""
        }

        return bitwiseResult
    }
}