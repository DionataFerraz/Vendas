package br.com.dionataferraz.vendas.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "userEntity")
data class UserEntity(
    @PrimaryKey(autoGenerate = true)
    val uid: Int = 0,
    val name: String,
    val accountBalance: Double,
    val accountManager: String
)