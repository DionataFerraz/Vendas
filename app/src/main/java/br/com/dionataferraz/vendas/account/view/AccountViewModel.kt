package br.com.dionataferraz.vendas.account.view

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.dionataferraz.vendas.data.model.User
import br.com.dionataferraz.vendas.data.usecase.GetUsersCase
import kotlinx.coroutines.launch

class AccountViewModel : ViewModel() {

    private val case: GetUsersCase by lazy {
        GetUsersCase()
    }

    fun createUser(user: User) {
        viewModelScope.launch {
            case.createUser(user)
        }
    }

    fun getUser(): User {
        val result = MutableLiveData<User>()

        viewModelScope.launch {
            val user = case.getUser()
            result.value = user
        }

        return result.value!!
    }
}