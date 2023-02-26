package com.github.smaugfm.lunchmoney

import java.net.URI

class PathAndQuery private constructor() {
    private val segments = StringBuilder()
    private var queryParams: StringBuilder? = null

    fun segment(folder: Any): PathAndQuery {
        val str: String = checkBlank(folder)
        segments.append("/")
        segments.append(str)
        return this
    }

    fun param(parameter: Any, value: Any): PathAndQuery {
        val parameterStr: String = checkBlank(parameter)
        val valueStr: String = checkBlank(value)
        if (queryParams != null) {
            queryParams?.append("&")
        } else {
            queryParams = StringBuilder()
        }
        val qp = queryParams!!
        qp.append(parameterStr)
        qp.append("=")
        qp.append(valueStr)
        return this
    }

    override fun toString(): String {
        val uri = URI(
            null,
            null,
            segments.toString(),
            queryParams?.toString(),
            null
        )
        return uri.toString()
    }

    companion object {
        fun segment(folder: Any) =
            PathAndQuery().segment(folder)

        fun param(parameter: Any, value: Any) =
            PathAndQuery().param(parameter, value)

        private fun checkBlank(folder: Any) =
            folder.toString().also {
                require(it.isNotBlank()) { "folder is blank" }
            }
    }
}
