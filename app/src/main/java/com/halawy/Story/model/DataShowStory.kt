package com.halawy.Story.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class DataShowStory(
    var TitleStory:String,
    var ImageStory:String,
    var IDStory:String,
): Parcelable
