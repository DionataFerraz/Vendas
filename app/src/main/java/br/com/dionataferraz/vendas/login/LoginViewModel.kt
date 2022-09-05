package br.com.dionataferraz.vendas.login

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.dionataferraz.vendas.login.data.remote.LoginDataSource
import kotlinx.coroutines.launch

class LoginViewModel : ViewModel() {

    private val error: MutableLiveData<Boolean> = MutableLiveData(false)
    val shouldShowError: LiveData<Boolean> = error

    fun login(email: String?, password: String?) {
        if (email != null && password != null) {
            viewModelScope.launch {
                val user = LoginDataSource().login(email, password)
                Log.e("teste", user.get().toString())
            }
        } else {
            error.value = true
        }
    }

}