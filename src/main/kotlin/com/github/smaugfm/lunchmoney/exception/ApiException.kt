package com.github.smaugfm.lunchmoney.exception

import java.io.IOException

open class ApiException : IOException {
    constructor(message: String) : super(message)
    constructor(cause: Throwable) : super(cause)
}
