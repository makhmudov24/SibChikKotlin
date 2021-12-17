package com.example.sibckickkt.db

import android.content.Context
import androidx.room.Database
import com.example.sibckickkt.tables.Menu1Week
import androidx.room.RoomDatabase
import kotlin.jvm.Volatile
import androidx.room.Room
import com.example.sibckickkt.tables.Categories
import java.util.concurrent.Executors

@Database(entities = [Menu1Week::class, Categories::class], version = 1, exportSchema = false)
abstract class MenuDatabase : RoomDatabase() {
    abstract fun menuDao(): MenuDao?

    companion object {
        @Volatile
        private var INSTANCE: MenuDatabase? = null
        private const val NUMBER_OF_THREADS = 4
        @JvmField
        val dbWriteExecutor = Executors.newFixedThreadPool(NUMBER_OF_THREADS)
        @JvmStatic
        fun getDatabase(context: Context): MenuDatabase? {
            if (INSTANCE == null) {
                synchronized(MenuDatabase::class.java) {
                    if (INSTANCE == null) {
                        INSTANCE = Room.databaseBuilder(
                            context.applicationContext,
                            MenuDatabase::class.java, "database"
                        )
                            .allowMainThreadQueries()
                            .build()
                    }
                }
            }
            return INSTANCE
        }
    }
}