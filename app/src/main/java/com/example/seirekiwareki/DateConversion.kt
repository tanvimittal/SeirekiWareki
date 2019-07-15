/*
西暦和暦変換クラス
 */
package com.example.seirekiwareki
import com.example.seirekiwareki.meijiStartDate
import com.example.seirekiwareki.meijiEndDate
import com.example.seirekiwareki.showaStartDate
import com.example.seirekiwareki.showaEndDate
import com.example.seirekiwareki.taishoStartDate
import com.example.seirekiwareki.taishoEndDate
import com.example.seirekiwareki.heiseiStartDate
import com.example.seirekiwareki.heiseiEndDate
import com.example.seirekiwareki.reiwaStartDate
import com.example.seirekiwareki.reiwaEndDate

import com.example.seirekiwareki.MEIJI
import com.example.seirekiwareki.TAISHO
import com.example.seirekiwareki.SHOWA
import com.example.seirekiwareki.HEISEI
import com.example.seirekiwareki.REIWA

import com.example.seirekiwareki.MONTH
import com.example.seirekiwareki.DAY
import java.text.SimpleDateFormat
import java.util.*

public class DateConversion{

    var era : String? = null
    fun seirekiToWareki(seirekiDate: String) : String{

        val df = SimpleDateFormat("yyyy/MM/dd")



        val date = df.parse(seirekiDate)



        val cal = Calendar.getInstance()



        cal.time = date

        var year = cal.get(Calendar.YEAR)



        var month = cal.get(Calendar.MONTH)+1



        var day = cal.get(Calendar.DAY_OF_MONTH)







        run loop@{



            GENGOU_DATA.forEach {



                val calStartDate = Calendar.getInstance()



                calStartDate.time = it["startDate"] as Date







                // 日付が startDate より大きかったら終わり



                if (cal.compareTo(calStartDate) >= 0) {

                    era = it["kanji"] as String



                    year = year - calStartDate.get(Calendar.YEAR) + 1



                    return@loop



                }



            }



        }
        return if (era == null) {



            "非対応です。"



        } else {



            "${era}${year}年${month}月${day}日"



        }

    }
}
