package com.bridou_n.crossfitsolid.features.classes

import android.content.Context
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import android.text.format.DateUtils
import com.bridou_n.crossfitsolid.R
import com.bridou_n.crossfitsolid.models.GroupActivity
import com.bridou_n.crossfitsolid.utils.extensionFunctions.getDayString
import org.joda.time.LocalDate
import java.util.*
import kotlin.collections.ArrayList

/**
 * Created by bridou_n on 27/07/2017.
 */

class DaysFragmentPagerAdapter(val fm: FragmentManager,
                               val ctx: Context,
                               val data: Array<GroupActivity>) : FragmentPagerAdapter(fm) {

    val days = mutableMapOf<Int, ArrayList<GroupActivity>>()

    init {
        for (ga in data) {
            val start = LocalDate.fromDateFields(ga.duration?.start)

            if (days[start.dayOfWeek] == null) {
                days[start.dayOfWeek] = ArrayList()
            }
            days[start.dayOfWeek]?.add(ga)
        }
    }

    override fun getItem(position: Int): Fragment {
        return DayViewFragment.newInstance(days[position + 1] ?: arrayListOf<GroupActivity>())
    }

    override fun getCount(): Int {
        return 7 // We only diplay data for one week
    }

    // TODO: update design of this
    override fun getPageTitle(position: Int): CharSequence {
        val currentDate = Date()

        return when (position) {
            0 -> ctx.getString(R.string.today)
            1 -> ctx.getString(R.string.tomorrow)
            else -> {
                currentDate.time = currentDate.time + (position * DateUtils.DAY_IN_MILLIS)
                currentDate.getDayString()
            }
        }
    }
}