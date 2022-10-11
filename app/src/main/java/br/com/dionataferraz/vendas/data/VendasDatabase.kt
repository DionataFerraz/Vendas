package br.com.dionataferraz.vendas.data

import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import br.com.dionataferraz.vendas.App
import br.com.dionataferraz.vendas.data.dao.UserDao
import br.com.dionataferraz.vendas.data.entity.User

@Database(entities = [User::class], version = 1)
abstract class VendasDatabase : RoomDatabase() {

    abstract fun userDao(): UserDao

    companion object {
        fun getInstance(): VendasDatabase {
            return Room.databaseBuilder(
                App.context,
                VendasDatabase::class.java,
                "vendas.db"
            ).build()
        }
    }
}