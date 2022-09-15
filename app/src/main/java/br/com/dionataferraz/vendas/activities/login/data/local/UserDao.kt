package br.com.dionataferraz.vendas.activities.login.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface UserDao {

    @Insert
    fun insertUser(userEntity: UserEntity)

    @Query("SELECT * FROM userTable")
    fun getUser(): List<UserEntity>

}