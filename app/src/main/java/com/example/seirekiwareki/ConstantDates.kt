// 和暦日付
package com.example.seirekiwareki

import java.text.SimpleDateFormat
import java.util.*

    // 明治元号の最初と最終日
    const val meijiStartDate = "18681023"
    const val meijiEndDate = "19120729"

    // 大正元号の最初と最終日
    const val taishoStartDate = "19120730"
    const val taishoEndDate = "19261224"

    // 昭和元号の最初と最終日
    const val showaStartDate = "19261225"
    const val showaEndDate = "19890106"

    // 平成元号の最初と最終日
    const val heiseiStartDate = "19890107"
    const val heiseiEndDate = "20190430"

    // 令和元号の最初と最終日
    const val reiwaStartDate = "20190501"
    const val reiwaEndDate = "99999999"

    // 元号の漢字
    const val MEIJI = "明治"
    const val TAISHO = "大正"
    const val SHOWA = "昭和"
    const val HEISEI = "平成"
    const val  REIWA = "令和"

    const val MONTH = "月"
    const val DAY = "日"

    // 元号の漢字の省略

    const val MEIJI_S = "明"
    const val TAISHO_S = "大"
    const val SHOWA_S = "昭"
    const val HEISEI_S = "平"
    const val  REIWA_S = "令"

    // 元号の漢字の省略

    const val MEIJI_E = "M"
    const val TAISHO_E = "T"
    const val SHOWA_E = "S"
    const val HEISEI_E = "H"
    const val  REIWA_E = "R"

    // 元号の最終年
    const val YEAR_START = 1
    const val MEIJI_LAST_YEAR = 45
    const val TAISHO_LAST_YEAR = 15
    const val SHOWA_LAST_YEAR = 64
    const val HEISEI_LAST_YEAR = 31

    const val MEIJI_START_YEAR = 1868
    const val TAISHO_START_YEAR = 1912
    const val SHOWA_START_YEAR = 1926
    const val HEISEI_START_YEAR = 1989
    const val REIWA_START_YEAR = 2019

val df = SimpleDateFormat("yyyy/MM/dd")

val GENGOU_DATA = listOf(

    // 時代が新しいものを前に置く必要がある。
    mapOf("startDate" to df.parse("2019/05/01"), "kanji" to REIWA),
    mapOf("startDate" to df.parse("1989/01/07"), "kanji" to HEISEI),
    mapOf("startDate" to df.parse("1926/12/25"), "kanji" to SHOWA),
    mapOf("startDate" to df.parse("1912/07/30"), "kanji" to TAISHO),
    mapOf("startDate" to df.parse("1868/10/23"), "kanji" to MEIJI)

)