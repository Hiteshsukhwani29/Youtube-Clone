package com.hitesh.youtube_clone.repository

import com.google.firebase.firestore.FirebaseFirestore
import com.hitesh.youtube_clone.model.UserInfo
import kotlinx.coroutines.tasks.await

class UserRepository {

    val db = FirebaseFirestore.getInstance()

    suspend fun getUserInfo(uid: String): UserInfo? {
        return db.collection("Users").document(uid).get().await().toObject(UserInfo::class.java)
    }

}