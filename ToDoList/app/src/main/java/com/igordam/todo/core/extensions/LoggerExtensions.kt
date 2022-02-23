package com.igordam.todo.core.extensions

import com.igordam.todo.core.network.Failure
import com.orhanobut.logger.Logger

fun Failure.loggerDebug() {
    when (this) {
        is Failure.NetworkFailure -> {
            logger("Failure:${this.javaClass.name}\n" +
                    "Code:${this.code}\nMessage:${this.message}")
        }
        is Failure.ParseFailure -> {
            logger("Failure:${this.javaClass.name}\n" +
                    "There is an error parsing something\nMessage:${this.message}")
        }
        is Failure.GenericFailure -> {
            logger("Failure:${this.javaClass.name} we do not know what happen.")
        }
    }
}

fun logger(value: String) {
    Logger.d(value)
}