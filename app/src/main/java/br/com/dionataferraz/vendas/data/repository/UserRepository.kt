package br.com.dionataferraz.vendas.data.repository

import androidx.lifecycle.LiveData
import br.com.dionataferraz.vendas.data.local.VendasDatasource
import br.com.dionataferraz.vendas.data.model.User

class UserRepository {
    private val datasource: VendasDatasource by lazy {
        VendasDatasource()
    }

    suspend fun createUser(user: User) {
        datasource.createUser(user)
    }

    suspend fun getUser(): User {
        val user = datasource.getUser()
        return user
    }
}