package ru.skillbranch.devintensive.extensions

import java.lang.IllegalStateException
import java.text.SimpleDateFormat
import java.util.*
//import java.util.regex.Pattern
const val SECOND = 1000L
const val MINUTE = 60*SECOND
const val HOUR = 60*MINUTE
const val DAY = 24*HOUR

fun Date.format(pattern: String = "HH:mm:ss dd.MM.yy"): String{
    val dateFormat = SimpleDateFormat(pattern, Locale("ru"))
    return dateFormat.format(this)
}

fun Date.add(value:Int, units: TimeUnits = TimeUnits.SECOND): Date{
    var time = this.time
    time += when (units) {
        TimeUnits.SECOND -> value * SECOND
        TimeUnits.MINUTE -> value * MINUTE
        TimeUnits.HOUR -> value * HOUR
        TimeUnits.DAY -> value * DAY
    }
    this.time = time
    return this
}

fun Date.humanizeDiff(humdate: Date = Date()): String{
    val diff: Int = ((humdate.time - this.time) / 1000).toInt()
    return when {
        diff in 0..1 -> "только что"
        diff in 2 until 45 -> "несколько секунд назад"
        diff in 45 until 75 -> "минуту назад"
        diff in 75 until 45*60 -> "${TimeUnits.MINUTE.plural(diff / 60)} назад"
        diff in 45*60 until 75*60 -> "час назад"
        diff in 75*60 until 22*3600 -> "${TimeUnits.HOUR.plural(diff / 3600)} назад"
        diff in 22*3600 until 26*3600 -> "день назад"
        diff in 26*3600..360*24*3600 -> "${TimeUnits.DAY.plural(diff / (24*3600))} назад"
        diff > 360*24*3600 -> "более года назад"
        diff in -1 until 0 -> "только что"
        diff in -45 until -1 -> "через несколько секунд"
        diff in -75 until -45 -> "через минуту"
        diff in -45*60 until -75 -> "через ${TimeUnits.MINUTE.plural(-1*diff / 60)}"
        diff in -75*60 until -45*60 -> "через час"
        diff in -22*3600 until -75*60 -> "через ${TimeUnits.HOUR.plural(-1*diff / 3600)}"
        diff in -26*3600 until -22*3600 -> "через день"
        diff in -360*24*3600 until -26*3600 -> "через ${TimeUnits.DAY.plural(-1*diff / (24*3600))}"
        diff < -360*24*3600 -> "более чем через год"
        else -> "Ошибка: $diff"
    }

}

enum class TimeUnits(val rus:List<String>) {
    SECOND(listOf("секунду","секунды","секунд")),
    MINUTE(listOf("минуту","минуты","минут")),
    HOUR(listOf("час","часа","часов")),
    DAY(listOf("день","дня","дней"));

    fun plural(value: Int): String{
        val res: Int
        if (value%100 in 11..19) res = 2
            else when (value%10) {
                1 -> res = 0
                in 2..4 -> res = 1
                0, in 5..9 -> res = 2
                else -> res = 2
            }
        return when (this) {
            SECOND -> "$value ${rus[res]}"
            MINUTE -> "$value ${rus[res]}"
            HOUR -> "$value ${rus[res]}"
            DAY -> "$value ${rus[res]}"
        }
    }

    }