package com.mateus.batista.testeandroidv2app.extensions

import com.mateus.batista.testeandroidv2app.data.remote.Response
import retrofit2.HttpException
import java.io.IOException

open class DataSourceException(message: String? = null, code: Int? = null) : Exception(message)
class RemoteDataNotFoundException(code: Int? = null) : DataSourceException(null, code)
class NoInternetException(message: String? = "No Internet Connection", code: Int? = 503) : DataSourceException(message, code)
class ServerException(message: String?) : Exception(message)

suspend fun <T : Any> safeApiCall(
    isOnline: () -> Boolean,
    call: suspend () -> T)
        : Response<T> {
    return when {
        isOnline() -> {
            try {
                val dataFromRemote = call()
                Response.Success(dataFromRemote)
            } catch (httpException: HttpException) {
                Response.Error(RemoteDataNotFoundException(httpException.code()))
            } catch (ioException: IOException) {
                Response.Error(ServerException(ioException.message))
            }
        }
        else -> Response.Error(NoInternetException())
    }
}