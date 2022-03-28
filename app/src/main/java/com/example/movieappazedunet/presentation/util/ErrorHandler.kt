package com.example.movieappazedunet.presentation.util

import android.util.Log
import com.example.movieappazedunet.domain.model.Error
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import retrofit2.HttpException
import java.io.IOException

class ErrorHandler() {
    @Throws(IOException::class)

    fun handleError(throwable: Throwable): String {
        throwable.printStackTrace()
        if (throwable is HttpException) {
            val json = throwable.response()!!.errorBody()!!.string()

            val moshi = Moshi.Builder().build()
            val jsonAdapter: JsonAdapter<Error> =
                moshi.adapter(Error::class.java)

            val error: Error? = jsonAdapter.fromJson(json)
            if (error?.message != null) {
                return error.message
            }
        }
        Log.d("errorhandler", "handleError: " + throwable.message.toString())
        return "Something went wrong"
    }

}

