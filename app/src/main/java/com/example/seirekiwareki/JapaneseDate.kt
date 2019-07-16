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
import kotlinx.android.synthetic.main.fragment_japanese_date.*
import java.lang.Exception
import java.text.SimpleDateFormat


import kotlin.collections.ArrayList

class JapaneseDate : Fragment(){

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

                    if (getString(R.string.no_era).equals(seirekiDate)){
                        Toast.makeText(it1, seirekiDate, Toast.LENGTH_LONG).show()
                    }

                    else if(getString(R.string.err_meiji).equals(seirekiDate) || getString(R.string.err_taisho).equals(seirekiDate) ||
                        getString(R.string.err_showa).equals(seirekiDate) || getString(R.string.err_heisei).equals(seirekiDate)){
                        Toast.makeText(it1, seirekiDate + "\n" + CORRECT_VAL, Toast.LENGTH_LONG).show()
                    }

                    else if (getString(R.string.incorrect_val).equals(seirekiDate)){
                        Toast.makeText(it1, seirekiDate, Toast.LENGTH_LONG).show()
                    }

                    else{
                        seirekiResult.text = seirekiDate
                    }
                }
            }
        }
        return parentHolder
    }

    fun getSeirekiDate(warekiDate : String) : String{

        var seirekiDate: String = INCORRECT_VAL
        var year : Int

        try{

            year = Regex("[^0-9 ]").replace(warekiDate.substring(0, warekiDate.indexOf("年")),"").toInt()
            // 明治
            if (warekiDate.contains(MEIJI) || warekiDate.contains(MEIJI_S) || warekiDate.contains(MEIJI_E)){

               if(year >= YEAR_START && year <= MEIJI_LAST_YEAR){
                 seirekiDate =    checkDate(warekiDate, MEIJI_E)
                }
                else {
                    return getString(R.string.err_meiji)
                }

            }

            // 大正
            else if (warekiDate.contains(TAISHO) || warekiDate.contains(TAISHO_S) || warekiDate.contains(TAISHO_E)){

                if(year >= YEAR_START && year <= TAISHO_LAST_YEAR){
                    seirekiDate =    checkDate(warekiDate, TAISHO_E)
                }
                else {
                    return getString(R.string.err_taisho)
                }
            }

            // 昭和
            else if (warekiDate.contains(SHOWA) || warekiDate.contains(SHOWA_S) || warekiDate.contains(SHOWA_E)){

                if(year >= YEAR_START && year <= SHOWA_LAST_YEAR){
                    seirekiDate =    checkDate(warekiDate, SHOWA_E)
                }
                else {
                    return getString(R.string.err_showa)
                }

            }

            // 平成
            else if (warekiDate.contains(HEISEI) || warekiDate.contains(HEISEI_S) || warekiDate.contains(HEISEI_E)){

                if(year >= YEAR_START && year <= HEISEI_LAST_YEAR){
                    seirekiDate =    checkDate(warekiDate, HEISEI_E)
                }
                else {
                    return getString(R.string.err_heisei)
                }

            }

            // 令和
            else if (warekiDate.contains(REIWA) || warekiDate.contains(REIWA_S) || warekiDate.contains(REIWA_E)){

               if(year >= YEAR_START ){
                   seirekiDate =    checkDate(warekiDate, REIWA_E)
                }
                else {
                   return getString(R.string.incorrect_val)
                }
            }

            else {
                return getString(R.string.no_era)
            }


        }catch (e: Exception){
            // エラーは無視する。
        }
        finally{
            return seirekiDate
        }
    }

    fun checkDate(date: String, era: String):String{
        var resultDate : String
        var year : Int
        var month = ""
        var day  = ""
        val slash = "/"
        var kanji = ""
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

            if (! month.isEmpty()){
                month = month.padStart(2,'0')
                resultDate = year.toString() + slash + month

                if (! day.isEmpty()){
                    day = day.padStart(2,'0')
                    resultDate = resultDate + slash + day
                }
                else {
                    resultDate = resultDate + slash + "01"
                }
                val format = SimpleDateFormat("yyyy/MM/dd")
                format.setLenient(false)
                val checkFinalDate = Calendar.getInstance()
                checkFinalDate.time = format.parse(resultDate)

                // 日付＞＝元号の最初日
                run loop@{
                    GENGOU_DATA.forEach {
                       val lstKanji = it["kanji"] as String
                        // 日付が startDate より大きかったら終わり
                        if (lstKanji.equals(kanji)) {
                            val calStartDate = Calendar.getInstance()
                            calStartDate.time = it["startDate"] as Date
                            if(checkFinalDate.compareTo(calStartDate) < 0){
                                return getString(R.string.incorrect_val)
                            }
                            return@loop
                        }
                    }
                }
            }
            else
            {
                resultDate = year.toString() + "年"
            }
            return resultDate
        }catch (e: Exception){
            resultDate = return getString(R.string.incorrect_val)
        }
    }
}
