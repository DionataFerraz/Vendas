package br.com.dionataferraz.vendas.login.data.repository

import br.com.dionataferraz.vendas.login.data.remote.ErrorModel
import br.com.dionataferraz.vendas.login.data.remote.LoginDataSource
import br.com.dionataferraz.vendas.login.data.remote.Result
import br.com.dionataferraz.vendas.login.data.response.UserResponse

class LoginRepository {

    private val dataSource by lazy {
        LoginDataSource()
    }

    suspend fun login(email: String, password: String):
            Result<UserResponse, ErrorModel> {

        return dataSource.login(password = password, email = email)
    }
}