package com.github.smaugfm.lunchmoney

object Util {
    fun getResourceAsString(path: String) =
        Util::class.java.classLoader.getResource(path)!!.readText()
}
