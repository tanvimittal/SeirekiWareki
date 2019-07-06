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

public class DateConversion{

    fun seirekiToWareki(seirekiDate: String) : String{

        var warekiDate : String = ""
        var era : String = ""


        var seirekiRetDate : String
        seirekiRetDate   = seirekiDate.replace("/", "")

        // 年、月、日を取得する。
        var year : Int
        var month : Int
        var day : Int

        year = seirekiRetDate.substring(0,4).toInt()
        month = seirekiRetDate.substring(4,6).toInt()
        day = seirekiRetDate.substring(6).toInt()

        if (seirekiRetDate >= meijiStartDate && seirekiRetDate <= meijiEndDate)
        {
            era = MEIJI
            year = year-1868+1
        }
        else if (seirekiRetDate >= taishoStartDate && seirekiRetDate <= taishoEndDate)
        {
            era = TAISHO
            year = year-1912+1
        }
        else if (seirekiRetDate >= meijiStartDate && seirekiRetDate <= meijiEndDate)
        {
            era = SHOWA
            year = year-1926+1
        }
        else if (seirekiRetDate >= heiseiStartDate && seirekiRetDate <= heiseiEndDate)
        {
            era = HEISEI
            year = year-1989+1
        }
        else if (seirekiRetDate >= reiwaStartDate && seirekiRetDate <= reiwaEndDate)
        {
            era = REIWA
            year = year-2019+1
        }

        warekiDate = year.toString() + era+ month.toString() + MONTH + day.toString()+ DAY

        return warekiDate
    }
}
