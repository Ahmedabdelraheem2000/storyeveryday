package com.halawy.Story.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.halawy.Story.model.DataChapter
import com.halawy.Story.model.DataDepartment
import com.halawy.Story.model.DataStory
import com.halawy.Story.repostory.RepostoryStory

class ViewModelStory(private var repostory: RepostoryStory) : ViewModel() {

    fun getAllDepartment(): LiveData<List<DataDepartment>> {
        return repostory.getAllDepartmentFrom_DataBase()
    }// get All Department from DataBase

    fun getAllStory(): LiveData<List<DataStory>> {
        return repostory.getAllStory()
    }// get All Department from DataBase

    fun getStory(DepartmentID:String): LiveData<List<DataStory>> {
        return repostory.getStoryFrom_DataBase(DepartmentID)
    } // get story from database where  departmentid == DID in DataStory

    fun getChapters(StoryID:String): LiveData<List<DataChapter>> {
        return repostory.getChaptersFrom_DataBase(StoryID)
    } // get story from database where  departmentid == DID in DataStory

    fun getChapters(StoryID:String,postion:Int): LiveData<DataChapter> {
        return repostory.getChaptersFrom_DataBase(StoryID,postion)
    } // get story from database where  position == position in DataStory

}