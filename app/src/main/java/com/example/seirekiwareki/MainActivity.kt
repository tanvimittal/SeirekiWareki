package com.example.seirekiwareki

import android.app.DatePickerDialog
import android.os.Bundle
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.tabs.TabLayout
import androidx.viewpager.widget.ViewPager
import androidx.appcompat.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.widget.EditText
import android.widget.TextView
import com.example.seirekiwareki.ui.main.SectionsPagerAdapter
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val sectionsPagerAdapter = SectionsPagerAdapter(this, supportFragmentManager)
        val viewPager: ViewPager = findViewById(R.id.view_pager)
        viewPager.adapter = sectionsPagerAdapter
        val tabs: TabLayout = findViewById(R.id.tabs)
        tabs.setupWithViewPager(viewPager)


        // Getting seirekiDate
//        val seirekiDate = findViewById(R.id.seirekiDate) as EditText



  //      val cal = java.util.Calendar.getInstance()
    //    var year = cal.get(java.util.Calendar.YEAR)
     //   var month = cal.get(java.util.Calendar.MONTH)
      //  var day = cal.get(java.util.Calendar.DAY_OF_MONTH)

        //var date = DatePickerDialog.OnDateSetListener { view, year, monthOfYear, dayOfMonth ->

          //  cal.set(java.util.Calendar.YEAR, year)
           // cal.set(java.util.Calendar.MONTH, monthOfYear)
           // cal.set(java.util.Calendar.DAY_OF_MONTH, dayOfMonth)
           // val dateFormat = "yyyy/MM/dd"
           // var sdf = SimpleDateFormat(dateFormat, Locale.JAPAN)
           // seirekiDate.setText(sdf.format(cal.time))

          //  var dateConversion = DateConversion()
          //  var strWarekiDate = dateConversion.seirekiToWareki(sdf.format(cal.time))

           // val warekiDate = findViewById(R.id.warekiDate) as TextView
           // warekiDate.setText(strWarekiDate)
       // }

        // Handler on Seireki Date
        //seirekiDate.setOnClickListener{

            //DatePickerDialog(
              //  this@MainActivity, date, cal
                //    .get(Calendar.YEAR), cal.get(Calendar.MONTH),
                //cal.get(Calendar.DAY_OF_MONTH)
            //).show()

 //       }
    }


}