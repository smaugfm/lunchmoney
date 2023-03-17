package io.github.smaugfm.lunchmoney.exception

import java.io.IOException

open class LunchmoneyApiException : IOException {
    constructor(message: String) : super(message)
    constructor(cause: Throwable) : super(cause)
}
