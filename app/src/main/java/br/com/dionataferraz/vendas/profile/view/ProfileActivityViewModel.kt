package br.com.dionataferraz.vendas.profile.view

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.dionataferraz.vendas.data.usecase.UserCase
import br.com.dionataferraz.vendas.model.AddUserModel
import kotlinx.coroutines.launch

class ProfileActivityViewModel : ViewModel() {

    private val useCase: UserCase by lazy {
        UserCase()
    }

    private val error: MutableLiveData<Boolean> = MutableLiveData(false)
    val shouldShowError: LiveData<Boolean> = error

    private val login: MutableLiveData<Boolean> = MutableLiveData(false)
    val shouldShowLogin: LiveData<Boolean> = login

    fun createUser(user: AddUserModel) {
        viewModelScope.launch {
            useCase.createUser(user)
            useCase.createUserRemote(user.name, user.email, user.password)
            login.value = true
        }
    }
}