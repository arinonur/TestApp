package com.example.marvelapp.app.base.error

import android.content.Context
import com.example.marvelapp.R
import com.example.marvelapp.data.exception.NoNetworkException

class ErrorHandler(private val context: Context) {

    fun getErrorMessage(throwable: Throwable): String {
        return when (throwable) {
            is NoNetworkException -> context.getString(R.string.no_network_connection)
            else -> throwable.message ?: context.getString(R.string.unknown_error)
        }
    }

}