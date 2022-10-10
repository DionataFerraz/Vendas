package br.com.dionataferraz.vendas.data.usecase

import androidx.lifecycle.LiveData
import br.com.dionataferraz.vendas.data.model.User
import br.com.dionataferraz.vendas.data.repository.UserRepository

class GetUsersCase {

    private val repository: UserRepository by lazy {
        UserRepository()
    }

    suspend fun createUser(user: User) {
        repository.createUser(user)
    }

    suspend fun getUser(): User {
        return repository.getUser()
    }
}