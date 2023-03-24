package com.example.btt2screen.tabview


import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.example.btt2screen.fragmentscreen.FragmentHome
import com.example.btt2screen.fragmentscreen.NowPlaying

class SampleAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm) {

    override fun getItem(position: Int): Fragment = when (position) {
        0 -> NowPlaying.newInstance()
        1 -> FragmentHome.newInstance()
        else ->
            getItem(position)
    }
    override fun getPageTitle(position: Int): CharSequence = when (position) {
        0 -> "Now Playing"
        1 -> "Top Rated"
        else -> ""
    }
    override fun getCount(): Int = 2

}