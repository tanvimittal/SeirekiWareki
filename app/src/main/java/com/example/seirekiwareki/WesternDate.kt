package com.example.seirekiwareki

import android.app.DatePickerDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.TextView
import androidx.fragment.app.Fragment
import java.text.SimpleDateFormat
import java.util.*


class WesternDate: Fragment(){

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val parentHolder = inflater.inflate(R.layout.fragment_western_date, container, false)

        // Getting seirekiDate
        val seirekiDate = parentHolder.findViewById(R.id.seirekiDate) as EditText
        val cal = java.util.Calendar.getInstance()
        var year = cal.get(java.util.Calendar.YEAR)
        var month = cal.get(java.util.Calendar.MONTH)
        var day = cal.get(java.util.Calendar.DAY_OF_MONTH)

        var date = DatePickerDialog.OnDateSetListener { view, year, monthOfYear, dayOfMonth ->

        cal.set(java.util.Calendar.YEAR, year)
        cal.set(java.util.Calendar.MONTH, monthOfYear)
        cal.set(java.util.Calendar.DAY_OF_MONTH, dayOfMonth)
        val dateFormat = "yyyy/MM/dd"
        var sdf = SimpleDateFormat(dateFormat, Locale.JAPAN)
        seirekiDate.setText(sdf.format(cal.time))

        var dateConversion = DateConversion()
        var strWarekiDate = dateConversion.seirekiToWareki(sdf.format(cal.time))

        val warekiDate = parentHolder.findViewById(R.id.warekiDate) as TextView
        warekiDate.setText(strWarekiDate)
        }

        // Handler on Seireki Date
        seirekiDate.setOnClickListener{

            this.context?.let { it1 ->
                DatePickerDialog(
                    it1, date, cal
                    .get(Calendar.YEAR), cal.get(Calendar.MONTH),
                cal.get(Calendar.DAY_OF_MONTH)
                ).show()
            }

        }

        return parentHolder
    }
}
