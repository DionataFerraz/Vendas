package br.com.dionataferraz.vendas.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import br.com.dionataferraz.vendas.data.entity.User

@Dao
interface UserDao {

    @Insert
    fun insertUser(user: User)

    @Query("SELECT u.* FROM user u LIMIT 1")
    fun getUser(): User?
}