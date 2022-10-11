package br.com.dionataferraz.vendas.login.data.repository

import br.com.dionataferraz.vendas.data.remote.ErrorModel
import br.com.dionataferraz.vendas.data.remote.RegisterDataSource
import br.com.dionataferraz.vendas.data.remote.Result
import br.com.dionataferraz.vendas.login.data.response.UserResponse

class LoginRepository {

    private val remoteDataSource by lazy {
        RegisterDataSource()
    }

    suspend fun login(
        name: String,
        email: String,
        password: String
    ): Result<UserResponse, ErrorModel> {
        return remoteDataSource.login(name, email, password)
    }
}