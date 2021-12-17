package com.example.sibckickkt.vm


import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.example.sibckickkt.tables.Categories
import com.example.sibckickkt.tables.Menu1Week

class MenuViewModel(application: Application) : AndroidViewModel(application) {

    val menu1Week: LiveData<List<Menu1Week?>?>?
    val categories: LiveData<List<Categories?>?>?

    var repository: MenuRepository

    fun insertMenu1(menu1Week: Menu1Week?) {
        repository.insertMenu1(menu1Week)
    }

    fun insertCategories(categories: Categories?) {
        repository.insertCategories(categories)
    }

    fun deleteMenu1() {
        repository.deleteMenu1()
    }

    fun deleteCategories() {
        repository.deleteCategories()
    }

    init {
        repository = MenuRepository(application)
        menu1Week = repository.menu1Week
        categories = repository.categories
    }
}