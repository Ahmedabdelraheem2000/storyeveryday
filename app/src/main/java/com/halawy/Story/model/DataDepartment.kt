package com.halawy.Story.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Department")
data class DataDepartment(
    @PrimaryKey
    var DID:String="",
    var Name:String="")
