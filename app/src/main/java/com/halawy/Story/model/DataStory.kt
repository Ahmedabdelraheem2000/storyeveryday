package com.halawy.Story.model
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Story")
data class DataStory(
    @PrimaryKey
    var Story_ID:String = "",
    var NameStory:String = "",
    var ImageStory:String = "",
    var Department_ID:String = ""

    )
