package com.example.datefromstring

import org.pojava.datetime.DateTime
import org.pojava.datetime.DateTimeConfig
import org.pojava.datetime.IDateTimeConfig
import java.util.*

class DateParser{
    val globalConfig get() = DateTimeConfig.getGlobalDefault()
    fun createConfig(dmyOrder: Boolean = false): IDateTimeConfig {
        val g = globalConfig
        return object : IDateTimeConfig{
            override fun isDmyOrder(): Boolean {
                return dmyOrder
            }

            override fun getTzMap(): MutableMap<String, String> {
                return g.tzMap
            }

            override fun getInputTimeZone(): TimeZone {
                return g.inputTimeZone
            }

            override fun getOutputTimeZone(): TimeZone {
                return g.outputTimeZone
            }

            override fun getLocale(): Locale {
                return g.locale
            }

            override fun getFormat(): String {
                return g.format
            }

            override fun getBcPrefix(): String {
                return g.bcPrefix
            }

            override fun getEpochDOW(): Int {
                return g.epochDOW
            }

            override fun lookupTimeZone(str: String?): TimeZone {
                return g.lookupTimeZone(str)
            }

            override fun lookupTimeZone(
                str: String?,
                defaultTimeZone: TimeZone?
            ): TimeZone {
                return g.lookupTimeZone(str,defaultTimeZone)
            }

            override fun lookupMonthIndex(monthNameOrAbbreviation: String?): Int {
                return g.lookupMonthIndex(monthNameOrAbbreviation)
            }

            override fun isUnspecifiedCenturyAlwaysInPast(): Boolean {
                return g.isUnspecifiedCenturyAlwaysInPast
            }

            override fun systemTime(): Long {
                return g.systemTime()
            }

            override fun validate() {
                return g.validate()
            }

        }
    }
    data class Date(
        val year: Int,
        val month: Int,
        val day: Int
    ){
        override fun toString(): String {
            return "$year/$month/$day"
        }
    }
    fun parse(input: String, dmyOrder: Boolean = false): Date?{
        try {
            val date = DateTime
                .parse(
                    input,
                    createConfig(dmyOrder)
                ).toDate()
            val calendar = Calendar.getInstance().apply {
                time = date
            }
            val year = calendar.get(Calendar.YEAR)
            val month = calendar.get(Calendar.MONTH)+1
            val day = calendar.get(Calendar.DATE)
            return Date(year,month,day)
        } catch (e: Exception) {
            return null
        }
    }
}