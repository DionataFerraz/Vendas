package br.com.dionataferraz.vendas.home.view

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.dionataferraz.vendas.data.usecase.UserCase
import br.com.dionataferraz.vendas.model.GetUserModel
import kotlinx.coroutines.launch

class HomeActivityViewModel : ViewModel() {

    private val case: UserCase by lazy {
        UserCase()
    }

    private val error: MutableLiveData<Boolean> = MutableLiveData(false)
    val shouldShowError: LiveData<Boolean> = error

    private val success: MutableLiveData<Boolean> = MutableLiveData(false)
    val shouldShowSuccess: LiveData<Boolean> = success

    private val user: MutableLiveData<GetUserModel?> = MutableLiveData()
    val getUser: LiveData<GetUserModel?> = user

    fun getUserData() {
        viewModelScope.launch {
            val userAccount = case.getUser()

            if (userAccount == null) {
                error.value = true
            } else {
                success.value = true
                user.value = userAccount
            }
        }
    }
}