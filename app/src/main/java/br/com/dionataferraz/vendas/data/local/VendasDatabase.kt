package br.com.dionataferraz.vendas.data.local

import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import br.com.dionataferraz.vendas.App
import br.com.dionataferraz.vendas.data.dao.TransactionDAO
import br.com.dionataferraz.vendas.data.dao.UserDAO
import br.com.dionataferraz.vendas.data.entity.TransactionEntity
import br.com.dionataferraz.vendas.data.entity.UserEntity

@Database(entities = [TransactionEntity::class, UserEntity::class], version = 1)
abstract class VendasDatabase : RoomDatabase() {

    abstract fun dao(): TransactionDAO

    abstract fun userDao(): UserDAO

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