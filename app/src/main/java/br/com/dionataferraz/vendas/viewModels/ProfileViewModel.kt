package br.com.dionataferraz.vendas.viewModels


import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import br.com.dionataferraz.vendas.models.PersonModel


class ProfileViewModel : ViewModel() {

    private val error: MutableLiveData<String> = MutableLiveData()
    val shouldShowError: LiveData<String> = error
    private val personModel: MutableLiveData<PersonModel> = MutableLiveData()
    val personModelLiveData: LiveData<PersonModel> = personModel

    fun createPerson(
        name: String?,
        age: String?,
        email: String?,
        password: String?,
        gender: String?
    ) {
        if (name.isNullOrBlank()
            && age.isNullOrBlank()
            && email.isNullOrBlank()
            && password.isNullOrBlank()
            && gender.isNullOrBlank()
        ) {
            error.value = "Preencha todos os campos!"

        } else if (name.isNullOrBlank()) {
            error.value = "O campo nome é obrigatório!"

        } else if (age.isNullOrBlank()) {
            error.value = "O campo idade é obrigatório!"

        } else if (email.isNullOrBlank()) {
            error.value = "O campo email é obrigatório!"

        } else if (password.isNullOrBlank()) {
            error.value = "O campo senha é obrigatório!"

        } else if (gender.isNullOrBlank()) {
            error.value = "O campo gênero é obrigatório!"

        } else {

            val personModelCreated = PersonModel(
                name = name,
                age = age,
                email = email,
                password = password,
                gender = gender
            )

            personModel.value = personModelCreated

        }
    }

}