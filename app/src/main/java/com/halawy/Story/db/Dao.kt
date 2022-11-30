package com.halawy.Story.db

import androidx.lifecycle.LiveData
import androidx.room.*
import androidx.room.Dao
import com.halawy.Story.model.DataChapter
import com.halawy.Story.model.DataDepartment
import com.halawy.Story.model.DataStory

@Dao
interface Dao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun InsertAllDepartmentFromDataBase(DepartmentList:List<DataDepartment>)

    @Query("DELETE FROM Department")
    fun DeleteAllDepartmentFromDataBase()

    @Query("Select * From Department")
    fun getAllDepartment(): LiveData<List<DataDepartment>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun InsertAllStoryFromDataBase(StoryList:List<DataStory>)

    @Query("DELETE FROM Story")
    fun DeleteAllStoryFromDataBase()

    @Query("Select * From Story where Department_ID = :DepartmentID")
    fun getStory(DepartmentID:String): LiveData<List<DataStory>>

    @Query("Select * From Story")
    fun getAllStory(): LiveData<List<DataStory>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun InsertAllChapterFromDataBase(StoryList:List<DataChapter>)

    @Query("DELETE FROM Chapter")
    fun DeleteAllChapterFromDataBase()

    @Query("Select * From Chapter where Story_ID = :StoryID Order by counter")
    fun getChapter(StoryID:String): LiveData<List<DataChapter>>

    @Query("Select * From Chapter where Story_ID = :StoryID AND counter = :postion Order by counter")
    fun getChapter(StoryID:String,postion:Int): LiveData<DataChapter>
}