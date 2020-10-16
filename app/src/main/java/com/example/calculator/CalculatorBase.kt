package com.example.calculator

import android.widget.TextView
import android.widget.Button
import kotlinx.android.synthetic.main.activity_main.*




fun getSelection(selectBtn : String, isOperation : Boolean, numberView : TextView, expressView : String){

    if(isOperation){
        numberView.text = ""

    }


//    var num = when (selectBtn.text){
//        "0" -> 0
//        "1" -> 1
//        "2" -> 2
//        "3" -> 3
//        "4" -> 4
//        "5" -> 5
//        "6" -> 6
//        "7" -> 7
//        "8" -> 8
//        "9" -> 9
//        else -> 0
//    }

}

fun getOperation(){

}

fun getDecimal(){

}

fun getEqual(){
    
}