package br.com.dionataferraz.vendas.login.data.remote

import android.util.Log
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class LoginDataSource {

    private val service = RetrofitNetworkClient.createNetworkClient().create(LoginApi::class.java)

    suspend fun login(email: String, password: String): Result<User, ErrorModel> {
        return withContext(Dispatchers.IO) {
            try {
                val user = service.login(email, password)
                Result.Success(user)
            } catch (exception: Exception) {
                Log.e("ERROR", "Ocorreu algum erro na api", exception)
                Result.Error(ErrorModel)
            }
        }

        /* CoroutineScope(Dispatchers.IO).launch {
             service.login(email, password)
         }*/
    }

}

object ErrorModel

sealed class Result<out D, out E> {
    data class Success<D>(val value: D) : Result<D, Nothing>()
    data class Error<E>(val value: E) : Result<Nothing, E>()

    fun get(): D? {
        return when (this) {
            is Success -> value
            is Error -> null
        }
    }
}