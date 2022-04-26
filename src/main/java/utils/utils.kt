package utils

import java.util.StringJoiner

fun split(string: String) = string.split(",".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()