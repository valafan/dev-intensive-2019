package ru.skillbranch.devintensive.extensions

fun String.stripHtml(): String{
    return replace("\\<.*?\\>".toRegex(), "")
}