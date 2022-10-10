package br.com.dionataferraz.vendas.data.dao

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import br.com.dionataferraz.vendas.data.entity.UserEntity

@Dao
interface UserDAO {

    @Insert
    fun insertUser(userEntity: UserEntity)

    @Query("SELECT * FROM userEntity u LIMIT 1")
    fun getUser(): UserEntity

    @Query("SELECT u.accountBalance FROM userEntity u")
    fun getAccountBalance(): Double
}