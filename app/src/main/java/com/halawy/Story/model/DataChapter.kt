package com.halawy.Story.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Chapter")
data class DataChapter(
    @PrimaryKey
    var Chapter_ID:String="",
    var NameChapter:String="",
    var TextChapter:String="",
    var Story_ID:String="",
    var DepartmentID:String="",
    var counter:Int=0
)
{}