package com.isoneday.kade3.mvp.view.activity

import android.os.Bundle
import android.support.design.widget.TabLayout
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter
import android.support.v7.app.AppCompatActivity
import com.isoneday.kade3.R
import com.isoneday.kade3.mvp.view.fragment.LastMatchFragment
import com.isoneday.kade3.mvp.view.fragment.NextMatchFragment
import kotlinx.android.synthetic.main.activity_history_booking.*




class FootballMatchActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_history_booking)

        tablayout.addTab(tablayout.newTab().setText(getString(R.string.lastmatch)))
        tablayout.addTab(tablayout.newTab().setText(getString(R.string.nextmatch)))
        val adapter = CustomAdapter(supportFragmentManager)
        pager.setAdapter(adapter)
        pager.addOnPageChangeListener(TabLayout.TabLayoutOnPageChangeListener(tablayout))
        tablayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab) {
                pager.currentItem = tab.position
            }

            override fun onTabUnselected(tab: TabLayout.Tab) {

            }

            override fun onTabReselected(tab: TabLayout.Tab) {

            }
        })}

    private inner class CustomAdapter(supportFragmentManager: FragmentManager) :
        FragmentStatePagerAdapter(supportFragmentManager) {

        override fun getItem(position: Int): Fragment? {
            if (position == 0) {
                return LastMatchFragment()
            } else if (position == 1) {
                return NextMatchFragment()
            }
            return null
        }

        override fun getCount(): Int {
            return 2
        }
    }
}
