package com.example.sibckickkt.vm

import com.example.sibckickkt.db.MenuDatabase.Companion.getDatabase

import android.app.Application
import com.example.sibckickkt.db.MenuDao
import androidx.lifecycle.LiveData
import com.example.sibckickkt.db.MenuDatabase
import com.example.sibckickkt.tables.Menu1Week
import com.example.sibckickkt.tables.Categories

class MenuRepository(application: Application) {
    private val menuDao: MenuDao?
    val menu1Week: LiveData<List<Menu1Week?>?>?
    val categories: LiveData<List<Categories?>?>?
    fun insertMenu1(menu1Week: Menu1Week?) {
        MenuDatabase.dbWriteExecutor.execute { menuDao!!.insertMenu1Week(menu1Week) }
    }

    fun insertCategories(categories: Categories?) {
        MenuDatabase.dbWriteExecutor.execute { menuDao!!.insertCategories(categories) }
    }

    fun deleteMenu1() {
        menuDao!!.deleteMenu1Week()
    }

    fun deleteCategories() {
        menuDao!!.deleteCategories()
    }

    init {
        menuDao = getDatabase(application.applicationContext)!!.menuDao()
        menu1Week = menuDao!!.LoadAllMenu1Week()
        categories = menuDao.LoadAllCategories()
    }
}