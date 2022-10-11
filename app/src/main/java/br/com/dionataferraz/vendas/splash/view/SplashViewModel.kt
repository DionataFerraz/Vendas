package br.com.dionataferraz.vendas.splash.view

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.dionataferraz.vendas.model.GetUserModel
import br.com.dionataferraz.vendas.data.usecase.UserCase
import br.com.dionataferraz.vendas.login.domain.usecase.GetLoginUsecase
import kotlinx.coroutines.launch

class SplashViewModel : ViewModel() {

    private val useCase: UserCase by lazy {
        UserCase()
    }

    private val error: MutableLiveData<Boolean> = MutableLiveData(false)
    val shouldShowError: LiveData<Boolean> = error

    private val home: MutableLiveData<Boolean> = MutableLiveData(false)
    val shouldShowHome: LiveData<Boolean> = home

    private val user: MutableLiveData<GetUserModel?> = MutableLiveData()
    val getUser: LiveData<GetUserModel?> = user

    fun getUser() {
        viewModelScope.launch {
            val localUser: GetUserModel? = useCase.getUser()

            if (localUser == null) {
                error.value = true
            } else {
                home.value = true
                user.value = localUser
            }
        }
    }
}