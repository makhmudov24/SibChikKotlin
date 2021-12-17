package com.example.sibchickkt

import android.util.Log
import com.example.sibchickkt.MainActivity.Companion.version
import com.example.sibchickkt.MainActivity.Companion.setFM
import com.example.sibchickkt.MainActivity.Companion.viewModel

import com.example.sibchickkt.LifeCycle.Companion.ref

import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.example.sibckickkt.tables.Categories
import com.example.sibckickkt.tables.Menu1Week

object RealtimeDB {
    var i = 0
    val dataFromDB: Unit
        get() {
            Log.i("STAAAART", "getDataFromDB")
            val vListener: ValueEventListener = object : ValueEventListener {
                override fun onDataChange(dataSnapshot: DataSnapshot) {
                    var dataSnapshot = dataSnapshot
                    if (dataSnapshot.child("/Version").value.toString().toDouble().toFloat() == version) {
                        setFM()
                        Log.i("Version", " VERSION")
                    } else {
                        viewModel!!.deleteCategories()
                        viewModel!!.deleteMenu1()
                        version = dataSnapshot.child("/Version").value.toString().toDouble()
                            .toFloat()
                        var categories = Categories()
                        categories.nextCategories = i
                        viewModel!!.insertCategories(categories)
                        dataSnapshot = dataSnapshot.child("/Week1/Categories")
                        for (ds in dataSnapshot.children) {
                            Log.i("STAAAART", ds.key!!)
                            for (ds1 in ds.children) {
                                addDataToRoomDB(Menu1Week(), ds.key, ds1)
                                i++
                            }
                            categories = Categories()
                            categories.nextCategories = i
                            viewModel!!.insertCategories(categories)
                            Log.i("STAAAART", java.lang.String.valueOf(categories.nextCategories))
                        }
                    }
                    Log.i("STAAAART", "MainActivity/setFM")
                }

                override fun onCancelled(databaseError: DatabaseError) {}
            }
            ref!!.addValueEventListener(vListener)
        }

    private fun addDataToRoomDB(menu: Menu1Week, category: String?, ds: DataSnapshot) {
        when (category) {
            "Drinks" -> menu.foodCategory = "Напитки"
            "First" -> menu.foodCategory = "Первые блюда"
            "Second" -> menu.foodCategory = "Вторые блюда"
            "Salads" -> menu.foodCategory = "Салаты"
            "Snacks" -> menu.foodCategory = "Закуски"
        }
        menu.name = ds.child("Name").value.toString()
        menu.weight = ds.child("Weight").value.toString()
        menu.price = ds.child("Price").value.toString()
        viewModel!!.insertMenu1(menu)
    }
}