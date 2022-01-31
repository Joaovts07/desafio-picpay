package com.picpay.desafio.android.data

import retrofit2.Response
import java.net.HttpURLConnection

sealed class Output<out Response> {
    data class Success<Response> (val value : Response): Output<Response>()
    data class Failure(val statusCode: Int): Output<Nothing>()
}


