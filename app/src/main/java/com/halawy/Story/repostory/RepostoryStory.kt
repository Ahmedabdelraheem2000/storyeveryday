package com.halawy.Story.repostory

import androidx.lifecycle.LiveData
import com.halawy.Story.db.Dao
import com.halawy.Story.model.DataChapter
import com.halawy.Story.model.DataDepartment
import com.halawy.Story.model.DataStory
import com.halawy.Story.server.FirebaseStory
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class RepostoryStory(var dao: Dao) {
    private var firebasestory= FirebaseStory()
    init {
        //Remove Data and Update
        removeallitemindatabaseandupdatadatafromserver()
    }
    fun getAllDepartmentFrom_DataBase(): LiveData<List<DataDepartment>> {
        return dao.getAllDepartment()
    }// get All Department from DataBase

    fun getAllStory(): LiveData<List<DataStory>> {
        return dao.getAllStory()
    }// get All Department from DataBase

    fun getStoryFrom_DataBase(DepartmentID:String):LiveData<List<DataStory>>{
        return dao.getStory(DepartmentID)
    } // get story from database where  departmentid == DID in DataStory
    fun getChaptersFrom_DataBase(StoryID:String):LiveData<List<DataChapter>>{
        return dao.getChapter(StoryID)
    } // get story from database where  departmentid == DID in DataStory

    fun getChaptersFrom_DataBase(StoryID:String,postion:Int):LiveData<DataChapter>{
        return dao.getChapter(StoryID,postion)
    } // get story from database where  departmentid == DID in DataStory

    private fun insert_Department_From_DataBase_Into_Firebase() {
    // insert all department from firebase to database
    CoroutineScope(Dispatchers.IO).launch{
              dao.InsertAllDepartmentFromDataBase(firebasestory.getDepartmentFromFirebase())
        }
    }

    private fun insert_Story_From_DataBase_Into_Firebase() =
        CoroutineScope(Dispatchers.IO).launch{
            dao.InsertAllStoryFromDataBase(firebasestory.getStoryFromFirebase())
    }// insert all Story from firebase to database

    private fun insert_Chapter_From_DataBase_Into_Firebase() =
        CoroutineScope(Dispatchers.IO).launch{
        dao.InsertAllChapterFromDataBase(firebasestory.getChapterFromFirebase())
    }// insert all Chapter from firebase to database

    private fun removeallitemindatabaseandupdatadatafromserver(){
        CoroutineScope(Dispatchers.IO).launch {
            dao.DeleteAllChapterFromDataBase()
            dao.DeleteAllStoryFromDataBase()
            dao.DeleteAllDepartmentFromDataBase()

            insert_Department_From_DataBase_Into_Firebase()
            insert_Story_From_DataBase_Into_Firebase()
            insert_Chapter_From_DataBase_Into_Firebase()
        }

    }

}