package ru.skillbranch.devintensive.utils

import java.lang.IllegalArgumentException

object Utils {
    fun parseFullName(fullName:String?):Pair<String?, String?>{

        val parts: List<String?>? = when {
            (fullName.isNullOrBlank()) -> listOf(null, null)
            (fullName.isNullOrEmpty()) -> listOf(null, null)
            (fullName == "") -> listOf(null, null)
            (fullName == " ") -> listOf(null, null)
            else -> fullName.split(" ")
        }

        val firstName = parts?.getOrNull(0)
        val lastName = parts?.getOrNull(1)
        return Pair(firstName, lastName)
    }

    fun toInitials(firstName:String?, lastName:String?): String?{
        val firstInitial: String? = when {
            firstName.isNullOrEmpty() -> null
            firstName == " " -> null
            firstName.isNotEmpty() -> firstName[0].toUpperCase().toString()
            else -> throw IllegalArgumentException("Ошибка: $firstName")
        }
        val lastInitial: String? = when {
            lastName.isNullOrEmpty() -> null
            lastName == " " -> null
            lastName.isNotEmpty() -> lastName[0].toUpperCase().toString()
            else -> throw IllegalArgumentException("Ошибка: $lastName")
        }
        return when {
            (firstInitial == null) and (lastInitial == null) -> null
            (firstInitial != null) and (lastInitial == null) -> firstInitial
            (firstInitial == null) and (lastInitial != null) -> lastInitial
            (firstInitial != null) and (lastInitial != null) -> firstInitial + lastInitial
            else -> throw IllegalArgumentException("Ошибка: $firstInitial $lastInitial")
        }
    }

    fun transliteration(payload:String?, divider:String=" "): String?{
        var S: String? = ""
        var buf: String
        val alf = mapOf(
        "а" to "a",
        "б" to "b",
        "в" to "v",
        "г" to "g",
        "д" to "d",
        "е" to "e",
        "ё" to "e",
        "ж" to "zh",
        "з" to "z",
        "и" to "i",
        "й" to "i",
        "к" to "k",
        "л" to "l",
        "м" to "m",
        "н" to "n",
        "о" to "o",
        "п" to "p",
        "р" to "r",
        "с" to "s",
        "т" to "t",
        "у" to "u",
        "ф" to "f",
        "х" to "h",
        "ц" to "c",
        "ч" to "ch",
        "ш" to "sh",
        "щ" to "sh'",
        "ъ" to "",
        "ы" to "i",
        "ь" to "",
        "э" to "e",
        "ю" to "yu",
        "я" to "ya"
        )

        if (payload.isNullOrEmpty()) S = null else
            for (i in payload.indices)
                when {
                    (payload[i].toString() in "A".."z") -> S += payload[i].toString()
                    (payload[i].toString().toLowerCase() in alf.keys) ->
                        if (payload[i].isLowerCase()) S += alf[payload[i].toString()]
                        else {
                            buf = alf[payload[i].toString().toLowerCase()].toString()
                            for (j in buf.indices) if (j == 0) S += buf[j].toUpperCase() else S += buf[j]
                        }
                    else -> S += divider
                }

        return S
    }

}