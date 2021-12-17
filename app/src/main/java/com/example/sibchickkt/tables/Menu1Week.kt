package com.example.sibckickkt.tables

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Menu1Week")
class Menu1Week {
    @PrimaryKey(autoGenerate = true)
    var id = 0
    var name: String? = null
    var weight: String? = null
    var price: String? = null
    var foodCategory: String? = null
}