package com.example.seirekiwareki.ui.main

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.example.seirekiwareki.Information
import com.example.seirekiwareki.JapaneseDate
import com.example.seirekiwareki.R
import com.example.seirekiwareki.WesternDate

private val TAB_TITLES = arrayOf(
    R.string.tab_text_1,
    R.string.tab_text_2,
    R.string.tab_text_3
)

/**
 * A [FragmentPagerAdapter] that returns a fragment corresponding to
 * one of the sections/tabs/pages.
 */
class SectionsPagerAdapter(private val context: Context, fm: FragmentManager) : FragmentPagerAdapter(fm) {

    // タブカウント
    private val Count = 3
    override fun getItem(position: Int): Fragment?{
        // getItem is called to instantiate the fragment for the given page.
        // Return a PlaceholderFragment (defined as a static inner class below).
        var fragment : Fragment? = null
        when(position){
            0 -> fragment = WesternDate()
            1 -> fragment = JapaneseDate()
            2 -> fragment = Information()
        }

        return fragment
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return context.resources.getString(TAB_TITLES[position])
    }

    override fun getCount(): Int {
        // Show 3 total pages.
        return Count
    }
}