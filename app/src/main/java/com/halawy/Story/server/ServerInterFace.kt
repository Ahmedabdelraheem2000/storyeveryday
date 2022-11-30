package com.halawy.Story.server

import com.halawy.Story.model.DataChapter
import com.halawy.Story.model.DataDepartment
import com.halawy.Story.model.DataStory


interface ServerInterFace {
     suspend fun getDepartmentFromFirebase():List<DataDepartment>
     suspend fun getStoryFromFirebase():List<DataStory>
     suspend fun getChapterFromFirebase():List<DataChapter>
}