package br.com.dionataferraz.vendas.data

import br.com.dionataferraz.vendas.data.entity.User
import br.com.dionataferraz.vendas.model.AddUserModel
import br.com.dionataferraz.vendas.model.GetUserModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class VendasDatasource {

    private val database: VendasDatabase by lazy {
        VendasDatabase.getInstance()
    }

    suspend fun getUser(): GetUserModel? {
        return withContext(Dispatchers.IO) {
            database.userDao().getUser()?.toGetUserModel()
        }
    }

    suspend fun createLocalUser(user: AddUserModel) {
        withContext(Dispatchers.IO) {
            database.userDao().insertUser(user.toEntity())
        }
    }

    private fun User.toGetUserModel(): GetUserModel {
        return GetUserModel(
            name = name, email = email, password = password
        )
    }

    private fun AddUserModel.toEntity(): User {
        return User(
            name = name, email = email, password = password
        )
    }
}