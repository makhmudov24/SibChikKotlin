package com.example.sibckickkt.db

import androidx.room.Dao
import androidx.lifecycle.LiveData
import androidx.room.Insert
import com.example.sibckickkt.tables.Menu1Week
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.sibckickkt.tables.Categories

@Dao
interface MenuDao {
    @Query("SELECT * FROM Menu1Week")
    fun LoadAllMenu1Week(): LiveData<List<Menu1Week?>?>?

    @Query("DELETE FROM Menu1Week")
    fun deleteMenu1Week()

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMenu1Week(menu1Week: Menu1Week?)

    @Query("SELECT * FROM Categories")
    fun LoadAllCategories(): LiveData<List<Categories?>?>?

    @Query("DELETE FROM Categories")
    fun deleteCategories()

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertCategories(categories: Categories?)
}