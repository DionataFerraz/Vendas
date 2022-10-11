package br.com.dionataferraz.vendas.data.remote

import br.com.dionataferraz.vendas.login.data.response.UserResponse
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface LoginApi {

    @POST("https://fundatec.herokuapp.com/api/login")
    suspend fun login(
        @Body name: String,
        @Body email: String,
        @Body password: String
    ): UserResponse
}