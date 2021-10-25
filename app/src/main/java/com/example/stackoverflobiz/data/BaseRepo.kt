package com.example.stackoverflobiz.data

import com.example.stackoverflobiz.utils.ErrorType
import com.example.stackoverflobiz.utils.Resource
import kotlinx.coroutines.*
import okhttp3.ResponseBody
import org.json.JSONObject
import retrofit2.HttpException
import retrofit2.Response
import java.io.IOException

abstract class BaseRepo constructor() {

    suspend fun <T> safeApiCall(api: suspend () -> Response<T>): Resource<T> {

        return withContext(Dispatchers.IO) {
            try {
                val response: Response<T> = api()
                Resource.Success(data = response.body()!!)
            } catch (e: IOException) {
                Resource.Error(
                    "Please check your network connection",
                    errorType = ErrorType.NETWORK
                )
            } catch (e: HttpException) {
                // val code = e.code() HTTP Exception code
                Resource.Error(
                    errorMessage = e.message ?: "Something went wrong",
                    errorType = ErrorType.HTTP
                )
            } catch (e: Exception) {
                Resource.Error(
                    errorMessage = e.message ?: "Something went wrong",
                    errorType = ErrorType.UNKNOWN
                )
            }
        }
    }

}