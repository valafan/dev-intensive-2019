package ru.skillbranch.devintensive.utils

import ru.skillbranch.devintensive.extensions.stripHtml
import java.net.URL

object Utils {

    fun blankToNull(str: String?): String? {
        return if (str?.isBlank() == true) null else str
    }

    fun initial(str: String?): String? {
        return if (str == null) "" else Character.toString(str[0]).toUpperCase()
    }

    fun parseFullName(fullName: String?): Pair<String?, String?>{
        val parts: List<String>? = fullName?.split(" ")

        val firstName = blankToNull(parts?.getOrNull(index = 0))
        val lastName = blankToNull(parts?.getOrNull(index = 1))

        return firstName to lastName
    }

    fun toInitials(firstName: String?, lastName: String?): String?{
        val firstNameInitial = initial(blankToNull(firstName))
        val lastNameInitial = initial(blankToNull(lastName))

        return blankToNull(firstNameInitial + lastNameInitial)
    }

    fun transliteration(payload: String, divider: String = " "): String{
        val parts: List<String> = payload.split(" ")
        val text: String =
            URL("https://skill-branch.ru/resources/dev-intensive-2019/lesson2/transliteration.html").readText()

        val textparts: List<String> =
            text.stripHtml().replace("Интервалы","").trim().split(",")

        var result: String = ""
        var symbol: String = ""
        var firstIteration: Boolean = true

        for (part in parts) {
            for (ch in part) {
                for (textpart in textparts) {
                    if (textpart.contains(ch.toString().toLowerCase())) {
                        var symbols = textpart.split(":")
                        symbol = symbols.get(index = 1).replace("\"","").trim()
                    }
                }
                if (ch == ch.toUpperCase()) {
                    result += symbol.substring(0,1).toUpperCase() + symbol.substring(1)
                }
                else {
                    result += symbol
                }
            }
            if (firstIteration == true) {
                result += divider
                firstIteration = false
            }
        }

        return result
    }
}