package com.erp.distribution.sfa.utils

import java.util.*

class DateTimeUtils {


    companion object{
        fun getZeroTimeDate(date: Date): Date {
            var date: Date = date
            val calendar: Calendar = Calendar.getInstance()
            calendar.setTime(date)
            calendar.set(Calendar.HOUR_OF_DAY, 0)
            calendar.set(Calendar.MINUTE, 0)
            calendar.set(Calendar.SECOND, 0)
            calendar.set(Calendar.MILLISECOND, 0)
            date = calendar.getTime()
            return date
        }
    }

}
