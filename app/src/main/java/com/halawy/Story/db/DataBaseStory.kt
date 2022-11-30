package com.halawy.Story.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.halawy.Story.model.DataChapter
import com.halawy.Story.model.DataDepartment
import com.halawy.Story.model.DataStory

@Database(entities = [DataDepartment::class, DataStory::class, DataChapter::class], version = 2 ,
    exportSchema = false)
abstract class DataBaseStory : RoomDatabase() {
    abstract fun getDao():Dao


    companion object{
        @Volatile
        private var instant:DataBaseStory?=null
        private val LOCK=Any()
        operator fun invoke(context: Context)= instant?: synchronized(LOCK){
            instant?: create_Database(context).also {
                instant=it
            }
        }
        val MIGRATION_1_2 = object : Migration(1, 2) {
            override fun migrate(database: SupportSQLiteDatabase) {
            }
        }
        private fun create_Database(context :Context)=
            Room.databaseBuilder(
                context.applicationContext,
                DataBaseStory::class.java,
                "story11.db"
            ).addMigrations(MIGRATION_1_2).build()

    }
}
