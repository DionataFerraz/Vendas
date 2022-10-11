package br.com.dionataferraz.vendas.login.domain.usecase

import br.com.dionataferraz.vendas.data.remote.ErrorModel
import br.com.dionataferraz.vendas.data.remote.Result
import br.com.dionataferraz.vendas.login.data.repository.LoginRepository
import br.com.dionataferraz.vendas.login.data.response.UserResponse

class GetLoginUsecase {
    private val repository by lazy {
        LoginRepository()
    }

    suspend fun login(name: String, email: String, password: String):
            Result<UserResponse, ErrorModel> {
        return repository.login(name = name, password = password, email = email)
    }
}