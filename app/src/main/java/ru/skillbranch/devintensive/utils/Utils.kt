package ru.skillbranch.devintensive.utils

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
        //val firstName?: String
        //val lastName?: String
        //if (parts[0].isBlank() || parts[0].isEmpty()) firstName = null else firstName = parts[0]
        //if (parts[1].isBlank() || parts[1].isEmpty()) lastName = null else lastName = parts[1]

        //if (parts[0].isBlank() || parts[0].isEmpty()) parts[0].replace(null)
        //if (parts[1].isBlank() || parts[1].isEmpty()) parts[1].replace(null)
        //val firstName = parts[0]//parts?.getOrNull(0)
        //val lastName = parts[1]//parts?.getOrNull(1)
        //if ((firstName.isBlank()) || (firstName.isEmpty())) firstName = null
        //if ((lastName.isBlank()) || (lastName.isEmpty())) lastName = null

    }

}