package br.com.dionataferraz.vendas.data.usecase

import br.com.dionataferraz.vendas.model.AddUserModel
import br.com.dionataferraz.vendas.model.GetUserModel
import br.com.dionataferraz.vendas.data.repository.UserRepository

class UserCase {

    private val repository: UserRepository by lazy {
        UserRepository()
    }

    suspend fun getUser(): GetUserModel? {
        return repository.getUser()
    }

    suspend fun createUser(user: AddUserModel) {
        repository.createUser(user)
    }
    
    suspend fun createUserRemote(name: String, email: String, password: String) {
        repository.createUserRemote(name, email, password)
    }
}