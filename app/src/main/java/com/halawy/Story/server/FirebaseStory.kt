package com.halawy.Story.server
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.halawy.Story.model.DataChapter
import com.halawy.Story.model.DataDepartment
import com.halawy.Story.model.DataStory
import kotlinx.coroutines.tasks.await

class FirebaseStory : ServerInterFace  {
    private var firebase=Firebase.firestore
    override suspend fun getDepartmentFromFirebase(): List<DataDepartment> {
        return try {
            firebase.collection("Department").get().await().toObjects(DataDepartment::class.java)
        }catch (e:Exception){
            emptyList<DataDepartment>()
        }
 }

    override suspend fun getStoryFromFirebase(): List<DataStory> {
        return try {
            firebase.collection("Story").get().await().toObjects(DataStory::class.java)

        }catch (e:Exception){
            emptyList<DataStory>()
        }
    }

    override suspend fun getChapterFromFirebase(): List<DataChapter> {
        return try {
            firebase.collection("Chapter").get().await().toObjects(DataChapter::class.java)
        }catch (e:Exception){
            emptyList<DataChapter>()
        }
    }


}

