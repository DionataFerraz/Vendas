package br.com.dionataferraz.vendas.data.repository

import br.com.dionataferraz.vendas.data.VendasDatasource
import br.com.dionataferraz.vendas.data.remote.RegisterDataSource
import br.com.dionataferraz.vendas.model.AddUserModel
import br.com.dionataferraz.vendas.model.GetUserModel

class UserRepository {

    private val datasource: VendasDatasource by lazy {
        VendasDatasource()
    }

    private val remoteDatasource: RegisterDataSource by lazy {
        RegisterDataSource()
    }

    suspend fun getUser(): GetUserModel? {
        return datasource.getUser()
    }

    suspend fun createUser(user: AddUserModel) {
        datasource.createLocalUser(user)
    }

    suspend fun createUserRemote(name: String, email: String, password: String) {
        remoteDatasource.login(name, email, password)
    }
}