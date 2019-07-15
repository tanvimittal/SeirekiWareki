package com.example.seirekiwareki

import android.app.DatePickerDialog
import android.os.Bundle
import android.telephony.SmsManager
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import java.util.*

import com.example.seirekiwareki.NO_J_INPUT
import com.example.seirekiwareki.NO_ERA

import com.example.seirekiwareki.MEIJI
import com.example.seirekiwareki.MEIJI_S
import com.example.seirekiwareki.MEIJI_E

import com.example.seirekiwareki.TAISHO
import com.example.seirekiwareki.TAISHO_S
import com.example.seirekiwareki.TAISHO_E

import com.example.seirekiwareki.SHOWA
import com.example.seirekiwareki.SHOWA_S
import com.example.seirekiwareki.SHOWA_E

import com.example.seirekiwareki.HEISEI
import com.example.seirekiwareki.HEISEI_S
import com.example.seirekiwareki.HEISEI_E

import com.example.seirekiwareki.REIWA
import com.example.seirekiwareki.REIWA_S
import com.example.seirekiwareki.REIWA_E

import com.example.seirekiwareki.YEAR_START
import com.example.seirekiwareki.MEIJI_LAST_YEAR
import com.example.seirekiwareki.TAISHO_LAST_YEAR
import com.example.seirekiwareki.SHOWA_LAST_YEAR
import com.example.seirekiwareki.HEISEI_LAST_YEAR
import java.lang.Exception
import java.text.SimpleDateFormat


import kotlin.collections.ArrayList

public class JapaneseDate : Fragment(){

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        //XMLを取得する。
        val parentHolder = inflater.inflate(R.layout.fragment_japanese_date, container, false)

        // ボタンのIDを取得する。
        val btnId = parentHolder.findViewById<Button>(R.id.btnConvert)

        btnId.setOnClickListener {
            this.context?.let { it1 ->

                // 入力された和暦日付を取得する。
                val warekiDateId = parentHolder.findViewById<EditText>(R.id.warekiDate)
                val warekiDate = warekiDateId.text.trim().toString()

                // 空白の場合
                if (TextUtils.isEmpty(warekiDate)){
                    Toast.makeText(it1, NO_J_INPUT, Toast.LENGTH_LONG).show()
                }

                else{
                    val seirekiDate = getSeirekiDate(warekiDate)

                    if (NO_ERA.equals(seirekiDate)){
                        Toast.makeText(it1, seirekiDate, Toast.LENGTH_LONG).show()
                    }

                    else if(ERR_MEIJI.equals(seirekiDate) || ERR_TAISHO.equals(seirekiDate) ||
                        ERR_SHOWA.equals(seirekiDate) || ERR_HEISEI.equals(seirekiDate)){
                        Toast.makeText(it1, seirekiDate + "\n" + CORRECT_VAL, Toast.LENGTH_LONG).show()
                    }

                    else if (INCORRECT_VAL.equals(seirekiDate)){
                        Toast.makeText(it1, seirekiDate, Toast.LENGTH_LONG).show()
                    }

                    else{
                        Toast.makeText(it1, seirekiDate, Toast.LENGTH_LONG).show()
                    }
                }
            }
        }
        return parentHolder
    }

    fun getSeirekiDate(warekiDate : String) : String{

        var seirekiDate: String = INCORRECT_VAL
        var result : List<String>
        var delimiters : String = ""
        var year : Int
        var month : Int
        var day : Int


        try{

            year = Regex("[^0-9 ]").replace(warekiDate.substring(0, warekiDate.indexOf("年")),"").toInt()
            // 明治
            if (warekiDate.contains(MEIJI) || warekiDate.contains(MEIJI_S) || warekiDate.contains(MEIJI_E)){

               if(year >= YEAR_START && year <= MEIJI_LAST_YEAR){
                 seirekiDate =    checkDate(warekiDate, MEIJI_E)
                }
                else {
                    return ERR_MEIJI
                }

            }

            // 大正
            else if (warekiDate.contains(TAISHO) || warekiDate.contains(TAISHO_S) || warekiDate.contains(TAISHO_E)){

                if(year >= YEAR_START && year <= TAISHO_LAST_YEAR){
                    seirekiDate =    checkDate(warekiDate, TAISHO_E)
                }
                else {
                    return ERR_TAISHO
                }
            }

            // 昭和
            else if (warekiDate.contains(SHOWA) || warekiDate.contains(SHOWA_S) || warekiDate.contains(SHOWA_E)){

                if(year >= YEAR_START && year <= SHOWA_LAST_YEAR){
                    seirekiDate =    checkDate(warekiDate, SHOWA_E)
                }
                else {
                    return ERR_SHOWA
                }

            }

            // 平成
            else if (warekiDate.contains(HEISEI) || warekiDate.contains(HEISEI_S) || warekiDate.contains(HEISEI_E)){

                if(year >= YEAR_START && year <= HEISEI_LAST_YEAR){
                    seirekiDate =    checkDate(warekiDate, HEISEI_E)
                }
                else {
                    return ERR_HEISEI
                }

            }

            // 令和
            else if (warekiDate.contains(REIWA) || warekiDate.contains(REIWA_S) || warekiDate.contains(REIWA_E)){

               if(year >= YEAR_START ){
                   seirekiDate =    checkDate(warekiDate, HEISEI_E)
                }
                else {
                    return INCORRECT_VAL
                }
            }

            else {
                return NO_ERA
            }


        }catch (e: Exception){
            // エラーは無視する。
        }
        finally{
            return seirekiDate
        }
    }

    fun checkDate(date: String, era: String):String{
        var resultDate = ""
        var year : Int
        var month : String = ""
        var day : String = ""
        val slash = "/"
        var kanji : String = ""
        try {

            year = Regex("[^0-9 ]").replace(date.substring(0, date.indexOf("年")),"").toInt()

            // 月がある場合
            if (date.contains("月")){
                month = Regex("[^0-9 ]").replace(date.substring(date.indexOf("年"), date.indexOf("月")),"")

                // 日がある場合
                if (date.contains("日")){
                    day = Regex("[^0-9 ]").replace(date.substring(date.indexOf("月"), date.indexOf("日")),"")
                }
            }

            when(era){
                MEIJI_E -> { year = year + MEIJI_START_YEAR - 1
                             kanji = MEIJI}
                TAISHO_E -> { year = year + TAISHO_START_YEAR - 1
                              kanji = TAISHO}
                SHOWA_E ->  { year = year + SHOWA_START_YEAR - 1
                              kanji = SHOWA}
                HEISEI_E -> { year = year + HEISEI_START_YEAR - 1
                              kanji = HEISEI }
                REIWA_E -> { year = year + REIWA_START_YEAR - 1
                             kanji = REIWA }
            }

            if (!month.isNullOrEmpty()){
                month = month.padStart(2,'0')
                resultDate = year.toString() + slash + month

                if (!day.isNullOrEmpty()){
                    day = day.padStart(2,'0')
                    resultDate = resultDate + slash + day
                }
                else {
                    resultDate = resultDate + slash + "01"
                }
                val format = SimpleDateFormat("yyyy/MM/dd")
                format.setLenient(false);
                format.parse(resultDate)
            }
            else
            {
                resultDate = year.toString() + "年"
            }

        }catch (e: Exception){

            resultDate = "Error in parsing"
        }finally{
            return resultDate
        }

    }



}
